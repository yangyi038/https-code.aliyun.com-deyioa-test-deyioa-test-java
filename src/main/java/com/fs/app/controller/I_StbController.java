package com.fs.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.app.common.I_BaseController;
import com.fs.app.common.I_Result;
import com.fs.app.service.I_TbindService;
import com.fs.app.service.I_ThotelService;
import com.fs.app.service.I_TstbService;
import com.fs.app.vomodel.MessageDto;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.SysParameter;
import com.fs.comm.model.TBind;
import com.fs.comm.model.THotel;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbLog;
import com.fs.web.service.ParameterService;
import com.fs.web.service.SysParameterManagerService;
import com.fs.web.service.T_stb_logService;

/**
 * 机顶盒设备相关接口
 * 
 * @author pzj
 *
 */
@RequestMapping("/api/hotel/")
@Controller
public class I_StbController extends I_BaseController {

	@Resource
	private I_TstbService stbService;

	@Resource
	private I_ThotelService hotelService;

	@Resource
	private I_TbindService bindService;

	@Resource
	private ParameterService parameterService;

	@Resource
	private SysParameterManagerService sysParameterService;

	@Resource
	private T_stb_logService stblogService;

	/**
	 * 机顶盒登录
	 *
	 * @param value
	 *            = "getHotelInfoByMac", method = RequestMethod.POST
	 * @return http://localhost:6080/hotelPro/api/hotel/userLogin?userid=个还挺热jet&type=1&Authenticator=4Ev9xCTtcp+ptpIEihAvPfWBlDMv1s7o0XImyJ2dPxnhyK1d0ILouHYsQHuxhF5a
	 */
	@RequestMapping("userLogin")
	@ResponseBody
	public I_Result<Map<String, Object>> userLogin(HttpServletRequest request) {
		I_Result<Map<String, Object>> result = new I_Result<Map<String, Object>>();
		// 请求参数
		String userid = request.getParameter("userid");// 机顶盒账号
		String type = request.getParameter("type");// 终端类型:传入的是机顶盒类型名称
		// String Authenticator = request.getParameter("Authenticator");// 3DES串
		// Authenticator = Authenticator.replaceAll(" ", "+");// 空格转化成+号

		if (StringUtils.isEmpty(userid)) {
			result.setResult(I_Result.CODE_NULL, "机顶盒账号不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(type)) {
			result.setResult(I_Result.CODE_NULL, "用户类型不能为空！");
			return result;
		}
		// if (StringUtils.isEmpty(Authenticator)) {
		// result.setResult(I_Result.CODE_NULL, "加密参数串不能为空！");
		// return result;
		// }

		// 获取终端类型
		Map<String, Object> map = new HashMap<>();
		map.put("pname", type);
		Parameter en = parameterService.getParameter(map);
		if (en == null || en.getPcode() == null) {
			result.setResult(I_Result.CODE_NULL, "没有此用户类型！");
			return result;
		}

		// 接口请求参数
		Map<String, Object> p = new HashMap<>();
		p.put("stbnum", userid);// 机顶盒账号
		p.put("stbtype", en.getPcode());// 终端类型

		// 获取机顶盒信息
		TStb stb = stbService.getStbInfo(p);
		if (stb == null) {
			result.setResult(I_Result.CODE_WITHOUT, "该机顶盒设备不存在！");
			return result;
		}

		// 注：3DES串参数说明
		// Authenticator=3DES(token+”$”+机顶盒账号stbnum+”$”+机顶盒密码pwd+”$”+MAC)
		// String token=stb.getTokencode();
		// if(token!=null){
		// token=token.replaceAll("%2B", "+");
		// }
		// String desStr = SecretUtils.encrypted( token + "$" + stb.getStbnum()
		// + "$" + stb.getPwd() + "$" + stb.getMac());
		//
		// if (!desStr.equals(Authenticator)) {
		// result.setResult(I_Result.CODE_FAIL, "3DES加密不一致！");
		// return result;
		// }

		// 获取与酒店的绑定关系信息
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("stbsid", stb.getSid());
		List<TBind> bindList = bindService.selectBind(par);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 获取酒店信息
		THotel hotel = new THotel();
		if (bindList != null && bindList.size() > 0) {
			if (bindList.get(0).getHotelsid() != null) {
				hotel = hotelService.selectByPrimaryKey(bindList.get(0).getHotelsid());
			}
			if (StringUtils.isNotEmpty(bindList.get(0).getWelcome())) {
				returnMap.put("salutatory", bindList.get(0).getWelcome());// 酒店欢迎词
			} else {
				returnMap.put("salutatory", null);// 酒店欢迎词
			}
		}

		// 获取系统配置心跳周期
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("companyid", stb.getCompanyid());
		map1.put("name", "hearttime_expire");// 心跳周期，单位秒
		SysParameter pa = sysParameterService.getSysParameter(map1);
		if (pa != null && StringUtils.isNotBlank(pa.getParameterValue())) {
			returnMap.put("heartTime", pa.getParameterValue());// 心跳周期，单位秒
		} else {
			returnMap.put("heartTime", null);
		}

		// 过滤返回字段
		returnMap.put("userid", stb.getSid());// 机顶盒主键ID
		returnMap.put("tipTime", 30);// 单位天，提前多少天做欠费/到期提醒。默认30天
		returnMap.put("effectiveTime", stb.getValiddate());// 账号有效截止日期
		returnMap.put("bootPic", hotel.getOpenpic());// 开机图片下载地址
		returnMap.put("logoPic", hotel.getOpenlogo());// 开机LOGO下载地址
		returnMap.put("startPic", hotel.getOpenvideo());// 开机动画下载地址
		returnMap.put("romVersion", stb.getRomversion());// Rom版本号
		returnMap.put("romUpdateAddr", hotel.getRomup());// Rom升级地址
		returnMap.put("hotelName", hotel.getHotelname());// 酒店名称
		returnMap.put("ssid", hotel.getWifi());// Wifi的账号
		returnMap.put("sspwd", hotel.getWifipwd());// Wifi密码
		returnMap.put("sspwdType", null);// Wifi密码类型，1:字符密码模式，2:二维码模式
		returnMap.put("txtUrl", hotel.getTxturl());// 接口文档中的通知公告管理地址，现用于生成二维码的地址
		returnMap.put("epgUrl", hotel.getEpgfirst());// Epg下载地址

		returnMap.put("userToken", stb.getTokencode());// token
		returnMap.put("userTokenExpireTime", null);// Token有效期，暂时未用
		returnMap.put("ntpDomain", hotel.getNtp());// Ntp服务器地址
		returnMap.put("logServerAddr", hotel.getLogserver());// 日志服务器地址
		returnMap.put("cspid", stb.getCompanyid());// 运营商id，(保留)
		returnMap.put("appAddr", hotel.getApplicationup());// 应用下载地址
		returnMap.put("appDownAddr", hotel.getAppblacklist());// 应用黑名单下载地址
		returnMap.put("columnAddr", hotel.getEpgfirst());// 栏目下载地址，(保留)

		// 跟新机顶盒信息
		String appVersion = request.getParameter("appVersion");// 应用版本号
		String wifiMac = request.getParameter("wifiMac");// Wifi热点mac地址
		String firmware = request.getParameter("firmware");// 固件型号，版本号等拼接
		String stbid = request.getParameter("sn");// 终端序列号
		TStb upstb = new TStb();
		upstb.setSid(stb.getSid());
		if (StringUtils.isNotBlank(appVersion))
			upstb.setApkversion(appVersion);
		if (StringUtils.isNotBlank(wifiMac))
			upstb.setWifimac(wifiMac);
		if (StringUtils.isNotBlank(firmware))
			upstb.setRomfirmware(firmware);
		if (StringUtils.isNotBlank(stbid))
			upstb.setStbid(stbid);
		if (StringUtils.isNotBlank(appVersion) || StringUtils.isNotBlank(wifiMac) || StringUtils.isNotBlank(firmware)
				|| StringUtils.isNotBlank(stbid))
			stbService.updateStbById(upstb);

		// 登陆成功，添加机顶盒日志或更新机顶盒日志的相关信息
		// 查询机顶盒日志是否已存在
		TStbLog log = stblogService.getStblogByPrimaryKey(stb.getStbnum());
		// 添加或更新日志信息
		if (log == null) {// 添加
			log = new TStbLog();
			log.setStbnum(stb.getStbnum());
			log.setCompanyid(stb.getCompanyid());
			log.setStbtoken(stb.getTokencode());
			log.setTerminaltype(stb.getStbtype());
			log.setLastonlinetime(new Date());// 上次心跳时间
			log.setStbid(stb.getStbid());
			log.setMac(stb.getMac());
			log.setLogintime(new Date());// 登录时间
			log.setApkversion(stb.getApkversion());
			log.setRomversion(stb.getRomversion());
			log.setRomfirmware(stb.getRomfirmware());
			log.setApptype(stb.getApptype());
			log.setOnlinestatus(2);// 心跳状态：1:中断 2：正常

			if (!stblogService.insertStbLog(log)) {
				result.setData(null);
				result.setResult(I_Result.CODE_FAIL, "机顶盒日志添加失败！");
				return result;
			}
		} else {// 修改
			log.setStbtoken(stb.getTokencode());
			log.setCompanyid(stb.getCompanyid());
			log.setTerminaltype(stb.getStbtype());
			log.setStbid(stb.getStbid());
			log.setMac(stb.getMac());
			log.setLogintime(new Date());// 登录时间
			log.setApkversion(stb.getApkversion());
			log.setRomversion(stb.getRomversion());
			log.setRomfirmware(stb.getRomfirmware());
			log.setApptype(stb.getApptype());
			log.setOnlinestatus(2);// 心跳状态：1:中断 2：正常

			if (!stblogService.updateStblog(log)) {
				result.setData(null);
				result.setResult(I_Result.CODE_FAIL, "机顶盒日志更新失败！");
				return result;
			}
		}

		// 返回酒店信息对象
		result.setData(returnMap);
		result.setResult(I_Result.CODE_OK, "机顶盒登录成功！");
		return result;
	}

	/**
	 * 检查机顶盒是否注册
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("getUserInfoByMac")
	@ResponseBody
	public MessageDto getUserInfoByMac(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String mac = request.getParameter("mac");
		if (StringUtils.isBlank(mac)) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("MAC地址不能为空");
			return dto;
		}

		MessageDto model = hotelService.getHotelInfoByMac(mac);
		return model;
	}

	/**
	 * 获取机顶盒token
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("getUserToken")
	@ResponseBody
	public MessageDto getUserToken(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		// System.out.println("解码前的userid:"+request.getParameter("userid"));
		String userid = "";
		try {
			userid = URLDecoder.decode(request.getParameter("userid"), "UTF-8");
			// System.out.println("解码后的userid:"+userid);
			// userid = new
			// String(request.getParameter("userid").getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(userid) || null == userid) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒账号不能为空");
			return dto;

		}

		MessageDto message = hotelService.getUserToken(userid);
		return message;
	}

	/**
	 * 机顶盒心跳接口
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("stbHeart")
	@ResponseBody
	public MessageDto stbHeart(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String userid = request.getParameter("userid");
		String userToken = request.getParameter("userToken");
		String playStatus = request.getParameter("playStatus");

		if (StringUtils.isBlank(userid)) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒账号不能为空");
			return dto;
		}

		if (StringUtils.isBlank(userToken)) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒Token不能为空");
			return dto;
		}

		MessageDto model = hotelService.stbHeart(userid, userToken, playStatus);
		return model;
	}

}
