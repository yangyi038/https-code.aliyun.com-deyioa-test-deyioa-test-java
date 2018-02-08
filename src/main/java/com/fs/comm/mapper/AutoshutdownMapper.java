package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Autoshutdown;

public interface AutoshutdownMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Autoshutdown record);
    int insertSelective(Autoshutdown record);
    Autoshutdown selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Autoshutdown record);
    int updateByPrimaryKey(Autoshutdown record);
    
    List<Map<String,Object>> getAllAutoshutdownInfo(Map<String, Object> p);
    
    int delAutoshutdownById(Long id);
    
    List<Autoshutdown> getAutoshutdown();
}