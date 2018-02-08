package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ImageLableMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.TImageTextMapper;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.ImageLable;
import com.fs.comm.model.Program;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ImageTextTemplateService")
public class ImageTextTemplateServiceImpl implements ImageTextTemplateService {

	@Resource
	private ImageLableMapper imageLableMapper;

	@Resource
	private TImageTextMapper imageTextMapper;

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
	 * 获取图文模板列表
	 * 
	 * @param p
	 * @return
	 */
	public List<ImageLable> getImageLableList(Map<String, Object> p) {
		List<ImageLable> templateList = imageLableMapper.getImageLableList(p);

		return templateList;
	}

	/**
	 * 添加图文模板
	 */
	@Override
	public boolean addImageLable(ImageLable model) {
		model.setCreateTime(new Date());
		model.setIsDelete("n");
		model.setStatus("未审核");
		int result = imageLableMapper.insertLable(model);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 浏览图文模板列表
	 */
	@Override
	public List<Map<String, Object>> getAllImageLableInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_title = imageLableMapper.getAllImageLableInfo(p);
		if (queryT_title != null && queryT_title.size() > 0) {
			for (Map<String, Object> map : queryT_title) {
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
		PageInfo page = new PageInfo(queryT_title);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_title;
	}

	@Override
	public int delImageLableById(Integer id) {
		int result = imageLableMapper.delImageLableById(id);
		if (result < 1) {
			return 0;
		} else {
			return result;
		}
	}

	@Override
	public int agreeImageLableById(Integer id) {
		int result = imageLableMapper.agreeImageLableById(id);
		if (result < 0) {
			return 0;
		} else {
			return result;
		}
	}

	@Override
	public int unArgeeImageLableById(Integer id) {
		int result = imageLableMapper.unAgreeImageLableById(id);
		if (result < 0) {
			return 0;
		} else {
			return result;
		}
	}

	@Override
	public ImageLable queryImageLableById(Integer id) {
		if (0 == id) {
			return null;
		} else {
			return imageLableMapper.queryImageLableById(id);
		}
	}

	@Override
	public int updateImageLableById(ImageLable model) {
		return imageLableMapper.updateByPrimaryKeySelective(model);

	}

	@Override
	public int unAgreeOnlineById(Integer id) {
		int result = 0;
		if (id <= 0) {
			return 0;
		} else {
			result = imageTextMapper.unAgreeOnlineById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("图文信息");
			result = columnTypeMapper.unAgreeOnline(columnType);
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

}
