package com.fs.web.service;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.SysLogMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 用户操作日志
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService{
	
	@Resource
	private SysLogMapper sysLogMapper;
	
	@Resource
	private TCompanyMapper companyMapper;

	@Override
	public List<Map<String, Object>> getAllSysLogInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list=sysLogMapper.getAllSysLogInfo(p);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object dotime = map.get("dotime");
				String time = sdf.format(dotime);
				map.put("dotime", time);
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
	public int insertSysLog(SysLog sysLog) {
		return sysLogMapper.insert(sysLog);
	}

}
