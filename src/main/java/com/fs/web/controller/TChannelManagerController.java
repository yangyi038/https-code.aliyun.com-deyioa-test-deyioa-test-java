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
import com.fs.comm.model.Channel;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TApplication;
import com.fs.web.service.ChannelService;
import com.fs.web.service.ColumnService;
import com.fs.web.service.SysLogService;;

/**
 * 直播频道管理
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_channel")
public class TChannelManagerController extends BaseController {

	@Resource
	private ColumnService columnService;


	@Resource
	private ChannelService channelService;

	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/channel_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_AppManager() {
		return "webmanage/t_channel/index_channel";
	}

	/**
	 * 浏览直播频道列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listChannel.action")
	// jqgrid组件列表查询系统 用户
	public String listT_AppManager(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<Map<String, Object>> t_appManager = channelService.getAllChannelInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_appManager, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 进入添加直播频道页面 */
	@RequestMapping("/showChannel.action")
	public String showT_AppManager(@ModelAttribute("model") TApplication model, Model retmodel) {
		/*
		 * List<String> columnList = columnService.selectColumnClassify();
		 * retmodel.addAttribute("columnList", columnList);
		 */
		List<Channel> list = channelService.queryAllChannelNumber();
		retmodel.addAttribute("list", list);
		return "webmanage/t_channel/add_channel";
	}

	/** 添加直播频道 */
	@RequestMapping("/add_channel.action")
	public String addT_AppManager(@ModelAttribute("model") Channel model, Model retmodel) {
		List<Channel> list = channelService.queryAllChannelNumber();
		if (list != null && list.size() > 0) {
			Integer no = model.getNumber();
			for (Channel channel : list) {
				Integer number = channel.getNumber();
				if (number.intValue() == no.intValue()) {
					retmodel.addAttribute("errorMeg", "该频道号已存在,请重新输入！");
					return "webmanage/t_channel/add_channel";
				}
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		
		// 添加
		boolean flag = channelService.addChannel(model);

		SysLog sysLog = this.getSysLog();

		if (flag) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("直播频道");
		sysLog.setOperdes("添加直播频道" + model.getNumber());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_channel/add_channel";
	}

	/**
	 * 删除直播视频
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_channel.action")
	public Map<String, Object> delAppManager(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		int result = channelService.delChannelById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除直播频道" + number);
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
	@RequestMapping("/onlineChannel.action")
	public Map<String, Object> onlineChannel(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		String status = request.getParameter("status");
		if ("未审核".equals(status)) {
			return ActionUtil.ajaxReturn(1, "", "");
		}
		int result = channelService.onlineChannel(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("上线");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("上线直播频道" + number);
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
	@RequestMapping("/unlineChannel.action")
	public Map<String, Object> unlineChannel(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		int result = channelService.unlineChannel(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("下线");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("下线直播频道" + number);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxSuccess("下线失败", "");
		} else {
			return ActionUtil.ajaxSuccess("下线成功", "");
		}

	}

	/**
	 * 审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agreeChannel.action")
	public Map<String, Object> agreeChannel(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		int result = channelService.agreeChannel(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("审核");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("审核直播频道" + number);
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
	 * 取消审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unAgreeChannel.action")
	public Map<String, Object> unAgreeChannel(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		int result = channelService.unAgreeChannel(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("取消审核");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("取消审核直播频道" + number);
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

	// ** 处理装载系统用户请求 *//*
	@RequestMapping("/showEditChannelWindow")
	public String showEditChannelWindow(@ModelAttribute("model") Channel model, Model retmodel) {

		Channel channelInfo = channelService.getChannelById(model.getId());
		retmodel.addAttribute("channelInfo", channelInfo);
		return "webmanage/t_channel/edit_channel";

	}

	/** 处理装载系统用户请求 */
	@RequestMapping("/updateChannel.action")
	public String updateChannel(@ModelAttribute("model") Channel model, Model retmodel) {
		int result = channelService.updateChannelById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("直播频道");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改直播频道" + model.getNumber());
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
		return "webmanage/t_channel/edit_channel";
	}

	/** 查看直播频道信息详情 */
	@RequestMapping("/queryChannelInfo.action")
	public String queryChannelInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			Channel channelInfo = channelService.getChannelById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = sdf.format(channelInfo.getCreateDate());
			channelInfo.setTempTime(createtime);
			retmodel.addAttribute("channelInfo", channelInfo);
		}

		return "webmanage/t_channel/channel_info";
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
}
