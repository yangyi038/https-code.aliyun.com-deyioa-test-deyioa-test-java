package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.AppEditionsMapper;
import com.fs.comm.mapper.TApplicationMapper;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.AppEditions;
import com.fs.comm.model.TApplication;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_ApplicationService")
public class T_ApplicationServiceImpl implements T_ApplicationService {

	@Resource
	private TApplicationMapper appManagerMapper;

	@Resource
	private AppEditionsMapper appEditionsMapper;
	@Resource
	private ColumnTypeMapper columnTypeMapper;

	@Resource
	private ProgramMapper programMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Override
	public boolean insertApp(TApplication model) {

		model.setIsDelete("n");
		model.setStatus("未上线");
		int result = appManagerMapper.insertApplication(model);
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnType("app应用");
			columnType.setCompanyid(model.getCompanyid());
			columnType.setName(model.getName());
			columnType.setContent(model.getSynopsis());
			columnType.setCreateTime(new Date());
			columnType.setFlag("4");
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
	 * 浏览应用列表
	 */
	@Override
	public List<Map<String, Object>> getAllAppManagerInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_appManager = appManagerMapper.getApplicationList(p);
		if (queryT_appManager != null && queryT_appManager.size() > 0) {
			for (Map<String, Object> map : queryT_appManager) {
				// 获取运营商名称
				Long companyid = (Long) map.get("companyid");
				if (companyid != null) {
					TCompany company = companyMapper.selectByPrimaryKey(companyid);
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						map.put("companyname", company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(queryT_appManager);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_appManager;
	}

	@Override
	public int delAppManagerById(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = appManagerMapper.delAppManagerById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");
			result = columnTypeMapper.deleteColumnTypeById(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null && list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
			result = programMapper.delProgramByModel(program);
		}
		return result;
	}

	@Override
	public int onlineAppManager(Integer id) {
		TApplication model = new TApplication();
		model.setId(id);
		model.setStatus("已上线");
		model.setOnlineTime(new Date());
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = appManagerMapper.updateByPrimaryKeySelective(model);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");
			result = columnTypeMapper.agreeOnline(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");

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
	public int unlineAppManager(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = appManagerMapper.unlineAppManager(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");
			result = columnTypeMapper.unAgreeOnline(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("app应用");

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
	public int undercarriageAppManager(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = appManagerMapper.undercarriageAppManager(id);
		}
		return result;
	}

	@Override
	public TApplication queryAppManagerById(Integer id) {
		return appManagerMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<String> queryAllParameterType() {
		return appManagerMapper.queryAllParameterType();
	}

	@Override
	public List<TApplication> queryAllName() {
		return appManagerMapper.queryAllName();
	}

	@Override
	public TApplication queryAppManagerByName(String name) {
		if ("".equals(name) && name.equals(null)) {
			return null;
		}

		return appManagerMapper.queryAppManagerByName(name);
	}

	@Override
	public int updateAppManagerById(TApplication model) {

		int result = appManagerMapper.updateByPrimaryKeySelective(model);

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("app应用");
			columnType.setName(model.getName());
			columnType.setContent(model.getSynopsis());
			result = columnTypeMapper.updateColumnTypeSelective(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("app应用");

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

	/******************************* 应用版本 *********************************************************/
	@Override
	public boolean insertAppEditions(AppEditions model) {

		model.setIsDelete("n");
		model.setCreateTime(new Date());
		int result = appEditionsMapper.insert(model);
		if (result > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Map<String, Object>> getAllAppEditionsInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_appEditions = appEditionsMapper.getAllAppEditionsInfo(p);
		PageInfo page = new PageInfo(queryT_appEditions);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_appEditions;
	}

	@Override
	public int delAppEditionsById(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = appEditionsMapper.delAppEditionsById(id);
		}
		return result;
	}

	@Override
	public AppEditions queryAppEditionsById(Integer id) {
		return appEditionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateAppEditionsById(AppEditions model) {
		return appEditionsMapper.updateByPrimaryKeySelective(model);
	}

}
