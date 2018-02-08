package com.fs.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.fantastic.ContextHolderUtils;
import com.fs.comm.model.Column;
import com.fs.comm.model.ImageLable;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.THotel;
import com.fs.comm.model.THotelGroup;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;
import com.fs.web.service.ColumnService;
import com.fs.web.service.ImageTextTemplateService;
import com.fs.web.service.SysLogService;
import com.fs.web.service.SysuserService;
import com.fs.web.service.T_hotelService;
import com.fs.web.service.T_hotel_groupService;
import com.fs.web.service.T_stbService;
import com.fs.web.service.T_stb_groupService;

/**
 * 公共Controller
 * 
 * @author pzj
 *
 */
public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	protected SysLogService sysLogService;

	@Resource
	protected T_hotelService t_hotelService;

	@Resource
	protected T_hotel_groupService t_hotel_groupService;

	@Resource
	protected T_stbService t_stbService;

	@Resource
	protected T_stb_groupService t_stb_groupService;

	@Resource
	protected ColumnService columnService;

	@Resource
	protected SysuserService userService;

	@Resource
	private ImageTextTemplateService templateService;

	/**
	 * 获取当前登录用户
	 */
	public Sysuser currentUser() {
		Sysuser user = ContextHolderUtils.getLoginUser();
		return user;
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

	/**
	 * 添加日志公共方法
	 * 
	 * sysLog.setOpertype("退出"); sysLog.setOperobject("退出系统");
	 * sysLog.setOperresult("成功"); sysLog.setOperdes("退出系统成功");
	 * 
	 * @return
	 */
	protected void insertSysLog(String opertype, String operobject, String operresult, String operdes) {
		SysLog sysLog = new SysLog();

		UUID uuid = UUID.randomUUID();
		sysLog.setId(uuid.toString().replace("-", "").toUpperCase());
		sysLog.setDotime(new Date());// 操作时间
		sysLog.setUsername(ContextHolderUtils.getLoginUser().getLoginname());// 操作用户

		sysLog.setOpertype(opertype);// 操作类型
		sysLog.setOperobject(operobject);// 操作模块
		sysLog.setOperresult(operresult);// 操作结果
		sysLog.setOperdes(operdes);// 操作描述
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取酒店列表
	 */
	protected List<THotel> getHotelList(Map<String, Object> p) {
		p = ViewDataCondition(p);
		List<THotel> hotelList = t_hotelService.getHotelList(p);
		return hotelList;
	}

	/**
	 * 获取用户组列表
	 */
	protected List<THotelGroup> getHotelGroupList() {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		// 获取用户组名称列表
		List<THotelGroup> hotelgrouplist = t_hotel_groupService.selectGroup(p);
		return hotelgrouplist;
	}

	/**
	 * 获取设备列表
	 */
	protected List<TStb> getStbList() {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<TStb> stbList = t_stbService.getStbList(p);
		return stbList;
	}

	/**
	 * 获取设备组列表
	 */
	protected List<TStbGroup> getStbgrouplist() {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<TStbGroup> stbgrouplist = t_stb_groupService.getStbGroupList(p);
		return stbgrouplist;
	}

	/**
	 * 获取epg根栏目列表
	 */
	protected List<Column> getColumnList() {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		// 获取epg根栏目列表
		List<Column> columnlist = columnService.getRootColumnList(p);
		return columnlist;
	}
	
	/**
	 * 获取图文模板列表
	 */
	protected List<ImageLable> getImageTextTemplateList() {
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<ImageLable> templatelist = templateService.getImageLableList(p);
		return templatelist;
	}
}