package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fs.comm.model.THotel;
import com.fs.comm.model.THotelGroup;

/**
 * 酒店组管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_hotel_group")
public class T_hotel_groupController extends BaseController {

	@RequestMapping("/browseT_hotel_group.action")
	/** 处理浏览酒店组请求 */
	public String browseT_hotel_group(HttpServletRequest request) {
		return "webmanage/t_hotel_group/browseHotelGroup";
	}

	@ResponseBody
	@RequestMapping("/listT_hotel_group.action")
	/** jqgrid组件列表-查询酒店组列表 */
	public RespJsonPageData getHotelGroupList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		
		List<THotelGroup> groupList = t_hotel_groupService.browseGroupList(jqGridPager, p);
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
	@RequestMapping("/preAddHotelGroup.action")
	public String preAddHotelGroup(@ModelAttribute("model") THotelGroup model, Model retmodel) {
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());
		return "webmanage/t_hotel_group/addHotelGroup";
	}

	/**
	 * 新增分组请求
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addHotelGroup.action")
	public String addHotelGroup(@ModelAttribute("model") THotelGroup model, Model retmodel) {
		// 检查合法性
		Map<String, Object> p;
		if (StringUtils.isEmpty(model.getGroupname())) {
			retmodel.addAttribute("meg", "组名称为必填信息！");
			return "webmanage/t_hotel_group/addHotelGroup";
		} else {
			p = new HashMap<String, Object>();
			p.put("groupname", model.getGroupname());
			List<THotelGroup> groupList = t_hotel_groupService.selectGroup(p);
			if (groupList.size() > 0) {
				retmodel.addAttribute("meg", "该组名称已经存在,请换个名称！");
				return "webmanage/t_hotel_group/addHotelGroup";
			}
		}
		if (model.getSid() != null) {
			p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			List<THotelGroup> groupList = t_hotel_groupService.selectGroup(p);
			if (groupList.size() > 0) {
				retmodel.addAttribute("meg", "该分组已经存在,请检查！");
				return "webmanage/t_hotel_group/addHotelGroup";
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		// 执行添加
		if (t_hotel_groupService.insertGroup(model)) {
			insertSysLog("添加", "用户组", "成功", currentUser().getLoginname() + "添加用户组成功");
			retmodel.addAttribute("meg", "新增分组成功！");
		} else {
			retmodel.addAttribute("meg", "新增分组失败！");
		}
		return "webmanage/t_hotel_group/addHotelGroup";
	}

	/**
	 * 删除分组
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delHotelGroup.action")
	public String delHotelGroup(@ModelAttribute("model") THotelGroup model) throws Exception {
		Map<String, Object> p;
		if (model.getSid() != null) {
			p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			int i = t_hotel_groupService.countGroup(p);
			if (i <= 0) {
				return "该分组不存在，请确认";
			}

			// 获取该组下用户列表
			p = new HashMap<String, Object>();
			p.put("hotelgroup", model.getSid());
			List<THotel> hotelList = t_hotelService.getHotelList(p);
			if (hotelList != null && hotelList.size() > 0) {
				return "该分组已被用户使用，请先解除分组与其下用户的关系";
			}

			if (t_hotel_groupService.delGroup(model.getSid())) {
				insertSysLog("删除", "用户组", "成功", currentUser().getLoginname() + "删除用户组成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 查看分组详情
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getHotelGroup.action")
	public String getHotelGroup(@ModelAttribute("model") THotelGroup model, Model retmodel) throws Exception {
		if (model.getSid() != null) {
			THotelGroup group = t_hotel_groupService.getGroupById(model.getSid());
			if (group != null) {
				// 获取组基本信息，配置信息
				retmodel.addAttribute("t_hotel_group", group);
				// 获取该组下用户列表
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("hotelgroup", group.getSid());
				retmodel.addAttribute("hotelList", getHotelList(p));
				// 获取epg根栏目列表
				retmodel.addAttribute("columnlist", getColumnList());

				return "webmanage/t_hotel_group/viewHotelGroup2";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 进入修改分组页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/editHotelGroup.action")
	public String editHotelGroup(@ModelAttribute("model") THotelGroup model, Model retmodel) {
		// 修改酒店组基本信息
		THotelGroup group = t_hotel_groupService.getGroupById(model.getSid());
		retmodel.addAttribute("t_hotel_group", group);
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());

		return "webmanage/t_hotel_group/editHotelGroup";
	}

	/**
	 * 修改酒店组
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateHotelGroupById.action")
	public String updateHotelGroupById(@ModelAttribute("model") THotelGroup model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		THotelGroup group = t_hotel_groupService.getGroupById(model.getSid());

		// 检查合法性
		if (StringUtils.isEmpty(model.getGroupname())) {
			retmodel.addAttribute("meg", "组名称为必填信息！");
			return "webmanage/t_hotel_group/editHotelGroup";
		} else {
			if (!model.getGroupname().equals(group.getGroupname())) {
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("groupname", model.getGroupname());
				List<THotelGroup> groupList = t_hotel_groupService.selectGroup(p);
				if (groupList.size() > 0) {
					retmodel.addAttribute("meg", "该组名称已经存在,请换个名称！");
					return "webmanage/t_hotel_group/editHotelGroup";
				}
			}
		}

		// model属性复制给order
		MyBeanUtils.copyBeanNotNull2Bean(model, group);
		// 执行修改
		if (t_hotel_groupService.updateGroupById(group)) {
			MyBeanUtils.copyBeanNotNull2Bean(group, model);

			insertSysLog("修改", "用户组", "成功", currentUser().getLoginname() + "修改用户组成功");
			retmodel.addAttribute("meg", "该分组修改成功！");
		} else {
			retmodel.addAttribute("meg", "该分组修改失败！");
		}
		retmodel.addAttribute("t_hotel_group", group);
		return "webmanage/t_hotel_group/editHotelGroup";
	}

}
