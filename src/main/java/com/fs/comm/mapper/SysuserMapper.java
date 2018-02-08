package com.fs.comm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fs.comm.model.Sysloginlog;
import com.fs.comm.model.Sysuser;

public interface SysuserMapper {
	
	
	List<Sysuser> browseSysuser(Map<String, Object> map);  
	
	/**
	 * 获取管理员信息
	 * @param p
	 * @return
	 */
	Sysuser selectSysuser(Map<String, Object> p);
	
	
	Sysuser loadSysuser(@Param(value="id") Integer id);
	int saveSysuser(Sysuser sysuser);
	int updateSysuser(Sysuser sysuser);
	
	/**
	 * 主键删除
	 * @param id
	 * @return
	 */
	int delById(@Param(value="id") Integer id);
	
	/**
	 * 删除
	 * @param sysloginlog
	 * @return
	 */
	int delSysUser(Map<String, Object> p);
	
	
	
	int saveSysloginlog(Sysloginlog sysloginlog);
	int updatePsw(@Param(value="password") String password,@Param(value="id") Integer id);
}