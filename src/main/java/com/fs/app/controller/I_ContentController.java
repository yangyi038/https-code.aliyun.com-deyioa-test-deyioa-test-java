package com.fs.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.app.common.I_BaseController;
import com.fs.app.common.I_Result;
import com.fs.comm.enums.ProgramType;
import com.fs.comm.model.Column;
import com.fs.comm.model.ImageText;
import com.fs.comm.model.Program;
import com.fs.comm.model.TStb;
import com.fs.web.service.ColumnService;
import com.fs.web.service.ImageTextService;
import com.fs.web.service.ProgramService;
import com.fs.web.service.T_stbService;

/**
 * 图文信息相关接口
 * 
 * @author pzj
 *
 */
@RequestMapping("/api/content/")
@Controller  class I_ContentController extends I_BaseController {

	@Resource
	private ImageTextService imageTextService;

	@Resource
	private T_stbService stbService;

	@Resource
	private ColumnService columnService;

	@Resource
	private ProgramService programService;

	/**
	 * 递归算法解析成树形结构
	 *
	 * @param cid
	 *            节点ID
	 * @return
	 * @author pzj
	 */
	private Column recursiveTree(int cid) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		Column node = columnService.queryColumnById(cid);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		Map<String, Object> map = new HashMap<>();
		map.put("parentid", cid);
		List<Column> childColumns = columnService.getRootColumnList(map);
		// 遍历子节点
		for (Column child : childColumns) {
			Column n = recursiveTree(child.getId()); // 递归
			node.getChildDepartment().add(n);
		}

