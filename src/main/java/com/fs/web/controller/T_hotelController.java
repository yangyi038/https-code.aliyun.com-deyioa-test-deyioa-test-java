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
import com.fs.comm.model.Column;
import com.fs.comm.model.THotel;
import com.fs.comm.model.THotelGroup;
import com.fs.web.service.T_bindService;
import com.fs.web.service.T_stbService;
import com.fs.web.vomodel.Group_SearchVo;

/**
 * 用户管理
 * 
 * @author yangyi
 *
 */
@Controller
@RequestMapping("/admin/t_hotel")
public class T_hotelController extends BaseController {

	@Resource
	private T_stbService t_stbService;

	@Resource
	private T_bindService t_bindService;
	
	
	@RequestMapping("/browseT_hotel.action")
	/** 处理浏览订单表请求 */
	public String browseT_hotel(HttpServletRequest request) {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<THotelGroup> hotelgrouplist = t_hotel_groupService.selectGroup(p);
		request.setAttribute("hotelgrouplist", hotelgrouplist);
		return "webmanage/t_hotel/browseHotel";
	}

	@ResponseBody
	@RequestMapping("/listT_hotel.action")
	/** jqgrid组件列表-查询订单列表 */
	public RespJsonPageData getHotelList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取用户列表
		List<THotel> hotelList = t_hotelService.browseHotelList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(hotelList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddHotel.action")
	public String preAddHotel(@ModelAttribute("model") THotel model, Model retmodel) {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		// 获取用户组名称列表
		List<THotelGroup> hotelgrouplist = t_hotel_groupService.selectGroup(p);
		// 获取epg根栏目列表
		List<Column> columnlist = columnService.getRootColumnList(p);

		retmodel.addAttribute("hotelgrouplist", hotelgrouplist);
		retmodel.addAttribute("columnlist", columnlist);

		return "webmanage/t_hotel/addHotel";
	}

	/**
	 * 新增用户请求
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addHotel.action")
	public String addEvaluationVideo(@ModelAttribute("model") THotel model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		// 用户名称不能重复
		if (StringUtils.isNotEmpty(model.getHotelname())) {
			p = new HashMap<String, Object>();
			p.put("hotelname", model.getHotelname());
			List<THotel> hotelList = t_hotelService.selectHotel(p);
			if (hotelList.size() > 0) {
				retmodel.addAttribute("meg", "该用户名称已经存在,请检查！");
				return "webmanage/t_hotel/addHotel";
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		// 执行添加
		if (t_hotelService.insertHotel(model)) {
			insertSysLog("添加", "用户", "成功", currentUser().getLoginname() + "添加用户成功");
			retmodel.addAttribute("meg", "新增用户成功！");
		} else {
			retmodel.addAttribute("meg", "新增用户失败！");
		}
		return "webmanage/t_hotel/addHotel";
	}

	/**
	 * 删除用户
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delHotel.action")
	public String delHotel(@ModelAttribute("model") THotel model) throws Exception {
		if (model.getSid() != null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			int i = t_hotelService.count_Hotel(p);
			if (i <= 0) {
				return "该用户不存在，请确认";
			}

			p = new HashMap<String, Object>();
			p.put("hotelsid", model.getSid());

			if (t_hotelService.delHotel(model.getSid())) {
				insertSysLog("删除", "用户", "成功", currentUser().getLoginname() + "删除用户成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 查看用户信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getHotelInfo.action")
	public String getHotelInfo(@ModelAttribute("model") THotel model, Model retmodel) throws Exception {
		if (model.getSid() != null) {
			THotel hotelDetail = t_hotelService.selectByPrimaryKey(model.getSid());
			if (hotelDetail != null) {
				// 拼地址
				hotelDetail.setAddress(hotelDetail.getProvince() + hotelDetail.getCity() + hotelDetail.getArea()
						+ hotelDetail.getAddress());
				// 用户组名称
				if (StringUtils.isNotEmpty(hotelDetail.getHotelgroup())) {
					Group_SearchVo vo = new Group_SearchVo();
					vo.setGroupNum(hotelDetail.getHotelgroup());
					List<THotelGroup> hotelgrouplist = t_hotel_groupService.getGroupInfoByCondition(vo);
					if (hotelgrouplist != null) {
						hotelDetail.setHotelgroup(hotelgrouplist.get(0).getGroupname());
					}
				}
				// 基本信息，配置信息
				retmodel.addAttribute("t_hotel", hotelDetail);

				Map<String, Object> map = new HashMap<>();
				map.put("companyid", currentUser().getCompanyid());
				// 获取epg根栏目列表
				List<Column> columnlist = columnService.getRootColumnList(map);
				retmodel.addAttribute("columnlist", columnlist);

				return "webmanage/t_hotel/viewHotel3";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 进入修改用户信息页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/PreEditHotel.action")
	public String PreEditHotel(@ModelAttribute("model") THotel model, Model retmodel) {
		// 获取用户信息
		THotel hotel = t_hotelService.selectByPrimaryKey(model.getSid());
		retmodel.addAttribute("t_hotel", hotel);

		retmodel.addAttribute("hotelgrouplist", getHotelGroupList());
		retmodel.addAttribute("columnlist", getColumnList());

		return "webmanage/t_hotel/editHotel2";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateHotelBySid.action")
	public String updateHotelBySid(@ModelAttribute("model") THotel model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		THotel hotel = t_hotelService.selectByPrimaryKey(model.getSid());
		// 用户名称不能重复
		if (StringUtils.isNotEmpty(model.getHotelname()) && !hotel.getHotelname().equals(model.getHotelname())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("hotelname", model.getHotelname());
			List<THotel> hotelList = t_hotelService.selectHotel(p);
			if (hotelList.size() > 0) {
				retmodel.addAttribute("meg", "该用户名称已经存在,请检查！");
				return "webmanage/t_hotel/editHotel2";
			}
		}

		// model属性复制给hotel
		MyBeanUtils.copyBeanNotNull2Bean(model, hotel);
		// 执行修改
		if (t_hotelService.updateByPrimaryKey(hotel)) {
			MyBeanUtils.copyBeanNotNull2Bean(hotel, model);

			insertSysLog("修改", "用户", "成功", currentUser().getLoginname() + "修改用户成功");
			retmodel.addAttribute("meg", "该用户信息修改成功！");
		} else {
			retmodel.addAttribute("meg", "该用户信息修改失败！");
		}
		retmodel.addAttribute("t_hotel", hotel);
		return "webmanage/t_hotel/editHotel2";
	}
}
