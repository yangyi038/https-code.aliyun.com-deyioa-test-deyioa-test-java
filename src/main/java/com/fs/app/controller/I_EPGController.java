package com.fs.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fs.app.service.I_EPGService;
import com.fs.app.vomodel.MessageDto;

/**
 * 栏目相关接口
 * @author pzj
 *
 */
@RequestMapping("/api/epg/")
@Controller
public class I_EPGController {

	@Resource
	private I_EPGService epgService;

	/**
	 * 获取栏目下节目列表
	 * 
	 * @param request
	 * @param response
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("getProgramListByCategoryId")
	public MessageDto getProgramListByCategoryId(HttpServletRequest request, HttpServletResponse response,
			Model retmodel) {
		String cid = request.getParameter("cid");//栏目ID
		String userToken = request.getParameter("userToken");//token
		String userId = request.getParameter("userId");
		String companyname = request.getParameter("companyname");
		if ("".equals(cid) || null == cid) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("栏目ID不能为空");
			return dto;
		}
		if ("".equals(userToken) || null == userToken) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("userToken不能为空");
			return dto;
		}
		if ("".equals(userId) || null == userId) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒账号不能为空");
			return dto;
		}
		if ("".equals(companyname) || null == companyname) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("运营商名称不能为空");
			return dto;
		}

		MessageDto dto = epgService.getAllColumnInfoById(cid, userToken, userId, companyname);
		return dto;
	}
	
	
}
