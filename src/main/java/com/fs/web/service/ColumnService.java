package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Column;


public interface ColumnService {
	
	/**
	 * 添加栏目
	 * @param model
	 * @return
	 */
	public boolean insertColumn(Column model);
	
	/**
	 * 获取栏目列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllColumnInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除栏目信息
	 * @param id
	 * @return
	 */
	public int delColumnById(Integer id);
	
	/**
	 * 通过审核
	 * @param id
	 * @return
	 */
	public int agreeColumnById(Integer id);
	
	/**
	 * 取消审核
	 * @param id
	 * @return
	 */
	public int unAgreeColumnById(Integer id);
	
	/**
	 * 查询所有的分类
	 * @return
	 */
	public List<Column> selectColumnClassify();
	
	/**
	 * 根据ID查询栏目信息
	 * @param id
	 * @return
	 */
	public Column queryColumnById(Integer id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateColumn(Column model);
	
	
	/******************************************************/
	
	/**
	 * 浏览栏目
	 * @param p
	 * @return
	 */
	public List<Column> browseColumn(Map<String, Object> p);
	
	/**
	 * 统计指定类的查询结果
	 * @param p
	 * @return
	 */
	public int countColumn(Map<String, Object> p);	
	
	/**
	 * 获取根栏目列表
	 * @return
	 */
	public List<Column> getRootColumnList(Map<String, Object> p);
	
}
