package com.fs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import com.fs.comm.model.THotelGroup;
import com.fs.comm.model.TStb;
import com.fs.web.service.T_bindService;

/**
 * 酒店终端管理controller
 * 
 * @author pzj
 *
 */
@Controller
@RequestMapping("/admin/t_bind")
public class T_bindController extends BaseController {

	@Resource
	private T_bindService t_bindService;

	@Resource
	private TStbMapper t_stbMapper;

	@RequestMapping("/browseT_bind.action")
	/** 处理浏览酒店终端绑定请求 */
	public String browseT_bind(HttpServletRequest request) {
		return "webmanage/t_bind/browseBind";
	}

	/** jqgrid组件列表-查询终端绑定列表 */
	@ResponseBody
	@RequestMapping("/listT_bind.action")
	public RespJsonPageData getBindList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		
		// 获取机顶盒和酒店绑定关系列表
		List<TBind> bindList = t_bindService.browseBindList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(bindList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/preAddBind.action")
	public String preAddBind(@ModelAttribute("model") TBind model, Model retmodel) {
		// 获取酒店列表
		retmodel.addAttribute("hotellist", getHotelList(new HashMap<>()));
		// 获取机顶盒列表
		retmodel.addAttribute("stbList", getStbList());

		return "webmanage/t_bind/addBind";
	}

	/**
	 * 新增请求
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/addBind.action")
	public String addBind(@ModelAttribute("model") TBind model, Model retmodel) {
		// 检验合法性
		Map<String, Object> p;
		// 查询该机顶盒是否已绑定酒店
		if (model.getStbsid() != null) {
			p = new HashMap<String, Object>();
			p.put("stbsid", model.getStbsid());
			List<TBind> bindList = t_bindService.selectBind(p);
			if (bindList.size() > 0) {
				retmodel.addAttribute("meg", "该终端已经绑定[" + bindList.get(0).getHotelname() + "],请先解绑！");
				return "webmanage/t_bind/addBind";
			}
		}

		model.setCompanyid(currentUser().getCompanyid());
		model.setOperatorid(currentUser().getOperatorid());
		model.setDealerid(currentUser().getDealerid());
		
		// 执行添加
		if (t_bindService.insertBind(model)) {
			insertSysLog("添加", "酒店机顶盒绑定", "成功", currentUser().getLoginname() + "添加酒店机顶盒绑定成功");
			retmodel.addAttribute("meg", "新增成功！");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
		}
		return "webmanage/t_bind/addBind";
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delBind.action")
	public String delBind(@ModelAttribute("model") TBind model) throws Exception {
		if (model.getSid() != null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("sid", model.getSid());
			int i = t_bindService.countBind(p);
			if (i <= 0) {
				return "该绑定关系不存在，请确认";
			}

			// 执行删除
			if (t_bindService.delBind(model.getSid())) {
				insertSysLog("删除", "解绑", "成功", currentUser().getLoginname() + "删除解绑成功");
				return "删除成功";
			} else {
				return "删除失败";
			}
		} else {
			return "删除失败";
		}
	}

	/**
	 * 查看终端绑定详情
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getBind.action")
	public String getBind(@ModelAttribute("model") TBind model, Model retmodel) throws Exception {
		if (model.getSid() != null) {
			TBind bind = t_bindService.selectByPrimaryKey(model.getSid());
			if (bind != null) {
				retmodel.addAttribute("t_bind", bind);
				return "webmanage/t_bind/viewBind";
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
	@RequestMapping("/editBind.action")
	public String editBind(@ModelAttribute("model") TBind model, Model retmodel) {
		TBind bind = t_bindService.selectByPrimaryKey(model.getSid());
		retmodel.addAttribute("t_bind", bind);
		// 获取酒店列表
		retmodel.addAttribute("hotellist", getHotelList(new HashMap<>()));
		// 获取机顶盒列表
		retmodel.addAttribute("stbList", getStbList());

		return "webmanage/t_bind/editBind";
	}

	/**
	 * 修改订单
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/updateBindById.action")
	public String updateBindById(@ModelAttribute("model") TBind model, Model retmodel)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("sid", model.getSid());
		TBind bind = t_bindService.selectByPrimaryKey(model.getSid());
		// model属性复制给order
		MyBeanUtils.copyBeanNotNull2Bean(model, bind);

		// 查询该机顶盒是否已绑定酒店
		if (model.getStbsid() != null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("stbsid", model.getStbsid());
			List<TBind> bindList = t_bindService.selectBind(p);
			if (bindList.size() > 0) {
				retmodel.addAttribute("meg", "该终端已经绑定[" + bindList.get(0).getHotelname() + "],请先解绑！");
				return "webmanage/t_bind/editBind";
			}
		}

		// 执行修改
		if (t_bindService.updateByPrimaryKey(bind)) {
			MyBeanUtils.copyBeanNotNull2Bean(bind, model);

			insertSysLog("修改", "绑定关系", "成功", currentUser().getLoginname() + "修改绑定关系成功");
			retmodel.addAttribute("meg", "修改成功！");
		} else {
			retmodel.addAttribute("meg", "修改失败！");
		}
		retmodel.addAttribute("t_bind", bind);
		return "webmanage/t_bind/editBind";
	}

	@RequestMapping("/searchT_hotel.action")
	/** 处理浏览订单表请求 */
	public String searchT_hotel(HttpServletRequest request) {
		// 获取用户组名称列表
		List<THotelGroup> hotelgrouplist = getHotelGroupList();
		request.setAttribute("hotelgrouplist", hotelgrouplist);
		return "webmanage/t_bind/searchHotel";
	}

	@ResponseBody
	@RequestMapping("/searchHotelList.action")
	/** jqgrid组件列表-查询订单列表 */
	public RespJsonPageData searchHotelList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		// 获取酒店列表
		List<THotel> hotelList = t_hotelService.browseHotelList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(hotelList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}

	@RequestMapping("/searchT_stb.action")
	/** 处理浏览订单表请求 */
	public String searchT_stb(HttpServletRequest request) {
		// 获取酒店名称列表
		request.setAttribute("hotellist", getHotelList(new HashMap<>()));
		// 获取机顶盒组列表
		request.setAttribute("stbgrouplist", getStbgrouplist());

		return "webmanage/t_bind/searchStb";
	}

	/** jqgrid组件列表-获取机顶盒列表 */
	@ResponseBody
	@RequestMapping("/searchStbList.action")
	public RespJsonPageData searchStbList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p=ViewDataCondition(p);
		// 获取机顶盒列表
		List<TStb> stbList = t_stbService.browseStbList(jqGridPager, p);
		// 去除已经绑定的机顶盒
		List<TStb> canBindList = new ArrayList<>();
		if (stbList != null && stbList.size() > 0) {
			for (TStb tStb : stbList) {
				Map<String, Object> map = new HashMap<>();
				map.put("stbsid", tStb.getSid());
				int check = t_bindService.countBind(map);
				if (check < 1)
					canBindList.add(tStb);
			}
		}

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(canBindList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
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

	/**
	 * 模板下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadBindTemplate.action")
	public void downloadBindTemplate(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("GBK");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(("终端绑定标准模板" + ".xlsx").getBytes(), "iso-8859-1"));// 下载文件的名称
			String fullPath = getPath("importBindTemplate", "xlsx");// 模板名称
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
	 * 进入导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/importBindPre.action")
	public String importBindPre(@ModelAttribute("model") TBind model, Model retmodel) {
		return "webmanage/t_bind/import_bind";
	}

	/**
	 * excel批导入终端绑定关系
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @param filePath
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/importBind.action")
	public String importBind(@ModelAttribute("model") TBind model, Model retmodel, HttpServletRequest request,
			@RequestParam(value = "filePath", required = false) MultipartFile filePath)
			throws IllegalStateException, IOException {

		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("UTF-8"));
		headers.setContentType(mt);
		Workbook hssfBook = null;

		InputStream is = filePath.getInputStream();// 获取excel数据
		if (!is.markSupported()) {
			is = new PushbackInputStream(is, 8);
		}

		// excel2007
		if (filePath.getOriginalFilename().contains(".xlsx")) {
			if (POIFSFileSystem.hasPOIFSHeader(is)) { // 后缀是xlsx但是是ole2格式的或更低版本
				hssfBook = new HSSFWorkbook(is);
			} else if (POIXMLDocument.hasOOXMLHeader(is)) { // 后缀是xlsx但是是OOXML格式的或更高版本
				hssfBook = new XSSFWorkbook(is);// OPCPackage.open(is)
			} else {
				hssfBook = new XSSFWorkbook(is);
			}
		} else {// excel2003
			hssfBook = new HSSFWorkbook(is);
		}

		Sheet sheet = hssfBook.getSheetAt(0);
		Row row = sheet.getRow(0);// 第一行数据
		int rowNum = sheet.getLastRowNum();
		if (rowNum == 0) {// 指表头
			retmodel.addAttribute("errorMeg", "您上传的excel没有数据！");
			return "webmanage/t_bind/import_bind";
		}
		String[] tableHeader = new String[] { "酒店名称", "机顶盒账号" };
		// 验证表头格式是否正确
		for (int i = 0; i < tableHeader.length; i++) {
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			if (!tableHeader[i].trim().equals(row.getCell(i).getStringCellValue().trim())) {
				retmodel.addAttribute("errorMeg", "模板列头不正确！");
				return "webmanage/t_bind/import_bind";
			}
		}

		// excel数据列表
		List<TBind> list = new ArrayList<TBind>();
		// 处理行数据
		for (Row r : sheet) {
			if (r.getRowNum() < 1) {
				continue;
			}
			TBind bind = new TBind();
			// 第一列：酒店名称
			if (null == r.getCell(0)) {
				String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "行的酒店名称是空值";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_bind/import_bind";
			}
			bind.setHotelname(r.getCell(0).getStringCellValue());

			// 第二列：机顶盒账号
			if (null == r.getCell(1)) {
				String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "行的机顶盒账号是空值";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_bind/import_bind";
			}
			bind.setStbnum(r.getCell(1).getStringCellValue());
			r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

			// 绑定其他信息
			bind.setCompanyid(currentUser().getCompanyid());
			bind.setOperatorid(currentUser().getOperatorid());
			bind.setDealerid(currentUser().getDealerid());
			
			bind.setWifi(model.getWifi());
			bind.setWifipwd(model.getWifipwd());
			bind.setCustomer(model.getCustomer());
			bind.setRoomnum(model.getRoomnum());
			bind.setWelcome(model.getWelcome());
			bind.setComm(model.getComm());
			bind.setNumFlag(r.getRowNum() + 1);

			list.add(bind);
		}

		// 判断该Excel中是否有重复机顶盒账号
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				// 行数
				Integer rowFlag = list.get(j).getNumFlag();
				if (list.get(i).getStbnum().equals(list.get(j).getStbnum())) {
					String errorMeg = "该Excel中第" + rowFlag.toString() + "行机顶盒账号是重复数据";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "webmanage/t_bind/import_bind";
				}
			}
		}

		// 验证酒店和机顶盒是否在可绑定的t_hotel and t_stb表中
		Map<String, Object> p = new HashMap<>();
		p=ViewDataCondition(p);

		// 获取酒店列表
		List<THotel> hotelList = getHotelList(new HashMap<>());
		// 获取机顶盒列表
		List<TStb> stbList = getStbList();
		// 可以绑定的机顶盒列表
		List<TStb> canBindList = new ArrayList<>();
		if (stbList != null && stbList.size() > 0) {
			for (TStb tStb : stbList) {
				Map<String, Object> map = new HashMap<>();
				map.put("stbsid", tStb.getSid());
				int check = t_bindService.countBind(map);
				if (check < 1)
					canBindList.add(tStb);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			// excel中机顶盒
			TBind en = list.get(i);
			// 验证excel中的机顶盒是否属于可绑定的机顶盒列表中
			boolean a = false;
			for (int j = 0; j < canBindList.size(); j++) {
				if (en.getStbnum().equals(canBindList.get(j).getStbnum())) {
					en.setStbsid(canBindList.get(j).getSid());
					a = true;
				}
			}
			if (!a) {
				String errorMeg = "该Excel中第" + en.getNumFlag().toString() + "行机顶盒信息不在可绑定的机顶盒列表中";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_bind/import_bind";
			}

			// 验证excel中的酒店是否属于可绑定的酒店列表中
			boolean b = false;
			for (int j = 0; j < hotelList.size(); j++) {
				if (en.getHotelname().equals(hotelList.get(j).getHotelname())) {
					en.setHotelsid(hotelList.get(j).getSid());
					en.setHotelnum(hotelList.get(j).getHotelnum());
					b = true;
				}
			}
			if (!b) {
				String errorMeg = "该Excel中第" + en.getNumFlag().toString() + "行酒店信息不在可绑定的酒店列表中";
				retmodel.addAttribute("errorMeg", errorMeg);
				return "webmanage/t_bind/import_bind";
			}
		}

		int result = t_bindService.importBind(list);
		if (result > 0) {
			retmodel.addAttribute("meg", "导入成功");
		} else {
			retmodel.addAttribute("meg", "导入失败");
		}
		return "webmanage/t_bind/import_bind";
	}

}
