package com.fs.web.service;


import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Title;

public interface TtitleService{
	
	/**
	 * 添加字幕
	 * @param model
	 * @return
	 */
	public boolean insertTitle(Title model);
	
	/**
	 * 获取字幕列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllTitleInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除字幕信息
	 * @param id
	 * @return
	 */
	public int delTitleById(String id);
	
	/**
	 * 字幕通过审核
	 * @param id
	 * @return
	 */
	public int agreeTitleById(String id);
	
	/**
	 * 字幕取消审核
	 * @param id
	 * @return
	 */
	public int unAgreeTitleById(String id);
	
	/**
	 * 根据ID查询字幕信息
	 * @param id
	 * @return
	 */
	public Title queryTitleById(Integer id);
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public int updateTitleById(Title model);
}
