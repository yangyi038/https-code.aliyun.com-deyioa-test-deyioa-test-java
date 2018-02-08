package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.LayoutSelectors;

public interface LayoutSelectorsMapper {
   
    int deleteByPrimaryKey(Long id);

    int insert(LayoutSelectors record);

    int insertSelective(LayoutSelectors record);

    LayoutSelectors selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LayoutSelectors record);

    int updateByPrimaryKey(LayoutSelectors record);
    
    
    List<Map<String,Object>> getAllLayoutSelectorsInfo(Map<String, Object> p);
    
    int delLayoutSelectorsById(Long id);
    
    List<LayoutSelectors> getLayoutSelectors();
}