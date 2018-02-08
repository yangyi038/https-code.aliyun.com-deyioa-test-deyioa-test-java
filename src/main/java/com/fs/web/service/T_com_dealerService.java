package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TCompanyAdmin;
import com.fs.comm.model.THotel;

/**
 * 经销商相关业务
 * @author pzj
 *
 */
public interface T_com_dealerService {

	/** 浏览经销商列表 */
	public List<TComDealer> browseDealerList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/** 获取经销商列表 */
	public List<TComDealer> getDealerList(Map<String, Object> p);
	
	
	/**
	 * 添加经销商
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean insertDealer(TComDealer recode);
	
	/**
	 * 删除运营商管理员
	 * @param sid
	 * @return
	 */
	public boolean delDealer(long sid);
	
	
	
//	
//	/**登录、登出日志记录*/
//	public boolean saveSysloginlog(TCompanyAdmin sysuser,String ip,String comments);
//	
//	/** 系统管理员登录 */
//	public TComDealer adminLogin(String loginName,String loginPwd);

	/**
	 * 主键获取经销商
	 * @param sid
	 * @return
	 */
	public TComDealer selectByPrimaryKey(Long sid);
	
	/**
	 * 修改经销商
	 * @param record
	 * @return
	 */
	public boolean  updateByPrimaryKey(TComDealer record);

}
