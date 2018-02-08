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
import com.fs.comm.model.TComDealer;
import com.fs.web.service.RoleService;
import com.fs.web.service.T_com_dealerService;

/**
 * 运营商管理员业务
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_com_dealer")
public class T_com_dealerController extends BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private T_com_dealerService dealerService;

	@Resource
	private RoleService roleService;

	/**
	 * 浏览经销商列表请求
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/browseT_com_dealer.action")
	public String browseT_com_dealer(HttpServletRequest request) {
		return "sysmanage/t_com_dealer/browseDealer";
	}

	/**
	 * 浏览经销商列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listT_com_dealer.action")
	public RespJsonPageData getComDealerList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<TComDealer> list = dealerService.browseDealerList(jqGridPager, p);
		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(list, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddDealer.action")
	public String preAddDealer(@ModelAttribute("model") TComDealer model, Model retmodel) {
		Map<String, Object> p = new HashMap<>();
		// 过滤适用经销商的角色
		p.put("touser", RoleType.经销商.getRoleValue());
		p = ViewDataCondition(p);

		List<Role> roles = roleService.browseRole(p);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_com_dealer/addDealer";
	}

	/**
	 * 添加经销商
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addDealer.action")
	public String addDealer(@ModelAttribute("model") TComDealer model, Model retmodel) {
		// 检查经销商名称是否已经存在
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", model.getName());
		List<TComDealer> adminList = dealerService.getDealerList(p);
		if (adminList.size() > 0) {
			retmodel.addAttribute("meg", "该经销商名称已存在！");
			return "sysmanage/t_com_dealer/addDealer";
		}
		// 检查用户名是否已存在
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getName());
		List<Sysuser> sysuserList = userService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/t_com_dealer/addDealer";
		}

		model.setCompanyid(currentUser().getCompanyid());// 当前一级运营商ID
		model.setOperatorid(currentUser().getOperatorid());// 当前二级运营商ID
		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getPwd())) {
			model.setPwd(MD5.MD5Encode(model.getPwd()));
		}
		// 执行添加
		if (dealerService.insertDealer(model)) {

			insertSysLog("添加", "运营商管理员", "成功", currentUser().getLoginname() + "添加运营商管理员成功！");
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/t_com_dealer/addDealer";
	}

	/**
	 * 删除运营商管理员
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delDealer.action")
	public String delDealer(@ModelAttribute("model") TComDealer model) throws Exception {
		if (model.getSid() != null) {
			if (dealerService.delDealer(model.getSid())) {

				insertSysLog("删除", "经销商", "成功", currentUser().getLoginname() + "删除经销商成功！");
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
	@RequestMapping("/PreEditDealer.action")
	public String PreEditDealer(@ModelAttribute("model") TComDealer model, Model retmodel) {
		TComDealer dealer=dealerService.selectByPrimaryKey(model.getSid());
		retmodel.addAttribute("t_com_dealer", dealer);
		
		Map<String, Object> map = new HashMap<>();
		map.put("companyid", currentUser().getCompanyid() == null ? null : currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/t_com_dealer/editDealer";
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
	@RequestMapping("/updateDealer.action")
	public String updateDealer(@ModelAttribute("model") TComDealer model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TComDealer admin = dealerService.selectByPrimaryKey(model.getSid());
		// 用户名称不能重复
		if (StringUtils.isNotEmpty(model.getName()) && !admin.getName().equals(model.getName())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("name", model.getName());
			List<TComDealer> adminList = dealerService.getDealerList(p);
			if (adminList.size() > 0) {
				retmodel.addAttribute("meg", "该管理员名称已存在！");
				return "sysmanage/t_com_dealer/editDealer";
			}
		}

		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getPwd())) {
			model.setPwd(MD5.MD5Encode(model.getPwd()));
		}
		// model属性复制给hotel
		MyBeanUtils.copyBeanNotNull2Bean(model, admin);
		// 执行修改
		if (dealerService.updateByPrimaryKey(admin)) {
			MyBeanUtils.copyBeanNotNull2Bean(admin, model);
			retmodel.addAttribute("meg", "该经销商信息修改成功！");
		} else {
			retmodel.addAttribute("meg", "该经销商信息修改失败！");
		}
		retmodel.addAttribute("t_com_dealer", admin);

		return "sysmanage/t_com_dealer/editDealer";
	}

}
