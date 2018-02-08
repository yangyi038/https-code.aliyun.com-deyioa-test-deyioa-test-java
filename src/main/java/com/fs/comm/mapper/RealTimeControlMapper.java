package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.RealTimeControl;

public interface RealTimeControlMapper {
    
    int deleteByPrimaryKey(Long id);
    int insert(RealTimeControl record);
    int insertSelective(RealTimeControl record);
    RealTimeControl selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(RealTimeControl record);
    int updateByPrimaryKey(RealTimeControl record);
    
    List<Map<String,Object>> getAllRealTimeControlInfo(Map<String, Object> p);
    
    int delRealTimeControlById(Long id);
    
    List<RealTimeControl> getRealTimeControl();
}