package com.fs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
import org.springframework.web.servlet.ModelAndView;

import com.fantastic.ContextHolderUtils;
import com.fantastic.RespJsonPageData;
import com.framework.ActionUtil;
import com.framework.jqgrid.JqGridPager;
import com.framework.util.ServletBeanTools;
import com.fs.comm.model.AnswerEntity;
import com.fs.comm.model.CateEntity;
import com.fs.comm.model.CategoryEntity;
import com.fs.comm.model.ExcelDataEntity;
import com.fs.comm.model.Fixing;
import com.fs.comm.model.IndustryEntity;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.ProblemEntity;
import com.fs.comm.model.ShopEntity;
import com.fs.comm.model.SysLog;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.ProblemService;
import com.fs.web.service.ShopService;

/**
 * 问题管理
 * 
 * @author yangyi
 *
 */
@Controller
@RequestMapping("/admin/problem")
public class ProblemController extends BaseController{

	@Resource
	private ProblemService problemService;
	
	@Resource
	private ShopService shopService;
	
	/**
	 * 主菜单进入到问题管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/problem.action")
	public String problem(HttpServletRequest request){
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<ShopEntity> shopList = problemService.selectShop(p);
		List<IndustryEntity> industryList = problemService.selectIndustry(p);
		List<CateEntity> qcateList = problemService.qcateList();
		List<CategoryEntity> categoryEntityList = problemService.getCategoryList();
		
		request.setAttribute("shopList", shopList);
		request.setAttribute("industryList", industryList);
		request.setAttribute("qcateList", qcateList);
		request.setAttribute("categoryEntityList", categoryEntityList);
		return "sysmanage/business/problem";
	}
	
	/**
	 * 根据查询条件检索数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list_problem.action")
	public RespJsonPageData getShopList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取问题列表
		List<ProblemEntity> problemList = problemService.problemList(jqGridPager, p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(problemList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
	
	/**
	 * 进入到问题添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addproblemPre.action")
	public String addProblemPre(@ModelAttribute("model") ProblemEntity model, Model retmodel) {
		
		// 获取问题信息
		ProblemEntity entity = problemService.selectShopByid(model);
		retmodel.addAttribute("problem", entity);
		
		// 获取问题添加页面的下拉列表
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<ShopEntity> shopList = problemService.selectShop(p);
		List<IndustryEntity> industryList = problemService.selectIndustry(p);
		List<CateEntity> qcateList = problemService.qcateList();
		
		retmodel.addAttribute("shopList", shopList);
		retmodel.addAttribute("industryList", industryList);
		retmodel.addAttribute("qcateList", qcateList);
		return "sysmanage/business/addProblem";
	}
	
	/**
	 * 添加问题页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/addproblem.action")
	public String addProblem(@ModelAttribute("model") ProblemEntity model, Model retmodel,
			HttpServletRequest request) {
		
		Date date = new Date();
		
		AnswerEntity ae = new AnswerEntity();
		ae.setAnswer(model.getAnswer());
		ae.setCreatetime(date);
		ae.setRecommend(model.getRecommend());
		ae.setRelationname(model.getRelationname());
		ae.setSuggest(model.getSuggest());
		ae.setProblemid(model.getId());
		ae.setDeflg(1);
		
		boolean result = problemService.addAnswer(ae);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("问题");
		sysLog.setOperdes("添加问题" + model.getQuestion());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addProblemPre(model,retmodel);
	}

	/**
	 * 进入到表头问题添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addproblemPre2.action")
	public String addProblemPre2(@ModelAttribute("model") ProblemEntity model, Model retmodel) {
		
		// 获取问题添加页面的下拉列表
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<ShopEntity> shopList = problemService.selectShop(p);
		List<IndustryEntity> industryList = problemService.selectIndustry(p);
		List<CateEntity> qcateList = problemService.qcateList();
		
		retmodel.addAttribute("shopList", shopList);
		retmodel.addAttribute("industryList", industryList);
		retmodel.addAttribute("qcateList", qcateList);
		return "sysmanage/business/addProblem2";
	}
	
	/**
	 * 添加问题页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/addproblem2.action")
	public String addProblem2(@ModelAttribute("model") ProblemEntity model, Model retmodel,
			HttpServletRequest request) {
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		model.setId(uuid);
		model.setDeflg(1);
		Date date = new Date();
		
		model.setCreatetime(date.toString());
		AnswerEntity ae = new AnswerEntity();
		ae.setProblemid(uuid);
		ae.setAnswer(model.getAnswer());
		ae.setAnswerForm(model.getAnswerForm());

		ae.setRecommend(model.getRecommend());
		ae.setRelationname(model.getRelationname());
		ae.setSuggest(model.getSuggest());
		ae.setDeflg(1);
		
		boolean result1 = problemService.addProblem(model);
		boolean result2 = problemService.addAnswer(ae);
		SysLog sysLog = this.getSysLog();
		if (result1 == true && result2 == true) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("问题");
		sysLog.setOperdes("添加问题" + model.getQuestion());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addProblemPre2(model,retmodel);
	}
	
	/**
	 * 进入问题编辑页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/editproblemPre.action")
	public String editProblemPre(Model retmodel,HttpServletRequest request) {
		
		int answerid = Integer.parseInt(request.getParameter("answerid"));
		String id = request.getParameter("id");
		AnswerEntity ae = problemService.selecetAnswer(answerid);
		ProblemEntity model = new ProblemEntity();
		model.setId(id);
		
		// 获取问题信息
		ProblemEntity entity = problemService.selectShopByid(model);
		retmodel.addAttribute("problem", entity);
		if(ae != null){
			retmodel.addAttribute("answer", ae);
		}else{
			ae = new AnswerEntity();
			ae.setId(0);
			retmodel.addAttribute("answer", ae);
		}
		
		Map<String, Object> p = ViewDataCondition(new HashMap<>());
		List<ShopEntity> shopList = problemService.selectShop(p);
		List<IndustryEntity> industryList = problemService.selectIndustry(p);
		List<CateEntity> qcateList = problemService.qcateList();
		
		retmodel.addAttribute("shopList", shopList);
		retmodel.addAttribute("industryList", industryList);
		retmodel.addAttribute("qcateList", qcateList);
		return "sysmanage/business/problemModify";
	}
	
	/**
	 * 编辑问题页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/editproblem.action")
	public String editProblem(@ModelAttribute("model") ProblemEntity model, Model retmodel,
			HttpServletRequest request) {
		int i = model.getAnswerid();
		Date date = new Date();
		
		AnswerEntity ae = new AnswerEntity();
		ae.setId(i);
		ae.setProblemid(model.getId());
		ae.setAnswer(model.getAnswer());
		ae.setUpdatetime(date);
		ae.setRecommend(model.getRecommend());
		ae.setRelationname(model.getRelationname());
		ae.setSuggest(model.getSuggest());
		ae.setDeflg(1);
		
		model.setUpdatetime(date);
		if(i == 0){
			ae.setCreatetime(new Date());
			problemService.addAnswer(ae);
		}else{
			ae.setUpdatetime(new Date());
			problemService.updateAnswer(ae);
		}
		
		boolean result = problemService.editProblem(model);
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "更新成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "更新失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("更新");
		sysLog.setOperobject("问题");
		sysLog.setOperdes("更新问题" + model.getQuestion());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editProblemPre(retmodel,request);
	}
	
	/**
	 * 删除问题操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/del_problem.action")
	public Map<String, Object> delProblem(@ModelAttribute("model") ProblemEntity model,
		   Model retmodel, HttpServletRequest request) throws Exception {
		if(model.getId() != null){
			AnswerEntity ae = new AnswerEntity();
			Date date = new Date();
			
			ae.setProblemid(model.getId());
			ae.setUpdatetime(date);
			model.setUpdatetime(date);
			
			boolean fla = problemService.deleteProblem(model);
			problemService.deleteAnswer(ae);
			SysLog sysLog = this.getSysLog();
			if (fla) {
				retmodel.addAttribute("meg", "删除成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "删除失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("删除");
			sysLog.setOperobject("问题");
			sysLog.setOperdes("删除问题" + model.getQuestion());
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(fla){
				return ActionUtil.ajaxSuccess("删除成功", "");
			}else{
				return ActionUtil.ajaxFail("删除失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("删除失败", "");
		}	
	}

	/**
	 * 删除答案操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/del_answer.action")
	public Map<String, Object> delAnswer(@ModelAttribute("model") ProblemEntity model,
		   Model retmodel, HttpServletRequest request) throws Exception {
		String answerid = request.getParameter("answerid");
		if(answerid != null){
			AnswerEntity ae = new AnswerEntity();
			ae.setId(Integer.parseInt(answerid));
			ae.setUpdatetime(new Date());
			
			boolean flg = problemService.deleteAnswerByid(ae);
			SysLog sysLog = this.getSysLog();
			if (flg == true) {
				retmodel.addAttribute("meg", "删除成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "删除失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("删除");
			sysLog.setOperobject("答案");
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(flg){
				return ActionUtil.ajaxSuccess("删除成功", "");
			}else{
				return ActionUtil.ajaxFail("删除失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("删除失败", "");
		}	
	}
	
	/**
	 * 进入到行业包添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addindustryPre.action")
	public String addIndustryPre(HttpServletRequest request) {
		return "sysmanage/business/addIndustry";
	}
	
	/**
	 * 添加行业包页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @return
	 */
	@RequestMapping("/addindustry.action")
	public String addIndustry(@ModelAttribute("model") IndustryEntity model, Model retmodel,
			HttpServletRequest request) {

		Sysuser user = ContextHolderUtils.getLoginUser();
		model.setCreatename(user.getLoginname());
		model.setCreatetime(new Date());
		boolean result = problemService.addIndustry(model);
		
		SysLog sysLog = this.getSysLog();
		if (result) {
			retmodel.addAttribute("meg", "新增成功！");
			sysLog.setOperresult("成功");
		} else {
			retmodel.addAttribute("meg", "新增失败！");
			sysLog.setOperresult("失败");
		}

		sysLog.setOpertype("添加");
		sysLog.setOperobject("行业包");
		sysLog.setOperdes("添加行业包" + model.getName());
		try {
			sysLogService.insertSysLog(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addIndustryPre(request);
	}
	
	/**
	 * 主菜单进入到问题恢復页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/problemRecovery.action")
	public String problemRecovery(HttpServletRequest request){
		return "sysmanage/business/problemRecovery";
	}
	
	/**
	 * 根据查询条件检索已经删除的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list_problemRecovery.action")
	public RespJsonPageData getProblemList(HttpServletRequest request, HttpServletResponse response) {
		JqGridPager jqGridPager = new JqGridPager(request);
		Map<String, Object> p = ServletBeanTools.getParameterMap(request);
		p = ViewDataCondition(p);
		// 获取问题列表
		List<ProblemEntity> answerList = problemService.selectAnswer(jqGridPager,p);

		RespJsonPageData RespJsonPageData = new RespJsonPageData();
		RespJsonPageData.pkgdata(answerList, jqGridPager);
		return RespJsonPageData.createFinallyResp(jqGridPager, p, response);
	}
	
	/**
	 * 恢复问题操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/recovery_problem.action")
	public Map<String, Object> problemRecovery(@ModelAttribute("model") ProblemEntity model,
		   Model retmodel, HttpServletRequest request) throws Exception {
		if(model.getId() != null){
			AnswerEntity ae = new AnswerEntity();
			Date date = new Date();
			
			ae.setProblemid(model.getId());
			ae.setUpdatetime(date);
			model.setUpdatetime(date);
			
			boolean fla = problemService.recoveryProblem(model);
			problemService.recoveryByPrimaryKey(ae);
			SysLog sysLog = this.getSysLog();
			if (fla) {
				retmodel.addAttribute("meg", "恢复成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "恢复失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("恢复");
			sysLog.setOperobject("问题");
			sysLog.setOperdes("恢复问题" + model.getQuestion());
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(fla){
				return ActionUtil.ajaxSuccess("恢复成功", "");
			}else{
				return ActionUtil.ajaxFail("恢复失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("恢复失败", "");
		}	
	}
	
	/**
	 * 恢复答案操作
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/recovery_answer.action")
	public Map<String, Object> answerRecovery(@ModelAttribute("model") ProblemEntity model,
		   Model retmodel, HttpServletRequest request) throws Exception {
		if(model.getId() != null){
			String id = request.getParameter("id");
			AnswerEntity ae = new AnswerEntity();
			Date date = new Date();
			ae.setId(Integer.parseInt(id));
			ae.setUpdatetime(date);
			
			boolean fla = problemService.recoveryByid(ae);
			SysLog sysLog = this.getSysLog();
			if (fla) {
				retmodel.addAttribute("meg", "恢复成功！");
				sysLog.setOperresult("成功");
			} else {
				retmodel.addAttribute("meg", "恢复失败！");
				sysLog.setOperresult("失败");
			}

			sysLog.setOpertype("恢复");
			sysLog.setOperobject("答案");
			sysLog.setOperdes("恢复答案" + model.getQuestion());
			try {
				sysLogService.insertSysLog(sysLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(fla){
				return ActionUtil.ajaxSuccess("恢复成功", "");
			}else{
				return ActionUtil.ajaxFail("恢复失败", "");
			}
		}else{
			return ActionUtil.ajaxFail("恢复失败", "");
		}	
	}
	
	/**
	 * 进入导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("/showImport.action")
	public String showImportFixing(HttpServletRequest request) {
		return "sysmanage/business/importProblem";
	}

	/**
	 * 导入页面
	 * 
	 * @param model
	 * @param retmodel
	 * @param request
	 * @param filePath
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/importProblem.action")
	public String importFixing(@ModelAttribute("model") Fixing model, Model retmodel, HttpServletRequest request,
			@RequestParam(value = "filePath", required = false) MultipartFile filePath)
			throws IllegalStateException, IOException {
		
		// 设定格式和编码
		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("UTF-8"));
		headers.setContentType(mt);
		
		Workbook hssfBook = null;
		InputStream is = filePath.getInputStream();// 获取excel数据

		// 获取商店id
		String name = filePath.getOriginalFilename();
		String names = name.substring(0, name.length()-5);
		ShopEntity entity = new ShopEntity();
		entity.setName(names);
		ShopEntity shop = shopService.selectByname(entity);
		
		// 获取创建时间和创建用户
		Date date = new Date();
		Sysuser user = ContextHolderUtils.getLoginUser();
		
		// 检测文件名称是不是以商店名称命名
		if(shop == null){
			retmodel.addAttribute("errorMeg", "上传文件的名称不正确！");
			return "sysmanage/business/importProblem";
		}
		
		// 设定Excel的版本
		if (filePath.getOriginalFilename().contains(".xlsx")) {
			hssfBook = new XSSFWorkbook(is); // excel2007
		} else {
			hssfBook = new HSSFWorkbook(is); // excel2003
		}
		
		// 获取shift页
		List<Sheet> sheetList = new ArrayList<Sheet>();
		sheetList.add(hssfBook.getSheetAt(0));
		sheetList.add(hssfBook.getSheetAt(1));
		sheetList.add(hssfBook.getSheetAt(2));
		sheetList.add(hssfBook.getSheetAt(3));
		sheetList.add(hssfBook.getSheetAt(4));
		sheetList.add(hssfBook.getSheetAt(5));
		sheetList.add(hssfBook.getSheetAt(6));
		
		// 读取每个shift的数据
		for(int i = 0; i< sheetList.size(); i++){
			Row row = sheetList.get(i).getRow(1);// 第一行数据
			int rowNum = sheetList.get(i).getLastRowNum();
			if (rowNum == 0) {// 指表头
				retmodel.addAttribute("errorMeg", "您上传的excel没有数据！");
				return "sysmanage/business/importProblem";
			}
//			String[] tableHeader = new String[] {"问题分类", "问题类型", "问题描述/原因", "Q（问题）", "衍生/可识别问题", "产品表头", "答案组成", "A（答案）", 
//					"关联产品", "推荐产品", "建议/备注", "变量标签（红色可使用）"};
//
//			// 验证表头格式是否正确
//			for (int j = 0; j < tableHeader.length; j++) {
//				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
//				String s = row.getCell(j).getStringCellValue().trim();
//				if (!tableHeader[j].trim().equals(s)) {
//					retmodel.addAttribute("errorMeg", "模板列头不正确！");
//					return "sysmanage/business/importProblem";
//				}
//			}

			// 将Excel里面的数据进行封装
			List<ProblemEntity> problemList = new ArrayList<ProblemEntity>();
			List<AnswerEntity> answerList = new ArrayList<AnswerEntity>();
			for (Row r : sheetList.get(i)) {
				if (r.getRowNum() < 2) {
					continue;
				}
				
				ProblemEntity problem = new ProblemEntity();
				AnswerEntity ans = new AnswerEntity();
				
				if (null != r.getCell(0) && r.getCell(0).getStringCellValue() != "") {
					r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					String cate = r.getCell(0).getStringCellValue();
					if (null != r.getCell(1) && r.getCell(1).getStringCellValue() != "") {
						String categoryName = r.getCell(1).getStringCellValue();
						Parameter para = new Parameter();
						para.setPname(categoryName);
						Parameter paras = problemService.getParameterListByname(para);
						if(paras != null){
							problem.setIndustry(paras.getPcode().toString());
						}
					}
					if (null != r.getCell(2) && r.getCell(2).getStringCellValue() != "") {
						String reason = r.getCell(2).getStringCellValue();
						problem.setReason(reason);
					}
					if (null != r.getCell(3) && r.getCell(3).getStringCellValue() != "") {
						String question = r.getCell(3).getStringCellValue();
						problem.setQuestion(question);
					}
					if (null != r.getCell(4) && r.getCell(4).getStringCellValue() != "") {
						String derive = r.getCell(4).getStringCellValue();
						problem.setDerive(derive);
					}
					if (null != r.getCell(5) && r.getCell(5).getStringCellValue() != "") {
						String titlename = r.getCell(5).getStringCellValue();
						problem.setTitlename(titlename);
					}
					if (null != r.getCell(6) && r.getCell(6).getStringCellValue() != "") {
						String answerForm = r.getCell(6).getStringCellValue();
						ans.setAnswerForm(answerForm);
					}
					if (null != r.getCell(7) && r.getCell(7).getStringCellValue() != "") {
						String answer = r.getCell(7).getStringCellValue();
						ans.setAnswer(answer);
					}
					if (null != r.getCell(8) && r.getCell(8).getStringCellValue() != "") {
						String relationname = r.getCell(8).getStringCellValue();
						ans.setRelationname(relationname);
					}
					if (null != r.getCell(9) && r.getCell(9).getStringCellValue() != "") {
						String recommend = r.getCell(9).getStringCellValue();
						ans.setRecommend(recommend);
					}
					if (null != r.getCell(10) && r.getCell(10).getStringCellValue() != "") {
						String suggest = r.getCell(10).getStringCellValue();
						ans.setSuggest(suggest);
					}
					if (null != r.getCell(11) && r.getCell(11).getStringCellValue() != "") {
						String bugs = r.getCell(11).getStringCellValue();
						problem.setBugs(bugs);
					}
					if(problem != null){
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						problem.setShopid(shop.getId());
						problem.setCategoryid(Integer.toString(i+1));
						problem.setDeflg(1);
						problem.setId(uuid);
						problem.setQcate(cate);
						problem.setCreatetime(date.toString());
						problem.setUserid(user.getLoginname());
						problemList.add(problem);
					}
					if(ans.getAnswer() != null){
						ans.setCreatetime(date);
						ans.setCreatename(user.getLoginname());
						ans.setDeflg(1);
						ans.setProblemid(problem.getId());
						answerList.add(ans);
					}
				} else {
					String errorMeg = "该Excel中第" + (r.getRowNum() + 1) + "问题分类是空值";
					retmodel.addAttribute("errorMeg", errorMeg);
					return "sysmanage/business/importProblem";
				}
			}

			for(ProblemEntity pe : problemList){
				boolean result = problemService.addProblem(pe);
				if (result == false) {
					retmodel.addAttribute("meg", "导入问题失败");
					return "sysmanage/business/importProblem";
				}
			}
			
			for(AnswerEntity as : answerList){
				boolean result2 = problemService.addAnswer(as);
				if (result2 == false) {
					retmodel.addAttribute("meg", "导入答案失败");
					return "sysmanage/business/importProblem";
				}
			}
		}
		
		retmodel.addAttribute("meg", "导入成功");
		return "sysmanage/business/importProblem";
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
	 * 导出EXCELL
	 * 
	 * @param entity
	 *            页面entity
	 * @param request
	 *            页面请求对象
	 * @param response
	 *            页面响应对象
	 * @throws Exception
	 */
	@RequestMapping("/exportExcell.action")
	protected void exportExcel(@ModelAttribute ModelAndView mav, @ModelAttribute ProblemEntity entity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String filePath = getPath("importantDemo", "xlsx");
		File reFile = new File(filePath);

		String shopid = request.getParameter("shopid");
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("shopid", shopid);
		
		XSSFWorkbook wb = CreatListExcell(setDataSource(entity, p), reFile, response);
		
		// excel名称名称取得
		String pName = URLEncoder.encode(  
                "问题管理表" + System.currentTimeMillis() + ".xlsx",  
                "UTF-8"); 
		if (request.getHeader("User-Agent").indexOf("Chrome") > -1
				|| request.getHeader("User-Agent").indexOf("Firefox") > -1) {
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(pName.getBytes("UTF-8"), "ISO8859-1"));
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(pName, "UTF-8"));
		}
		
		// 160817 DaiLei Update End
		ServletOutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
			wb.write(outStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outStream.close();
		}
	}
	
