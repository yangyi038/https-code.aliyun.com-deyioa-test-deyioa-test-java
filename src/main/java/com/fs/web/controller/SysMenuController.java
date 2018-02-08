package com.fs.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.Tree;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.DateTool;
import com.fs.comm.model.Sysmenu;
import com.fs.web.service.SysmenuService;

/**
 * 系统菜单管理
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/sysmenu")
public class SysMenuController extends BaseController {

	@Resource
	private SysmenuService sysmenuService;

	/** 处理浏览请求 */
	@RequestMapping("/browseSysmenu.action")
	public String browseSysmenu() {
		return "sysmanage/sysmenu/browseSysmenu";
	}

	/**
	 * 浏览组织机构树状图
	 */
	@ResponseBody
	@RequestMapping("/listSysmenu.action")
	public String listSysmenu(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		List<Sysmenu> sysmenuList = sysmenuService.browseSysmenu(jqGridPager, null);
		try {
			JqGridTool.jqGridQuery(request, response, sysmenuList, jqGridPager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回Json格式数据
		return null;
	}

	/** 进入新增页面 */
	@RequestMapping("/preAddSysmenu.action")
	public String preAddSysmenu(@ModelAttribute("model") Sysmenu model, Model retmodel) {
		Sysmenu menu = sysmenuService.loadSysmenu(model.getId());
		retmodel.addAttribute("sysmenu", menu);
		return "sysmanage/sysmenu/addSysmenu";
	}

	/** 处理新增系统菜单请求 */
	@RequestMapping("/addSysmenu.action")
	public String addSysmenu(@ModelAttribute("model") Sysmenu model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("menuname", model.getMenuname());
		p.put("menuparent", model.getMenuparent());
		List<Sysmenu> menuList = sysmenuService.browseSysmenuList(p);
		if (menuList.size() > 0) {
			retmodel.addAttribute("meg", "菜单名称已存在！");
			return "sysmanage/sysmenu/addSysmenu";
		}

		model.setValid(1 + "");// 默认有效
		model.setAddtime(DateTool.getTimestamp());// 创建时间

		if (sysmenuService.insertSysmenu(model)) {// 保存新增
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		retmodel.addAttribute("sysmenu", model);
		return "sysmanage/sysmenu/addSysmenu";
	}

	/**
	 * 删除菜单
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delSysmenu.action")
	public String delSysmenu(@ModelAttribute("model") Sysmenu model) throws Exception {
		String actionMsg = "";
		if (model.getId() != null) {
			if (model.getId() == 99999999) {
				return "根菜单不能删除！";
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("menuparent", model.getId());
			int i = sysmenuService.countSysmenu(p);
			if (i > 0) {
				return "该菜单有子菜单,不能删除！";
			}
			// 执行删除
			if (sysmenuService.delSysmenu(model.getId())) {
				actionMsg = "删除成功";
			} else {
				actionMsg = "删除失败";
			}
		} else {
			actionMsg = "删除失败";
		}
		return actionMsg;
	}

	/**
	 * 处理查看组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/viewSysmenu.action")
	public Sysmenu viewSysmenu(@ModelAttribute("model") Sysmenu model) throws Exception {
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的新闻
			Sysmenu menu = sysmenuService.loadSysmenu(model.getId());
			if (menu != null) {
				if (menu.getMenuparent() != null) {
					Sysmenu paremenu = sysmenuService.loadSysmenu(menu.getMenuparent());
					menu.setParentMenu(paremenu);
				}
				return menu;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/** 进入修改页面 */
	@RequestMapping("/editSysmenu.action")
	public String editSysmenu(@ModelAttribute("model") Sysmenu model, Model retmodel) {
		Sysmenu menu = sysmenuService.loadSysmenu(model.getId());
		if (menu.getMenuparent() != null) {
			Sysmenu paremenu = sysmenuService.loadSysmenu(menu.getMenuparent());
			menu.setParentMenu(paremenu);
		}
		retmodel.addAttribute("sysmenu", menu);
		return "sysmanage/sysmenu/editSysmenu";
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateSysmenu.action")
	public String updateSysmenu(@ModelAttribute("model") Sysmenu model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("menuname", model.getMenuname());
		where.put("notid", model.getId());
		List<Sysmenu> menuList = sysmenuService.browseSysmenuList(where);
		if (menuList.size() > 0) {
			retmodel.addAttribute("meg", "菜单名称已存在！");
			return "sysmanage/sysmenu/editSysmenu";
		}
		Sysmenu menu = sysmenuService.loadSysmenu(model.getId());
		BeanUtils.copyProperties(menu, model);
		// 修改
		if (sysmenuService.updateSysmenu(menu)) {
			BeanUtils.copyProperties(model, menu);
			retmodel.addAttribute("meg", "更新成功！");
		} else {
			retmodel.addAttribute("meg", "更新失败！");
		}
		if (menu.getMenuparent() != null) {
			Sysmenu pmenu = sysmenuService.loadSysmenu(menu.getMenuparent());
			menu.setParentMenu(pmenu);
		}
		retmodel.addAttribute("sysmenu", menu);
		return "sysmanage/sysmenu/editSysmenu";
	}

	/** 加载菜单树 */
	@ResponseBody
	@RequestMapping("/loadTree.action")
	public List<Map<String, Object>> loadTree(HttpServletRequest request) {
		String menuparent = request.getParameter("id");
		String level = request.getParameter("level");
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (menuparent == null) {
				map.put("id", 99999999);
		} else {
			map.put("menuparent", menuparent);
		}
		List<Sysmenu> menuList = sysmenuService.browseSysmenuList(map);
		try {
			List<Map<String, Object>> retlist = Tree.BuildTreeSysmenu(menuList, level == null ? "" : level);
			return retlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}