package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;

/** 系统菜单管理 */
public interface SysmenuService {
	/** 系统管理员登录 */
	public Sysuser adminLogin(String loginName, String loginPwd);

	/** 浏览系统菜单 */
	public List<Sysmenu> browseSysmenu(JqGridPager jqGridPager, Map<String, Object> p);

	/** 获取系统菜单列表 */
	public List<Sysmenu> browseSysmenuList(Map<String, Object> p);

	/** 获取指定的菜单 */
	public Sysmenu loadSysmenu(Integer id);

	/**
	 * 添加系统菜单
	 * 
	 * @param record
	 * @return
	 */
	public boolean insertSysmenu(Sysmenu record);

	/** 删除 */
	public boolean delSysmenu(Integer id);

	/** 修改 */
	public boolean updateSysmenu(Sysmenu record);

	/** 统计查询结果 */
	public int countSysmenu(Map<String, Object> p);

}
