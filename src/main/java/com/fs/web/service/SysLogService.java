package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.SysLog;

public interface SysLogService {
	
	/**
	 * 查询所有的日志信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllSysLogInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 添加日志
	 * @param sysLog
	 * @return
	 */
	public int insertSysLog(SysLog sysLog);
}
