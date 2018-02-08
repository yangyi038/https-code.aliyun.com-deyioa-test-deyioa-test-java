package com.fs.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fantastic.DateUtilsEx;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.DateTool;
import com.fs.comm.mapper.SysuserMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TComDealer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_com_dealerService")
/** 运营商管理员业务 */
public class T_com_dealerServiceImpl implements T_com_dealerService {

	@Resource
	private TComDealerMapper dealerMapper;
	@Resource
	private SysuserMapper sysuserMapper;

	/** 浏览经销商列表 */
	public List<TComDealer> browseDealerList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TComDealer> bindList = dealerMapper.getDealerList(p);
		if (bindList != null && bindList.size() > 0) {
			for (TComDealer tBind : bindList) {
				tBind.setCreatetimeStr(DateUtilsEx.date2Str(tBind.getCreatetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
			}
		}
		PageInfo page = new PageInfo(bindList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return bindList;
	}

	/** 获取经销商列表 */
	public List<TComDealer> getDealerList(Map<String, Object> p) {
		List<TComDealer> adminList = dealerMapper.getDealerList(p);
		return adminList;
	}

	/**
	 * 添加运营商管理员
	 * 
	 * @param hotel
	 * @return
	 */
	@Transactional
	public boolean insertDealer(TComDealer recode) {
		try {
			int num = dealerMapper.insertDealer(recode);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时添加到 管理员（sysuser）表
				Sysuser admin = new Sysuser();
				admin.setCompanyid(recode.getCompanyid());
				admin.setOperatorid(recode.getOperatorid());
				admin.setDealerid(recode.getSid());
				
				admin.setLoginname(recode.getName());
				admin.setLoginpwd(recode.getPwd());
				admin.setPrivilege(recode.getPrivilege());// 角色权限
				admin.setAdmintype(4);// 经销商
				admin.setLogintimes(0);
				admin.setLastlogin(DateTool.getTimestamp());
				admin.setIsclose(1);
				admin.setDepid(1);

				if (sysuserMapper.saveSysuser(admin) > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除经销商
	 * 
	 * @param sid
	 * @return
	 */
	@Transactional
	public boolean delDealer(long sid) {
		try {
			int num = dealerMapper.deleteDealer(sid);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				Map<String, Object> map = new HashMap<>();
				map.put("dealerid", sid);
				map.put("admintype", 4);// 经销商
				if (sysuserMapper.delSysUser(map) > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	/** 新增系统用户 */
//	public boolean saveSysloginlog(TCompanyAdmin sysuser, String ip, String comments) {
//		try {
//			Sysloginlog sysloginlog = new Sysloginlog();
//			sysloginlog.setLoginname(sysuser.getLoginname());
//			sysloginlog.setLogintime(DateTool.getTimestamp());
//			sysloginlog.setSid(Integer.parseInt(sysuser.getSid() + ""));
//			sysloginlog.setComments(comments);
//			sysloginlog.setLoginip(ip);
//			if (sysuserMapper.saveSysloginlog(sysloginlog) > 0) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//
//	/** 系统管理员登录 */
//	public TCompanyAdmin adminLogin(String loginname, String loginpwd) {
//		Map<String, Object> p = new HashMap<String, Object>();
//		p.put("loginname", loginname);
//		p.put("loginpwd", loginpwd);
//		TCompanyAdmin sysuser = adminMapper.getAdmin(p);
//		return sysuser;
//	}

	/**
	 * 主键获取经销商
	 * 
	 * @param sid
	 * @return
	 */
	public TComDealer selectByPrimaryKey(Long sid) {
		TComDealer admin = dealerMapper.selectByPrimaryKey(sid);
		return admin;
	}

	/**
	 * 修改经销商信息
	 */
	public boolean updateByPrimaryKey(TComDealer company) {
		try {
			int cc = dealerMapper.updateByPrimaryKey(company);
			if (cc > 0 || cc == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时修改系统登录（sysuser）表
				Map<String, Object> p = new HashMap<>();
				p.put("companyid", company.getCompanyid());
				p.put("operatorid", company.getOperatorid());
				p.put("dealerid", company.getSid());
				Sysuser admin = sysuserMapper.selectSysuser(p);
				if (admin != null) {
					admin.setLoginname(company.getName());
					admin.setLoginpwd(company.getPwd());
					admin.setPrivilege(company.getPrivilege());// 角色权限
				}

				if (sysuserMapper.updateSysuser(admin) > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
