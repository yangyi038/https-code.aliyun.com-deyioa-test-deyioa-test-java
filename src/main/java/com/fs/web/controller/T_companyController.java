package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.MyBeanUtils;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.MD5;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.THotelGroup;
import com.fs.web.service.RoleService;
import com.fs.web.service.T_companyService;

/**
 * 运营商管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_company")
public class T_companyController extends BaseController {

	@Resource
	private RoleService roleService;

	@Resource
	private T_companyService companyService;

	/** 处理浏览运营商列表请求 */
	@RequestMapping("/browseCompany.action")
	public String browseCompany() {
		return "sysmanage/t_company/browseCompany";
	}

	/** jqgrid组件列表查询运营商 */
	@ResponseBody
	@RequestMapping("/listCompany.action")
	public String listCompany(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		List<TCompany> companyList = companyService.browseCompany(jqGridPager, null);
		try {
			JqGridTool.jqGridQuery(request, response, companyList, jqGridPager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回Json格式数据
		return null;
	}

	/** 进入新增页面 */
	@RequestMapping("/preAddCompany.action")
	public String preAddCompany(Model retmodel) {
		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_company/addCompany";
	}

	/** 处理新增运营商请求 */
	@RequestMapping("/addCompany.action")
	public String addSysuser(@ModelAttribute("model") TCompany model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("companyname", model.getCompanyname());
		List<TCompany> companyList = companyService.getCompanyList(p);
		if (companyList.size() > 0) {
			retmodel.addAttribute("meg", "该运营商名已经存在！");
			return "sysmanage/t_company/addCompany";
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getCompanyname());
		List<Sysuser> sysuserList = userService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/t_company/addCompany";
		}

		// 新增运营商
		if (companyService.insertCompany(model)) {

			insertSysLog("添加", "运营商", "成功", currentUser().getLoginname() + "添加运营商成功");
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/t_company/addCompany";
	}

	/**
	 * 处理删除运营商请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delCompany.action")
	public String delCompany(@ModelAttribute("model") TCompany model) throws Exception {
		if (model.getSid() != null) {
			// 删除运营商
			if (companyService.delCompany(model.getSid())) {

				insertSysLog("删除", "运营商", "成功", currentUser().getLoginname() + "删除运营商成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 进入修改运营商页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preEditCompany.action")
	public String preEditCompany(@ModelAttribute("model") TCompany model, Model retmodel) {

		// 修改运营商信息
		TCompany company = companyService.getCompanyInfo(model.getSid());
		retmodel.addAttribute("t_company", company);

		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_company/editCompany";
	}

	/**
	 * 修改运营商
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/editCompany.action")
	public String editCompany(@ModelAttribute("model") TCompany model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {

		TCompany company = companyService.getCompanyInfo(model.getSid());
		if (company == null) {
			retmodel.addAttribute("meg", "该运营商不存在，请检查！");
			return "sysmanage/t_company/editCompany";
		}

		if (StringUtils.isNotBlank(model.getCompanyname())
				&& !model.getCompanyname().equals(company.getCompanyname())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("companyname", model.getCompanyname());
			List<TCompany> list = companyService.getCompanyList(p);
			if (list.size() > 0) {
				retmodel.addAttribute("meg", "该运营商名已经存在！");
				return "sysmanage/t_company/editCompany";
			}
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getCompanyname());
		List<Sysuser> sysuserList = userService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/t_company/addCompany";
		}

		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getLoginpwd())) {
			model.setLoginpwd(MD5.MD5Encode(model.getLoginpwd()));
		}
		// model属性复制给company
		MyBeanUtils.copyBeanNotNull2Bean(model, company);
		// 执行修改
		if (companyService.updateCompany(company)) {
			MyBeanUtils.copyBeanNotNull2Bean(company, model);

			insertSysLog("修改", "运营商", "成功", currentUser().getLoginname() + "修改运营商成功");
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("t_company", company);
		return "sysmanage/t_company/editCompany";
	}

}
