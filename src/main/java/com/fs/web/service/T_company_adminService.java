package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompanyAdmin;
import com.fs.comm.model.THotel;

/** 运营商管理员管理业务逻辑接口 */
public interface T_company_adminService {

	/** 获取管理员列表 */
	public List<TCompanyAdmin> browseAdminList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/** 获取管理员列表 */
	public List<TCompanyAdmin> getAdminList( Map<String, Object> p);
	
	/**
	 * 添加运营商管理员
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean insertAdmin(TCompanyAdmin admin);
	
	/**
	 * 删除运营商管理员
	 * @param sid
	 * @return
	 */
	public boolean delAdmin(long sid);
	
	
	
	
	/**登录、登出日志记录*/
	public boolean saveSysloginlog(TCompanyAdmin sysuser,String ip,String comments);
	
	/** 系统管理员登录 */
	public TCompanyAdmin adminLogin(String loginName,String loginPwd);

	/**
	 * 主键获取运营商管理员
	 * @param sid
	 * @return
	 */
	public TCompanyAdmin selectByPrimaryKey(Long sid);
	
	/**
	 * 修改管理员信息
	 * @param record
	 * @return
	 */
	public boolean  updateByPrimaryKey(TCompanyAdmin record);

}
