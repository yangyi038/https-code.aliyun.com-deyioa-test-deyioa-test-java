package com.fs.web.service;



import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.AppEditions;
import com.fs.comm.model.TApplication;


public interface T_ApplicationService {
	
	/**
	 * 添加应用
	 * @param model
	 * @return
	 */
	public boolean insertApp(TApplication model);
	
	
	/**
	 * 获取应用列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllAppManagerInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	
	/**
	 * 根据ID删除应用列表信息
	 * @param id
	 * @return
	 */
	public int delAppManagerById(Integer id);
	
	/**
	 * 上线
	 * @param id
	 * @return
	 */
	public int onlineAppManager(Integer id);
	
	/**
	 * 下线
	 * @param id
	 * @return
	 */
	public int unlineAppManager(Integer id);
	
	/**
	 * 下架
	 * @param id
	 * @return
	 */
	public int undercarriageAppManager(Integer id);
	
	/**
	 * 根据ID查询单个应用信息
	 * @param id
	 * @return
	 */
	public TApplication queryAppManagerById(Integer id);
	
	/**
	 * 查询所有的参数类型
	 * @return
	 */
	public List<String> queryAllParameterType();
	
	/**
	 * 查询所有的名称
	 * @return
	 */
	public List<TApplication> queryAllName();
	
	/**
	 * 根据应用列表查询
	 * @param name
	 * @return
	 */
	public TApplication queryAppManagerByName(String name);
	
	/**
	 * 根据ID修改
	 * @param model
	 * @return
	 */
	public int updateAppManagerById(TApplication model);
	
	
	/**************************应用版本**********************************************/
	
	/**
	 * 添加应用版本信息
	 * @param model
	 * @return
	 */
	public boolean insertAppEditions(AppEditions model);
	
	
	/**
	 * 获取应用版本信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllAppEditionsInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除应用版本信息
	 * @param id
	 * @return
	 */
	public int delAppEditionsById(Integer id);
	
	/**
	 * 根据ID查询单个应用版本信息
	 * @param id
	 * @return
	 */
	public AppEditions queryAppEditionsById(Integer id);
	
	/**
	 * 根据ID修改应用版本信息
	 * @param model
	 * @return
	 */
	public int updateAppEditionsById(AppEditions model);
}