	private List<Object> getObjectList(ProblemEntity entityData) {
        List<Object> lsDetail = new ArrayList<Object>();
        
        // 问题分类
        lsDetail.add(entityData.getQcate());
        
        // 问题类型
        lsDetail.add(entityData.getIndustry());
        
        // 问题描述/原因
        lsDetail.add(entityData.getReason());
        
        // Q（问题）
        lsDetail.add(entityData.getQuestion());
        
        // 衍生/可识别问题
        lsDetail.add(entityData.getDerive());
        
        // 产品表头
        lsDetail.add(entityData.getTitlename());
        
        // 答案组成
        lsDetail.add(entityData.getAnswerForm());
        
        // A（答案）
        lsDetail.add(entityData.getAnswer());
        
        // 关联产品
        lsDetail.add(entityData.getRelationname());
        
        // 推荐产品
        lsDetail.add(entityData.getRecommend());

        // 建议/备注
        lsDetail.add(entityData.getSuggest());
        
        // 变量标签（红色可使用）
        lsDetail.add(entityData.getBugs());
        
        return lsDetail;
    }
	
	 /**
     * 设定模板数据源
     * 
     * @param entity ProblemEntity
     * @return 主数据源
     */
    public List<List<ExcelDataEntity>> setDataSource(ProblemEntity entity, Map<String, Object> p ) throws Exception {

        // 组装导出数据
        // 处理数据，第一层list代表sheet页，第二层代表要写入sheet页里面的数据
        List<List<ExcelDataEntity>> workbookList = new ArrayList<List<ExcelDataEntity>>();
        
        // 第1个List
        List<ExcelDataEntity> firstList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data1 = new ExcelDataEntity();
        // 设定开始行
        data1.setStartRow(1);
        // 设定开始列
        data1.setStartCol(0);
        data1.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list1 = new ArrayList<List<Object>>();

        // 第2个List
        List<ExcelDataEntity> secondList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data2 = new ExcelDataEntity();
        // 设定开始行
        data2.setStartRow(1);
        // 设定开始列
        data2.setStartCol(0);
        data2.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list2 = new ArrayList<List<Object>>();

        // 第3个List
        List<ExcelDataEntity> thirdList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data3 = new ExcelDataEntity();
        // 设定开始行
        data3.setStartRow(1);
        // 设定开始列
        data3.setStartCol(0);
        data3.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list3 = new ArrayList<List<Object>>();

        // 第4个List
        List<ExcelDataEntity> forthList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data4 = new ExcelDataEntity();
        // 设定开始行
        data4.setStartRow(1);
        // 设定开始列
        data4.setStartCol(0);
        data4.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list4 = new ArrayList<List<Object>>();

        // 第5个List
        List<ExcelDataEntity> fifthList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data5 = new ExcelDataEntity();
        // 设定开始行
        data5.setStartRow(1);
        // 设定开始列
        data5.setStartCol(0);
        data5.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list5 = new ArrayList<List<Object>>();

        // 第6个List
        List<ExcelDataEntity> sixthList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data6 = new ExcelDataEntity();
        // 设定开始行
        data6.setStartRow(1);
        // 设定开始列
        data6.setStartCol(0);
        data6.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list6 = new ArrayList<List<Object>>();

        // 第7个List
        List<ExcelDataEntity> seventhList = new ArrayList<ExcelDataEntity>();
        // 代表一个列表
        ExcelDataEntity data7 = new ExcelDataEntity();
        // 设定开始行
        data7.setStartRow(1);
        // 设定开始列
        data7.setStartCol(0);
        data7.setHasBorder(false);
        // 原辅料一览列表数据设定
        List<List<Object>> list7 = new ArrayList<List<Object>>();

		List<ProblemEntity> problemList = problemService.getProblemList(p);
		
		for(ProblemEntity model : problemList){
			if(model.getCategoryid().equals("1")){
				list1.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("2")){
				list2.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("3")){
				list3.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("4")){
				list4.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("5")){
				list5.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("6")){
				list6.add(getObjectList(model));
			}
			if(model.getCategoryid().equals("7")){
				list7.add(getObjectList(model));
			}
		}

        // 产品问题
        data1.setData(list1);
        firstList.add(data1);
        workbookList.add(firstList);

        // 活动优惠
        data2.setData(list2);
        secondList.add(data1);
        workbookList.add(secondList);
        
        // 下单付款
        data3.setData(list3);
        thirdList.add(data3);
        workbookList.add(thirdList);
        
        // 快递物流
        data4.setData(list4);
        forthList.add(data4);
        workbookList.add(forthList);
        
        // 售后退款
        data5.setData(list5);
        fifthList.add(data5);
        workbookList.add(fifthList);
        
        // 店铺服务
        data6.setData(list6);
        sixthList.add(data6);
        workbookList.add(sixthList);
        
        // 聊天互动
        data7.setData(list7);
        seventhList.add(data7);
        workbookList.add(seventhList);

        // 添加进xls
        return workbookList;
    }


