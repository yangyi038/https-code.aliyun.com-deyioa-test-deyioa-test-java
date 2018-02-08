package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.MyBeanUtils;
import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.DateTool;
import com.framework.util.MD5;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.RoleService;
import com.fs.web.service.SysuserService;

/**
 * 系统用户管理controller
 * 
 * @author dyl
 *
 */
@Controller
@RequestMapping("/admin/sysuser")
public class SysuserController extends BaseController{
	@Resource
	private SysuserService sysuserService;
	@Resource
	private RoleService roleService;

	@RequestMapping("/browseSysuser.action")
	/** 处理浏览新闻请求 */
	public String browseSysuser() {
		return "sysmanage/sysuser/browseSysuser";
	}

	@ResponseBody
	@RequestMapping("/listSysuser.action")
	/** jqgrid组件列表查询系统 用户 */
	public String listSysuser(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		List<Sysuser> sysuserList = sysuserService.browseSysuser(jqGridPager, null);
		try {
			JqGridTool.jqGridQuery(request, response, sysuserList, jqGridPager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回Json格式数据
		return null;
	}

	/** 进入新增页面 */
	@RequestMapping("/preAddSysuser.action")
	public String preAddSysuser(Model retmodel) {
		Map<String, Object> map=new HashMap<>();
		map.put("companyid", currentUser().getCompanyid()==null? null:currentUser().getCompanyid());
		
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/sysuser/addSysuser";
	}

	/** 处理新增系统用户请求 */
	@RequestMapping("/addSysuser.action")
	public String addSysuser(@ModelAttribute("model") Sysuser model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("loginname", model.getLoginname());
		List<Sysuser> sysuserList = sysuserService.browseSysuser(p);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已经存在！");
			return "sysmanage/sysuser/addSysuser";
		}
		
		model.setLogintimes(0);
		model.setLastlogin(DateTool.getTimestamp());
		model.setIsclose(1);
		model.setDepid(1);
		model.setAdmintype(0);// 系统管理员
		// 登录密码MD5加密
		if (StringUtils.isNotBlank(model.getLoginpwd())) {
			model.setLoginpwd(MD5.MD5Encode(model.getLoginpwd()));
		}

		// 添加管理员
		if (sysuserService.saveSysuser(model)) {
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/sysuser/addSysuser";
	}

	// public String getRandomId(String pid){
	// int r=(int)(Math.random()*1000);
	// String id=pid+r;
	// int num=service.countSysuser("select count(*) from Sysuser where
	// id='"+id+"'");
	// if(num>0){
	// getRandomId(pid);
	// }
	// return id;
	// }
	//
	/**
	 * 处理删除系统用户请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delSysuser.action")
	public String delSysuser(@ModelAttribute("model") Sysuser model) throws Exception {
		if (model.getId() != null) {
			Sysuser sysuser=sysuserService.loadSysuser(model.getId());
			//删除
			if (sysuserService.delSysuser(sysuser)){
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 处理注销系统用户请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/logofforno.action")
	public Map<String, Object> logofforno(@ModelAttribute("model") Sysuser model) throws Exception {
		if (model.getId() != null) {
			Sysuser sysuser = (Sysuser) sysuserService.loadSysuser(model.getId());
			sysuser.setIsclose(model.getIsclose());
			if (sysuserService.updateSysuser(sysuser)) {
				return ActionUtil.ajaxSuccess("注销成功", "");
			} else {
				return ActionUtil.ajaxFail("注销失败", "");
			}
		} else {
			return ActionUtil.ajaxFail("注销失败", "");
		}
	}

	/**
	 * 处理查看系统用户请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/viewSysuser.action")
	public Sysuser viewSysuser(@ModelAttribute("model") Sysuser model) throws Exception {
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的新闻
			Sysuser tempSysuser = sysuserService.loadSysuser(model.getId());
			if (tempSysuser != null) {
				return tempSysuser;

			} else {

				return null;
			}
		} else {
			return null;
		}
	}

	/** 处理装载系统用户请求 */
	@RequestMapping("/editSysuser.action")
	public String editSysuser(@ModelAttribute("model") Sysuser model, Model retmodel) {
		Sysuser tempSysuser = sysuserService.loadSysuser(model.getId());
		retmodel.addAttribute("sysuser", tempSysuser);
		
		Map<String, Object> map=new HashMap<>();
		map.put("companyid", currentUser().getCompanyid()==null? null:currentUser().getCompanyid());
		List<Role> roles = roleService.browseRole(map);
		retmodel.addAttribute("roles", roles);
		return "sysmanage/sysuser/editSysuser";
	}

	/**
	 * 处理更新系统用户请求
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/updateSysuser.action")
	public String updateSysuser(@ModelAttribute("model") Sysuser model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("loginname", model.getLoginname());
		where.put("notid", model.getId());//非当前ID的用户
		List<Sysuser> sysuserList = sysuserService.browseSysuser(where);
		if (sysuserList.size() > 0) {
			retmodel.addAttribute("meg", "用户名已存在！");
			return "sysmanage/sysuser/editSysuser";
		}
		
		Sysuser tempSysuser = sysuserService.loadSysuser(model.getId());
		MyBeanUtils.copyBeanNotNull2Bean(model, tempSysuser);
		
		if (model.getLoginpwd() != null && model.getLoginpwd().length() > 0) {
			tempSysuser.setLoginpwd(MD5.MD5Encode(model.getLoginpwd()));
		}
		
		if (sysuserService.updateSysuser(tempSysuser)) {// 调用业务逻辑组件更新指定的系统用户
			MyBeanUtils.copyBeanNotNull2Bean(tempSysuser, model);
			
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("sysuser", tempSysuser);
		return "sysmanage/sysuser/editSysuser";
	}

	@RequestMapping("/toupdatePsw.action")
	public String toupdatePsw(Model retmodel) {
		return "sysmanage/sysuser/toupdatePsw";

	}

	/**
	 * 更新密码
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updatePsw.action")
	public String updatePsw(HttpServletRequest request, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		String password = request.getParameter("loginpwd");
		String password1 = request.getParameter("loginpwd1");
		if (!password.equals(password1)) {
			retmodel.addAttribute("meg", "前后密码输入不一致！");
		} else {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
			boolean issue = sysuserService.updatePsw(MD5.MD5Encode(password1), sysuser.getId());
			if (issue) {// 调用业务逻辑组件更新指定的系统用户
				retmodel.addAttribute("meg", "密码更新成功！");
			} else {
				retmodel.addAttribute("meg", "密码更新失败！");
			}
		}
		return "sysmanage/sysuser/toupdatePsw";
	}
}
