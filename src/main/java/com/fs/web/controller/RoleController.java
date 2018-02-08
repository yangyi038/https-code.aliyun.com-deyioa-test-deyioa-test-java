package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.fs.comm.model.Role;
import com.fs.web.service.RoleService;

/**
 * 角色管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;

	@RequestMapping("/browseRole.action")
	/** 处理浏览新闻请求 */
	public String browseRole() {
		return "sysmanage/role/browseRole";
	}

	@ResponseBody
	@RequestMapping("/listRole.action")
	/** jqgrid组件列表查询系统 用户 */
	public String listRole(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);

		Map<String, Object> p = new HashMap<>();
		p = ViewDataCondition(p);

		List<Role> roleList = roleService.browseRole(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, roleList, jqGridPager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回Json格式数据
		return null;
	}

	/** 进入新增页面 */
	@RequestMapping("/preAddRole.action")
	public String preAddRole(@ModelAttribute("model") Role model, Model retmodel) {
		return "sysmanage/role/addRole";
	}

	/** 处理新增组织机构请求 */
	@RequestMapping("/addRole.action")
	public String addRole(@ModelAttribute("model") Role model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("rolename", model.getRolename());
		p = ViewDataCondition(p);
		List<Role> roleList = roleService.browseRole(p);
		if (roleList.size() > 0) {
			retmodel.addAttribute("meg", "角色名已经存在！");
			return "sysmanage/role/addRole";
		}

		model.setCompanyid(currentUser().getCompanyid() != null ? currentUser().getCompanyid() : null);

		if (roleService.saveRole(model)) {// 调用业务逻辑组件保存新增的角色
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/role/addRole";
	}

	/**
	 * 处理删除组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delRole.action")
	public String delRole(@ModelAttribute("model") Role model) throws Exception {
		if (model.getId() != null) {
			int i = roleService.countSysuser(model.getId());
			if (i > 0) {
				return "有用户存在该角色,不能删除";
			}
			if (roleService.delRole(model.getId())) {// 调用业务逻辑组件删除指定的角色
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 处理查看组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/viewRole.action")
	public Role viewRole(@ModelAttribute("model") Role model) throws Exception {
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的新闻
			Role tempRole = roleService.loadRole(model.getId());
			if (tempRole != null) {
				return tempRole;

			} else {

				return null;
			}
		} else {
			return null;
		}
	}

	/** 处理装载组织机构请求 */
	@RequestMapping("/editRole.action")
	public String editRole(@ModelAttribute("model") Role model, Model retmodel) {
		Role tempRole = roleService.loadRole(model.getId());
		retmodel.addAttribute("role", tempRole);
		return "sysmanage/role/editRole";
	}

	/**
	 * 处理更新组织机构请求
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/updateRole.action")
	public String updateRole(@ModelAttribute("model") Role model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("rolename", model.getRolename());
		where.put("notid", model.getId());
		List<Role> roleList = roleService.browseRole(where);
		if (roleList.size() > 0) {
			retmodel.addAttribute("meg", "角色已存在！");
			return "sysmanage/role/editRole";
		}
		Role tempRole = roleService.loadRole(model.getId());
		BeanUtils.copyProperties(tempRole, model);
		if (roleService.updateRole(tempRole)) {// 调用业务逻辑组件更新指定的组织机构
			BeanUtils.copyProperties(model, tempRole);
			retmodel.addAttribute("meg", "角色更新成功！");
		} else {
			retmodel.addAttribute("meg", "角色更新失败！");
		}
		retmodel.addAttribute("role", tempRole);
		return "sysmanage/role/editRole";
	}

	/**
	 * 加载权限树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadTree.action")
	public List<Map<String, Object>> loadTree(HttpServletRequest request) {
		String menuparent = request.getParameter("id");
		String checkstate = request.getParameter("checkstate");
		String privilege = request.getParameter("privilege");
		List<Map<String, Object>> sysmenuList = roleService.browseSysmenu(menuparent, privilege, checkstate);
		return sysmenuList;

	}
}
