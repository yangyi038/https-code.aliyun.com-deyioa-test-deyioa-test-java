package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Streaming;

public interface StreamingMapper {
	
    int deleteByPrimaryKey(Long id);
    int insert(Streaming record);
    int insertSelective(Streaming record);
    Streaming selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Streaming record);
    int updateByPrimaryKey(Streaming record);
    
    /**
     * 浏览流媒体配置列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getAllStreamingInfo(Map<String, Object> p);
    
    int delStreamingById(Long id);
    
}