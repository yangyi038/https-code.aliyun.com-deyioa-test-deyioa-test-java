package com.fs.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.FileTool;
import com.framework.util.ServletBeanTools;
import com.framework.util.Tools;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.T_picture;
import com.fs.web.service.SysLogService;
import com.fs.web.service.T_pictureService;

/**
 * 图片管理
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_picture")
public class T_pictureManagerController extends BaseController {

	@Resource
	private T_pictureService pictureService;

	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/picture_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_picture() {
		return "webmanage/t_picture/index_picture";
	}

	/**
	 * 显示图片列表
	 */
	@ResponseBody
	@RequestMapping("/listT_picture.action")
	/** jqgrid组件列表查询系统 用户 */
	public String listT_Picture(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);

		List<Map<String, Object>> t_picture = pictureService.getAllPictureInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_picture, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 进入添加图片页面 */
	@RequestMapping("/addPicturePre.action")
	public String addPicturePre(@ModelAttribute("model") T_picture model, Model retmodel) {
		// 获取图片组名称列表(管理员，运营商，二级运营商，经销商)
		Map<String, Object> p=new HashMap<>();
		List<T_picture> picList = pictureService.selectPictureGroup(ViewDataCondition(p));
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picGroup = picList.get(i).getPictureGroup();
			if (set.add(picGroup)) {
				newList.add(picGroup);
			}
		}
		retmodel.addAttribute("picList", newList);

		return "webmanage/t_picture/add_picture";
	}

	/** 添加上传图片 */
	@RequestMapping("/addT_picture.action")
	public String addT_picture(@ModelAttribute("model") T_picture model, Model retmodel, HttpServletRequest request,
			@RequestParam(value = "tipic", required = false) MultipartFile tipic)
			throws IllegalStateException, IOException {
		
		if (StringUtils.isNotBlank(model.getNewPictureGroup())) {
			model.setPictureGroup(model.getNewPictureGroup());
		}
		String oldPictureName = tipic.getOriginalFilename();
		model.setOldPictureName(oldPictureName);
		if (tipic != null && tipic.getSize() > 0) {
			//国际化配置中的存储路径
			ResourceBundle resb = ResourceBundle.getBundle("messages_zh_CN");
			String path = resb.getString("imgpath") + "picture/";
			FileTool.createDir(path);
			String fileName = Tools.getRndFilename() + Tools.getFileExtName(tipic.getOriginalFilename());
			Tools.saveFilemvc(path, fileName, tipic);
			model.setPictureName(fileName);
			//图片路径
			model.setPic_url(path+fileName);
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());

		// 添加图片
		boolean flag = pictureService.insertPicture(model);
		SysLog sysLog = new SysLog();
		if (flag) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}
		sysLog.setDotime(new Date());
		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		sysLog.setOpertype("添加");
		sysLog.setOperobject("图片管理");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		sysLog.setUsername(sysuser.getLoginname());
		sysLog.setOperdes("添加图片信息" + oldPictureName);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "webmanage/t_picture/add_picture";
	}

	/** 查看图片详情 */
	@RequestMapping("/query_picture.action")
	public String queryT_picture(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			T_picture picture = pictureService.queryPictureById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uploadTime = sdf.format(picture.getUploadTime());
			picture.setTempTime(uploadTime);
			retmodel.addAttribute("picture", picture);
		}

		return "webmanage/t_picture/picture_info";
	}

	@ResponseBody
	@RequestMapping("/delPicturet.action")
	public Map<String, Object> updateRecaccount(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pictureName = request.getParameter("pictureName");
		int result = pictureService.delPictureById(id);

		SysLog sysLog = new SysLog();
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setDotime(new Date());
		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		sysLog.setOpertype("删除");
		sysLog.setOperobject("图片管理");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		sysLog.setUsername(sysuser.getLoginname());
		sysLog.setOperdes("删除图片信息" + pictureName);

		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return ActionUtil.ajaxSuccess("状态更新成功", "");
		} else {
			return ActionUtil.ajaxFail("状态更新失败", "");
		}

	}

	@ResponseBody
	@RequestMapping("/agreePicturet.action")
	public Map<String, Object> agree(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pictureName = request.getParameter("pictureName");
		int result = pictureService.agreePictureById(id);

		SysLog sysLog = new SysLog();
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setDotime(new Date());
		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		sysLog.setOpertype("审核");
		sysLog.setOperobject("图片管理");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		sysLog.setUsername(sysuser.getLoginname());
		sysLog.setOperdes("审核图片信息" + pictureName);

		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("审核通过", "");
		} else {
			return ActionUtil.ajaxFail("审核失败", "");
		}

	}

	@ResponseBody
	@RequestMapping("/unAgreePicturet.action")
	public Map<String, Object> unAgree(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pictureName = request.getParameter("pictureName");
		int result = pictureService.unAgreePictureById(id);

		SysLog sysLog = new SysLog();
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setDotime(new Date());
		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		sysLog.setOpertype("删除");
		sysLog.setOperobject("图片管理");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		sysLog.setUsername(sysuser.getLoginname());
		sysLog.setOperdes("取消审核图片信息" + pictureName);

		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("取消审核成功", "");
		} else {
			return ActionUtil.ajaxFail("取消审核失败", "");
		}

	}

	/** 处理装载系统用户请求 */
	@RequestMapping("/updatePicture.action")
	public String updatePicture(@ModelAttribute("model") T_picture model, Model retmodel) {
		
		Map<String,Object> p=new HashMap<>();
		T_picture picture = pictureService.queryPictureById(model.getId());
		List<T_picture> picList = pictureService.selectPictureGroup(ViewDataCondition(p));
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picGroup = picList.get(i).getPictureGroup();
			if (set.add(picGroup)) {
				newList.add(picGroup);
			}
		}
		retmodel.addAttribute("picture", picture);
		retmodel.addAttribute("picList", newList);
		return "webmanage/t_picture/edit_picture";
	}
}
