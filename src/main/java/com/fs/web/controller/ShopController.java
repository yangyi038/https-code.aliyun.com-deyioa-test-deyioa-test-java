package com.fs.web.controller;

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

import com.fantastic.RespJsonPageData;
import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.comm.mapper.ShopEntityMapper;
import com.fs.comm.model.Department;
import com.fs.comm.model.ShopEntity;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.ShopService;

/**
 * 商铺管理
 * 
 * @author yangyi
 *
 */
@Controller
@RequestMapping("/admin/shop")
public class ShopController extends BaseController{

	@Resource
	private ShopService shopService;
	
	@Resource
	private ShopEntityMapper shopEntityMapper;
	
	/**
	 * 进入到商铺管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shop.action")
	public String problem(HttpServletRequest request){

		List<Department> groupList = shopService.selectDepartment();
		request.setAttribute("groupList", groupList);
		return "sysmanage/business/shop";
	}
	
	/**
	 * 根据查询条件检索数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list_shop.action")
	public RespJsonPageData getShopList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取问题列表
		List<ShopEntity> shopList = shopService.selectShopList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(shopList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
	
	/**
	 * 进入到问题添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addshopPre.action")
	public String addshopPre(@ModelAttribute("model") ShopEntity model, Model retmodel) {

		// 获取商铺添加页面下拉列表
		List<Department> groupList = shopService.selectDepartment();
		retmodel.addAttribute("groupList", groupList);
		return "sysmanage/business/addShop";
	}
	
	/**
	 * 添加商铺页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/addshop.action")
	public String addProblem(@ModelAttribute("model") ShopEntity model, Model retmodel,
			HttpServletRequest request) {
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Date date = new Date();
		
		model.setId(uuid);
		model.setCreatetime(date.toString());
		model.setDeflg(1);
		boolean result = shopService.insertSelective(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("商铺");
		sysLog.setOperdes("添加商铺" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addshopPre(model,retmodel);
	}
	
	/**
	 * 进入商铺编辑页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/editshopPre.action")
	public String editshopPre(@ModelAttribute("model") ShopEntity model, Model retmodel) {
		
		// 获取问题信息
		ShopEntity entity = shopEntityMapper.selectByid(model);
		retmodel.addAttribute("shop", entity);

		// 获取商铺添加页面下拉列表
		List<Department> groupList = shopService.selectDepartment();
		retmodel.addAttribute("groupList", groupList);

		return "sysmanage/business/shopModify";
	}
	
	/**
	 * 编辑问题页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/editshop.action")
	public String editProblem(@ModelAttribute("model") ShopEntity model, Model retmodel,
			HttpServletRequest request) {

		model.setUpdatetime(new Date());
		boolean result = shopService.updateShop(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "更新成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "更新失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("更新");
		sysLog.setOperobject("商铺");
		sysLog.setOperdes("更新商铺" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editshopPre(model,retmodel);
	}
	
	/**
	 * 删除商铺操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/del_shop.action")
	public Map<String, Object> delProblem(@ModelAttribute("model") ShopEntity model,
		   Model retmodel) throws Exception {
		
		if(model.getId() != null){
			model.setUpdatetime(new Date());
			boolean fla = shopService.deleteByid(model);
			SysLog sysLog = this.getSysLog();
			if (fla) {
				retmodel.addAttribute("meg", "删除成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "删除失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("删除");
			sysLog.setOperobject("商铺");
			sysLog.setOperdes("删除商铺" + model.getName());
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(fla){
				return ActionUtil.ajaxSuccess("删除成功", "");
			}else{
				return ActionUtil.ajaxFail("删除失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("删除失败", "");
		}	
	}

	/**
	 * 主菜单进入到商铺恢復页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopRecovery.action")
	public String problemRecovery(HttpServletRequest request){
		return "sysmanage/business/shopRecovery";
	}
	
	/**
	 * 查询出已删除的商铺信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list_recoveryShop.action")
	public RespJsonPageData getRecoveryShopList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取问题列表
		List<ShopEntity> shopList = shopService.selectRecoveryShopList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(shopList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
	
	/**
	 * 恢复商铺操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/recovery_shop.action")
	public Map<String, Object> recoveryProblem(@ModelAttribute("model") ShopEntity model,
		   Model retmodel) throws Exception {
		
		if(model.getId() != null){
			model.setUpdatetime(new Date());
			boolean fla = shopService.recoveryByid(model);
			SysLog sysLog = this.getSysLog();
			if (fla) {
				retmodel.addAttribute("meg", "恢复成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "恢复失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("恢复");
			sysLog.setOperobject("商铺");
			sysLog.setOperdes("恢复商铺" + model.getName());
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(fla){
				return ActionUtil.ajaxSuccess("恢复成功", "");
			}else{
				return ActionUtil.ajaxFail("恢复失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("恢复失败", "");
		}	
	}

	
	/**
	 * 根据请求用户ID， 权限控制
	 */
	protected Map<String, Object> ViewDataCondition(Map<String, Object> p) {
		if (currentUser().getCompanyid() != null) {
			p.put("companyid", currentUser().getCompanyid());
		}
		if (currentUser().getOperatorid() != null) {
			p.put("operatorid", currentUser().getOperatorid());
		}
		if (currentUser().getDealerid() != null) {
			p.put("dealerid", currentUser().getDealerid());
		}
		return p;
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