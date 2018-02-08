package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.SysVer;

public interface SysVerMapper {
	
    int deleteByPrimaryKey(Long id);
    int insert(SysVer record);
    int insertSelective(SysVer record);
    SysVer selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(SysVer record);
    int updateByPrimaryKey(SysVer record);
    
    List<Map<String,Object>> getAllSysVerInfo(Map<String, Object> p);
    
    int delSysVerById(Long id);
    
    List<SysVer> queryAllSysVerInfo();
}