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
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TComOperator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_com_operatorService")
/** 运营商管理员业务 */
public class T_com_operatorServiceImpl implements T_com_operatorService {

	@Resource
	private TComOperatorMapper operatorMapper;
	@Resource
	private SysuserMapper sysuserMapper;

	/**
	 * 浏览二级运营商列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TComOperator> browseOperatorList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TComOperator> bindList = operatorMapper.getOperatorList(p);
		if (bindList != null && bindList.size() > 0) {
			for (TComOperator tBind : bindList) {
				tBind.setCreatetimeStr(DateUtilsEx.date2Str(tBind.getCreatetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
			}
		}
		PageInfo page = new PageInfo(bindList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return bindList;
	}

	/** 获取运营商列表 */
	public List<TComOperator> getOperatorList(Map<String, Object> p) {
		List<TComOperator> adminList = operatorMapper.getOperatorList(p);
		return adminList;
	}

	/**
	 * 添加
	 * 
	 * @param hotel
	 * @return
	 */
	@Transactional
	public boolean insertOperator(TComOperator company) {
		try {
			int num = operatorMapper.insertOperator(company);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时添加到 管理员（sysuser）表
				Sysuser admin = new Sysuser();
				admin.setCompanyid(company.getCompanyid());// 运营商ID
				admin.setOperatorid(company.getSid());// 二级运营商ID

				admin.setLoginname(company.getName());
				admin.setLoginpwd(company.getPwd());
				admin.setPrivilege(company.getPrivilege());// 角色权限
				admin.setAdmintype(3);// 运营商管理员
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
	 * 删除
	 * 
	 * @param sid
	 * @return
	 */
	@Transactional
	public boolean delOperator(long sid) {
		try {
			int num = operatorMapper.deleteOperator(sid);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				Map<String, Object> map = new HashMap<>();
				map.put("operatorid", sid);
				map.put("admintype", 3);// 运营商管理员是2，
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

	/**
	 * 主键获取运营商信息
	 * 
	 * @param sid
	 * @return
	 */
	public TComOperator selectByPrimaryKey(Long sid) {
		TComOperator admin = operatorMapper.selectByPrimaryKey(sid);
		return admin;
	}

	/**
	 * 修改二级运营商
	 */
	public boolean updateOperator(TComOperator company) {
		try {
			int cc = operatorMapper.updateOperator(company);
			if (cc > 0 || cc == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				// 同时修改系统登录（sysuser）表
				Map<String, Object> p = new HashMap<>();
				p.put("companyid", company.getCompanyid());
				p.put("operatorid", company.getSid());
				Sysuser admin = sysuserMapper.selectSysuser(p);
				if (admin != null) {
					admin.setLoginname(company.getName());
					admin.setLoginpwd(company.getPwd());
					admin.setPrivilege(company.getPrivilege());// 角色权限
				}else{
					System.out.println("error:系统表中未找到该用户");
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
