package com.fs.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.SysParameter;
import com.fs.web.service.SysParameterManagerService;

/**
 * 系统参数配置
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_sysParameter")
public class SysParameterManagerController extends BaseController {

	@Resource
	private SysParameterManagerService sysParameterManagerService;

	/**
	 * 请求到主页面
	 * 
	 * @return
	 */
	@RequestMapping("/sysParameter_manager.action")
	public String toSysParameter() {
		return "sysmanage/t_sysParameter/index_sys_parameter";
	}

	/**
	 * 浏览系统配置页
	 */
	@ResponseBody
	@RequestMapping("/listSysParameter.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listSysParameter(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		List<Map<String, Object>> list = sysParameterManagerService.getAllSysParameterInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, list, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/show_addSysParameter.action")
	public String toAddSysParameter() {
		return "sysmanage/t_sysParameter/add_sysParameter";
	}

	/** 添加系统参数配置 */
	@RequestMapping("/addSysParameter.action")
	public String addSysParameter(@ModelAttribute("model") SysParameter model, Model retmodel) {
		// 检查该参数名称是否已存在
		Map<String, Object> p = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(model.getName())) {
			p = new HashMap<String, Object>();
			p.put("companyid", currentUser().getCompanyid());
			p.put("name", model.getName());
			SysParameter en = sysParameterManagerService.getSysParameter(p);
			if (en != null) {
				retmodel.addAttribute("meg", "该系统参数名称已经存在,请先删除或直接修改！");
				return "sysmanage/t_sysParameter/add_sysParameter";
			}
		}

		// 添加
		model.setCompanyid(currentUser().getCompanyid());
		int result = sysParameterManagerService.insertSysParameter(model);

		if (result > 0) {
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "sysmanage/t_sysParameter/add_sysParameter";
	}

	/**
	 * 请求到修改面
	 * 
	 * @return
	 */
	@RequestMapping("/showUpdateWindow.action")
	public String showUpdateWindow(@ModelAttribute("model") SysParameter model, Model retmodel) {
		Long id = model.getId();
		model = sysParameterManagerService.getSysParameterById(id);
		retmodel.addAttribute("model", model);
		return "sysmanage/t_sysParameter/edit_sysParameter";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatSysParameter.action")
	public String updatSysParameter(@ModelAttribute("model") SysParameter model, Model retmodel) {

		int result = sysParameterManagerService.updateSysParameterById(model);
		if (result > 0) {
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		return "sysmanage/t_sysParameter/edit_sysParameter";
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_SysParameter")
	public Map<String, Object> delSysParameter(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int result = sysParameterManagerService.delSysParameter(Long.parseLong(id));
		if (result < 1) {
			return ActionUtil.ajaxFail("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
		}

	}

	/** 查看详情 */
	@RequestMapping("/querySysParameterInfo.action")
	public String queryAppEditionsInfo(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		if (!"".equals(id) || null != id) {
			SysParameter model = sysParameterManagerService.getSysParameterById(Long.parseLong(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createTime = model.getCreateTime();
			if (null != createTime) {
				String tempTime = sdf.format(createTime);
				model.setTempTime(tempTime);
			}

			retmodel.addAttribute("model", model);
		}

		return "sysmanage/t_sysParameter/sysParameter_info";
	}
}
