package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.TitleMapper;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.Title;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("TtitleService")
public class TtitleServiceImpl implements TtitleService {

	@Resource
	private TitleMapper titleMap;

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
	 * 添加字幕
	 */
	@Override
	public boolean insertTitle(Title model) {
		model.setCreateDate(new Date());
		model.setIsDelete("n");
		model.setStatus("未审核");
		// 添加
		int result = titleMap.insertTitle(model);
		System.out.println(model.getId());
		if (result > 0) {
			ColumnType record = new ColumnType();
			record.setIsDelete("n");
			record.setCreateTime(new Date());
			record.setColumnType("字幕");
			record.setContent(model.getContent());
			record.setName(model.getName());
			record.setStatus("未审核");
			record.setFlag("1");
			record.setCompanyid(model.getCompanyid());
			record.setColumnId(model.getId());
			result = columnTypeMapper.insert(record);
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 浏览字幕列表
	 */
	@Override
	public List<Map<String, Object>> getAllTitleInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_title = titleMap.getAllTitleInfo(p);
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
	public int delTitleById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return result;
		} else {
			result = titleMap.delTitleById(id);

		}
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");
			result = columnTypeMapper.deleteColumnTypeById(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.delProgramByModel(program);
			}

		}

		return result;
	}

	@Override
	public int agreeTitleById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return 0;
		} else {
			result = titleMap.agreeTitleById(id);
		}
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");
			result = columnTypeMapper.agreeCheckColumnType(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");

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
			result = programMapper.agreeCheckprogram(program);
		}
		return result;
	}

	@Override
	public int unAgreeTitleById(String id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return 0;
		} else {
			result = titleMap.unAgreeTitleById(id);
		}
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");
			result = columnTypeMapper.unAgreeCheckColumnType(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(Integer.parseInt(id));
			columnType.setColumnType("字幕");

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
	public Title queryTitleById(Integer id) {

		if (0 == id) {
			return null;
		} else {
			return titleMap.queryTitleById(id);
		}

	}

	@Override
	public int updateTitleById(Title model) {
		int result = titleMap.updateTitleById(model);
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("字幕");
			columnType.setName(model.getName());
			columnType.setContent(model.getContent());
			result = columnTypeMapper.updateColumnTypeSelective(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("字幕");

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
}
