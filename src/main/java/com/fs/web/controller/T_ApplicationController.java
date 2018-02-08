package com.fs.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.FileTool;
import com.framework.util.ServletBeanTools;
import com.framework.util.Tools;
import com.fs.comm.model.AppEditions;
import com.fs.comm.model.TApplication;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.T_ApplicationService;
import com.fs.web.service.ColumnService;
import com.fs.web.service.SysLogService;

/**
 * 应用管理
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_application")
public class T_ApplicationController extends BaseController {

	@Resource
	private ColumnService columnService;

	@Resource
	private SysLogService sysLogService;

	@Resource
	private T_ApplicationService applicationService;

	@RequestMapping("/application_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_AppManager() {
		return "webmanage/t_application/browserApplication";
	}

	/**
	 * 浏览应用列表
	 */
	@ResponseBody
	@RequestMapping("/listT_appManager.action")
	// jqgrid组件列表查询系统 用户
	public String listT_AppManager(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<Map<String, Object>> t_appManager = applicationService.getAllAppManagerInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_appManager, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入添加应用页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showApplication.action")
	public String showT_AppManager(@ModelAttribute("model") TApplication model, Model retmodel) {
		/*
		 * List<String> columnList = columnService.selectColumnClassify();
		 * retmodel.addAttribute("columnList", columnList);
		 */
		return "webmanage/t_application/add_application";
	}

	/** 添加应用 */
	@RequestMapping("/add_appManager.action")
	public String addT_AppManager(@ModelAttribute("model") TApplication model, Model retmodel) {

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());

		boolean flag = applicationService.insertApp(model);

		SysLog sysLog = this.getSysLog();
		if (flag) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("应用列表");
		sysLog.setOperdes("添加应用列表" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_application/add_application";
	}

	/**
	 * 删除应用列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_appManager")
	public Map<String, Object> delAppManager(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = applicationService.delAppManagerById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("应用列表");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除应用列表" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
		}

	}

	/**
	 * 上线
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/onlineAppmanager.action")
	public Map<String, Object> onlineAppmanager(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = applicationService.onlineAppManager(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("上线");
		sysLog.setOperobject("应用列表");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("上线应用列表" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("上线失败", "");
		} else {
			return ActionUtil.ajaxSuccess("上线成功", "");
		}

	}

	/**
	 * 下线
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unlineAppmanager.action")
	public Map<String, Object> unlineAppmanager(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = applicationService.unlineAppManager(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("下线");
		sysLog.setOperobject("应用列表");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("下线应用列表" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("下线失败", "");
		} else {
			return ActionUtil.ajaxSuccess("下线成功", "");
		}

	}

	/**
	 * 下架
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/undercarriageAppManager.action")
	public Map<String, Object> undercarriageAppManager(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = applicationService.undercarriageAppManager(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("下架");
		sysLog.setOperobject("应用列表");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("下架应用列表" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("下架失败", "");
		} else {
			return ActionUtil.ajaxSuccess("下架成功", "");
		}

	}

	/**
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditAppWindow.action")
	public String showColumnWindow(@ModelAttribute("model") TApplication model, Model retmodel) {

		TApplication appManager = applicationService.queryAppManagerById(model.getId());
		List<String> list = applicationService.queryAllParameterType();
		retmodel.addAttribute("appManager", appManager);
		retmodel.addAttribute("list", list);
		return "webmanage/t_application/edit_application";

	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatAppManager.action")
	public String updatetImageWindow(@ModelAttribute("model") TApplication model, Model retmodel) {

		int result = applicationService.updateAppManagerById(model);
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("应用列表");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改应用列表" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		return "webmanage/t_application/edit_application";
	}

	/** 查看应用列表详情 */
	@RequestMapping("/queryAppInfo.action")
	public String queryAppInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			TApplication appManager = applicationService.queryAppManagerById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date onlineTime = appManager.getOnlineTime();
			if (null != onlineTime) {
				String tempTime = sdf.format(onlineTime);
				appManager.setTempTime(tempTime);
			}

			retmodel.addAttribute("appManager", appManager);
		}

		return "webmanage/t_application/application_info";
	}

	/**
	 * 删除应用列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_appEditions.action")
	public Map<String, Object> delAppEditions(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = applicationService.delAppEditionsById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("应用版本");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除应用版本" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
		}

	}

	@RequestMapping("/showEditAppEditionsWindow.action")
	public String showEditAppEditionsWindow(@ModelAttribute("model") AppEditions model, Model retmodel) {
		AppEditions appEditions = applicationService.queryAppEditionsById(model.getId());
		List<TApplication> list = applicationService.queryAllName();
		retmodel.addAttribute("appEditions", appEditions);
		retmodel.addAttribute("list", list);
		return "webmanage/t_application/edit_appEditions";

	}

	/**
	 * 修改应用版本
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatAppEditions.action")
	public String updatAppEditions(@ModelAttribute("model") AppEditions model, Model retmodel) {

		int result = applicationService.updateAppEditionsById(model);
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("应用版本");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改应用版本" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		return "webmanage/t_application/edit_appEditions";
	}

	

	// 设置登录信息
	private SysLog getSysLog() {
		SysLog sysLog = new SysLog();
		sysLog.setDotime(new Date());
		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		sysLog.setUsername(sysuser.getLoginname());
		return sysLog;
	}

	/**
	 * ============================ 应用版本
	 * ==============================================
	 */

	/**
	 * 跳转应用版本列表
	 * 
	 * @param request
	 * @param response
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/appVersionPre.action")
	public String appVersionPre(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		retmodel.addAttribute("id", id);
		retmodel.addAttribute("name", name);
		return "webmanage/t_application/browseAppVersion";
	}

	/**
	 * 浏览应用版本列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listAppVersion.action")
	public String listAppVersion(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		List<Map<String, Object>> t_appEditions = applicationService.getAllAppEditionsInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_appEditions, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入添加应用版本页面
	 */
	@RequestMapping("/addAppVersionPre.action")
	public String addAppVersionPre(@ModelAttribute("model") AppEditions model, Model retmodel) {
		/* List<AppManager> list = applicationService.queryAllName(); */
		retmodel.addAttribute("model", model);
		return "webmanage/t_application/add_appVersion";
	}

	/** 添加应用版本信息 */
	@RequestMapping("/addAppVersion.action")
	public String addAppVersion(@ModelAttribute("model") AppEditions model, Model retmodel,
			@RequestParam(value = "tipic", required = false) MultipartFile tipic) {

		// 包原名称
		model.setPackageName(tipic.getOriginalFilename());
		if (tipic != null && tipic.getSize() > 0) {
			// 国际化配置中的存储路径
			ResourceBundle resb = ResourceBundle.getBundle("messages_zh_CN");
			String path = resb.getString("imgpath") + "picture/";
			FileTool.createDir(path);
			String fileName = Tools.getRndFilename() + Tools.getFileExtName(tipic.getOriginalFilename());
			try {
				Tools.saveFilemvc(path, fileName, tipic);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 包路径
			model.setDownloadUrl(path + fileName);
		}
		boolean flag = applicationService.insertAppEditions(model);

		SysLog sysLog = this.getSysLog();
		if (flag) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}
		sysLog.setOpertype("添加");
		sysLog.setOperobject("应用版本");
		sysLog.setOperdes("添加应用版本" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_application/add_appVersion";
	}

	/** 查看应用版本 */
	@RequestMapping("/getAppVersionInfo.action")
	public String getAppVersionInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			AppEditions appEditions = applicationService.queryAppEditionsById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createTime = appEditions.getCreateTime();
			if (null != createTime) {
				String tempTime = sdf.format(createTime);
				appEditions.setTempTime(tempTime);
			}

			retmodel.addAttribute("appEditions", appEditions);
		}

		return "webmanage/t_application/view_appversion";
	}
}
