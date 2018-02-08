package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.Tree;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.DateTool;
import com.fs.comm.model.Department;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.DepartmentService;

/**
 * 组织机构管理controller
 * 
 * @author dyl
 *
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController {
	@Resource
	private DepartmentService departmentService;

	@RequestMapping("/browseDepartment.action")
	/** 处理浏览组织机构请求 */
	public String browseDepartment() {
		return "sysmanage/department/browseDepartment";
	}

	/**
	 * 浏览组织机构树状图
	 */
	@ResponseBody
	@RequestMapping("/listDepartment.action")
	public String listDepartment(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		List<Department> departmentList = departmentService.browseDepartment(jqGridPager, null);
		try {
			JqGridTool.jqGridQuery(request, response, departmentList, jqGridPager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回Json格式数据
		return null;
	}
	
	/** 进入新增页面 */
	@RequestMapping("/preAddDepartment.action")
	public String preAddDepartment(@ModelAttribute("model") Department model, Model retmodel) {
		Department tempDepartment = null;
		tempDepartment = departmentService.loadDepartment(model.getId());
		retmodel.addAttribute("department", tempDepartment);
		return "sysmanage/department/addDepartment";
	}

	/** 处理新增组织机构请求 */
	@RequestMapping("/addDepartment.action")
	public String addDepartment(@ModelAttribute("model") Department model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("depname", model.getDepname());
		p.put("depparent", model.getDepparent());
		List<Department> departmentList = departmentService.browseDepartment(p);
		if (departmentList.size() > 0) {
			retmodel.addAttribute("meg", "组织机构名称已存在！");
			return "sysmanage/department/addDepartment";
		}
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		model.setFlag(1);
		model.setOperatetime(DateTool.getTimestamp());
		model.setOperator(sysuser.getLoginname());
		Department depparent = departmentService.loadDepartment(model.getDepparent());
		model.setLevels(depparent.getLevels() + 1);
		if (departmentService.saveDepartment(model)) {// 调用业务逻辑组件保存新增的组织机构
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		retmodel.addAttribute("department", model);
		return "sysmanage/department/addDepartment";
	}

	/**
	 * 处理删除组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delDepartment.action")
	public String delDepartment(@ModelAttribute("model") Department model) throws Exception {
		String actionMsg = "";
		if (model.getId() != null) {
			if (model.getId() == 1) {
				return "基础组织机构不能删除！";
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("depparent", model.getId());
			int i = departmentService.countDepartment(p);
			if (i > 0) {
				return "该组织机构有子组织机构,不能删除！";
			}
			if (departmentService.delDepartment(model.getId())) {// 调用业务逻辑组件删除指定的组织机构
				actionMsg = "删除成功";
			} else {
				actionMsg = "删除失败";
			}
		} else {
			actionMsg = "删除失败";
		}
		return actionMsg;
	}

	/**
	 * 处理查看组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/viewDepartment.action")
	public Department viewDepartment(@ModelAttribute("model") Department model) throws Exception {
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的新闻
			Department tempDepartment = departmentService.loadDepartment(model.getId());
			if (tempDepartment != null) {
				if (tempDepartment.getDepparent() != null) {
					Department pDepartment = departmentService.loadDepartment(tempDepartment.getDepparent());
					tempDepartment.setParentDepartment(pDepartment);
				}
				return tempDepartment;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	

	/** 进入修改组织机构页面 */
	@RequestMapping("/editDepartment.action")
	public String editDepartment(@ModelAttribute("model") Department model, Model retmodel) {
		Department tempDepartment = departmentService.loadDepartment(model.getId());
		if (tempDepartment.getDepparent() != null) {
			Department pDepartment = departmentService.loadDepartment(tempDepartment.getDepparent());
			tempDepartment.setParentDepartment(pDepartment);
		}
		retmodel.addAttribute("department", tempDepartment);
		return "sysmanage/department/editDepartment";
	}

	/**
	 * 处理更新组织机构请求
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/updateDepartment.action")
	public String updateDepartment(@ModelAttribute("model") Department model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("depname", model.getDepname());
		where.put("notid", model.getId());
		List<Department> departmentList = departmentService.browseDepartment(where);
		if (departmentList.size() > 0) {
			retmodel.addAttribute("meg", "组织机构名称已存在！");
			return "sysmanage/department/editDepartment";
		}
		if (departmentService.updateDepartment(model)) {// 调用业务逻辑组件更新指定的组织机构
			retmodel.addAttribute("meg", "组织机构更新成功！");
		} else {
			retmodel.addAttribute("meg", "组织机构更新失败！");
		}
		if (model.getDepparent() != null) {
			Department pDepartment = departmentService.loadDepartment(model.getDepparent());
			model.setParentDepartment(pDepartment);
		}
		retmodel.addAttribute("department", model);
		return "sysmanage/department/editDepartment";
	}

	/** 加载组织机构树 */
	@ResponseBody
	@RequestMapping("/loadTree.action")
	public List<Map<String, Object>> loadTree(HttpServletRequest request) {
		String depparent = request.getParameter("id");
		String level = request.getParameter("level");
		Map<String, Object> where = new HashMap<String, Object>();
		if (depparent == null) {
			String isall = request.getParameter("isall");
			if (isall != null && !isall.equals("true") && !isall.equals("null")) {
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
				where.put("id", sysuser.getDepartment().getId());
			} else {
				where.put("id", 1);
			}
		} else {
			where.put("depparent", depparent);
		}
		List<Department> departmentList = departmentService.browseDepartment(where);
		try {
			List<Map<String, Object>> retlist = Tree.BuildTree(departmentList, level == null ? "" : level);
			return retlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
