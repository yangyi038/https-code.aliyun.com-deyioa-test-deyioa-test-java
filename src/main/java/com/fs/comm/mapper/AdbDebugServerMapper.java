package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.AdbDebugServer;

public interface AdbDebugServerMapper {
   
    int deleteByPrimaryKey(Long id);
    int insert(AdbDebugServer record);
    int insertSelective(AdbDebugServer record);
    AdbDebugServer selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(AdbDebugServer record);
    int updateByPrimaryKey(AdbDebugServer record);
    
    List<Map<String,Object>> getAllAdbDebugServerInfo(Map<String, Object> p);
    
    int delAdbDebugServerById(Long id);
    
    List<AdbDebugServer> getAdbdebugserver();
}