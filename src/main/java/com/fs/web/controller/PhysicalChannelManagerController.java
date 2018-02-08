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
import com.fs.comm.model.PhysicalChannel;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.ChannelService;
import com.fs.web.service.PhysicalChannelService;
import com.fs.web.service.SysLogService;;

/**
 * 应用管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/t_physical_channel")
public class PhysicalChannelManagerController {

	@Resource
	private ChannelService channelService;
	
	@Resource
	private PhysicalChannelService physicalChannelService;
	
	@Resource
	private SysLogService sysLogService;
	
	@RequestMapping("/physicalChannel_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_physical_channel(HttpServletRequest request, HttpServletResponse response,Model retmodel) {
		String id= request.getParameter("id");
		String number= request.getParameter("number");
		retmodel.addAttribute("id", id);
		retmodel.addAttribute("number", number);
		return "sysmanage/t_physical_channel/index";
	}


	/** 处理上传图片请求 */
	@RequestMapping("/showPhysicalChannel.action")
	public String showT_AppManager(@ModelAttribute("model") PhysicalChannel model, Model retmodel) {
		
		retmodel.addAttribute("model", model);
		return "sysmanage/t_physical_channel/add_physical_channel";
	}
	
	/** 处理上传图片请求 */
	@RequestMapping("/add_PhysicalChannel.action")
	public String addPhysicalChannel(@ModelAttribute("model")PhysicalChannel model, Model retmodel) {
		/*Channel channel = channelService.getChannelByNumber(model.getChannelNumber());
		model.setChannelId(channel.getId());*/
		boolean flag = physicalChannelService.addPhysicalChannel(model);
		
		SysLog sysLog = this.getSysLog();
		
		if(flag){
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
			sysLog.setOperresult("失败");
		}
		
		sysLog.setOpertype("添加");
		sysLog.setOperobject("物理频道");
		sysLog.setOperdes("添加物理频道"+model.getChannelNumber());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sysmanage/t_physical_channel/add_physical_channel";
	}
	
	
	@ResponseBody
	@RequestMapping("/listPhsicalChannel.action")
	//** jqgrid组件列表查询系统 用户 *//*
	public String listT_AppManager(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		
		List<Map<String, Object>> t_appManager = physicalChannelService.getAllPhysicalChannelInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request,response,t_appManager,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除直播视频
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_phsicalChannel.action")
	 public Map<String, Object> delAppManager(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		String channelNumber= request.getParameter("channelNumber");
		int result = physicalChannelService.delPhsicalChannelById(Integer.parseInt(id));
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("删除物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result<1){
			return ActionUtil.ajaxSuccess("删除失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("删除成功", ""); 
		}
		
	 }
	
	/**
	 * 激活
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activateChannel.action")
	 public Map<String, Object> activateChannel(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		String channelNumber= request.getParameter("channelNumber");
		int result = physicalChannelService.activateChannel(Integer.parseInt(id));
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("激活");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("激活物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result<1){
			return ActionUtil.ajaxSuccess("激活失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("激活成功", ""); 
		}
		
	 }
	
	/**
	 * 停止
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stopChannel.action")
	 public Map<String, Object> stopChannel(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = physicalChannelService.stopChannel(Integer.parseInt(id));
		
		String channelNumber= request.getParameter("channelNumber");
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("停止");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("停止物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result<1){
			return ActionUtil.ajaxSuccess("停止失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("停止成功", ""); 
		}
		
	 }
	
	/**
	 * 有效
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/effective.action")
	 public Map<String, Object> effectiveChannel(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = physicalChannelService.effectiveChannel(Integer.parseInt(id));
		
		String channelNumber= request.getParameter("channelNumber");
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("有效");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("有效物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result<1){
			return ActionUtil.ajaxSuccess("设定失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("设定成功", ""); 
		}
		
	 }
	
	/**
	 * 无效
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unEffective.action")
	 public Map<String, Object> unEffectiveChannel(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = physicalChannelService.unEffectiveChannel(Integer.parseInt(id));
		
		String channelNumber= request.getParameter("channelNumber");
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("无效");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("无效物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result<1){
			return ActionUtil.ajaxSuccess("设定无效失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("设定无效成功", ""); 
		}
		
	 }
	
	
	/**
	 * 废弃
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/scrapChannel.action")
	 public Map<String, Object> scrapChannel(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = physicalChannelService.scrapChannel(Integer.parseInt(id));
		
		String channelNumber= request.getParameter("channelNumber");
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("废弃");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("废弃物理频道"+channelNumber);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result<1){
			return ActionUtil.ajaxSuccess("废弃失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("废弃成功", ""); 
		}
		
	 }
	
	
	//** 处理装载系统用户请求 *//*
	@RequestMapping("/showPhsicalChannelWindow.action" )
	public String showEditChannelWindow(HttpServletRequest request){
		String id = request.getParameter("id");
		PhysicalChannel physicalChannel = physicalChannelService.getPhysicalChannelById(Integer.parseInt(id));
		request.setAttribute("physicalChannel", physicalChannel);
		return "sysmanage/t_physical_channel/edit_physical_channel";
		
	}
	
	
	/** 处理装载系统用户请求 */
	@RequestMapping("/updatePhsicalChannel.action" )
	public String updateChannel(@ModelAttribute( "model" ) PhysicalChannel model,Model retmodel){
		int result = physicalChannelService.updatePhysicalChannelById(model);
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("物理频道");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("修改物理频道"+model.getChannelNumber());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result>0){ 
			retmodel.addAttribute("meg", "修改成功！");  
		}else{ 
			retmodel.addAttribute("meg", "修改失败！");  
		}
		return "sysmanage/t_physical_channel/edit_physical_channel";
	}
	
	
	/** 查看直播频道信息详情 */
	@RequestMapping("/queryPhysicalChannel.action")
	public String queryPhysicalChannel(HttpServletRequest request,HttpServletResponse response,Model retmodel) {
		String id= request.getParameter("id");
		if(!"".equals(id)||null!=id){
			PhysicalChannel physicalChannel = physicalChannelService.getPhysicalChannelById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = sdf.format(physicalChannel.getCreateTime());
			physicalChannel.setTempTime(createtime);
			retmodel.addAttribute("physicalChannel", physicalChannel);
		}
		
		return "sysmanage/t_physical_channel/physical_channel_info";
	}
	
	//设置登录信息
		private SysLog getSysLog(){
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
