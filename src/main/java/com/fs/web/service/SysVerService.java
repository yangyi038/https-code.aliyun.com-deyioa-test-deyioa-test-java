package com.fs.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.AdbDebugServer;
import com.fs.comm.model.Autoshutdown;
import com.fs.comm.model.LayoutSelectors;
import com.fs.comm.model.RealTimeControl;
import com.fs.comm.model.SysVer;
import com.fs.comm.model.UiPwd;

public interface SysVerService {

	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public int insertSysVer(SysVer model);
	
	
	/**
	 * 获取参数列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllSysVerInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delSysVerById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public SysVer getSysVerById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateSysVerById(SysVer model);
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<SysVer> queryAllSysVerInfo();
	
	
	/*************************************autoshutdown   start******************************************************/
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public int insertAutoshutdown(Autoshutdown model);
	
	/**
	 * 查询
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllAutoshutdownInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delAutoshutdownById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public Autoshutdown getAutoshutdownById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateAutoshutdownById(Autoshutdown model);
	
	
	/*************************************autoshutdown   end******************************************************/
	
	
	/*************************************layoutSelectors   start******************************************************/
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public int insertLayoutSelectors(LayoutSelectors model);
	
	/**
	 * 查询
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllLayoutSelectorsInfo(JqGridPager jqGridPager,Map<String, Object> p);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delLayoutSelectorsById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public LayoutSelectors getLayoutSelectorsById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateLayoutSelectorsById(LayoutSelectors model);
	
	/*************************************layoutSelectors   end******************************************************/
	
	
	

	/*************************************uipwd   start******************************************************/
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public int insertUiPwd(UiPwd model);
	
	/**
	 * 查询
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllUiPwdInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delUiPwdById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public UiPwd getUiPwdById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateUiPwdById(UiPwd model);
	
	/*************************************uipwd   end******************************************************/
	
	
	
	

	/*************************************layoutSelectors   start******************************************************/
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public int insertAdbDebugServer(AdbDebugServer model);
	
	/**
	 * 查询
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllAdbDebugServerInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delAdbDebugServerById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public AdbDebugServer getAdbDebugServerById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateAdbDebugServerById(AdbDebugServer model);
	

	/*************************************uipwd   end******************************************************/
	
	
	
	/*************************************RealTimeControl   start******************************************************/
	
	public int insertRealTimeControl(RealTimeControl model);
	
	/**
	 * 查询
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllRealTimeControlInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delRealTimeControlById(Long id);
	
	/**
	 * 根据ID查询对象信息
	 * @param id
	 * @return
	 */
	public RealTimeControl getRealTimeControlById(Long id);
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	public int updateRealTimeControlById(RealTimeControl model);
	
	
	/*************************************RealTimeControl   end******************************************************/


}
