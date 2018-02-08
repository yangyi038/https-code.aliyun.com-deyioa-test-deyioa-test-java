package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.AppEditions;



public interface AppEditionsMapper {
	
    int deleteByPrimaryKey(Integer id);
    int insert(AppEditions record);
    int insertSelective(AppEditions record);
    AppEditions selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(AppEditions record);
    int updateByPrimaryKey(AppEditions record);
    
    List<Map<String,Object>> getAllAppEditionsInfo(Map<String, Object> p);
    
    int delAppEditionsById(Integer id);
}