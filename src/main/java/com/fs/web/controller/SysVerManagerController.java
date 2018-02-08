package com.fs.web.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.AdbDebugServer;
import com.fs.comm.model.Autoshutdown;
import com.fs.comm.model.LayoutSelectors;
import com.fs.comm.model.RealTimeControl;
import com.fs.comm.model.SysVer;
import com.fs.comm.model.UiPwd;
import com.fs.web.service.SysVerService;

@Controller
@RequestMapping("/admin/t_sysVer")
public class SysVerManagerController {
	
	@Resource
	private SysVerService sysVerService;
	
	/**
	 *请求到主页面
	 * @return 
	 */
	@RequestMapping("/sysVer_manager.action")
	public String toSysVer() {
		return "sysmanage/t_sysVer/index";
	}
	
	/**
	 * 请求到添加页面
	 * @return
	 */
	@RequestMapping("/show_addSysVer.action")
	public String toAddSysParameter(Model retmodel) {
		
		return "sysmanage/t_sysVer/add_sysVer";
	}
	
	/** 添加*/
	@RequestMapping("/addT_sysVer.action")
	public String addSysParameter(@ModelAttribute("model")SysVer model, Model retmodel) {
		int result = sysVerService.insertSysVer(model);
		if(result>0){
			retmodel.addAttribute("meg", "新增成功！"); 
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
		}
		return "sysmanage/t_sysVer/add_sysVer";
	}
	
