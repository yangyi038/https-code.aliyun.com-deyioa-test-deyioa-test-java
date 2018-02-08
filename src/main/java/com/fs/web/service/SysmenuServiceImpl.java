package com.fs.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.SysmenuMapper;
import com.fs.comm.mapper.SysuserMapper;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 系统用户管理业务逻辑接口实现 */
@Service("sysmenuService")
public class SysmenuServiceImpl implements SysmenuService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private SysuserMapper sysuserMapper;

	@Resource
	private SysmenuMapper menuMapper;

	/** 系统管理员登录 */
	public Sysuser adminLogin(String loginname, String loginpwd) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("loginname", loginname);
		p.put("loginpwd", loginpwd);

		Sysuser sysuser = sysuserMapper.selectSysuser(p);
		return sysuser;
	}

	/** 浏览系统菜单 */
	public List<Sysmenu> browseSysmenu(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Sysmenu> menuList = menuMapper.browseSysmenu(p);
		PageInfo page = new PageInfo(menuList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return menuList;
	}

	/** 获取系统菜单列表 */
	public List<Sysmenu> browseSysmenuList(Map<String, Object> p) {
		List<Sysmenu> menuList = menuMapper.browseSysmenu(p);
		return menuList;
	}

	/** 获取指定菜单 */
	public Sysmenu loadSysmenu(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	/**
	 * 添加系统菜单
	 * 
	 * @param record
	 * @return
	 */
	public boolean insertSysmenu(Sysmenu record) {
		try {
			if (menuMapper.insertSelectiveSysmenu(record) > 0) {
				
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 删除指定的部门 */
	public boolean delSysmenu(Integer id) {
		try {
			if (menuMapper.deleteSysmenuById(id) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 统计指定类的查询结果 */
	public int countSysmenu(Map<String, Object> p) {
		return menuMapper.countSysmenu(p);
	}

	/** 修改部门 */
	public boolean updateSysmenu(Sysmenu record) {
		try {
			if (menuMapper.updateSysmenu(record) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
