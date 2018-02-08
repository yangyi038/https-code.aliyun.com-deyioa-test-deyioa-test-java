package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.Title;


/**
 * 字幕管理mapper
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TitleMapper {
	
	/**
	 * 添加字幕
	 * @param model
	 * @return
	 */
	 int insertTitle(Title model);
	 
	 
	 List<Map<String,Object>> getAllTitleInfo(Map<String, Object> p);
	 int delTitleById(String id);
	 int agreeTitleById(String id);
	 int unAgreeTitleById(String id);
	 Title queryTitleById(Integer id);
	 
	 int updateTitleById(Title model);
}