	@ResponseBody
	@RequestMapping("/listSysVer.action")
	//** jqgrid组件列表查询系统 用户 *//*
	public String listSysVer(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		
		List<Map<String, Object>> list = sysVerService.getAllSysVerInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request,response,list,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_SysVer.action")
	 public Map<String, Object> delSysParameter(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = sysVerService.delSysVerById(Long.parseLong(id));
		if(result<1){
			return ActionUtil.ajaxFail("删除失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("删除成功", ""); 
		}
		
	 }
	
	/**
	 *请求到修改面
	 * @return
	 */
	@RequestMapping("/showEditSysVerWindow.action")
	public String showUpdateWindow(@ModelAttribute( "model" ) SysVer model,Model retmodel) {
		Long id = model.getId();
		model = sysVerService.getSysVerById(id);
		
		retmodel.addAttribute("model", model);
		return "sysmanage/t_sysVer/edit_sysVer";
	}
	
	/**
	  * 修改
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/updatSysVer.action" )
	public String updatSysParameter(@ModelAttribute( "model" ) SysVer model,Model retmodel){
		
		int result = sysVerService.updateSysVerById(model);
		
		if(result>0){ 
			retmodel.addAttribute("meg", "修改成功！");  
		}else{ 
			retmodel.addAttribute("meg", "修改失败！");  
		}
		return "sysmanage/t_sysVer/edit_sysVer";
	}
	
	
	/**
	  * 查看
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/query_sysVer.action" )
	public String querySysVer(@ModelAttribute( "model" ) SysVer model,Model retmodel){
		
		Long id = model.getId();
		model = sysVerService.getSysVerById(id);
		retmodel.addAttribute("model", model);
		
		return "sysmanage/t_sysVer/sysVer_info";
	}
	
	/*************************************autoshutdown   start******************************************************/
	/**
	 *请求到主页面
	 * @return 
	 */
	@RequestMapping("/autoshutdown_manager.action")
	public String toAutoshutdown(HttpServletRequest request, HttpServletResponse response) {
		String code= request.getParameter("code");
		if("autoShutdown".equals(code)){
			return "sysmanage/t_sysVer/autoShutdown_index";
		}else if("layoutSelectors".equals(code)){
			return "sysmanage/t_sysVer/layoutSelectors_index";
		}else if("uiPwd".equals(code)){
			return "sysmanage/t_sysVer/uiPwd_index";
		}else if("adbDebugServer".equals(code)){
			return "sysmanage/t_sysVer/adbDebugServer_index";
		}else if("realTimeControl".equals(code)){
		
			return "sysmanage/t_sysVer/realTimeControl_index";
		}
		return null;
	}
	
	/**
	 * 请求到添加页面
	 * @return
	 */
	@RequestMapping("/show_addAutoShutdown.action")
	public String toAutoShutdown() {
		
		return "sysmanage/t_sysVer/add_autoShutdown";
	}
	
	/** 添加*/
	@RequestMapping("/addT_autoShutdown.action")
	public String addAutoShutdown(@ModelAttribute("model")Autoshutdown model, Model retmodel) {
		int result = sysVerService.insertAutoshutdown(model);
		if(result>0){
			retmodel.addAttribute("meg", "新增成功！"); 
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
		}
		return "sysmanage/t_sysVer/add_autoShutdown";
	}
	
	@ResponseBody
	@RequestMapping("/listAutoShutdown.action")
	//** jqgrid组件列表查询系统 用户 *//*
	public String listAutoShutdown(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		
		List<Map<String, Object>> list = sysVerService.getAllAutoshutdownInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request,response,list,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_autoShutdown.action")
	 public Map<String, Object> delAutoShutdown(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = sysVerService.delAutoshutdownById(Long.parseLong(id));
		if(result<1){
			return ActionUtil.ajaxFail("删除失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("删除成功", ""); 
		}
		
	 }
	
	
	/**
	 *请求到修改面
	 * @return
	 */
	@RequestMapping("/showEditAutoshutDownWindow.action")
	public String showUpdateWindow(@ModelAttribute( "model" ) Autoshutdown model,Model retmodel) {
		Long id = model.getId();
		model = sysVerService.getAutoshutdownById(id);
		
		retmodel.addAttribute("model", model);
		return "sysmanage/t_sysVer/edit_autoShutdown";
	}
	
	
	/**
	  * 修改
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/updateAutoShutdown.action" )
	public String updateAutoShutdown(@ModelAttribute( "model" ) Autoshutdown model,Model retmodel){
		
		int result = sysVerService.updateAutoshutdownById(model);
		
		if(result>0){ 
			retmodel.addAttribute("meg", "修改成功！");  
		}else{ 
			retmodel.addAttribute("meg", "修改失败！");  
		}
		return "sysmanage/t_sysVer/edit_autoShutdown";
	}
	
	/**
	  * 查看
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/showAutoshutDownInfo.action" )
	public String querySysVer(@ModelAttribute( "model" ) Autoshutdown model,Model retmodel){
		
		Long id = model.getId();
		model = sysVerService.getAutoshutdownById(id);
		retmodel.addAttribute("model", model);
		
		return "sysmanage/t_sysVer/autoShutdown_info";
	}
	
	/*************************************autoshutdown   end******************************************************/
	
	
	/*************************************layoutSelectors   start******************************************************/
	
	/**
	 * 请求到添加页面
	 * @return
	 */
	@RequestMapping("/show_addLayoutSelectors.action")
	public String toAddLayoutSelectors() {
		
		return "sysmanage/t_sysVer/add_layoutSelectors";
	}
	
	/** 添加*/
	@RequestMapping("/addT_LayoutSelectors.action")
	public String addLayoutSelectors(@ModelAttribute("model")LayoutSelectors model, Model retmodel) {
		int result = sysVerService.insertLayoutSelectors(model);
		if(result>0){
			retmodel.addAttribute("meg", "新增成功！"); 
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
		}
		return "sysmanage/t_sysVer/add_layoutSelectors";
	}
	
	@ResponseBody
	@RequestMapping("/listLayoutSelectors.action")
	//** jqgrid组件列表查询系统 用户 *//*
	public String listLayoutSelectors(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		
		List<Map<String, Object>> list = sysVerService.getAllLayoutSelectorsInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request,response,list,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_layoutSelectors.action")
	 public Map<String, Object> delLayoutSelectors(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = sysVerService.delLayoutSelectorsById(Long.parseLong(id));
		if(result<1){
			return ActionUtil.ajaxFail("删除失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("删除成功", ""); 
		}
		
	 }
	
	/**
	 *请求到修改面
	 * @return
	 */
	@RequestMapping("/showEditnLayoutSelectorsWindow.action")
	public String showEditnLayoutSelectorsWindow(@ModelAttribute( "model" ) LayoutSelectors model,Model retmodel) {
		Long id = model.getId();
		model = sysVerService.getLayoutSelectorsById(id);
		
		retmodel.addAttribute("model", model);
		return "sysmanage/t_sysVer/edit_layoutSelectors";
	}
	
	/**
	  * 修改
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/updateLayoutSelectors.action" )
	public String updateLayoutSelectors(@ModelAttribute( "model" ) LayoutSelectors model,Model retmodel){
		
		int result = sysVerService.updateLayoutSelectorsById(model);
		
		if(result>0){ 
			retmodel.addAttribute("meg", "修改成功！");  
		}else{ 
			retmodel.addAttribute("meg", "修改失败！");  
		}
		return "sysmanage/t_sysVer/edit_layoutSelectors";
	}
	
	
	/**
	  * 查看
	  * @param model
	  * @param retmodel
	  * @return
	  */
	@RequestMapping("/showlayoutSelectorsInfo.action" )
	public String querylayoutSelectorsInfo(@ModelAttribute( "model" ) LayoutSelectors model,Model retmodel){
		Long id = model.getId();
		model = sysVerService.getLayoutSelectorsById(id);
		
		retmodel.addAttribute("model", model);
		
		return "sysmanage/t_sysVer/layoutSelectors_info";
	}
	/*************************************layoutSelectors   end******************************************************/
	
	
	
	/*************************************uipwd   Start******************************************************/
	
	/**
	 * 请求到添加页面
	 * @return
	 */
	@RequestMapping("/show_addUiPwd.action")
	public String toAddUiPwd() {
		
		return "sysmanage/t_sysVer/add_uipwd";
	}
	
	
	/** 添加*/
	@RequestMapping("/addT_uiPwd.action")
	public String adduiPwd(@ModelAttribute("model")UiPwd model, Model retmodel) {
		int result = sysVerService.insertUiPwd(model);
		if(result>0){
			retmodel.addAttribute("meg", "新增成功！"); 
		}else{
			retmodel.addAttribute("meg", "新增失败！"); 
		}
		return "sysmanage/t_sysVer/add_uipwd";
	}
	
	@ResponseBody
	@RequestMapping("/listUiPwd.action")
	//** jqgrid组件列表查询系统 用户 *//*
	public String listUiPwd(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager=new JqGridPager(request); 
		Map<String,Object> p=ServletBeanTools.getParameterMap(request);
		
		List<Map<String, Object>> list = sysVerService.getAllUiPwdInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request,response,list,jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_uipwd.action")
	 public Map<String, Object> delUipwd(HttpServletRequest request,HttpServletResponse response){
		String id= request.getParameter("id");
		int result = sysVerService.delUiPwdById(Long.parseLong(id));
		if(result<1){
			return ActionUtil.ajaxFail("删除失败", ""); 
		}else{
			return ActionUtil.ajaxSuccess("删除成功", ""); 
		}
		
	 }
	 
	 /**
		 *请求到修改面
		 * @return
		 */
		@RequestMapping("/showEditnUiPwdWindow.action")
		public String showEditnUiPwdWindow(@ModelAttribute( "model" ) UiPwd model,Model retmodel) {
			Long id = model.getId();
			model = sysVerService.getUiPwdById(id);
			
			retmodel.addAttribute("model", model);
			return "sysmanage/t_sysVer/edit_uipwd";
		}
		
		/**
		  * 修改
		  * @param model
		  * @param retmodel
		  * @return
		  */
		@RequestMapping("/updateUiPwd.action" )
		public String updateUiPwd(@ModelAttribute( "model" ) UiPwd model,Model retmodel){
			
			int result = sysVerService.updateUiPwdById(model);
			
			if(result>0){ 
				retmodel.addAttribute("meg", "修改成功！");  
			}else{ 
				retmodel.addAttribute("meg", "修改失败！");  
			}
			return "sysmanage/t_sysVer/edit_uipwd";
		}
		
		/**
		  * 查看
		  * @param model
		  * @param retmodel
		  * @return
		  */
		@RequestMapping("/showUiPwdInfo.action" )
		public String showUiPwdInfo(@ModelAttribute( "model" ) UiPwd model,Model retmodel){
			Long id = model.getId();
			model = sysVerService.getUiPwdById(id);
			
			retmodel.addAttribute("model", model);
			
			return "sysmanage/t_sysVer/uipwd_info";
		}
	/*************************************uipwd   end******************************************************/
		
		
		
		
	/*************************************adbDebugServer   start******************************************************/
		
		/**
		 * 请求到添加页面
		 * @return
		 */
		@RequestMapping("/show_addadbDebugServer.action")
		public String toAddAdbDebugServer() {
			
			return "sysmanage/t_sysVer/add_adbDebugServer";
		}	
		
		/** 添加*/
		@RequestMapping("/addAdbDebugServer.action")
		public String addAdbDebugServer(@ModelAttribute("model")AdbDebugServer model, Model retmodel) {
			int result = sysVerService.insertAdbDebugServer(model);
			if(result>0){
				retmodel.addAttribute("meg", "新增成功！"); 
			}else{
				retmodel.addAttribute("meg", "新增失败！"); 
			}
			return "sysmanage/t_sysVer/add_adbDebugServer";
		}
		
		
		@ResponseBody
		@RequestMapping("/listadbDebugServer.action")
		//** jqgrid组件列表查询系统 用户 *//*
		public String listadbDebugServer(HttpServletRequest request, HttpServletResponse response) {
			JqGridPager jqGridPager=new JqGridPager(request); 
			Map<String,Object> p=ServletBeanTools.getParameterMap(request);
			
			List<Map<String, Object>> list = sysVerService.getAllAdbDebugServerInfo(jqGridPager, p);
			try {
				JqGridTool.jqGridQuery(request,response,list,jqGridPager);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 删除
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/del_adbDebugServer.action")
		 public Map<String, Object> delAdbDebugServer(HttpServletRequest request,HttpServletResponse response){
			String id= request.getParameter("id");
			int result = sysVerService.delAdbDebugServerById(Long.parseLong(id));
			if(result<1){
				return ActionUtil.ajaxFail("删除失败", ""); 
			}else{
				return ActionUtil.ajaxSuccess("删除成功", ""); 
			}
			
		 }
		
		
		/**
		 *请求到修改面
		 * @return
		 */
		@RequestMapping("/showEditnAdbDebugServerWindow.action")
		public String showEditnAdbDebugServerWindow(@ModelAttribute( "model" ) AdbDebugServer model,Model retmodel) {
			Long id = model.getId();
			model = sysVerService.getAdbDebugServerById(id);
			
			retmodel.addAttribute("model", model);
			return "sysmanage/t_sysVer/edit_adbDebugServer";
		}
		
		/**
		  * 修改
		  * @param model
		  * @param retmodel
		  * @return
		  */
		@RequestMapping("/updateAdbDebugServer.action" )
		public String updateAdbDebugServer(@ModelAttribute( "model" ) AdbDebugServer model,Model retmodel){
			
			int result = sysVerService.updateAdbDebugServerById(model);
			
			if(result>0){ 
				retmodel.addAttribute("meg", "修改成功！");  
			}else{ 
				retmodel.addAttribute("meg", "修改失败！");  
			}
			return "sysmanage/t_sysVer/edit_adbDebugServer";
		}
		
		/**
		  * 查看
		  * @param model
		  * @param retmodel
		  * @return
		  */
		@RequestMapping("/showAdbDebugServerInfo.action" )
		public String showAdbDebugServerInfo(@ModelAttribute( "model" ) AdbDebugServer model,Model retmodel){
			Long id = model.getId();
			model = sysVerService.getAdbDebugServerById(id);
			
			retmodel.addAttribute("model", model);
			
			return "sysmanage/t_sysVer/adbDebugServer_info";
		}
		
	/*************************************adbDebugServer   end******************************************************/
		
		
	/*************************************realTimeControl   start******************************************************/
		/**
		 * 请求到添加页面
		 * @return
		 */
		@RequestMapping("/show_addrealTimeControl.action")
		public String toAddrealTimeControl() {
			
			return "sysmanage/t_sysVer/add_realTimeControl";
		}	
		
		/** 添加*/
		@RequestMapping("/addRealTimeControl.action")
		public String addRealTimeControl(@ModelAttribute("model")RealTimeControl model, Model retmodel) {
			int result = sysVerService.insertRealTimeControl(model);
			if(result>0){
				retmodel.addAttribute("meg", "新增成功！"); 
			}else{
				retmodel.addAttribute("meg", "新增失败！"); 
			}
			return "sysmanage/t_sysVer/add_adbDebugServer";
		}
	
		@ResponseBody
		@RequestMapping("/listRealTimeControl.action")
		//** jqgrid组件列表查询系统 用户 *//*
		public String listRealTimeControl(HttpServletRequest request, HttpServletResponse response) {
			JqGridPager jqGridPager=new JqGridPager(request); 
			Map<String,Object> p=ServletBeanTools.getParameterMap(request);
			
			List<Map<String, Object>> list = sysVerService.getAllRealTimeControlInfo(jqGridPager, p);
			try {
				JqGridTool.jqGridQuery(request,response,list,jqGridPager);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 删除
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/del_realTimeControl.action")
		 public Map<String, Object> delRealTimeControl(HttpServletRequest request,HttpServletResponse response){
			String id= request.getParameter("id");
			int result = sysVerService.delRealTimeControlById(Long.parseLong(id));
			if(result<1){
				return ActionUtil.ajaxFail("删除失败", ""); 
			}else{
				return ActionUtil.ajaxSuccess("删除成功", ""); 
			}
			
		 }
		
		/**
		 *请求到修改面
		 * @return
		 */
		@RequestMapping("/showEditRealTimeControlWindow.action")
		public String showEditRealTimeControlWindow(@ModelAttribute( "model" ) RealTimeControl model,Model retmodel) {
			Long id = model.getId();
			model = sysVerService.getRealTimeControlById(id);
			
			retmodel.addAttribute("model", model);
			return "sysmanage/t_sysVer/edit_realTimeControl";
		}
		
		/**
		  * 修改
		  * @param model
		  * @param retmodel
		  * @return
		  */
		@RequestMapping("/updateRealTimeControl.action" )
		public String updateRealTimeControl(@ModelAttribute( "model" ) RealTimeControl model,Model retmodel){
			
			int result = sysVerService.updateRealTimeControlById(model);
			
			if(result>0){ 
				retmodel.addAttribute("meg", "修改成功！");  
			}else{ 
				retmodel.addAttribute("meg", "修改失败！");  
			}
			return "sysmanage/t_sysVer/edit_realTimeControl";
		}
		
		
	/*************************************realTimeControl   end******************************************************/
}
