package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.MyBeanUtils;
import com.fantastic.RespJsonPageData;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.MD5;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompanyAdmin;
import com.fs.web.service.RoleService;
import com.fs.web.service.T_company_adminService;

/**
 * 运营商管理员业务
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_company_admin")
public class T_company_adminController extends BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private T_company_adminService adminService;

	@Resource
	private RoleService roleService;

	@RequestMapping("/browseT_company_admin.action")
	/** 处理管理员表请求 */
	public String browseT_company_admin(HttpServletRequest request) {
		return "sysmanage/t_company_admin/browseAdmin";
	}

	@ResponseBody
	@RequestMapping("/listT_company_admin.action")
	/** jqgrid组件列表-查询订单列表 */
	public RespJsonPageData getCompanyAdminList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<TCompanyAdmin> adminList = adminService.browseAdminList(jqGridPager, p);
		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(adminList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddAdmin.action")
	public String preAddAdmin(@ModelAttribute("model") TCompanyAdmin model, Model retmodel) {
		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());

		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_company_admin/addAdmin";
	}

	/**
	 * 添加运营商管理员
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addAdmin.action")
	public String addAdmin(@ModelAttribute("model") TCompanyAdmin model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("loginname", model.getLoginname());
		List<TCompanyAdmin> adminList = adminService.getAdminList(p);
		if (adminList.size() > 0) {
			retmodel.addAttribute("meg", "该管理员名称已存在！");
			return "sysmanage/t_company_admin/addAdmin";
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getLoginname());
		List<Sysuser> sysuserList = userService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/t_company_admin/addAdmin";
		}

		model.setCompanyid(currentUser().getCompanyid());// 当前运营商ID
		model.setAdmintype(2);// 运营商管理员
		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getLoginpwd())) {
			model.setLoginpwd(MD5.MD5Encode(model.getLoginpwd()));
		}
		// 执行添加
		if (adminService.insertAdmin(model)) {

			insertSysLog("添加", "运营商管理员", "成功", currentUser().getLoginname() + "添加运营商管理员成功！");
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/t_company_admin/addAdmin";
	}

	/**
	 * 删除运营商管理员
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delAdmin.action")
	public String delAdmin(@ModelAttribute("model") TCompanyAdmin model) throws Exception {
		if (model.getSid() != null) {
			if (adminService.delAdmin(model.getSid())) {

				insertSysLog("删除", "运营商管理员", "成功", currentUser().getLoginname() + "删除运营商管理员成功！");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 进入修改运营商管理员页面
	 *
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/PreEditYunAdmin.action")
	public String PreEditYunAdmin(@ModelAttribute("model") TCompanyAdmin model, Model retmodel) {
		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_company_admin/editAdmin";
	}

	/**
	 * 修改运营商管理员信息
	 *
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateYunAdmin.action")
	public String updateYunAdmin(@ModelAttribute("model") TCompanyAdmin model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TCompanyAdmin admin = adminService.selectByPrimaryKey(model.getSid());
		// 用户名称不能重复
		if (StringUtils.isNotEmpty(model.getLoginname()) && !admin.getLoginname().equals(model.getLoginname())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("loginname", model.getLoginname());
			List<TCompanyAdmin> adminList = adminService.getAdminList(p);
			if (adminList.size() > 0) {
				retmodel.addAttribute("meg", "该管理员名称已存在！");
				return "sysmanage/t_company_admin/editAdmin";
			}
		}
		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getLoginpwd())) {
			model.setLoginpwd(MD5.MD5Encode(model.getLoginpwd()));
		}
		// model属性复制给hotel
		MyBeanUtils.copyBeanNotNull2Bean(model, admin);
		// 执行修改
		if (adminService.updateByPrimaryKey(admin)) {
			MyBeanUtils.copyBeanNotNull2Bean(admin, model);
			retmodel.addAttribute("meg", "该酒店信息修改成功！");
		} else {
			retmodel.addAttribute("meg", "该酒店信息修改失败！");
		}
		retmodel.addAttribute("t_company_admin", admin);

		return "sysmanage/t_company_admin/editAdmin";
	}

}
