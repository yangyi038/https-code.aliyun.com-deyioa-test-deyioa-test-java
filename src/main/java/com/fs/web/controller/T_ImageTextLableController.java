package com.fs.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.Column;
import com.fs.comm.model.ImageLable;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.ImageTextService;
import com.fs.web.service.ImageTextTemplateService;
import com.fs.web.service.SysLogService;

/**
 * 图文信息管理
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_imageText")
public class T_ImageTextLableController extends BaseController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private ImageTextService imageTextService;

	@Resource
	private ImageTextTemplateService imageTextTemplateService;

	@RequestMapping("/imageTextLable_manager.action")
	/** 跳转图文信息模板页面 */
	public String T_imageLable() {
		return "webmanage/t_image_lable/index_image_template";
	}

	/**
	 * 浏览图文模板列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listT_imageLable.action")
	// jqgrid组件列表查询系统 用户
	public String listT_imageLable(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<Map<String, Object>> t_picture = imageTextTemplateService.getAllImageLableInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_picture, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 进入添加图文模板页面 */
	@RequestMapping("/showImageTextLable.action")
	public String showImageTextLable(@ModelAttribute("model") Column model, Model retmodel) {
		/*
		 * List<String> columnList = columnService.selectColumnClassify();
		 * retmodel.addAttribute("columnList", columnList);
		 */
		return "webmanage/t_image_lable/add_image_lable";
	}

	/** 添加图文模板 */
	@RequestMapping("/addImageTextLable.action")
	public String addImageTextLable(@ModelAttribute("model") ImageLable model, Model retmodel) {

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());

		// 添加
		boolean result = imageTextTemplateService.addImageLable(model);

		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("图文信息模板");
		sysLog.setOperdes("添加图文信息模板" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_image_lable/add_image_lable";
	}

	/**
	 * 根据Id删除图文信息模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delImageLable.action")
	public Map<String, Object> delImageLable(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxSuccess("删除失败", "");
		}
		int result = imageTextTemplateService.delImageLableById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("图文信息模板");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除图文信息模板" + name);
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
	 * 根据ID取消审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unAgreeImageLable.action")
	public Map<String, Object> unAgreeImageLable(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxSuccess("取消审核失败", "");
		}
		int result = imageTextTemplateService.unArgeeImageLableById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("取消审核");
		sysLog.setOperobject("图文信息模板");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("取消审核图文信息模板" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("取消审核失败", "");
		} else {
			return ActionUtil.ajaxSuccess("取消审核成功", "");
		}

	}

	/**
	 * 根据ID通过审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agreeImageLable.action")
	public Map<String, Object> agreeImageLable(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxSuccess("审核失败", "");
		}

		int result = imageTextTemplateService.agreeImageLableById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("审核");
		sysLog.setOperobject("图文信息模板");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("审核图文信息模板" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("审核失败", "");
		} else {
			return ActionUtil.ajaxSuccess("审核成功", "");
		}

	}

	/**
	 * 根據ID查詢信息
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditWindow.action")
	public String showEditWindow(@ModelAttribute("model") ImageLable model, Model retmodel) {
		Integer id = model.getId();
		ImageLable imageLable = imageTextTemplateService.queryImageLableById(id);
		retmodel.addAttribute("imageLable", imageLable);
		return "webmanage/t_image_lable/edit_image_lable";
	}

	/** 处理装载系统用户请求 */
	@RequestMapping("/updateImageLable.action")
	public String updateImageLable(@ModelAttribute("model") ImageLable model, Model retmodel) {
		int result = imageTextTemplateService.updateImageLableById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("图文信息模板");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改图文信息模板" + model.getName());
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
		return "webmanage/t_image_lable/edit_image_lable";
	}

	/** 查询图文信息模板详情 */
	@RequestMapping("/queryInageLableInfo.action")
	public String queryInageLableInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			ImageLable imageLable = imageTextTemplateService.queryImageLableById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tempTime = sdf.format(imageLable.getCreateTime());
			imageLable.setTempTime(tempTime);
			retmodel.addAttribute("imageLable", imageLable);
		}
		return "webmanage/t_image_lable/image_lable_info";
	}

	/*****************************************************************************************/
	@RequestMapping("/showAttribute.action")
	/** 处理浏览 用户表请求 */
	public String T_attribute() {
		return "webmanage/t_image_lable/attribute";
	}

	/** 处理上传图片请求 */
	@RequestMapping("/showAddAttributeWindow.action")
	public String showAddAttributeWindow(@ModelAttribute("model") ImageLable model, Model retmodel) {
		retmodel.addAttribute("imageLable", model);
		return "webmanage/t_image_lable/add_attribute";
	}

	/** 处理上传图片请求 *//*
					 * @RequestMapping("/addAttribute.action") public String
					 * addAttribute(@ModelAttribute("model") Tattribute model,
					 * Model retmodel) { boolean result =
					 * imageTextService.addTattribute(model); if(result){
					 * retmodel.addAttribute("meg", "新增成功！"); }else{
					 * retmodel.addAttribute("meg", "新增失败！"); }
					 * 
					 * return "webmanage/t_image_lable/add_image_lable"; }
					 */

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
}
