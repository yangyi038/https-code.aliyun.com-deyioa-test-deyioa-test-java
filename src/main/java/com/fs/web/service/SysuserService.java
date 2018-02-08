package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Sysuser;

/** 系统用户管理业务逻辑接口 */
public interface SysuserService {
	
	
	/** 系统管理员登录 */
	public Sysuser adminLogin(String loginName,String loginPwd);
	/** 装载指定的管理员 */	
	public Sysuser loadSysuser(Integer id);	
//	/** 获取管理员信息 */
//	public Sysuser getSysuser(Map<String, Object> p);
	
	
	/** 浏览管理员 */
	public List<Sysuser> browseSysuser(JqGridPager jqGridPager,Map<String, Object> p);	
	/** 浏览角色 */
	public List<Sysuser> browseSysuser(Map<String, Object> p);
	
	
	/** 删除指定的管理员 */	
	public boolean delSysuser( Sysuser user);	
	/** 新增管理员 */
	public boolean saveSysuser(Sysuser sysuser);
	/** 修改管理员 */
	public boolean updateSysuser(Sysuser sysuser);
	/**登录、登出日志记录*/
	public boolean saveSysloginlog(Sysuser sysuser,String ip,String comments);
	/** 更新密码 */	
	public boolean updatePsw(String password,Integer id);
}
