package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.SysParameter;

public interface SysParameterMapper {
	
    int deleteByPrimaryKey(Long id);
    int insert(SysParameter record);
    int insertSelective(SysParameter record);
    SysParameter selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(SysParameter record);
    int updateByPrimaryKey(SysParameter record);
    
    /**
     * 浏览系统参数配置
     * @param p
     * @return
     */
    List<Map<String,Object>> getAllSysParameterInfo(Map<String, Object> p);
    
    int delSysParameter(Long id);
    
    /**
     * 获取系统参数配置
     * @param id
     * @return
     */
    SysParameter getSysParameter(Map<String, Object> p);
    
}