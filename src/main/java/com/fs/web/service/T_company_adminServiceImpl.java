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
import com.fs.comm.mapper.TCompanyAdminMapper;
import com.fs.comm.model.Sysloginlog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompanyAdmin;
import com.fs.comm.model.TStbGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_company_adminService")
/** 运营商管理员业务 */
public class T_company_adminServiceImpl implements T_company_adminService {

	@Resource
	private TCompanyAdminMapper adminMapper;
	@Resource
	private SysuserMapper sysuserMapper;

	/**
	 * 获取运营商管理员列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TCompanyAdmin> browseAdminList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TCompanyAdmin> bindList = adminMapper.getAdminList(p);
		if (bindList != null && bindList.size() > 0) {
			for (TCompanyAdmin tBind : bindList) {
				tBind.setCreatetimeStr(DateUtilsEx.date2Str(tBind.getCreatetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
			}
		}
		PageInfo page = new PageInfo(bindList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return bindList;
	}

	/** 获取运营商管理员列表 */
	public List<TCompanyAdmin> getAdminList(Map<String, Object> p) {
		List<TCompanyAdmin> adminList = adminMapper.getAdminList(p);
		return adminList;
	}

	/**
	 * 添加运营商管理员
	 * 
	 * @param hotel
	 * @return
	 */
	@Transactional
	public boolean insertAdmin(TCompanyAdmin company) {
		try {
			int num = adminMapper.insertAdmin(company);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时添加到 管理员（sysuser）表
				Sysuser admin = new Sysuser();
				admin.setCompanyid(company.getCompanyid());
				admin.setCompanyadminid(company.getSid());
				admin.setLoginname(company.getLoginname());
				admin.setLoginpwd(company.getLoginpwd());
				admin.setPrivilege(company.getPrivilege());// 角色权限
				admin.setAdmintype(company.getAdmintype());// 运营商管理员
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
	 * 删除管理员
	 * 
	 * @param sid
	 * @return
	 */
	@Transactional
	public boolean delAdmin(long sid) {
		try {
			int num = adminMapper.deleteAdmin(sid);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				Map<String, Object> map = new HashMap<>();
				map.put("companyadminid", sid);
				map.put("admintype", 2);// 运营商管理员是2，
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

	/** 新增系统用户 */
	public boolean saveSysloginlog(TCompanyAdmin sysuser, String ip, String comments) {
		try {
			Sysloginlog sysloginlog = new Sysloginlog();
			sysloginlog.setLoginname(sysuser.getLoginname());
			sysloginlog.setLogintime(DateTool.getTimestamp());
			sysloginlog.setSid(Integer.parseInt(sysuser.getSid() + ""));
			sysloginlog.setComments(comments);
			sysloginlog.setLoginip(ip);
			if (sysuserMapper.saveSysloginlog(sysloginlog) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 系统管理员登录 */
	public TCompanyAdmin adminLogin(String loginname, String loginpwd) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("loginname", loginname);
		p.put("loginpwd", loginpwd);
		TCompanyAdmin sysuser = adminMapper.getAdmin(p);
		return sysuser;
	}

	/**
	 * 主键获取运营商管理员信息
	 * 
	 * @param sid
	 * @return
	 */
	public TCompanyAdmin selectByPrimaryKey(Long sid) {
		TCompanyAdmin admin = adminMapper.selectByPrimaryKey(sid);
		return admin;
	}

	/**
	 * 修改管理员信息
	 */
	public boolean updateByPrimaryKey(TCompanyAdmin company) {
		try {
			int cc = adminMapper.updateByPrimaryKey(company);
			if (cc > 0 || cc == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时修改系统登录（sysuser）表
				Map<String, Object> p = new HashMap<>();
				p.put("companyid", company.getCompanyid());
				p.put("companyadminid", company.getSid());
				Sysuser admin = sysuserMapper.selectSysuser(p);
				if (admin != null) {
					admin.setLoginname(company.getLoginname());
					admin.setLoginpwd(company.getLoginpwd());
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
