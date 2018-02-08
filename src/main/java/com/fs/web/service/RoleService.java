package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysmenu;

/** 角色管理业务逻辑接口 */
public interface RoleService {
	/** 浏览角色 */
	public List<Role> browseRole(JqGridPager jqGridPager,Map<String, Object> p);
	/** 浏览角色 */
	public List<Role> browseRole(Map<String, Object> p);
	/** 装载指定的角色 */	
	public Role loadRole(Integer id);	
	/** 删除指定的角色 */	
	public boolean delRole(Integer id);	
	/** 新增角色 */
	public boolean saveRole(Role role);
	/** 修改角色 */
	public boolean updateRole(Role role);
	/** 新增或修改参数权限 */	
	//public boolean saveOrUpdateSysparper(Sysparper sysparper);
	/** 角色栏目列表 */
	//public List<Role> listRole();
	/**统计指定类的查询结果*/
	public int countSysuser(Integer id);
	// 加载权限树
	public List<Map<String, Object>> browseSysmenu(String menuparent,String privilege,String checkstate);
	
}
