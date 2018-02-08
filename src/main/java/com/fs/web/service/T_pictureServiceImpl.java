package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.T_pictureMapper;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.T_picture;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_pictureService")
public class T_pictureServiceImpl implements T_pictureService {

	@Resource
	private T_pictureMapper pictureMapper;

	@Resource
	private ColumnTypeMapper columnTypeMapper;

	@Resource
	private ProgramMapper programMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Resource
	private TComOperatorMapper operatorMapper;

	@Resource
	private TComDealerMapper dealerMapper;
	
	/**
	 * 添加图片
	 */
	@Transactional
	public boolean insertPicture(T_picture model) {
		model.setIsDelete("n");
		model.setStatus("未审核");
		model.setUploadTime(new Date());
		int result = pictureMapper.insertPicture(model);
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnType("图片");
			columnType.setName(model.getPictureName());
			columnType.setCreateTime(new Date());
			columnType.setFlag("5");
			columnType.setIsDelete("n");
			columnType.setCompanyid(model.getCompanyid());
			columnType.setStatus("未上线");
			columnType.setColumnId(model.getId());
			try {
				result = columnTypeMapper.insert(columnType);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	 * 浏览图片列表
	 */
	public List<Map<String, Object>> getAllPictureInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_picture = pictureMapper.getAllPictureInfo(p);
		if (queryT_picture != null && queryT_picture.size() > 0) {
			for (Map<String, Object> map : queryT_picture) {
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
		PageInfo page = new PageInfo(queryT_picture);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_picture;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delPictureById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return result;
		} else {
			result = pictureMapper.deletePictureById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");

			result = columnTypeMapper.deleteColumnTypeById(columnType);
			if (result < 1) {
				try {
					throw new RuntimeException();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				try {
					result = programMapper.delProgramByModel(program);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int agreePictureById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return result;
		} else {
			result = pictureMapper.agreePicture(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");
			try {
				result = columnTypeMapper.agreeCheckColumnType(columnType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");

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
	public int unAgreePictureById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return result;
		} else {
			result = pictureMapper.unAgreePicture(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");
			result = columnTypeMapper.unAgreeCheckColumnType(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("图片");

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
	public T_picture queryPictureById(Integer id) {
		if (id != 0) {
			T_picture model = pictureMapper.queryPictureById(id);
			return model;
		}
		return null;
	}

	@Override
	public List<T_picture> selectPictureGroup(Map<String,Object> p) {
		return pictureMapper.selectPictuireGroup(p);
	}

}
