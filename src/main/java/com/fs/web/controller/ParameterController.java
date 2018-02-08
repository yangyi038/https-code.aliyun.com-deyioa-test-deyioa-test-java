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
import com.fs.comm.model.Parameter;
import com.fs.comm.model.TStbGroup;
import com.fs.web.service.ParameterService;

/**
 * 枚举参数controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/parameter")
public class ParameterController extends BaseController {

	@Resource
	private ParameterService parameterService;

	/**
	 * 浏览枚举列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/browseParameter.action")
	public String browseParameter(HttpServletRequest request) {
		return "sysmanage/parameter/browseParameter";
	}

	/**
	 * 浏览枚举列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listParameter.action")
	public RespJsonPageData listParameter(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);

		List<Parameter> list = parameterService.browseParameter(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(list, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddParameter.action")
	public String preAddParameter(@ModelAttribute("model") Parameter model, Model retmodel) {
		return "sysmanage/parameter/addParameter";
	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addParameter.action")
	public String addParameter(@ModelAttribute("model") Parameter model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		// 枚举（类型和名称）是否重复
		p.put("ptype", model.getPtype());
		p.put("pname", model.getPname());
		List<Parameter> groupList = parameterService.getParameterList(p);
		if (groupList.size() > 0) {
			retmodel.addAttribute("meg", "该设备组名称已经存在,请检查！");
			return "sysmanage/parameter/addParameter";
		}

		// 执行添加
		if (parameterService.insertParameter(model)) {

			insertSysLog("添加", "枚举", "成功", currentUser().getLoginname() + "添加枚举成功");
			retmodel.addAttribute("meg", "新增枚举成功！");
		} else {
			retmodel.addAttribute("meg", "新增枚举失败！");
		}
		return "sysmanage/parameter/addParameter";
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delParameter.action")
	public String delParameter(@ModelAttribute("model") Parameter model) throws Exception {
		Map<String, Object> p;
		if (model.getId() != null) {
			p = new HashMap<String, Object>();
			p.put("id", model.getId());
			Parameter i = parameterService.getParameter(p);
			if (i == null)
				return "该枚举不存在，请确认";

			if (parameterService.delParameter(model.getId())) {
				insertSysLog("删除", "枚举", "成功", currentUser().getLoginname() + "删除枚举成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preEditParameter.action")
	public String preEditParameter(@ModelAttribute("model") Parameter model, Model retmodel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", model.getId());
		Parameter group = parameterService.getParameter(map);
		retmodel.addAttribute("parameter", group);
		
		return "sysmanage/parameter/editParameter";
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
	@RequestMapping("/updateParameter.action")
	public String updateParameter(@ModelAttribute("model") Parameter model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", model.getId());
		Parameter group = parameterService.getParameter(map);
		// 枚举名称是否重复
		if (StringUtils.isNotEmpty(model.getPname()) && !model.getPname().equals(group.getPname())) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("pname", model.getPname());
			List<Parameter> groupList = parameterService.getParameterList(p);
			if (groupList.size() > 0) {
				retmodel.addAttribute("meg", "该枚举名称已经存在,请检查！");
				return "sysmanage/parameter/editParameter";
			}
		}

		// model属性复制给order
		MyBeanUtils.copyBeanNotNull2Bean(model, group);
		// 执行修改
		if (parameterService.updateParameter(group)) {
			MyBeanUtils.copyBeanNotNull2Bean(group, model);

			insertSysLog("修改", "枚举信息", "成功", currentUser().getLoginname() + "修改枚举信息成功");
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("parameter", group);
		return "sysmanage/parameter/editParameter";
	}

}
