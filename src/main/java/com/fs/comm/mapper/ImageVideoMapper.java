package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.ImageVideo;

@Repository
@SuppressWarnings("javadoc")
public interface ImageVideoMapper {
	
    int deleteByPrimaryKey(Integer id);
    int insert(ImageVideo record);
    int insertSelective(ImageVideo record);
    ImageVideo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(ImageVideo record);
    int updateByPrimaryKey(ImageVideo record);
    
    List<Map<String,Object>> getImageVideoInfo(Map<String, Object> p);
    
    public int delVideo(Integer id);
}