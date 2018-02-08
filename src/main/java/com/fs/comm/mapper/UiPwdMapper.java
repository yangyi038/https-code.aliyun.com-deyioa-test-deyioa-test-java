package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.UiPwd;

public interface UiPwdMapper {
    
    int deleteByPrimaryKey(Long id);
    int insert(UiPwd record);
    int insertSelective(UiPwd record);
    UiPwd selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(UiPwd record);
    int updateByPrimaryKey(UiPwd record);
    
    List<Map<String,Object>> getAllUiPwdInfo(Map<String, Object> p);
    
    int delUiPwdById(Long id);
    
    List<UiPwd> getUiPwd();
}