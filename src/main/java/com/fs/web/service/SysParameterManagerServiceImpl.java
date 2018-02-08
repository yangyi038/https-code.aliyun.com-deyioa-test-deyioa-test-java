package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.SysParameterMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.SysParameter;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("sysParameterManagerService")
public class SysParameterManagerServiceImpl implements SysParameterManagerService {

	@Resource
	private SysParameterMapper sysParameterMapper;

	@Resource
	private TCompanyMapper companyMapper;

	/**
	 * 添加系统参数配置
	 */
	@Override
	public int insertSysParameter(SysParameter model) {
		model.setIsDelete("n");
		model.setCreateTime(new Date());
		return sysParameterMapper.insert(model);
	}

	/**
	 * 浏览配置列表
	 */
	@Override
	public List<Map<String, Object>> getAllSysParameterInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> list = sysParameterMapper.getAllSysParameterInfo(p);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				// 获取运营商名称
				Long companyid = (Long) map.get("companyid");
				if (companyid != null) {
					TCompany company = companyMapper.selectByPrimaryKey(companyid);
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						map.put("companyname", company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public SysParameter getSysParameterById(Long id) {
		return sysParameterMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSysParameterById(SysParameter model) {
		return sysParameterMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delSysParameter(Long id) {
		return sysParameterMapper.delSysParameter(id);
	}
	
	/**
	 * 获取系统参数
	 */
	@Override
	public SysParameter getSysParameter(Map<String, Object> map) {
		return sysParameterMapper.getSysParameter(map);
	}

}
