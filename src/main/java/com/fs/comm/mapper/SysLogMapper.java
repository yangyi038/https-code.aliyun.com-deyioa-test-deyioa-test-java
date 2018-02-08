package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.SysLog;



public interface SysLogMapper {
	
	/**
	 * 浏览用户操作日志
	 * @param p
	 * @return
	 */
	List<Map<String,Object>> getAllSysLogInfo(Map<String, Object> p);
	
    int insert(SysLog record);

    int insertSelective(SysLog record);
}