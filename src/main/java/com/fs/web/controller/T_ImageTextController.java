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
import com.fs.comm.model.Image;
import com.fs.comm.model.ImageText;
import com.fs.comm.model.ImageVideo;
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
public class T_ImageTextController extends BaseController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private ImageTextService imageTextService;

	@Resource
	private ImageTextTemplateService imageTextTemplateService;

	@RequestMapping("/imageText_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_image() {
		return "webmanage/t_image_text/browseImageText";
	}

	/**
	 * 浏览图文信息列表
	 */
	@ResponseBody
	@RequestMapping("/listT_imageText.action")
	/** jqgrid组件列表查询系统 用户 */
	public String listT_imageText(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<Map<String, Object>> t_picture = imageTextService.getAllImageTextInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_picture, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 进入添加图文信息页面 */
	@RequestMapping("/addImageTextPre.action")
	public String addImageTextPre(@ModelAttribute("model") ImageText model, Model retmodel) {
		// 模板列表
		retmodel.addAttribute("templateList", getImageTextTemplateList());
		return "webmanage/t_image_text/addImageText";
	}

	/** 添加图文信息 */
	@RequestMapping("/addImageText.action")
	public String addImageText(@ModelAttribute("model") ImageText model, Model retmodel) {

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());

		// 添加
		boolean result = imageTextService.addImageText(model);

		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}
		sysLog.setOpertype("添加");
		sysLog.setOperobject("图文信息");
		sysLog.setOperdes("添加图文信息" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_image_text/addImageText";
	}

	/**
	 * 根据Id删除图文信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_imageText.action")
	public Map<String, Object> delImageText(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("删除失败", "");
		}

		int result = imageTextService.delImageTextById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除图文信息" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
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
	@RequestMapping("/agreeImageText.action")
	public Map<String, Object> agreeImageText(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("审核失败", "");
		}

		int result = imageTextService.agreeImageTextById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("审核");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("审核图文信息" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("审核失败", "");
		} else {
			return ActionUtil.ajaxSuccess("审核成功", "");
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
	@RequestMapping("/unImageText.action")
	public Map<String, Object> unAgreeImageText(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("取消审核失败", "");
		}

		int result = imageTextService.unAgreeImageTextById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("取消审核");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("取消审核图文信息" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("取消审核失败", "");
		} else {
			return ActionUtil.ajaxSuccess("取消审核成功", "");
		}

	}

	/**
	 * 根据ID上线
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/onlineImageText.action")
	public Map<String, Object> onlineImageText(HttpServletRequest request, HttpServletResponse response,
			Model retmodel) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("上线失败", "");
		}

		if ("未审核".equals(status)) {
			return ActionUtil.ajaxReturn(1, "未审核不能上线", "");
		}
		int result = imageTextService.agreeOnlineById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("上线");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("上线图文信息" + name);
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
	 * 根据ID上线
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unlineImageText.action")
	public Map<String, Object> unOnlineImageText(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("下线失败", "");
		}

		int result = imageTextTemplateService.unAgreeOnlineById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("下线");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("下线图文信息" + name);
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
	 * 根據ID查詢信息并返回修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/editImageTextPre.action")
	public String editImageTextPre(@ModelAttribute("model") ImageText model, Model retmodel) {
		Integer id = model.getId();
		ImageText imageText = imageTextService.queryImageTextbyId(id);
		retmodel.addAttribute("imageText", imageText);
		return "/webmanage/t_image_text/edit_image";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatetImageWindow.action")
	public String updatetImageWindow(@ModelAttribute("model") ImageText model, Model retmodel) {

		int result = imageTextService.updateImageTextbyId(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("下线");
		sysLog.setOperobject("图文信息");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("下线图文信息" + model.getName());
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
		return "/webmanage/t_image_text/edit_image";
	}

	/** 查询详情 */
	@RequestMapping("/queryImageInfo.action")
	public String queryImageInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			ImageText imageText = imageTextService.queryImageTextbyId(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tempTime = sdf.format(imageText.getCreateTime());
			imageText.setTempDate(tempTime);
			retmodel.addAttribute("imageText", imageText);
		}
		return "/webmanage/t_image_text/image_info";
	}

	/*************************************
	 * 视频 start
	 *********************************************************/

	@ResponseBody
	@RequestMapping("/listT_imageVideo.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listT_imageVideo(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);

		List<Map<String, Object>> t_picture = imageTextService.getImageVideoByImageTextId(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_picture, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/** 跳转添加视频页面 */
	@RequestMapping("/show_pictureInfo.action")
	public String videoInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		retmodel.addAttribute("id", id);
		return "/webmanage/t_image_text/video_index";
	}

	/** 跳转添加视频页面 */
	@RequestMapping("/show_addVideo.action")
	public String show_addVideo(@ModelAttribute("model") ImageVideo model, Model retmodel) {
		/* String id= request.getParameter("id"); */
		retmodel.addAttribute("model", model);
		return "/webmanage/t_image_text/add_video";
	}

	/** 添加视频 */
	@RequestMapping("/addVideo.action")
	public String addVideo(@ModelAttribute("model") ImageVideo model, Model retmodel) {
		int result = imageTextService.addVideo(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("添加");
		sysLog.setOperobject("视频");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("添加视频" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "/webmanage/t_image_text/add_video";
	}

	/** 跳转修改视频页面 */
	@RequestMapping("/show_editVideo.action")
	public String show_editVideo(@ModelAttribute("model") ImageText model, Model retmodel) {
		/* String id= request.getParameter("id"); */
		retmodel.addAttribute("model", model);
		return "/webmanage/t_image_text/edit_video";
	}

	/** 修改视频 */
	@RequestMapping("/editVideo.action")
	public String editVideo(@ModelAttribute("model") ImageVideo model, Model retmodel) {
		int result = imageTextService.editVideo(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("视频");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改视频" + model.getName());
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
		return "/webmanage/t_image_text/edit_video";
	}

	/** 删除视频 */
	@ResponseBody
	@RequestMapping("/del_video.action")
	public Map<String, Object> delVideo(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = imageTextService.delVideo(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("视频");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除视频" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("删除成功", "");
		} else {
			return ActionUtil.ajaxFail("删除失败", "");
		}
	}

	/*************************************
	 * 视频 end
	 *********************************************************/

	/*************************************
	 * 图片 start
	 *********************************************************/
	
	

	/** 删除缩略图 */
	@ResponseBody
	@RequestMapping("/del_image.action")
	public Map<String, Object> del_image(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = imageTextService.delImage(Integer.parseInt(id));
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("缩略图");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除缩略图" + name);

		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("删除成功", "");
		} else {
			return ActionUtil.ajaxFail("删除失败", "");
		}
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
	 * ================== 缩略图 pzj add =======================================
	 */

	/** 跳转浏览缩略图列表 */
	@RequestMapping("/thumbnailIndex.action")
	public String thumbnailIndex(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		retmodel.addAttribute("id", id);
		return "/webmanage/t_image_text/thumbnail_index";
	}
	
	/**
	 * 浏览缩略图列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listThumbnail.action")
	public String listThumbnail(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);

		List<Map<String, Object>> t_image = imageTextService.getImageByImageTextId(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_image, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 跳转添加缩略图页面 */
	@RequestMapping("/addThumbnailPre.action")
	public String addThumbnailPre(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		retmodel.addAttribute("id", id);
		return "/webmanage/t_image_text/add_thumbnail";
	}

	/** 添加缩略图 */
	@RequestMapping("/addThumbnail.action")
	public String addThumbnail(@ModelAttribute("model") Image model, Model retmodel, HttpServletRequest request,
			@RequestParam(value = "tipic", required = false) MultipartFile tipic)
			throws IllegalStateException, IOException {

		//图片原名称
		model.setFileName(tipic.getOriginalFilename());
		if (tipic != null && tipic.getSize() > 0) {
			//国际化配置中的存储路径
			ResourceBundle resb = ResourceBundle.getBundle("messages_zh_CN");
			String path = resb.getString("imgpath") + "picture/";
			FileTool.createDir(path);
			String fileName = Tools.getRndFilename() + Tools.getFileExtName(tipic.getOriginalFilename());
			Tools.saveFilemvc(path, fileName, tipic);
			//图片路径
			model.setImageUrl(path+fileName);
		}
		
		int result = imageTextService.addImage(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("添加");
		sysLog.setOperobject("缩略图");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("添加缩略图" + model.getName());

		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "/webmanage/t_image_text/add_thumbnail";
	}
}
