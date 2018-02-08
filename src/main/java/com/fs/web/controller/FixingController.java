package com.fs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.jqgrid.JqGridTool;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.Card;
import com.fs.comm.model.Fixing;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.FixingService;
import com.fs.web.service.SysLogService;

/**
 * 前端设备的云端管理
 * 
 * @author jzb
 *
 */
@RequestMapping("/admin/t_fixing")
@Controller
public class FixingController {

	@Resource
	private FixingService fixingService;

	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/fixing_manage.action")
	/** 处理浏览 用户表请求 */
	public String T_index() {
		return "webmanage/t_fixing/browsefixing";
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showAddFixing.action")
	public String showAddFixing(@ModelAttribute("model") Fixing model, Model retmodel) {
		return "webmanage/t_fixing/add_fixing";
	}

	/** 添加 */
	@RequestMapping("/add_Fixing.action")
	public String addImageText(@ModelAttribute("model") Fixing model, Model retmodel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (model.getTempProductionData() != null) {
			try {
				Date productionData = sdf.parse(model.getTempProductionData());
				model.setProductionData(productionData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempOpeningData() != null) {
			try {
				Date openingData = sdf.parse(model.getTempOpeningData());
				model.setOpeningData(openingData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempClosingData() != null) {
			try {
				Date closingData = sdf.parse(model.getTempClosingData());
				model.setClosingData(closingData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		boolean result = fixingService.addFixing(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("前段设备");
		sysLog.setOperdes("添加前段设备" + model.getFixingSn());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_fixing/add_fixing";
	}

	@ResponseBody
	@RequestMapping("/listFixing.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listFixing(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);

		List<Map<String, Object>> list = fixingService.getAllFixingInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, list, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 根据Id删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_fixing.action")
	public Map<String, Object> delFixing(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String fixingSn = request.getParameter("fixingSn");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("删除失败", "");
		}

		int result = fixingService.delFixingById(Long.parseLong(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("前端设备");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除前端设备" + fixingSn);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
		}

	}

	/**
	 * 根據ID查詢信息并返回修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditFixing.action")
	public String showEditFixing(@ModelAttribute("model") Fixing model, Model retmodel) {
		Long id = model.getId();
		Fixing fixing = fixingService.queryFixingById(id);
		retmodel.addAttribute("fixing", fixing);
		return "/webmanage/t_fixing/edit_fixing";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatetFixing.action")
	public String updatetFixing(@ModelAttribute("model") Fixing model, Model retmodel) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (model.getTempProductionData() != null) {
			try {
				Date productionData = sdf.parse(model.getTempProductionData());
				model.setProductionData(productionData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempOpeningData() != null) {
			try {
				Date openingData = sdf.parse(model.getTempOpeningData());
				model.setOpeningData(openingData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (model.getTempClosingData() != null) {
			try {
				Date closingData = sdf.parse(model.getTempClosingData());
				model.setClosingData(closingData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int result = fixingService.updateFixingById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("前端设备");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改前端设备" + model.getFixingSn());
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
		return "/webmanage/t_fixing/edit_fixing";
	}

	/**
	 * 查看
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/queryFixingInfo.action")
	public String queryFixingInfo(@ModelAttribute("model") Fixing model, Model retmodel) {

		Long id = model.getId();
		Fixing fixing = fixingService.queryFixingById(id);
		retmodel.addAttribute("fixing", fixing);
		return "/webmanage/t_fixing/fixing_info";
	}

	/**
	 * 进入导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showImportFixing.action")
	public String showImportFixing(@ModelAttribute("model") Fixing model, Model retmodel) {

		return "webmanage/t_fixing/import_fixing";
	}

	/** 导入 */
	@RequestMapping("/importFixing.action")
	public String importFixing(@ModelAttribute("model") Fixing model, Model retmodel, HttpServletRequest request,
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
			return "webmanage/t_fixing/import_fixing";
		}
		String[] tableHeader = new String[] { "设备号", "用户组", "出厂日期" };

		// 验证表头格式是否正确
		for (int i = 0; i < tableHeader.length; i++) {
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			if (!tableHeader[i].trim().equals(row.getCell(i).getStringCellValue().trim())) {
				retmodel.addAttribute("errorMeg", "模板列头不正确！");
				return "webmanage/t_fixing/import_fixing";
			}
		}

		List<Fixing> list = new ArrayList<Fixing>();
		for (Row r : sheet) {
			if (r.getRowNum() < 1) {
				continue;
			}
			Fixing fixing = new Fixing();
			if (null != r.getCell(0)) {

				r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				String fixingSn = r.getCell(0).getStringCellValue();
				fixing.setFixingSn(fixingSn);
				fixing.setNumFlag(r.getRowNum() + 1);
				if (null != r.getCell(1)) {
					r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String userGroup = r.getCell(1).getStringCellValue();
					fixing.setUserGroup(userGroup);
				}
				if (null != r.getCell(2)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date productionData = r.getCell(2).getDateCellValue();
					fixing.setProductionData(productionData);
				}
			} else {
				String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "设备号是空值";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_fixing/import_fixing";
			}
			list.add(fixing);
		}
		// 判断该Excel中是否有重复数据
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getFixingSn().equals(list.get(j).getFixingSn())) {
					Integer rowFlag = list.get(j).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行设备号是重复数据";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_fixing/import_fixing";
				}

			}
		}

		// 验证MAC地址格式及与数据库MAC地址是否重复
		List<Fixing> fixingList = fixingService.queryAllFixing();
		for (int i = 0; i < list.size(); i++) {

			// 验证MAC地址是否已存在数据库
			for (int j = 0; j < fixingList.size(); j++) {
				if (list.get(i).getFixingSn().equals(fixingList.get(j).getFixingSn())) {
					Integer rowFlag = list.get(i).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行设备号已存在";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_fixing/import_fixing";
				}
			}
		}

		int result = fixingService.importFixing(list);
		if (result > 0) {
			retmodel.addAttribute("meg", "导入成功");
		} else {
			retmodel.addAttribute("meg", "导入失败");
		}
		return "webmanage/t_fixing/import_fixing";
	}

	/***************************************************** 采集卡 ***********************************************************************************/

	@RequestMapping("/card_manager.action")
	/** 处理浏览 用户表请求 */
	public String T_card(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String id = request.getParameter("id");
		retmodel.addAttribute("id", id);
		return "webmanage/t_fixing/card_index";
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showAddCard.action")
	public String showAddCard(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String fixingId = request.getParameter("fixingId");
		retmodel.addAttribute("fixingId", fixingId);
		return "webmanage/t_fixing/add_card";
	}

	/** 添加采集卡 */
	@RequestMapping("/add_card.action")
	public String addCard(@ModelAttribute("model") Card model, Model retmodel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (model.getTempLastTime() != null && !"".equals(model.getTempLastTime())) {
			try {
				Date lastTime = sdf.parse(model.getTempLastTime());
				model.setLastTime(lastTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		boolean result = fixingService.addCard(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("采集卡");
		sysLog.setOperdes("添加采集卡" + model.getProgrammeCode());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "webmanage/t_fixing/add_card";
	}

	@ResponseBody
	@RequestMapping("/listCard.action")
	// ** jqgrid组件列表查询系统 用户 *//*
	public String listCard(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		List<Map<String, Object>> list = fixingService.getAllCardInfo(jqGridPager, p);
		try {
			JqGridTool.jqGridQuery(request, response, list, jqGridPager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据Id删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del_card.action")
	public Map<String, Object> delCard(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String programmeCode = request.getParameter("programmeCode");
		if ("".equals(id) || null == id) {
			return ActionUtil.ajaxFail("删除失败", "");
		}

		int result = fixingService.delCardById(Long.parseLong(id));

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("删除");
		sysLog.setOperobject("采集卡");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("删除采集卡" + programmeCode);
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result < 1) {
			return ActionUtil.ajaxFail("删除失败", "");
		} else {
			return ActionUtil.ajaxSuccess("删除成功", "");
		}

	}

	/**
	 * 根據ID查詢信息并返回修改页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showEditCard.action")
	public String showEditCard(@ModelAttribute("model") Card model, Model retmodel) {
		Long id = model.getId();
		Card card = fixingService.queryCardById(id);
		retmodel.addAttribute("card", card);
		return "/webmanage/t_fixing/edit_card";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/updatetCard.action")
	public String updatetCard(@ModelAttribute("model") Card model, Model retmodel) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String tempLastTime = model.getTempLastTime();
		if (tempLastTime != null && !"".equals(tempLastTime)) {
			try {
				Date lastTime = sdf.parse(model.getTempLastTime());
				model.setLastTime(lastTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		int result = fixingService.updateCardById(model);

		SysLog sysLog = this.getSysLog();
		sysLog.setOpertype("修改");
		sysLog.setOperobject("采集卡");
		sysLog.setOperresult(result > 0 ? "成功" : "失败");
		sysLog.setOperdes("修改采集卡" + model.getProgrammeCode());
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
		return "/webmanage/t_fixing/edit_card";
	}

	/**
	 * 查看
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/querycardInfo.action")
	public String queryCardInfo(@ModelAttribute("model") Card model, Model retmodel) {

		Long id = model.getId();
		Card card = fixingService.queryCardById(id);
		retmodel.addAttribute("card", card);
		return "/webmanage/t_fixing/card_info";
	}

	/**
	 * 进入导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showImportCard.action")
	public String showImportCard(@ModelAttribute("model") Card model, Model retmodel) {
		Long id = model.getFixingId();
		retmodel.addAttribute("id", id);
		return "webmanage/t_fixing/import_card";
	}

	/** 导入 */
	@RequestMapping("/importCard.action")
	public String importCard(@ModelAttribute("model") Card model, Model retmodel, HttpServletRequest request,
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
			return "webmanage/t_fixing/import_card";
		}
		String[] tableHeader = new String[] { "节目组号", "CA号", "设备帐号组", "固件版本", "Rom版本" };
		// 验证表头格式是否正确
		for (int i = 0; i < tableHeader.length; i++) {
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			if (!tableHeader[i].trim().equals(row.getCell(i).getStringCellValue().trim())) {
				retmodel.addAttribute("errorMeg", "模板列头不正确！");
				return "webmanage/t_fixing/import_card";
			}
		}

		List<Card> list = new ArrayList<Card>();
		for (Row r : sheet) {
			if (r.getRowNum() < 1) {
				continue;
			}
			Card card = new Card();
			if (null != r.getCell(0)) {

				r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				String programmeCode = r.getCell(0).getStringCellValue();
				card.setProgrammeCode(programmeCode);
				card.setNumFlag(r.getRowNum() + 1);
				if (null != r.getCell(1)) {
					r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String caCode = r.getCell(1).getStringCellValue();
					card.setCaCode(caCode);
				}
				if (null != r.getCell(2)) {
					r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					String stbGroup = r.getCell(2).getStringCellValue();
					card.setStbGroup(stbGroup);
				}

				if (null != r.getCell(3)) {
					r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					String firmware = r.getCell(3).getStringCellValue();
					card.setFirmware(firmware);
				}
				if (null != r.getCell(4)) {
					r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					String romversion = r.getCell(4).getStringCellValue();
					card.setRomversion(romversion);
				}
			} else {
				String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "节目组号是空值";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_fixing/import_card";
			}
			list.add(card);

		}
		// 判断该Excel中是否有重复数据
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getProgrammeCode().equals(list.get(j).getProgrammeCode())) {
					Integer rowFlag = list.get(j).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "节目组号是重复数据";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_fixing/import_card";
				}
			}
		}
		Long fixingId = model.getFixingId();
		List<Card> fixingList = fixingService.queryAllCardByFixingId(fixingId);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < fixingList.size(); j++) {
				if (list.get(i).getProgrammeCode().equals(fixingList.get(j).getProgrammeCode())) {
					Integer rowFlag = list.get(i).getNumFlag();
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行节目组号已存在";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_fixing/import_card";
				}
			}
		}
		int result = fixingService.importCard(list, fixingId);
		if (result > 0) {
			retmodel.addAttribute("meg", "导入成功");
		} else {
			retmodel.addAttribute("meg", "导入失败");
		}
		return "webmanage/t_fixing/import_card";
	}

	/**
	 * 模板下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadFixingTemplate.action")
	public void downloadFixingTemplate(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("GBk");
			response.setContentType("multipart/form-data");
			//"Content-Disposition", "attachment;fileName=importFixingTemplate.xlsx"
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(("导入前端设备标准模板" + ".xlsx").getBytes(), "iso-8859-1"));// 下载文件的名称      导入前端设备标准模板
			String fullPath = getPath("importFixingTemplate", "xlsx");// 模板名称
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

	// public void downloadFixingTemplate(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// URL save =
	// Thread.currentThread().getContextClassLoader().getResource("");
	// String str = save.toString();
	// str = str.substring(6, str.length());
	// str = str.replaceAll("%20", " ");
	// int num = str.lastIndexOf("hotelPro");// hotelPro为项目名，应用到不同的项目中，这个需要修改！
	// str = str.substring(0, num + "hotelPro".length());
	// str = str + "\\excel\\importFixingTemplate.xlsx";// Excel模板所在的路径。
	//
	// System.out.println(str);
	//
	// File f = new File(str);
	// // 设置response参数，可以打开下载页面
	// response.reset();
	// response.setContentType("multipart/form-data");//application/vnd.ms-excel;charset=utf-8
	// try {
	// response.setHeader("Content-Disposition","attachment;filename=" + new
	// String(("导入前端设备标准模板" + ".xlsx").getBytes(), "iso-8859-1"));//
	// 下载文件的名称 
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// ServletOutputStream out = response.getOutputStream();
	// BufferedInputStream bis = null;
	// BufferedOutputStream bos = null;
	// try {
	// bis = new BufferedInputStream(new FileInputStream(f));
	// bos = new BufferedOutputStream(out);
	// byte[] buff = new byte[2048];
	// int bytesRead;
	// while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	// bos.write(buff, 0, bytesRead);
	// }
	// } catch (final IOException e) {
	// throw e;
	// } finally {
	// if (bis != null)
	// bis.close();
	// if (bos != null)
	// bos.close();
	// }
	// }

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