	/**
	 * 设定帐票参数
	 * 
	 * @param entity
	 *            页面entity
	 * @return 参数Map
	 * @throws Exception
	 */

	private XSSFWorkbook CreatListExcell(List<List<ExcelDataEntity>> dtLists, File template,
			HttpServletResponse response) throws Exception {
		InputStream is = new FileInputStream(template);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		
		// 标题样式
		XSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setWrapText(true);
		
		// 取各个sheet对应的数据
		for (int index = 0; index < dtLists.size(); index++) {
			List<ExcelDataEntity> dtList = dtLists.get(index);
			XSSFSheet sheet = wb.getSheetAt(index);
			if (dtList == null || dtList.size() == 0) {
				continue;
			}
			
			// 分别取各个列表对应的数据
			for (int ix = 0; ix < dtList.size(); ix++) {
				ExcelDataEntity dt = dtList.get(ix);
				Integer startRow = dt.getStartRow();
				Integer startCol = dt.getStartCol();

				// header 设定
				List<String> header = dt.getHeader();

				XSSFRow row = sheet.getRow(startRow.intValue());
				if (row == null) {
					row = sheet.createRow(startRow.intValue());
				}

				for (int i = 0; i < header.size(); i++) {
					XSSFCell ce = row.getCell(i + startCol);
					if (ce == null) {
						ce = row.createCell(i + startCol);
					}
					ce.setCellStyle(headerStyle);
					ce.getCellStyle().setWrapText(true);
					ce.setCellValue(new XSSFRichTextString(header.get(i)));
					
					// 列自适应
					if (dt.getWidth().size() > 0 && dt.getWidth().size() == header.size()) {
						sheet.setColumnWidth(i + startCol, dt.getWidth().get(i));
					}
				}

				List<List<Object>> list = dt.getData();
				List<Integer> mergStart = new ArrayList<Integer>();
				if(list.size() > 0){
					for (int i = 0; i < list.get(0).size(); i++) {
						mergStart.add(i, -1);
					}
				}
				
				for (int j = 0; j < list.size(); j++) {
					Integer jstart = startRow + j + 1;
					XSSFRow row2 = sheet.getRow(jstart.intValue());
					if (row2 == null) {
						row2 = sheet.createRow(jstart.intValue());
					}

					for (int i = 0; i < list.get(j).size(); i++) {
						Integer istart = startCol + i;
						XSSFCell ce = row2.getCell(istart);
						if (ce == null) {
							ce = row2.createCell(istart);
						}
						
						Object val = list.get(j).get(i);
						boolean isStringValue = val instanceof String;
						String strVal = null;
						if (isStringValue) {
							strVal = (String) val;
						}
						
						// 如果为数字类型的需要转型，否则excell第一次打开时，格式化不起作用
						short format = ce.getCellStyle().getDataFormat();
						if (val != null && (isStringValue == false || !strVal.startsWith(dt.getMergMark()))) {
							if ((format == 224 || format == 225 || format == 226) || (format <= 11 && format >= 1)
									|| (format <= 44 && format >= 23 && format != 40)
									|| ce.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								if (isStringValue && strVal.startsWith("=")) {
									ce.setCellFormula(strVal.substring(1));
								} else {
									try {
										ce.setCellValue(Double.parseDouble(String.valueOf(val)));
									} catch (Exception e) {
										ce.setCellValue(new XSSFRichTextString(String.valueOf(val)));
									}
								}
							} else {
								if (val instanceof Double) {
									ce.setCellValue((Double) val);
								} else if (val instanceof Integer) {
									ce.setCellValue((Integer) val);
								} else if (val instanceof BigDecimal) {
									ce.setCellValue(((BigDecimal) val).doubleValue());
								} else if (val instanceof String) {
									ce.setCellValue(new XSSFRichTextString(strVal));
								} else {
									ce.setCellValue(new XSSFRichTextString(String.valueOf(val)));
								}
							}
						}
					}
				}
			}
		}
		return wb;
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