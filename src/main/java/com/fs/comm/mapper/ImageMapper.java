package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.Image;

@Repository
@SuppressWarnings("javadoc")
public interface ImageMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 添加缩略图
     * @param record
     * @return
     */
    int insertImage(Image record);
    
    int insertSelective(Image record);
    Image selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Image record);
    int updateByPrimaryKey(Image record);
    
    /**
     * 浏览缩略图列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getImageInfo(Map<String, Object> p);
    
    public int delImage(Integer id);
}