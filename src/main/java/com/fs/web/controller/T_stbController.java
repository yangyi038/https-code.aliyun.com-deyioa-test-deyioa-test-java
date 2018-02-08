package com.fs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fantastic.MyBeanUtils;
import com.fantastic.RespJsonPageData;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.model.TBind;
import com.fs.comm.model.THotel;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;
import com.fs.web.service.ColumnService;
import com.fs.web.service.T_bindService;

/**
 * 机顶盒管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_stb")
public class T_stbController extends BaseController {

	@Resource
	private ColumnService columnService;

	@Resource
	private T_bindService t_bindService;

	@Resource
	private TStbMapper t_stbMapper;

	@RequestMapping("/browseT_stb.action")
	/** 处理浏览订单表请求 */
	public String browseT_stb(HttpServletRequest request) {
		// 获取酒店名称列表
		request.setAttribute("hotellist", getHotelList(new HashMap<>()));
		// 获取机顶盒组列表
		request.setAttribute("stbgrouplist", getStbgrouplist());

		return "webmanage/t_stb/browseStb";
	}

	/** jqgrid组件列表-获取机顶盒列表 */
	@ResponseBody
	@RequestMapping("/listT_stb.action")
	public RespJsonPageData getStbList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取机顶盒列表
		List<TStb> stbList = t_stbService.browseStbList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(stbList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddStb.action")
	public String preAddStb(@ModelAttribute("model") TStb model, Model retmodel) {
		// 获取酒店列表
		List<THotel> hotellist = t_hotelService.getHotelList(new HashMap<>());
		retmodel.addAttribute("hotellist", hotellist);
		// 获取机顶盒组名称列表
		retmodel.addAttribute("stbgrouplist", getStbgrouplist());
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());

		return "webmanage/t_stb/addStb";
	}

	/**
	 * 添加机顶盒请求
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addStb.action")
	public String addStb(@ModelAttribute("model") TStb model, Model retmodel) {
		// 条件验证
		Map<String, Object> p;
		if (model.getSid() != null) {
			p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			TStb stbList = t_stbService.selectStb(p);
			if (stbList != null) {
				retmodel.addAttribute("meg", "该机顶盒已经存在,请检查！");
				return "webmanage/t_stb/addStb";
			}
		}
		if (model.getStbnum() != null) {
			p = new HashMap<String, Object>();
			p.put("stbnum", model.getStbnum());
			TStb stbList = t_stbService.selectStb(p);
			if (stbList != null) {
				retmodel.addAttribute("meg", "该机顶盒账号已经存在,请重新输入！");
				return "webmanage/t_stb/addStb";
			}
		}
		if (!model.getPwd().equals(model.getPwdsure())) {
			retmodel.addAttribute("meg", "两次输入密码不一样,请确认并重新输入！");
			return "webmanage/t_stb/addStb";
		}

		// mac地址正则格式
		String trueMacAddress = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
		if (StringUtils.isNotEmpty(model.getMac())) {
			if (model.getMac().length() == 12) {
				StringBuffer buff = new StringBuffer(model.getMac());
				int index;
				for (index = 2; index < buff.length(); index += 3) {
					buff.insert(index, ':');
				}
				model.setMac(buff.toString());
			}
			if (!model.getMac().matches(trueMacAddress)) {
				retmodel.addAttribute("meg", "mac输入格式不对,请确认并重新输入！");
				return "webmanage/t_stb/addStb";
			}
			// mac是否重复
			p = new HashMap<String, Object>();
			p.put("mac", model.getMac());
			TStb stbList = t_stbService.selectStb(p);
			if (stbList != null) {
				retmodel.addAttribute("meg", "该MAC地址已被机顶盒账号为：[" + stbList.getStbnum() + "]使用,请确认！");
				return "webmanage/t_stb/addStb";
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		// 添加
		if (t_stbService.insertStb(model)) {
			insertSysLog("添加", "机顶盒", "成功", currentUser().getLoginname() + "添加机顶盒成功");
			retmodel.addAttribute("meg", "添加机顶盒成功！");
		} else {
			retmodel.addAttribute("meg", "添加机顶盒失败！");
		}
		return "webmanage/t_stb/addStb";
	}

	/**
	 * 删除机顶盒
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delStb.action")
	public String delStb(@ModelAttribute("model") TStb model) throws Exception {
		if (model.getSid() != null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			int i = t_stbService.countStb(p);
			if (i <= 0) {
				return "该机顶盒不存在，请确认";
			}

			p = new HashMap<String, Object>();
			p.put("stbsid", model.getSid());
			List<TBind> bindList = t_bindService.selectBind(p);
			if (bindList != null && bindList.size() > 0) {
				return "该机顶盒已经绑定[" + bindList.get(0).getHotelname() + "],请先解绑！";
			}

			if (t_stbService.delStb(model.getSid())) {
				insertSysLog("删除", "机顶盒", "成功", currentUser().getLoginname() + "删除机顶盒成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 查看机顶盒信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStb.action")
	public String getStb(@ModelAttribute("model") TStb model, Model retmodel) throws Exception {
		if (model.getSid() != null) {
			TStb stb = t_stbService.getStbById(model.getSid());
			if (stb != null) {
				//
				retmodel.addAttribute("t_stb", stb);
				// 获取酒店列表
				retmodel.addAttribute("hotellist", getHotelList(new HashMap<>()));
				// 获取机顶盒组名称列表
				retmodel.addAttribute("stbgrouplist", getStbgrouplist());
				// 获取epg根栏目列表
				retmodel.addAttribute("columnlist", getColumnList());

				return "webmanage/t_stb/viewStb";
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
	@RequestMapping("/editStb.action")
	public String editOrderVideo(@ModelAttribute("model") TStb model, Model retmodel) {
		//
		TStb stb = t_stbService.getStbById(model.getSid());
		retmodel.addAttribute("t_stb", stb);
		// 获取酒店列表
		retmodel.addAttribute("hotellist", getHotelList(new HashMap<>()));
		// 获取机顶盒组名称列表
		retmodel.addAttribute("stbgrouplist", getStbgrouplist());
		// 获取epg根栏目列表
		retmodel.addAttribute("columnlist", getColumnList());

		return "webmanage/t_stb/editStb";
	}

	/**
	 * 修改机顶盒
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateStbById.action")
	public String updateStbById(@ModelAttribute("model") TStb model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TStb stb = t_stbService.getStbById(model.getSid());
		// 验证合法性
		Map<String, Object> p;
		if (model.getStbnum() != null && !model.getStbnum().equals(stb.getStbnum())) {
			p = new HashMap<String, Object>();
			p.put("stbnum", model.getStbnum());
			TStb stbList = t_stbService.selectStb(p);
			if (stbList != null) {
				retmodel.addAttribute("meg", "该机顶盒账号已经存在,请重新输入！");
				return "webmanage/t_stb/editStb";
			}
		}
		if (!model.getPwd().equals(model.getPwdsure())) {
			retmodel.addAttribute("meg", "两次输入密码不一样,请确认并重新输入！");
			return "webmanage/t_stb/editStb";
		}
		// mac地址正则格式
		String trueMacAddress = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
		if (StringUtils.isNotEmpty(model.getMac()) && !model.getMac().equals(stb.getMac())) {
			if (model.getMac().length() == 12) {
				StringBuffer buff = new StringBuffer(model.getMac());
				int index;
				for (index = 2; index < buff.length(); index += 3) {
					buff.insert(index, ':');
				}
				model.setMac(buff.toString());
			}
			if (!model.getMac().matches(trueMacAddress)) {
				retmodel.addAttribute("meg", "mac输入格式不对,请确认并重新输入！");
				return "webmanage/t_stb/editStb";
			}
			// mac是否重复
			p = new HashMap<String, Object>();
			p.put("mac", model.getMac());
			TStb stbList = t_stbService.selectStb(p);
			if (stbList != null) {
				retmodel.addAttribute("meg", "该MAC地址已被机顶盒账号为：[" + stbList.getStbnum() + "]使用,请确认！");
				return "webmanage/t_stb/editStb";
			}
		}

		// model属性复制给stb
		MyBeanUtils.copyBeanNotNull2Bean(model, stb);
		// 执行修改
		if (t_stbService.updateStbById(stb)) {
			MyBeanUtils.copyBeanNotNull2Bean(stb, model);

			insertSysLog("修改", "机顶盒", "成功", currentUser().getLoginname() + "修改机顶盒成功");
			retmodel.addAttribute("meg", "该机顶盒修改成功！");
		} else {
			retmodel.addAttribute("meg", "该机顶盒修改失败！");
		}
		retmodel.addAttribute("t_stb", stb);
		return "webmanage/t_stb/editStb";
	}

	/**
	 * 进入导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/importStbPre.action")
	public String importStbPre(@ModelAttribute("model") TStb model, Model retmodel) {
		List<TStbGroup> list = t_stb_groupService.getAllStbGroup();
		retmodel.addAttribute("list", list);
		return "webmanage/t_stb/import_stb";
	}

	/**
	 * excel批导入机顶盒
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @param filePath
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/importStb.action")
	public String importStb(@ModelAttribute("model") TStb model, Model retmodel, HttpServletRequest request,
			@RequestParam(value = "filePath", required = false) MultipartFile filePath)
			throws IllegalStateException, IOException {

		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("UTF-8"));
		headers.setContentType(mt);
		Workbook hssfBook = null;
		InputStream is = filePath.getInputStream();// 获取excel数据
		if (filePath.getOriginalFilename().contains(".xlsx")) {
			hssfBook = new XSSFWorkbook(is); // excel2007
		} else {
			hssfBook = new HSSFWorkbook(is); // excel2003
		}

		Sheet sheet = hssfBook.getSheetAt(0);
		Row row = sheet.getRow(0);// 第一行数据
		int rowNum = sheet.getLastRowNum();
		if (rowNum == 0) {// 指表头
			retmodel.addAttribute("errorMeg", "您上传的excel没有数据！");
			return "webmanage/t_stb/import_stb";
		}
		String[] tableHeader = new String[] { "机顶盒账号", "mac地址", "机顶盒密码" };// stbid

		// 验证表头格式是否正确
		for (int i = 0; i < tableHeader.length; i++) {
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			if (!tableHeader[i].trim().equals(row.getCell(i).getStringCellValue().trim())) {
				retmodel.addAttribute("errorMeg", "模板列头不正确！");
				return "webmanage/t_stb/import_stb";
			}
		}

		List<TStb> list = new ArrayList<TStb>();
		for (Row r : sheet) {
			if (r.getRowNum() < 1) {
				continue;
			}
			TStb stb = new TStb();
			// 机顶盒账号
			if (null == r.getCell(0) && null != r.getCell(1)) {
				r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				String mac = r.getCell(1).getStringCellValue();
				mac = mac.replaceAll(":", "");
				stb.setStbnum(mac);
			} else {
				r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				stb.setStbnum(r.getCell(0).getStringCellValue());
			}

			// mac地址
			if (null == r.getCell(1)) {
				String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "MAC地址是空值";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_stb/import_stb";
			}
			stb.setMac(r.getCell(1).getStringCellValue());
			r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

			// pwd
			stb.setPwd(r.getCell(2).getStringCellValue());
			r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);

			// 设置其他信息
			stb.setCompanyid(currentUser().getCompanyid());
			stb.setOperatorid(currentUser().getOperatorid());
			stb.setDealerid(currentUser().getDealerid());

			String groupName = model.getStbgroupname();
			if (null != groupName && !"".equals(groupName)) {
				TStbGroup record = t_stb_groupService.getTStbGroupByGrpupName(groupName);
				if (null != record) {
					stb.setStbgroup(record.getSid().toString());// 机顶盒组
				}
			}
			stb.setStbtype(model.getStbtype());// 机顶盒类型
			stb.setRomversion(model.getRomversion());// 应用版本号
			stb.setRomfirmware(model.getRomfirmware());// 固件版本号
			stb.setNumFlag(r.getRowNum() + 1);

			list.add(stb);
		}

		// 判断该Excel中是否有重复数据
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getStbnum().equals(list.get(j).getStbnum())) {
					Integer rowFlag = list.get(j).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行机顶盒账号是重复数据";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_stb/import_stb";
				}

				if (list.get(i).getMac().equals(list.get(j).getMac())) {
					Integer rowFlag = list.get(j).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行MAC地址是重复数据";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_stb/import_stb";
				}

			}
		}

		// 验证MAC地址格式及与数据库MAC地址是否重复
		String trueMacAddress = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
		List<TStb> stbList = t_stbMapper.getStbList(null);
		for (int i = 0; i < list.size(); i++) {
			String mac = list.get(i).getMac();
			if (StringUtils.isNotEmpty(mac)) {
				if (list.get(i).getMac().length() == 12) {
					StringBuffer buff = new StringBuffer(mac);
					int index;
					for (index = 2; index < buff.length(); index += 3) {
						buff.insert(index, ':');
					}
					mac = buff.toString();
				}
				if (!mac.matches(trueMacAddress)) {
					Integer rowFlag = list.get(i).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行MAC地址格式不正确";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_stb/import_stb";
				}

			}

			// 验证MAC地址是否已存在数据库
			for (int j = 0; j < stbList.size(); j++) {
				if (list.get(i).getMac().equals(stbList.get(j).getMac())) {
					Integer rowFlag = list.get(i).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行MAC地址已存在";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_stb/import_stb";
				}
				if (list.get(i).getStbnum().equals(stbList.get(j).getStbnum())) {
					Integer rowFlag = list.get(i).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行机顶盒账号已存在";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_stb/import_stb";
				}
			}

		}

		int result = t_stbService.importStb(list);
		if (result > 0) {
			retmodel.addAttribute("meg", "导入成功");
		} else {
			retmodel.addAttribute("meg", "导入失败");
		}
		return "webmanage/t_stb/import_stb";
	}

	/**
	 * 模板下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadStbTemplate.action")
	public void downloadStbTemplate(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("GBK");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(("导入机顶盒标准模板" + ".xlsx").getBytes(), "iso-8859-1"));// 下载文件的名称
			String fullPath = getPath("importStbTemplate", "xlsx");// 模板名称
			File file = new File(fullPath);// 创建新文件
			if (file != null && !file.exists()) {
				file.createNewFile();
			}
			OutputStream output = response.getOutputStream();
			InputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[4 * 1024];
			int byteRead = -1;
			while ((byteRead = (inputStream.read(buffer))) != -1) {
				output.write(buffer, 0, byteRead);
			}
			output.flush();
			inputStream.close();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取模板路径
	 * 
	 * @param fileName
	 * @param suffix
	 * @return
	 */
	private String getPath(String fileName, String suffix) {
		String local_file_path = FixingController.class.getResource(File.separator).getPath();
		local_file_path = local_file_path.substring(0, local_file_path.indexOf("WEB-INF"));
		String dir = local_file_path + "excel" + File.separator;
		String fullPath = dir + fileName + "." + suffix;
		return fullPath;
	}

}
