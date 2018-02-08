package com.fs.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ImageMapper;
import com.fs.comm.mapper.ImageVideoMapper;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.TImageTextMapper;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Image;
import com.fs.comm.model.ImageText;
import com.fs.comm.model.ImageVideo;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.Program;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ImageTextService")
public class ImageTextServiceImpl implements ImageTextService {

	@Resource
	private TImageTextMapper imageTextMapper;
	
	@Resource
	private ColumnTypeMapper columnTypeMapper;

	@Resource
	private ProgramMapper programMapper;

	@Resource
	private ImageVideoMapper imageVideoMapper;

	@Resource
	private ImageMapper imageMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Resource
	private TComOperatorMapper operatorMapper;

	@Resource
	private TComDealerMapper dealerMapper;
	
	@Resource
	private ParameterMapper parameterMapper;

	/**
	 * 添加图文信息
	 */
	@Override
	public boolean addImageText(ImageText model) {

		model.setCreateTime(new Date());
		model.setIsDelete("n");
		model.setStatus("未审核");
		model.setLineStatus("未上线");
		int result = imageTextMapper.insertImageText(model);
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnType("图文信息");
			columnType.setCompanyid(model.getCompanyid());
			columnType.setName(model.getName());
			columnType.setContent(model.getContent());
			columnType.setCreateTime(new Date());
			columnType.setFlag("2");
			columnType.setIsDelete("n");
			columnType.setStatus("未上线");
			columnType.setColumnId(model.getId());
			result = columnTypeMapper.insert(columnType);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 浏览图文列表
	 */
	@Override
	public List<Map<String, Object>> getAllImageTextInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_imageText = imageTextMapper.getAllImageTextInfo(p);
		if (queryT_imageText != null && queryT_imageText.size() > 0) {
			for (Map<String, Object> map : queryT_imageText) {
				// 获取运营商名称
				Long companyid = (Long) map.get("companyid");
				Long operatorid = (Long) map.get("operatorid");
				Long dealerid = (Long) map.get("dealerid");
				if (companyid != null) {
					TCompany company = companyMapper.selectByPrimaryKey(companyid);
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						map.put("companyname", company.getCompanyname());
				}
				if (operatorid != null) {// 二级运营商名称
					TComOperator ope = operatorMapper.selectByPrimaryKey(operatorid);
					if (ope != null && StringUtils.isNotBlank(ope.getName()))
						map.put("operatorname", ope.getName());
				}
				if (dealerid != null) {// 经销商名称
					TComDealer dea = dealerMapper.selectByPrimaryKey(dealerid);
					if (dea != null && StringUtils.isNotBlank(dea.getName()))
						map.put("dealername", dea.getName());
				}

			}
		}
		PageInfo page = new PageInfo(queryT_imageText);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_imageText;
	}

	@Override
	public int delImageTextById(Integer id) {

		int result = 0;
		if ("".equals(id) || null == id) {
			return 0;
		} else {
			result = imageTextMapper.delImageTextById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("图文信息");
			result = columnTypeMapper.deleteColumnTypeById(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("图文信息");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
		}
		return result;
	}

	@Override
	public int unAgreeImageTextById(Integer id) {
		int result = imageTextMapper.unAgreeImageTextById(id);
		if (result <= 0) {
			return 0;
		} else {
			return result;
		}
	}

	@Override
	public int agreeImageTextById(Integer id) {
		int result = imageTextMapper.agreeImageTextById(id);

		if (result <= 0) {
			return 0;
		} else {
			return result;
		}

	}

	@Override
	public int agreeOnlineById(Integer id) {
		int result = 0;
		if (id <= 0) {
			return 0;
		} else {
			result = imageTextMapper.agreeOnlineById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("图文信息");
			result = columnTypeMapper.agreeOnline(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("图文信息");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
		}
		return result;

	}

	@Override
	public int agreeUnOnlineById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据主键获取图文信息
	 */
	@Override
	public ImageText queryImageTextbyId(Integer id) {
		
		ImageText text=imageTextMapper.selectByPrimaryKey(id);
		if (text.getShowType() != null) {
			Map<String, Object> p = new HashMap<>();
			p.put("ptype", "showtype");
			p.put("pcode", text.getShowType());
			Parameter par = parameterMapper.getParameter(p);
			text.setShowTypeStr(par.getPname());
		}
		return text;
	}

	@Override
	public int updateImageTextbyId(ImageText model) {
		int result = imageTextMapper.updateByPrimaryKeySelective(model);

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("图文信息");
			columnType.setName(model.getName());
			columnType.setContent(model.getContent());
			try {
				result = columnTypeMapper.updateColumnTypeSelective(columnType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("图文信息");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				program.setContent(columnType.getName());
				result = programMapper.updateProgramSelective(program);
			}
		}
		return result;
	}

	@Override
	public int addVideo(ImageVideo model) {
		model.setIsDelete("n");
		model.setCreateTime(new Date());
		return imageVideoMapper.insert(model);
	}

	@Override
	public int addPicture(ImageVideo model) {
		return imageVideoMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<Map<String, Object>> getImageVideoByImageTextId(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_imageVideo = imageVideoMapper.getImageVideoInfo(p);
		PageInfo page = new PageInfo(queryT_imageVideo);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_imageVideo;
	}

	@Override
	public int editVideo(ImageVideo model) {
		return imageVideoMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delVideo(Integer id) {

		return imageVideoMapper.delVideo(id);
	}

	/**
	 * 浏览缩略图列表
	 */
	@Override
	public List<Map<String, Object>> getImageByImageTextId(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_image = imageMapper.getImageInfo(p);
		PageInfo page = new PageInfo(queryT_image);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_image;
	}

	@Override
	public int addImage(Image model) {
		model.setCreateTime(new Date());
		model.setIsDelete("n");
		return imageMapper.insertImage(model);
	}

	@Override
	public int delImage(Integer id) {
		return imageMapper.delImage(id);
	}
}
