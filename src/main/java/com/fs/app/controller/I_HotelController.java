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
import com.fs.app.service.I_TbindService;
import com.fs.app.service.I_ThotelService;
import com.fs.app.service.I_TstbService;
import com.fs.comm.model.TBind;
import com.fs.comm.model.TStb;
import com.fs.web.service.ParameterService;
import com.fs.web.service.T_bindService;
import com.fs.web.service.T_stbService;

/**
 * 酒店用户相关接口
 * 
 * @author pzj
 *
 */
@RequestMapping("/api/hotel/")
@Controller
public class I_HotelController extends I_BaseController {

	@Resource
	private I_TstbService stbService;

	@Resource
	private I_ThotelService hotelService;

	@Resource
	private I_TbindService bindService;

	@Resource
	private ParameterService parameterService;

	@Resource
	private T_stbService t_stbService;

	@Resource
	private T_bindService t_bindService;

	/**
	 * 获取用户下机顶盒列表接口
	 */
	@RequestMapping("getStbListByHotelsid")
	@ResponseBody
	public I_Result<List<TStb>> getStbListByHotelsid(HttpServletRequest request) {
		I_Result<List<TStb>> result = new I_Result<List<TStb>>();
		String hotelsid = request.getParameter("hotelsid");// 酒店ID

		if (StringUtils.isBlank(hotelsid)) {
			result.setResult(I_Result.CODE_NULL, "酒店ID不能为空！");
			return result;
		}
		
		// 获取酒店下绑定的机顶盒列表
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("hotelsid", hotelsid);
		List<TBind> bindList = t_bindService.selectBind(p);
		List<TStb> stbList = new ArrayList<>();
		if (bindList != null && bindList.size() > 0) {
			for (TBind tBind : bindList) {
				TStb stb = t_stbService.getStbById(tBind.getStbsid());
				if (stb != null) {
					stbList.add(stb);
				}
			}
		}

		// 返回酒店信息对象
		result.setData(stbList);
		result.setResult(I_Result.CODE_OK, "获取酒店下机顶盒列表成功！");
		return result;
	}

}
