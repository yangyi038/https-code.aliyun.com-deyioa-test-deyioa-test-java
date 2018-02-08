package com.fs.web.controller;

import java.text.ParseException;
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
import com.fs.comm.model.Streaming;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.StreamingService;
import com.fs.web.service.SysLogService;

/**
 * 流媒体配置
 * 
 * @author pzj
 */
@Controller
@RequestMapping("/admin/t_streaming")
public class StreamingConfigurationController extends BaseController {

	@Resource
	private StreamingService streamingService;

	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/streaming.action")
	/** 处理浏览 用户表请求 */
	public String T_index() {
		return "webmanage/t_streaming/index_streaming";
	}

	/**
	 * 浏览流媒体配置列表
	 */
	@ResponseBody
	@RequestMapping("/listStreaming.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listFixing(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<Map<String, Object>> list = streamingService.getAllStreamingInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, list, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showAddString.action")
	public String showAddFixing(@ModelAttribute("model") Streaming model, Model retmodel) {
		return "webmanage/t_streaming/add_streaming";
	}

	/** 添加 流媒体配置*/
	@RequestMapping("/add_Streaming.action")
	public String addImageText(@ModelAttribute("model") Streaming model, Model retmodel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (model.getTempProductionData() != null) {
			try {
				Date productionData = sdf.parse(model.getTempProductionData());
				model.setProductionData(productionData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempStartTime() != null) {
			try {
				Date startTime = sdf.parse(model.getTempStartTime());
				model.setStartTime(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempOverTime() != null) {
			try {
				Date overData = sdf.parse(model.getTempOverTime());
				model.setOverTime(overData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempLastTime() != null) {
			try {
				Date lastTime = sdf.parse(model.getTempLastTime());
				model.setLastTime(lastTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		model.setCompanyId(currentUser().getCompanyid());
		boolean result = streamingService.addStreaming(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("流媒体配置");
		sysLog.setOperdes("添加流媒体配置" + model.getProgrammeNum());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_streaming/add_streaming";
	}

	/**
	 * 根据Id删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_streaming.action")
	public Map<String, Object> delFixing(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String programmeNum = request.getParameter("programmeNum");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("删除失败", "");
		}

		int result = streamingService.delStreamingById(Long.parseLong(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("流媒体配置");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除流媒体配置" + programmeNum);
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
	 * 根據ID查詢信息并返回修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditStreaming.action")
	public String showEditFixing(@ModelAttribute("model") Streaming model, Model retmodel) {
		Long id = model.getId();
		Streaming streaming = streamingService.queryStreamingById(id);
		retmodel.addAttribute("streaming", streaming);
		return "/webmanage/t_streaming/edit_streaming";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatetStreaming.action")
	public String updatetFixing(@ModelAttribute("model") Streaming model, Model retmodel) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (model.getTempProductionData() != null) {
			try {
				Date productionData = sdf.parse(model.getTempProductionData());
				model.setProductionData(productionData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempStartTime() != null) {
			try {
				Date startTime = sdf.parse(model.getTempStartTime());
				model.setStartTime(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempOverTime() != null) {
			try {
				Date overTime = sdf.parse(model.getTempOverTime());
				model.setOverTime(overTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (model.getTempLastTime() != null) {
			try {
				Date LastTime = sdf.parse(model.getTempLastTime());
				model.setLastTime(LastTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int result = streamingService.updateStreamingById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("流媒体配置");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改流媒体配置" + model.getProgrammeNum());
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
		return "/webmanage/t_streaming/edit_streaming";
	}

	/**
	 * 查看
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/queryStreamingInfo.action")
	public String queryStreamingInfo(@ModelAttribute("model") Streaming model, Model retmodel) {
		Long id = model.getId();
		Streaming streaming = streamingService.queryStreamingById(id);
		retmodel.addAttribute("streaming", streaming);
		return "/webmanage/t_streaming/streaming_info";
	}

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
