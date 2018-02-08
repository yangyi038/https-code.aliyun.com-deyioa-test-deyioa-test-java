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
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.Title;
import com.fs.web.service.SysLogService;
import com.fs.web.service.TtitleService;

/**
 * 字幕管理
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_title")
public class TitleManagerController extends BaseController {
	
	@Resource
	private TtitleService titleService;
	
	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/title_manager.action")
	/** 处理浏览 用户表请求 */
	public String browseT_user() {
		return "webmanage/t_title/index_title";
	}

	/**
	 * 浏览字幕列表
	 */
	@ResponseBody
	@RequestMapping("/listT_title.action")
	/** jqgrid组件列表查询系统 用户 */
	public String listT_Title(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<Map<String, Object>> t_Title = titleService.getAllTitleInfo(jqGridPager,p);
		try {
			JqGridTool.jqGridQuery(request,response,t_Title,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** 进入添加字幕页面*/
	@RequestMapping("/showT_title.action")
	public String showT_picture(@ModelAttribute("model") Title model, Model retmodel) {
		return "webmanage/t_title/add_title";
	}
	
	/** 添加字幕 */
	@RequestMapping("/addT_title.action")
	public String addT_picture(@ModelAttribute("model") Title model, Model retmodel) {
		
		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		//添加
		boolean flag = titleService.insertTitle(model);
		
		SysLog sysLog = this.getSysLog();
		if(flag){
			retmodel.addAttribute("meg", "新增成功！"); 
			sysLog.setOperresult("成功");
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
			sysLog.setOperresult("失败");
		}
		sysLog.setOpertype("添加");
		sysLog.setOperobject("字幕管理");
		sysLog.setOperdes("添加字幕"+model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "webmanage/t_title/add_title";
	}
	
	
	@ResponseBody
	@RequestMapping("/delTitle.action")
	 public Map<String, Object> updateRecaccount(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		String name= request.getParameter("name");
		int result = titleService.delTitleById(id);
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("字幕管理");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("删除字幕"+name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result>0){
			return ActionUtil.ajaxSuccess("状态更新成功", ""); 
		}else{
			return ActionUtil.ajaxFail("状态更新失败", ""); 
		}
		
	 }
	
	@ResponseBody
	@RequestMapping("/agreeTitle.action")
	 public Map<String, Object> agree(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		String name= request.getParameter("name");
		int result = titleService.agreeTitleById(id);
		
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("审核");
		sysLog.setOperobject("字幕管理");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("审核字幕"+name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result>0){
			return ActionUtil.ajaxSuccess("审核通过", ""); 
		}else{
			return ActionUtil.ajaxFail("审核失败", ""); 
		}
		
	 }
	
	@ResponseBody
	@RequestMapping("/unAgreeTitle.action")
	 public Map<String, Object> unAgree(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = titleService.unAgreeTitleById(id);
		String name= request.getParameter("name");
		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("取消审核");
		sysLog.setOperobject("字幕管理");
		sysLog.setOperresult(result>0?"成功":"失败");
		sysLog.setOperdes("取消审核字幕"+name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result>0){
			return ActionUtil.ajaxSuccess("取消成功", ""); 
		}else{
			return ActionUtil.ajaxFail("取消失败", ""); 
		}
		
	 }
	
	/** 处理装载系统用户请求 */
	@RequestMapping("/showUpdateWindow.action" )
	public String showUpdateWindow(@ModelAttribute( "model" ) Title model,Model retmodel){
			Integer id = model.getId();
			Title title = titleService.queryTitleById(id);
			retmodel.addAttribute("title", title);
			return "webmanage/t_title/edit_title";
	}
	
	/** 处理装载系统用户请求 */
	@RequestMapping("/updateTitle.action" )
	public String updateTitle(@ModelAttribute( "model" ) Title model,Model retmodel){
			int result = titleService.updateTitleById(model);
			
			SysLog sysLog = this.getSysLog();
			sysLog.setOpertype("修改");
			sysLog.setOperobject("字幕管理");
			sysLog.setOperresult(result>0?"成功":"失败");
			sysLog.setOperdes("修改字幕"+model.getName());
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
			return "webmanage/t_title/edit_title";
	}
	
	/** 查看详情 */
	@RequestMapping("/queryTitleInfo.action" )
	public String queryTitleInfo(HttpServletRequest request,HttpServletResponse response,Model retmodel){
		String id= request.getParameter("id");
		if(!"".equals(id)||null!=id){
			Title title = titleService.queryTitleById(Integer.parseInt(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tempTime = sdf.format(title.getCreateDate());
			title.setTempTime(tempTime);
			retmodel.addAttribute("title", title);
		}
		
			return "webmanage/t_title/title_info";
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
