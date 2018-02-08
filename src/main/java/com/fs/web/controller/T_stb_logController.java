package com.fs.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.RespJsonPageData;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.TStbLog;
import com.fs.web.service.T_stbService;
import com.fs.web.service.T_stb_logService;

/**
 * 机顶盒日志管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_stb_log")
public class T_stb_logController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private T_stbService t_stbService;
	
	@Resource
	private T_stb_logService t_stb_logService;

	@RequestMapping("/browseT_stb_log.action")
	/** 浏览日志列表请求 */
	public String browseT_stb_log(HttpServletRequest request) {
		return "webmanage/t_stb_log/browseStbLog";
	}

	/**获取机顶盒日志列表 */
	@ResponseBody
	@RequestMapping("/listT_stb_log.action")
	public RespJsonPageData getStbLogList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		//获取日志列表
		List<TStbLog> logList = t_stb_logService.browseStbLogList(jqGridPager, p);
		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(logList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
	
	


}