		return node;
	}

	/**
	 * 获取首页栏目列表
	 * 
	 * @param request
	 *            说明： 栏目的内容返回，由机顶盒添加处进行配置，按从 手机栏目---定制栏目---主栏目的顺序返回
	 * @return http://localhost:6080/hotelPro/api/content/programIndex?userid=10010&userToken=1
	 */
	@RequestMapping("programIndex")
	@ResponseBody
	public Map<String, Object> programIndex(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		// 请求参数
		String stbnum = request.getParameter("userid");// 机顶盒账号
		String token = request.getParameter("userToken");// Token

		if (StringUtils.isEmpty(stbnum)) {
			result.put("info", "机顶盒账号不能为空！");
			result.put("code", I_Result.CODE_NULL);
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.put("info", "token不能为空！");
			result.put("code", I_Result.CODE_NULL);
			return result;
		} else {
			// 验证token
		}

		Map<String, Object> stbmap = new HashMap<>();
		stbmap.put("stbnum", Integer.parseInt(stbnum));
		// 机顶盒对象
		TStb entity = stbService.selectStb(stbmap);

		// Map<String, Object> returnMap = new HashMap<String, Object>();
		if (entity != null) {
			// 获取机顶盒栏目ID
			Integer columnid;
			if (StringUtils.isNotBlank(entity.getTelroot())) {// 手机栏目
				columnid = Integer.parseInt(entity.getTelroot());
			} else if (StringUtils.isNotBlank(entity.getEpgprivate())) {// 定制栏目
				columnid = Integer.parseInt(entity.getEpgprivate());
			} else {// 主栏目
				columnid = Integer.parseInt(entity.getEpgroot());
			}
			// 获取其下级栏目
			Map<String, Object> colmap = new HashMap<>();
			colmap.put("parentid", columnid);
			List<Column> childColumns = columnService.getRootColumnList(colmap);
			if (childColumns != null && childColumns.size() > 0) {
				List<Map<String, Object>> newchild = new ArrayList<>();
				for (Column column : childColumns) {
					Map<String, Object> child = new HashMap<>();
					child.put("id", column.getId());// 栏目ID
					child.put("name", column.getName());// 栏目名称
					child.put("logo", null);// 栏目logo图片
					// 是否有子栏目
					Map<String, Object> submap = new HashMap<>();
					submap.put("parentid", column.getId());
					List<Column> subcol = columnService.getRootColumnList(submap);
					child.put("subCategoryUrl", subcol != null && subcol.size() > 0 ? 1 : 0);// 子栏目请求地址:是否有子栏目

					child.put("programListUrl", null);// 栏目节目列表 url 接口地址
					child.put("type", column.getType());// 栏目类型
					child.put("poster", null);

					newchild.add(child);
				}
				result.put("category", newchild);// 一级栏目列表
			}
		}

		result.put("info", "获取栏目列表成功！");
		result.put("code", I_Result.CODE_OK);
		System.out.println(result);
		return result;
	}

	/**
	 * 根据栏目ID，获取其下级栏目列表
	 * 
	 * @param request
	 * @return  http://localhost:6080/hotelPro/api/content/getLowerColumnList?cid=47&userToken=1
	 */
	@RequestMapping("getLowerColumnList")
	@ResponseBody
	public Map<String, Object> getLowerColumnList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		// 请求参数
		String columnid = request.getParameter("cid");// 栏目ID
		String token = request.getParameter("userToken");// Token

		if (StringUtils.isEmpty(columnid)) {
			result.put("info", "栏目ID不能为空！");
			result.put("code", I_Result.CODE_NULL);
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.put("info", "token不能为空！");
			result.put("code", I_Result.CODE_NULL);
			return result;
		} else {
			// 验证token
		}

		// 获取其下级栏目
		Map<String, Object> colmap = new HashMap<>();
		colmap.put("parentid", columnid);
		List<Column> childColumns = columnService.getRootColumnList(colmap);
		if (childColumns != null && childColumns.size() > 0) {
			List<Map<String, Object>> newchild = new ArrayList<>();
			for (Column column : childColumns) {
				Map<String, Object> child = new HashMap<>();
				child.put("id", column.getId());// 栏目ID
				child.put("name", column.getName());// 栏目名称
				child.put("logo", null);// 栏目logo图片
				// 是否有子栏目
				Map<String, Object> submap = new HashMap<>();
				submap.put("parentid", column.getId());
				List<Column> subcol = columnService.getRootColumnList(submap);
				child.put("subCategoryUrl", subcol != null && subcol.size() > 0 ? 1 : 0);// 子栏目请求地址:是否有子栏目

				child.put("programListUrl", null);// 栏目节目列表 url 接口地址
				child.put("type", column.getType());// 栏目类型
				child.put("poster", null);

				newchild.add(child);
			}
			result.put("category", newchild);// 一级栏目列表
		}

		result.put("info", "获取栏目列表成功！");
		result.put("code", I_Result.CODE_OK);
		return result;
	}

	/**
	 * 查询一个栏目的节目列表，节目可以是电影，直播，素材图片，app 应用，图文信息，栏目混合。
	 * 
	 * @param request
	 * @return http://localhost:6080/hotelPro/api/content/getProgramListByCategoryId?cid=47&userToken=1
	 */
	@RequestMapping("getProgramListByCategoryId")
	@ResponseBody
	public Map<String, Object> getProgramListByCategoryId(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		// 请求参数
		String columnid = request.getParameter("cid");// 栏目id
		String token = request.getParameter("userToken");// Token

		if (StringUtils.isEmpty(columnid)) {
			result.put("code", I_Result.CODE_NULL);
			result.put("info", "栏目id不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.put("code", I_Result.CODE_NULL);
			result.put("info", "token不能为空！");
			return result;
		} else {
			// 验证token
		}

		Map<String, Object> map = new HashMap<>();
		map.put("columnid", Integer.parseInt(columnid));
		List<Program> list = programService.getProgramListByColumnId(map);
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> proList = new ArrayList<>();
			for (Program program : list) {
				Map<String, Object> promap = new HashMap<>();
				promap.put("id", program.getId());// 节目id
				promap.put("name", program.getName());// 节目名称
				promap.put("type", program.getProgramType());// 节目类型
				promap.put("imageUrl", null);// 节目logo图片
				promap.put("packageName", null);// 包路径，只对应用类型节目有效
				promap.put("apkconfig", null);// App 参数配置，只对应用类型节目有效
				promap.put("detailUrl", null);// 查询节目详细接口地址，除栏目类型节目，
												// 栏目类型节目不需要查询详情
				promap.put("programListUrl", null);// 该字段主要针对栏目类型节目，可以继续
													// 查询栏目下的节目列表
				// 说明：
				// 欢迎词栏目的内容返回，查找顺序为，单个机顶盒定义—酒店用户组定义—栏目挂载（从字幕处选择）

				proList.add(promap);
			}

			result.put("list", proList);// 节目列表
			result.put("totalNum", proList.size());// 节目总数
		}

		result.put("code", I_Result.CODE_OK);
		result.put("info", "获取栏目下节目列表成功！");
		return result;
	}

	/**
	 * 获取图文信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getImageTextDetail")
	@ResponseBody
	public I_Result<Map<String, Object>> getImageTextDetail(HttpServletRequest request) {
		I_Result<Map<String, Object>> result = new I_Result<Map<String, Object>>();
		// 请求参数
		String id = request.getParameter("id");// 图文id
		String token = request.getParameter("usertoken");// Token

		if (StringUtils.isEmpty(id)) {
			result.setResult(I_Result.CODE_NULL, "图文id不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.setResult(I_Result.CODE_NULL, "token不能为空！");
			return result;
		} else {
			// 验证token
		}

		ImageText entity = imageTextService.queryImageTextbyId(Integer.parseInt(id));

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (entity != null) {
			returnMap.put("id", entity.getId());// 图文信息 id
			returnMap.put("content", entity.getContent());// 图文信息详情内容
			returnMap.put("title", entity.getName());// 图文信息标题
			// 详情信息内容格式，0：文字，1：图片，2：
			// 视频,3：HTML,4：关联展示
			returnMap.put("displayFormat", entity.getShowType());// 显示格式

			returnMap.put("imageurl", null);// displayFormat是视频时，视频的缩略图
			// 当 displayFormat=0 或者 1，为图文信息内容图片连接地址列表
			// displayFormat=2，为 视频连 接 地 址 列 表 ，
			// 当displayFormat=3，为图文信息 html 连接地址列表，
			// 当 displayFormat=4，位图文信息内容图片列表
			returnMap.put("fileurls", null);
		}

		// 返回酒店信息对象
		result.setData(returnMap);
		result.setResult(I_Result.CODE_OK, "获取图文信息成功！");
		return result;
	}

	/**
	 * 获取欢迎词
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getWellText")
	@ResponseBody
	public I_Result<Map<String, Object>> getWellText(HttpServletRequest request) {
		I_Result<Map<String, Object>> result = new I_Result<Map<String, Object>>();
		// 请求参数
		String stbid = request.getParameter("userid");// 机顶盒id
		String token = request.getParameter("usertoken");// Token

		if (StringUtils.isEmpty(stbid)) {
			result.setResult(I_Result.CODE_NULL, "机顶盒id不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.setResult(I_Result.CODE_NULL, "token不能为空！");
			return result;
		} else {
			// 验证token
		}

		TStb entity = stbService.getStbById(Integer.parseInt(stbid));

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (entity != null) {
			returnMap.put("text", entity.getWelcome());// 获取欢迎词
		}

		result.setData(returnMap);
		result.setResult(I_Result.CODE_OK, "获取欢迎词成功！");
		return result;
	}

	/**
	 * 获取栏目下应用列表
	 * 
	 * @param request
	 * @return http://localhost:6080/hotelPro/api/content/getApp?cid=67&userToken=1
	 */
	@RequestMapping("getApp")
	@ResponseBody
	public Map<String, Object> getApp(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		// 请求参数
		String columnid = request.getParameter("cid");// 栏目id
		String token = request.getParameter("userToken");// Token

		if (StringUtils.isEmpty(columnid)) {
			result.put("code",I_Result.CODE_NULL);
			result.put("info", "栏目id不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(token)) {
			result.put("code",I_Result.CODE_NULL);
			result.put("info",  "token不能为空！");
			return result;
		} else {
			// 验证token
		}

		Map<String, Object> map = new HashMap<>();
		map.put("columnid", Integer.parseInt(columnid));
		map.put("programtype", ProgramType.应用.getValue());
		List<Program> list = programService.getProgramListByColumnId(map);
		List<Map<String, Object>> proList=new ArrayList<>();
		if (list != null && list.size()>0) {
			for (Program program : list) {
				Map<String, Object> pro = new HashMap<>();
				pro.put("id", program.getId());//应用id
				pro.put("name", program.getName());//应用名字
				pro.put("packagename", null);//应用包名
				pro.put("apkconfig", null);//应用配置参数
				pro.put("poster", null);//应用缩略图
				pro.put("intro", null);//应用介绍
				pro.put("edition", null);//包路径，只对应用类型节目有效
				pro.put("downloadlink", null);//App 参数配置，只对应用类型节目有效
				
				proList.add(pro);
			}
		}
		result.put("application", proList);// 应用列表

		result.put("code",I_Result.CODE_OK);
		result.put("info",  "获取栏目下应用列表成功！");
		return result;
	}
}
