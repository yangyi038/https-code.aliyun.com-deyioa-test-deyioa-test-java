package com.fs.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.jqgrid.JqGridPager;
import com.framework.util.DateTool;
import com.framework.util.MD5;
import com.fs.comm.mapper.SysuserMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_companyService")
/** 运营商管理业务逻辑接口实现 */
public class T_companyServiceImpl implements T_companyService {
	/** 通过依赖注入Mapper组件实例 */

	@Resource
	private SysuserMapper sysuserMapper;
	@Resource
	private TCompanyMapper tCompanyMapper;

	/** 浏览运营商 */
	public List<TCompany> browseCompany(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TCompany> companyList = tCompanyMapper.getCompanyList(p);
		PageInfo<List<TCompany>> page = new PageInfo(companyList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return companyList;
	}

	/** 获取运营商列表 */
	public List<TCompany> getCompanyList(Map<String, Object> p) {
		List<TCompany> companyList = tCompanyMapper.getCompanyList(p);
		return companyList;
	}

	/** 新增系统用户 */
	@Transactional
	public boolean insertCompany(TCompany company) {
		try {
			// 登录密码MD5加密
			if (StringUtils.isNotBlank(company.getLoginpwd())) {
				company.setLoginpwd(MD5.MD5Encode(company.getLoginpwd()));
			}
			company.setIsopen(1);// 开通

			if (tCompanyMapper.insertCompany(company) > 0) {
				// 同时添加到 管理员（sysuser）表
				Sysuser admin = new Sysuser();
				admin.setCompanyid(company.getSid());
				admin.setLoginname(company.getCompanyname());
				admin.setLoginpwd(company.getLoginpwd());
				admin.setPrivilege(company.getRole());// 角色权限
				admin.setAdmintype(1);// 运营商
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

	/** 删除运营商 */
	@Transactional
	public boolean delCompany(Long id) {
		try {
			if (tCompanyMapper.delCompany(id) > 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("companyid", id);
				map.put("admintype", 1);// 运营商是1，
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
	 * 根据主键获取运营商信息
	 */
	public TCompany getCompanyInfo(Long sid) {
		TCompany company = tCompanyMapper.selectByPrimaryKey(sid);
		return company;
	}

	/** 修改运营商 */
	public boolean updateCompany(TCompany company) {
		try {
			if (tCompanyMapper.updateCompany(company) > 0) {
				// 同时修改系统登录（sysuser）表
				Map<String, Object> p = new HashMap<>();
				p.put("companyid", company.getSid());
				p.put("loginname", company.getCompanyname());
				Sysuser admin = sysuserMapper.selectSysuser(p);
				if (admin != null) {
					admin.setLoginname(company.getCompanyname());
					admin.setLoginpwd(company.getLoginpwd());
					admin.setPrivilege(company.getRole());// 角色权限
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

	//
	// /** 新增系统用户*/
	// public boolean saveSysloginlog(Sysuser sysuser,String ip,String
	// comments){
	// try{
	// Sysloginlog sysloginlog=new Sysloginlog();
	// sysloginlog.setLoginname(sysuser.getLoginname());
	// sysloginlog.setLogintime(DateTool.getTimestamp());
	// sysloginlog.setSid(sysuser.getId());
	// sysloginlog.setComments(comments);
	// sysloginlog.setLoginip(ip);
	// if(sysuserMapper.saveSysloginlog(sysloginlog)>0){
	// return true;
	// }else{
	// return false;
	// }
	// }catch(Exception ex){
	// ex.printStackTrace();
	// return false;
	// }
	// }
	// /** 更新密码 */
	// public boolean updatePsw(String password,Integer id){
	// try{
	// if(sysuserMapper.updatePsw(password,id)>0){
	// return true;
	// }else{
	// return false;
	// }
	// }catch(Exception ex){
	// ex.printStackTrace();
	// return false;
	// }
	// }
	// public SysuserMapper getSysuserMapper() {
	// return sysuserMapper;
	// }
	//
	// public void setSysuserMapper(SysuserMapper sysuserMapper) {
	// this.sysuserMapper = sysuserMapper;
	// }

}
