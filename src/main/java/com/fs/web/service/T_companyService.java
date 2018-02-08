package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TCompany;

/** 系统用户管理业务逻辑接口 */
public interface T_companyService {
	
	/** 浏览运营商 */
	public List<TCompany> browseCompany(JqGridPager jqGridPager,Map<String, Object> p);	
	
	/** 获取运营商列表*/
	public List<TCompany> getCompanyList(Map<String, Object> p);
	
	/** 新增运营商 */
	public boolean insertCompany(TCompany company);
	
	/** 删除运营商 */	
	public boolean delCompany(Long sid);	
	
	/**
	 * 根据主键获取运营商信息
	 */
	public TCompany getCompanyInfo(Long sid);
	
	/** 修改运营商 */
	public boolean updateCompany(TCompany company);
	
	
//	/** 系统管理员登录 */
//	public Sysuser adminLogin(String loginName,String loginPwd);
//	/** 装载指定的管理员 */	
//	public Sysuser loadSysuser(Integer id);	


//	/** 修改管理员 */
//	public boolean updateSysuser(Sysuser sysuser);
//	/**登录、登出日志记录*/
//	public boolean saveSysloginlog(Sysuser sysuser,String ip,String comments);
//	/** 更新密码 */	
//	public boolean updatePsw(String password,Integer id);
}
