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
import com.fs.comm.enums.RoleType;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TComOperator;
import com.fs.web.service.RoleService;
import com.fs.web.service.T_com_operatorService;

/**
 * 二级运营商
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_com_operator")
public class T_com_operatorController extends BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private T_com_operatorService operatorService;

	@Resource
	private RoleService roleService;

	/**
	 * 浏览请求
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/browseT_com_operator.action")
	public String browseT_com_operator(HttpServletRequest request) {
		return "sysmanage/t_com_operator/browseOperator";
	}

	/**
	 * 浏览二级运营商列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listT_com_operator.action")
	public RespJsonPageData getOperatorList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<TComOperator> adminList = operatorService.browseOperatorList(jqGridPager, p);
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
	@RequestMapping("/preAddOperator.action")
	public String preAddOperator(@ModelAttribute("model") TComOperator model, Model retmodel) {
		Map<String, Object> map = new HashMap<>();
		// 过滤适用二级运营商的角色
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		map.put("touser", RoleType.二级运营商.getRoleValue());

		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_com_operator/addOperator";
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addOperator.action")
	public String addOperator(@ModelAttribute("model") TComOperator model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", model.getName());
		List<TComOperator> adminList = operatorService.getOperatorList(p);
		if (adminList.size() > 0) {
			retmodel.addAttribute("meg", "该二级运营商名称已存在！");
			return "sysmanage/t_com_operator/addOperator";
		}

		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getName());
		List<Sysuser> sysuserList = userService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/t_com_operator/addOperator";
		}

		model.setCompanyid(currentUser().getCompanyid());// 所属运营商ID
		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getPwd())) {
			model.setPwd(MD5.MD5Encode(model.getPwd()));
		}
		// 执行添加
		if (operatorService.insertOperator(model)) {

			insertSysLog("添加", "二级运营商", "成功", currentUser().getLoginname() + "添加二级运营商成功！");
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/t_com_operator/addOperator";
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delOperator.action")
	public String delOperator(@ModelAttribute("model") TComOperator model) throws Exception {
		if (model.getSid() != null) {
			if (operatorService.delOperator(model.getSid())) {

				insertSysLog("删除", "二级运营商", "成功", currentUser().getLoginname() + "删除二级运营商成功！");
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
	@RequestMapping("/PreEditOperator.action")
	public String PreEditOperator(@ModelAttribute("model") TComOperator model, Model retmodel) {
		TComOperator operator=operatorService.selectByPrimaryKey(model.getSid());
		retmodel.addAttribute("t_com_operator", operator);
		
		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_com_operator/editOperator";
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
	@RequestMapping("/updateOperator.action")
	public String updateOperator(@ModelAttribute("model") TComOperator model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TComOperator admin = operatorService.selectByPrimaryKey(model.getSid());
		// 用户名称不能重复
		if (StringUtils.isNotEmpty(model.getName()) && !admin.getName().equals(model.getName())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("name", model.getName());
			List<TComOperator> adminList = operatorService.getOperatorList(p);
			if (adminList.size() > 0) {
				retmodel.addAttribute("meg", "该运营商名称已存在！");
				return "sysmanage/t_com_operator/editOperator";
			}
		}

		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getPwd())) {
			model.setPwd(MD5.MD5Encode(model.getPwd()));
		}
		// model属性复制给hotel
		MyBeanUtils.copyBeanNotNull2Bean(model, admin);
		// 执行修改
		if (operatorService.updateOperator(admin)) {
			MyBeanUtils.copyBeanNotNull2Bean(admin, model);
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("t_com_operator", admin);

		return "sysmanage/t_com_operator/editOperator";
	}

}
