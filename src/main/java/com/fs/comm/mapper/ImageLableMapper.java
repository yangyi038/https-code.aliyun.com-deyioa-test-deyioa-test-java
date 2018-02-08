package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.ImageLable;

/**
 * 图文模板
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface ImageLableMapper {
	
	/**
     * 获取图文模板列表
     * @param p
     * @return
     */
    List<ImageLable> getImageLableList(Map<String, Object> p);
	
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 添加图文模板
     * @param record
     * @return
     */
    int insertLable(ImageLable record);
    
    
    int insertSelective(ImageLable record);
    ImageLable selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(ImageLable record);
    int updateByPrimaryKey(ImageLable record);
    
    
    /**
     * 浏览图文模板列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getAllImageLableInfo(Map<String, Object> p);
    
    int delImageLableById(Integer id);
    
    int unAgreeImageLableById(Integer id);
    
    int agreeImageLableById(Integer id);
    
    ImageLable queryImageLableById(Integer id);
}