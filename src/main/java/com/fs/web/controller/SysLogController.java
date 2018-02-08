package com.fs.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.RespJsonPageData;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.web.service.SysLogService;


/**
 * 用户操作日志
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/sysLog")
public class SysLogController extends BaseController {
	
	@Resource
	private SysLogService sysLogService;
	
	@RequestMapping("/sysLog_manager.action")
	/** 处理浏览 用户表请求 */
	public String sysLog() {
		return "sysmanage/syslog/index_syslog";
	}
	
	/** jqgrid组件列表-获取日志列表 */
	@ResponseBody
	@RequestMapping("/list_sysLog.action")
	public RespJsonPageData getStbList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<Map<String, Object>> list = sysLogService.getAllSysLogInfo(jqGridPager, p);
		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(list, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
}
