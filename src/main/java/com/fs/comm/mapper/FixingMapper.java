package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Fixing;

public interface FixingMapper {
	
    int deleteByPrimaryKey(Long id);
    int insert(Fixing record);
    int insertSelective(Fixing record);
    Fixing selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Fixing record);
    int updateByPrimaryKey(Fixing record);
    
    List<Map<String,Object>> getAllFixingInfo(Map<String, Object> p);
    
    int delFixingById(Long id);
    
    List<Fixing> queryAllFixing();
}