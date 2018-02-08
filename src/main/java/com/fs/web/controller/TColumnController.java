package com.fs.web.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.framework.Tree;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.Column;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.ColumnService;
import com.fs.web.service.ProgramService;
import com.fs.web.service.SysLogService;
import com.fs.web.service.TtitleService;

/**
 * 栏目管理 
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_column")
public class TColumnController {

	@Resource
	private ColumnService columnService;

	@Resource
	private TtitleService titleService;

	@Resource
	private ProgramService programService;

	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/column_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_picture() {
		return "sysmanage/t_column/index_column";
	}

	/**
	 * 浏览栏目列表
	 */
	@ResponseBody
	@RequestMapping("/listT_column.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listT_Picture(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);

		List<Map<String, Object>> t_picture = columnService.getAllColumnInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_picture, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/agreeColumn.action")
	public Map<String, Object> agree(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int result = columnService.agreeColumnById(Integer.parseInt(id));
		if (result == 1) {
			return ActionUtil.ajaxSuccess("审核通过", "");
		} else {
			return ActionUtil.ajaxSuccess("审核失败", "");
		}

	}

	@ResponseBody
	@RequestMapping("/unAgreeColumn.action")
	public Map<String, Object> unAgree(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int result = columnService.unAgreeColumnById(Integer.parseInt(id));
		if (result == 1) {
			return ActionUtil.ajaxSuccess("取消审核成功", "");
		} else {
			return ActionUtil.ajaxSuccess("取消审核失败", "");
		}

	}

	/***********************************************************************************************************/

	/** 加载组织机构树 */
	@ResponseBody
	@RequestMapping("/loadTree.action")
	public List<Map<String, Object>> loadTree(HttpServletRequest request) {
		String parent = request.getParameter("id");
		Map<String, Object> where = new HashMap<String, Object>();

		String level = request.getParameter("level");
		if (parent == null) {
			where.put("id", 1);
		} else {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
			Long companyid = sysuser.getCompanyid();
			where.put("companyid", companyid);
			where.put("parent", parent);
		}
		List<Column> columnList = columnService.browseColumn(where);
		try {
			List<Map<String, Object>> retlist = Tree.BuildTreeColumn(columnList, level == null ? "" : level);
			return retlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 处理查看组织机构请求
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/viewColumn.action")
	public Column viewDepartment(@ModelAttribute("model") Column model) throws Exception {
		if (model.getId() != null) {
			Column tempColumn = columnService.queryColumnById(model.getId());

			if (tempColumn != null) {
				if (tempColumn.getParentId() != null) {
					Column column = columnService.queryColumnById(model.getId());
					tempColumn.setParentColumn(column);

				}
				return tempColumn;

			} else {

				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showT_column.action")
	public String showT_column(@ModelAttribute("model") Column model, Model retmodel) {
		// List<Column> columnList = columnService.selectColumnClassify();
		Column column = null;
		column = columnService.queryColumnById(model.getId());

		retmodel.addAttribute("column", column);

		return "sysmanage/t_column/add_column";
	}

	/**
	 * 新增栏目
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/add_column.action")
	public String addT_picture(@ModelAttribute("model") Column model, Model retmodel) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", model.getName());
		p.put("parent", model.getParentId());
		List<Column> columnList = columnService.browseColumn(p);
		if (columnList.size() > 0) {
			retmodel.addAttribute("meg", "该栏目已存在！");
			return "sysmanage/t_column/add_column";
		}

		boolean flag = columnService.insertColumn(model);
		SysLog sysLog = this.getSysLog();

		if (flag) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("栏目");
		sysLog.setOperdes("添加栏目" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		retmodel.addAttribute("column", model);
		return "sysmanage/t_column/add_column";
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/delColumn.action") public Map<String, Object>
	 * updateRecaccount(HttpServletRequest request,HttpServletResponse
	 * response){ String id= request.getParameter("id"); int result =
	 * columnService.delColumnById(Integer.parseInt(id)); if(result==1){ return
	 * ActionUtil.ajaxSuccess("状态更新成功", ""); }else{ return
	 * ActionUtil.ajaxSuccess("状态更新失败", ""); }
	 * 
	 * }
	 */

	@ResponseBody
	@RequestMapping("/delColumn.action")
	public Map<String, Object> updateRecaccount(@ModelAttribute("model") Column model) {
		String actionMsg = "";
		SysLog sysLog = this.getSysLog();
		if (model.getId() != null) {
			if (model.getId() == 1) {
				sysLog.setOperresult("失败");
				return ActionUtil.ajaxFail("顶级分类不能删除", "");
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("parentId", model.getId());
			int i = columnService.countColumn(p);
			if (i > 0) {
				sysLog.setOperresult("失败");
				return ActionUtil.ajaxFail("该栏目有子栏目,不能删除！", "");
			}
			int result = columnService.delColumnById(model.getId());
			if (result > 0) {
				sysLog.setOperresult("成功");
				actionMsg = "删除成功";
			} else {
				sysLog.setOperresult("失败");
				actionMsg = "删除失败";
			}
		} else {
			sysLog.setOperresult("失败");
			actionMsg = "删除失败";
		}

		sysLog.setOpertype("删除");
		sysLog.setOperobject("栏目");
		sysLog.setOperdes("删除栏目" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ** 返回修改页面*//*
	@RequestMapping("/showColumnWindow.action")
	public String showColumnWindow(@ModelAttribute("model") Column model, Model retmodel) {
		Column column = columnService.queryColumnById(model.getId());
		List<Column> columnList = columnService.selectColumnClassify();
		retmodel.addAttribute("column", column);
		retmodel.addAttribute("columnList", columnList);
		return "sysmanage/t_column/edit_column";

	}

	// ** 处理装载系统用户请求 *//*
	@RequestMapping("/updateColumn.action")
	public String updateColumn(@ModelAttribute("model") Column model, Model retmodel) {

		Map<String, Object> where = new HashMap<String, Object>();
		where.put("name", model.getName());
		where.put("notid", model.getId());
		SysLog sysLog = this.getSysLog();
		List<Column> columnList = columnService.browseColumn(where);
		if (columnList.size() > 0) {
			retmodel.addAttribute("meg", "该栏目已存在！");
			sysLog.setOperresult("失败");
			return "sysmanage/t_column/edit_column";
		}

		int result = columnService.updateColumn(model);

		sysLog.setOpertype("修改");
		sysLog.setOperobject("栏目");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改栏目" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("column", model);
		return "sysmanage/t_column/edit_column";
	}

	/**
	 * 跳转到栏目列表
	 * 
	 * @return
	 */
	@RequestMapping("/column_info.action")
	public String queryColumnINfo(@ModelAttribute("model") Column model, Model retmodel) {
		Integer id = model.getId();
		retmodel.addAttribute("column", model);
		return "sysmanage/t_column/program_title";

	}

	/**
	 * 查询字幕节目
	 */
	@ResponseBody
	@RequestMapping("/list_titleColumn.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listTitleColumn(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		p.put("id", id);
		p.put("name", name);
		List<Map<String, Object>> t_title = programService.getAlltitleColumn(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_title, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 处理上传图片请求 */
	@RequestMapping("/showT_titleColumn.action")
	public String showAddProgram(@ModelAttribute("model") Column model, Model retmodel) {
		return "sysmanage/t_column/add_program";
	}

	/**
	 * 查询字幕节目
	 */
	@ResponseBody
	@RequestMapping("/list_title.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listTitle(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		List<Map<String, Object>> t_title = titleService.getAllTitleInfo(jqGridPager, p);

		try {
			JqGridTool.jqGridQuery(request, response, t_title, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询字幕节目
	 */
	@ResponseBody
	@RequestMapping("/list_columnType.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listTitleColumnType(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		String type = request.getParameter("type");
		if ("不限".equals(type)) {
			p.put("type", "");
		} else {
			p.put("type", type);
		}
		List<Map<String, Object>> t_title = programService.getAlltitleColumnType(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, t_title, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转到栏目列表
	 * 
	 * @return
	 */
	@RequestMapping("/column_type.action")
	public String queryColumnType(@ModelAttribute("model") Column model, Model retmodel) {
		retmodel.addAttribute("column", model);
		return "sysmanage/t_column/add_program";

	}

	/**
	 * 添加节目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addProgram.action")
	public Map<String, Object> unlineChannel(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");

		if ("".equals(id) || null == id) {
			return null;
		}
		String columnId = request.getParameter("columnId");
		Column column = columnService.queryColumnById(Integer.parseInt(columnId));

		String columnName = request.getParameter("columnName");
		String name = request.getParameter("name");
		Program tempPropgram = programService.getProgramByColumnId(Integer.parseInt(columnId), name);
		if (null != tempPropgram) {
			return ActionUtil.ajaxFail("该节目已存在", "");
		}

		ColumnType model = programService.getColumnTypeById(Integer.parseInt(id));
		Program program = new Program();

		if ("".equals(columnName) || null == columnName) {
			program.setName("");
		} else {
			program.setName(columnName);
		}

		if ("".equals(columnId) || null == columnId) {
			program.setColumnId(0);
		} else {
			program.setColumnId(Integer.parseInt(columnId));
		}

		String columnType = request.getParameter("columnType");
		if ("".equals(columnType) || null == columnType) {
			program.setType("");
		} else {
			program.setType(columnType);
		}
		program.setContent(model.getName());
		program.setStatus(model.getStatus());
		program.setProgramType(model.getColumnType());
		program.setNumber(0);
		program.setIsDelete("n");
		program.setSeat(0);
		program.setShowType(column.getShowType());
		program.setColumntypeId((long) model.getId());
		int result = programService.insertProgram(program);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("添加");
		sysLog.setOperobject("节目");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("添加节目" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("添加成功", "");
		} else {
			return ActionUtil.ajaxSuccess("添加失败", "");
		}

	}

	@ResponseBody
	@RequestMapping("/del_program.action")
	public Map<String, Object> delProgram(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		int result = programService.delProgramById(Integer.parseInt(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("节目");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除节目" + name);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			return ActionUtil.ajaxSuccess("删除成功", "");
		} else {
			return ActionUtil.ajaxFail("删除失败", "");
		}

	}

	/**
	 * 根據ID查詢信息并返回修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditWindow.action")
	public String showEditWindow(@ModelAttribute("model") Program model, Model retmodel) {
		Long id = model.getId();
		Program propgram = programService.getProgramById(Integer.parseInt(String.valueOf(id)));
		retmodel.addAttribute("propgram", propgram);
		return "sysmanage/t_column/edit_program";
	}

	/** 处理装载系统用户请求 */
	@RequestMapping("/updateProgram.action")
	public String updateChannel(@ModelAttribute("model") Program model, Model retmodel) {
		int result = programService.updateProgramById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("节目");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改节目" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		return "sysmanage/t_column/edit_program";
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
