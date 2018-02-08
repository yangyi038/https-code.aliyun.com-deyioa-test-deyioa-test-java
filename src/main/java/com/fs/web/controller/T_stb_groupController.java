package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantastic.MyBeanUtils;
import com.fantastic.RespJsonPageData;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;
import com.fs.web.service.ColumnService;
import com.fs.web.service.T_stbService;
import com.fs.web.service.T_stb_groupService;

/**
 * 机顶盒组管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_stb_group")
public class T_stb_groupController extends BaseController {
	
	@Resource
	private T_stb_groupService t_stb_groupService;

	@Resource
	private T_stbService t_stbService;

	@Resource
	private ColumnService columnService;

	@RequestMapping("/browseT_stb_group.action")
	/** 处理浏览机顶盒组请求 */
	public String browseT_stb_group(HttpServletRequest request) {
		return "webmanage/t_stb_group/browseStbGroup";
	}

	/** jqgrid组件列表-获取机顶盒组列表 */
	@ResponseBody
	@RequestMapping("/listT_stb_group.action")
	public RespJsonPageData getStbGroupList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<TStbGroup> groupList = t_stb_groupService.browseStbGroupList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(groupList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddStbGroup.action")
	public String preAddStbGroup(@ModelAttribute("model") TStbGroup model, Model retmodel) {
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());
		return "webmanage/t_stb_group/addStbGroup";
	}

	/**
	 * 新增分组
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addStbGroup.action")
	public String addStbGroup(@ModelAttribute("model") TStbGroup model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		// 设备组名称是否重复
		if (StringUtils.isNotEmpty(model.getGroupname())) {
			p = new HashMap<String, Object>();
			p.put("companyid", currentUser().getCompanyid());
			p.put("groupname", model.getGroupname());
			List<TStbGroup> groupList = t_stb_groupService.selectStbGroup(p);
			if (groupList.size() > 0) {
				retmodel.addAttribute("meg", "该设备组名称已经存在,请检查！");
				return "webmanage/t_stb_group/addStbGroup";
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		// 执行添加
		if (t_stb_groupService.insertStbGroup(model)) {

			insertSysLog("添加", "设备组", "成功", currentUser().getLoginname() + "添加设备组成功");
			retmodel.addAttribute("meg", "新增分组成功！");
		} else {
			retmodel.addAttribute("meg", "新增分组失败！");
		}
		return "webmanage/t_stb_group/addStbGroup";
	}

	/**
	 * 删除分组
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delStbGroup.action")
	public String delStbGroup(@ModelAttribute("model") TStbGroup model) throws Exception {
		Map<String, Object> p;
		if (model.getSid() != null) {
			p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			int i = t_stb_groupService.countStbGroup(p);
			if (i <= 0) {
				return "该分组不存在，请确认";
			}

			// 获取组下机顶盒列表
			p = new HashMap<String, Object>();
			p.put("stbgroup", model.getSid());
			List<TStb> stbList = t_stbService.getStbList(p);
			if (stbList != null && stbList.size() > 0) {
				return "该分组已被机顶盒使用，请先解除分组与其下机顶盒的关系";
			}

			if (t_stb_groupService.delStbGroup(model.getSid())) {
				insertSysLog("删除", "设备组", "成功", currentUser().getLoginname() + "删除设备组成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 查看组信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStbGroup.action")
	public String getStbGroup(@ModelAttribute("model") TStbGroup model, Model retmodel) throws Exception {
		if (model.getSid() != null) {
			TStbGroup group = t_stb_groupService.getStbGroupById(model.getSid());
			if (group != null) {
				// 获取组基本信息，配置信息
				retmodel.addAttribute("t_stb_group", group);
				// 获取组下机顶盒列表
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("stbgroup", group.getSid());
				List<TStb> stbList = t_stbService.getStbList(p);
				retmodel.addAttribute("stbList", stbList);
				// 获取epg根栏目列表
				retmodel.addAttribute("columnlist", getColumnList());

				return "webmanage/t_stb_group/viewStbGroup2";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preEditStbGroup.action")
	public String preEditStbGroup(@ModelAttribute("model") TStbGroup model, Model retmodel) {
		//
		TStbGroup group = t_stb_groupService.getStbGroupById(model.getSid());
		retmodel.addAttribute("t_stb_group", group);
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());

		return "webmanage/t_stb_group/editStbGroup";
	}

	/**
	 * 修改分组信息
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateStbGroupById.action")
	public String updateStbGroupById(@ModelAttribute("model") TStbGroup model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TStbGroup group = t_stb_groupService.getStbGroupById(model.getSid());
		// 设备组名称是否重复
		if (StringUtils.isNotEmpty(model.getGroupname()) && !model.getGroupname().equals(group.getGroupname())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("groupname", model.getGroupname());
			List<TStbGroup> groupList = t_stb_groupService.selectStbGroup(p);
			if (groupList.size() > 0) {
				retmodel.addAttribute("meg", "该设备组名称已经存在,请检查！");
				return "webmanage/t_stb_group/editStbGroup";
			}
		}

		// model属性复制给order
		MyBeanUtils.copyBeanNotNull2Bean(model, group);
		// 执行修改
		if (t_stb_groupService.updateStbGroupById(group)) {
			MyBeanUtils.copyBeanNotNull2Bean(group, model);
			
			insertSysLog("修改", "设备组", "成功", currentUser().getLoginname() + "修改设备组成功");
			retmodel.addAttribute("meg", "该分组修改成功！");
		} else {
			retmodel.addAttribute("meg", "该分组修改失败！");
		}
		retmodel.addAttribute("t_stb_group", group);
		return "webmanage/t_stb_group/editStbGroup";
	}

}
