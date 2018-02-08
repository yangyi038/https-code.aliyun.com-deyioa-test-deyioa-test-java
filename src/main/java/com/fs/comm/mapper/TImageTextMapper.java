package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.ImageText;


/**
 * 图文信息Mapper
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TImageTextMapper {

	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加图文信息
	 * @param record
	 * @return
	 */
    int insertImageText(ImageText record);
    
    
    int insertSelective(ImageText record);

    
    /**
     * 根据主键获取图文信息
     * @param id
     * @return
     */
    ImageText selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageText record);

    int updateByPrimaryKey(ImageText record);
    
    /**
     * 浏览字幕列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getAllImageTextInfo(Map<String, Object> p);

    int delImageTextById(Integer id);
    
    int agreeImageTextById(Integer id);
    
    int unAgreeImageTextById(Integer id);
    
    int unAgreeOnlineById(Integer id);
    int agreeOnlineById(Integer id);
    
    int agreeUnlineById(Integer id);
}