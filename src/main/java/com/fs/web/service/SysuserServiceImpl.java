package com.fs.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.jqgrid.JqGridPager;
import com.framework.util.DateTool;
import com.fs.comm.mapper.SysuserMapper;
import com.fs.comm.mapper.TCompanyAdminMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.Sysloginlog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("SysuserService")
/** 系统用户管理业务逻辑接口实现 */
public class SysuserServiceImpl implements SysuserService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private SysuserMapper sysuserMapper;
	@Resource
	private TCompanyMapper companyMapper;
	@Resource
	private TCompanyAdminMapper adminMapper;

	/** 系统管理员登录 */
	public Sysuser adminLogin(String loginname, String loginpwd) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("loginname", loginname);
		p.put("loginpwd", loginpwd);

		Sysuser sysuser = null;
		try {
			sysuser = sysuserMapper.selectSysuser(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sysuser;
	}

	// /** 获取管理员信息 */
	// public Sysuser getSysuser(Map<String, Object> p) {
	// Sysuser sysuser = sysuserMapper.selectSysuser(p);
	// return sysuser;
	// }

	/** 系统用户加载 */
	public Sysuser loadSysuser(Integer id) {
		Sysuser sysuser = sysuserMapper.loadSysuser(id);
		return sysuser;
	}

	/** 新增系统管理员 */
	@Transactional
	public boolean saveSysuser(Sysuser sysuser) {
		try {
			if (sysuserMapper.saveSysuser(sysuser) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 修改系统用户 */
	public boolean updateSysuser(Sysuser sysuser) {
		try {
			if (sysuserMapper.updateSysuser(sysuser) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 浏览系统用户 */
	public List<Sysuser> browseSysuser(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Sysuser> querySysuser = sysuserMapper.browseSysuser(p);

		// 过滤掉超级管理员admin
		List<Sysuser> userlist = new ArrayList<Sysuser>();

		if (querySysuser != null && querySysuser.size() > 0) {
			for (Sysuser user : querySysuser) {
				// 获取运营商名称
				if (user.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(user.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						user.setCompanyname(company.getCompanyname());
				}
				
					userlist.add(user);
				
			}
		}

		PageInfo<List<Sysuser>> page = new PageInfo(userlist);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return userlist;
	}

	/** 浏览系统用户 */
	public List<Sysuser> browseSysuser(Map<String, Object> p) {
		List<Sysuser> deps = sysuserMapper.browseSysuser(p);
		return deps;
	}

	/** 删除指定的系统用户 */
	@Transactional
	public boolean delSysuser(Sysuser sysuser) {
		try {
			if (sysuserMapper.delById(sysuser.getId()) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 新增系统用户 */
	public boolean saveSysloginlog(Sysuser sysuser, String ip, String comments) {
		try {
			Sysloginlog sysloginlog = new Sysloginlog();
			sysloginlog.setLoginname(sysuser.getLoginname());
			sysloginlog.setLogintime(DateTool.getTimestamp());
			sysloginlog.setSid(sysuser.getId());
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

	/** 更新密码 */
	public boolean updatePsw(String password, Integer id) {
		try {
			if (sysuserMapper.updatePsw(password, id) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public SysuserMapper getSysuserMapper() {
		return sysuserMapper;
	}

	public void setSysuserMapper(SysuserMapper sysuserMapper) {
		this.sysuserMapper = sysuserMapper;
	}

}
