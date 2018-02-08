package com.fs.comm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fs.comm.model.Role;
import com.fs.comm.model.Sysmenu;

public interface RoleMapper {
	
	Role loadRole(@Param(value="id") Integer id);
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	int saveRole(Role role);
	
	int updateRole(Role role);
	
	/**
	 * 获取角色列表
	 * @param map
	 * @return
	 */
	List<Role> browseRole(Map<String, Object> map);  
	
	int delById(@Param(value="id") Integer id);
	int countSysuser(@Param(value="id") Integer id); 
	
	/**
	 * 获取菜单列表
	 * @param menuparent
	 * @return
	 */
	List<Sysmenu> getmenu(Map<String, Object> map);
	
}