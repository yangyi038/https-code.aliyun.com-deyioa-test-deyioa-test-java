package com.fs.app.controller;

import java.util.ArrayList;
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

import com.fantastic.SecretUtils;
import com.fs.app.service.I_TbindService;
import com.fs.app.service.I_ThotelService;
import com.fs.app.service.I_TstbService;
import com.fs.app.service.VerSercice;
import com.fs.app.vomodel.AdbDebugServerDto;
import com.fs.app.vomodel.AutoShutdownDto;
import com.fs.app.vomodel.LayoutSelectorsDto;
import com.fs.app.vomodel.MessageDto;
import com.fs.app.vomodel.ProduceDto;
import com.fs.app.vomodel.RealTimeControlDto;
import com.fs.app.vomodel.UiPwdDto;
import com.fs.app.vomodel.VerDto;
import com.fs.app.vomodel.VerListDto;
import com.fs.comm.model.AdbDebugServer;
import com.fs.comm.model.Autoshutdown;
import com.fs.comm.model.LayoutSelectors;
import com.fs.comm.model.RealTimeControl;
import com.fs.comm.model.SysVer;
import com.fs.comm.model.UiPwd;
import com.fs.web.service.ParameterService;

/**
 * 初始化设置相关接口
 * 
 * @author pzj
 *
 */
@RequestMapping("/api/sysset/")
@Controller
public class I_SysSetController {

	@Resource
	private I_TstbService stbService;

	@Resource
	private I_ThotelService hotelService;

	@Resource
	private I_TbindService bindService;

	@Resource
	private ParameterService parameterService;

	@Resource
	private VerSercice verSercice;

	@RequestMapping("getThreeDES")
	@ResponseBody
	public String getThreeDES(HttpServletRequest request) {
		String token = request.getParameter("token");
		String stbnum = request.getParameter("stbnum");
		String pwd = request.getParameter("pwd");
		String mac = request.getParameter("mac");

		String desStr = SecretUtils.encrypted(token + "$" + stbnum + "$" + pwd + "$" + mac);

		return desStr;
	}

	/**
	 * 编码器设备心跳
	 * 
	 * @param request
	 * @param response
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("EncoderHeart")
	@ResponseBody
	public MessageDto EncoderHeart(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String userid = request.getParameter("userid");
		String userToken = request.getParameter("userToken");
		String playStatus = request.getParameter("playStatus");

		if ("".equals(userid) && null == userid) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒账号不能为空");
			return dto;
		}

		if ("".equals(userToken) && null == userToken) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("userToken不能为空");
			return dto;
		}

		MessageDto model = hotelService.stbHeart(userid, userToken, playStatus);
		return model;
	}

	/**
	 * 服务器心跳接口
	 * 
	 * @param request
	 * @param response
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("ServerHeart")
	@ResponseBody
	public MessageDto ServerHeart(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String userid = request.getParameter("userid");
		String userToken = request.getParameter("userToken");
		String playStatus = request.getParameter("playStatus");

		if ("".equals(userid) && null == userid) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("机顶盒账号不能为空");
			return dto;
		}

		if ("".equals(userToken) && null == userToken) {
			MessageDto dto = new MessageDto();
			dto.setCode(101);
			dto.setInfo("userToken不能为空");
			return dto;
		}

		MessageDto model = hotelService.stbHeart(userid, userToken, playStatus);
		return model;
	}

	/**
	 * 出厂登记
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("producemac")
	@ResponseBody
	public ProduceDto producemac(HttpServletRequest request, HttpServletResponse response, Model retmodel) {
		String type = request.getParameter("type");
		String appVersion = request.getParameter("appVersion");
		String firmware = request.getParameter("firmware");
		String mac = request.getParameter("mac");
		String chipid = request.getParameter("chipid");

		ProduceDto dto = new ProduceDto();
		if ("".equals(type) || null == type) {
			dto.setStatus(0);
			dto.setInfo("终端类型不能为空");
			return dto;
		}

		if ("".equals(appVersion) || null == appVersion) {
			dto.setStatus(0);
			dto.setInfo("应用版本号不能为空");
			return dto;
		}

		if ("".equals(firmware) || null == firmware) {
			dto.setStatus(0);
			dto.setInfo("固件版本号不能为空");
			return dto;
		}

		if ("".equals(mac) || null == mac) {
			dto.setStatus(0);
			dto.setInfo("MAC地址不能为空");
			return dto;
		}

		String trueMacAddress = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
		if (StringUtils.isNotEmpty(mac)) {
			if (mac.length() == 12) {
				StringBuffer buff = new StringBuffer(mac);
				int index;
				for (index = 2; index < buff.length(); index += 3) {
					buff.insert(index, ':');
				}
				mac = buff.toString();
			}
			if (!mac.matches(trueMacAddress)) {
				dto.setStatus(0);
				dto.setInfo("mac输入格式不对,请确认并重新输入！");
				return dto;
			}
		}

		if ("".equals(chipid) || null == chipid) {
			dto.setStatus(0);
			dto.setInfo("主芯片号不能为空");
			return dto;
		}

		ProduceDto model = hotelService.producemac(type, appVersion, firmware, mac, chipid);
		return model;
	}

	/**
	 * 返回Json
	 * 
	 * @param model
	 * @param retmodel
	 * @return
	 */
	@RequestMapping("generatorConfig")
	@ResponseBody
	public Map<String, Object> generatorConfig(HttpServletRequest request, HttpServletResponse response,
			Model retmodel) {
		List<SysVer> list = verSercice.getAllSysVer();
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.size() > 0 && list != null) {
			List<VerDto> verCodeList = new ArrayList<VerDto>();
			List<VerDto> pingBaiduUrlList = new ArrayList<VerDto>();
			List<VerDto> tempServerList = new ArrayList<VerDto>();
			List<VerDto> updateFirmwareUrlList = new ArrayList<VerDto>();
			List<VerDto> updateAppUrlList = new ArrayList<VerDto>();
			List<VerDto> liveTvBootPicList = new ArrayList<VerDto>();
			List<VerDto> liveTvDecoderList = new ArrayList<VerDto>();
			List<VerDto> liveTvMainUrlList = new ArrayList<VerDto>();
			List<VerDto> homePageBootPicList = new ArrayList<VerDto>();
			List<VerDto> homePageBackgroundList = new ArrayList<VerDto>();
			List<VerDto> homePageHotelNameList = new ArrayList<VerDto>();
			List<VerDto> unionWifiNameList = new ArrayList<VerDto>();
			List<VerDto> unionWifiPwdList = new ArrayList<VerDto>();
			List<VerDto> homePageLayoutjarList = new ArrayList<VerDto>();
			List<VerDto> uninstallApksList = new ArrayList<VerDto>();
			List<Object> autoShutdownList = new ArrayList<Object>();

			List<Object> LayoutSelectorsList = new ArrayList<Object>();
			List<VerDto> isNoNeedNetList = new ArrayList<VerDto>();
			List<VerDto> serviceTypeList = new ArrayList<VerDto>();
			List<Object> uiPwdList = new ArrayList<Object>();
			List<Object> adbDebugServerDtoList = new ArrayList<Object>();
			List<Object> RealTimeControlDtoList = new ArrayList<Object>();

			for (SysVer ver : list) {
				if (!"".equals(ver.getCode()) && "verCode".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					verCodeList.add(value);
					map.put(ver.getCode(), verCodeList);
				}
				if (!"".equals(ver.getCode()) && "pingBaiduUrl".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					pingBaiduUrlList.add(value);

					map.put(ver.getCode(), pingBaiduUrlList);
				}

				if (!"".equals(ver.getCode()) && "tempServer".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					tempServerList.add(value);
					map.put(ver.getCode(), tempServerList);
				}

				if (!"".equals(ver.getCode()) && "updateFirmwareUrl".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					updateFirmwareUrlList.add(value);
					map.put(ver.getCode(), updateFirmwareUrlList);
				}

				if (!"".equals(ver.getCode()) && "updateAppUrl".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					updateAppUrlList.add(value);
					map.put(ver.getCode(), updateAppUrlList);
				}

				if (!"".equals(ver.getCode()) && "liveTvBootPic".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					liveTvBootPicList.add(value);
					map.put(ver.getCode(), liveTvBootPicList);
				}
				if (!"".equals(ver.getCode()) && "liveTvDecoder".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					liveTvDecoderList.add(value);
					map.put(ver.getCode(), liveTvDecoderList);
				}
				if (!"".equals(ver.getCode()) && "liveTvMainUrl".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					liveTvMainUrlList.add(value);
					map.put(ver.getCode(), liveTvMainUrlList);
				}
				if (!"".equals(ver.getCode()) && "homePageBootPic".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					homePageBootPicList.add(value);
					map.put(ver.getCode(), homePageBootPicList);
				}
				if (!"".equals(ver.getCode()) && "homePageBackground".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					homePageBackgroundList.add(value);
					map.put(ver.getCode(), homePageBackgroundList);
				}
				if (!"".equals(ver.getCode()) && "homePageHotelName".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					homePageHotelNameList.add(value);
					map.put(ver.getCode(), homePageHotelNameList);
				}
				if (!"".equals(ver.getCode()) && "unionWifiName".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					unionWifiNameList.add(value);
					map.put(ver.getCode(), unionWifiNameList);
				}
				if (!"".equals(ver.getCode()) && "unionWifiPwd".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					unionWifiPwdList.add(value);
					map.put(ver.getCode(), unionWifiPwdList);
				}
				if (!"".equals(ver.getCode()) && "homePageLayoutjar".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					homePageLayoutjarList.add(value);
					map.put(ver.getCode(), homePageLayoutjarList);
				}
				if (!"".equals(ver.getCode()) && "uninstallApks".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					uninstallApksList.add(value);
					map.put(ver.getCode(), uninstallApksList);
				}
				if (!"".equals(ver.getCode()) && "autoShutdown".equals(ver.getCode())) {
					List<Autoshutdown> resultList = verSercice.getAutoshutdown();
					if (resultList != null && resultList.size() > 0) {
						for (Autoshutdown model : resultList) {
							AutoShutdownDto dto = new AutoShutdownDto();
							dto.setStarttime(model.getStarttime());
							dto.setEndtime(model.getEndtime());
							dto.setDur(model.getDur());
							dto.setTimersetcheckdur(model.getTimersetcheckdur());
							dto.setCountdowntime(model.getCountdowntime());
							dto.setCountdowntip(model.getCountdowntip());
							dto.setExcludedate(model.getExcludedate());
							autoShutdownList.add(dto);
							VerListDto verList = new VerListDto();
							verList.setName(autoShutdownList);
							verList.setAllowGroup(ver.getAllowGroup());

							map.put(ver.getCode(), verList);
						}
					}

				}
				if (!"".equals(ver.getCode()) && "layoutSelectors".equals(ver.getCode())) {
					List<LayoutSelectors> resultList = verSercice.getLayoutselectors();
					if (resultList != null && resultList.size() > 0) {
						for (LayoutSelectors model : resultList) {
							LayoutSelectorsDto dto = new LayoutSelectorsDto();
							dto.setName(model.getName());
							dto.setFocusedimg(model.getFocusedimg());
							LayoutSelectorsList.add(dto);

							VerListDto verList = new VerListDto();
							verList.setName(LayoutSelectorsList);
							verList.setAllowGroup(ver.getAllowGroup());

							map.put(ver.getCode(), verList);
						}
					}

				}
				if (!"".equals(ver.getCode()) && "isNoNeedNet".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					isNoNeedNetList.add(value);
					map.put(ver.getCode(), isNoNeedNetList);
				}
				if (!"".equals(ver.getCode()) && "serviceType".equals(ver.getCode())) {
					VerDto value = new VerDto();
					value.setName(ver.getName());
					value.setAllowGroup(ver.getAllowGroup());
					serviceTypeList.add(value);
					map.put(ver.getCode(), serviceTypeList);
				}
				if (!"".equals(ver.getCode()) && "uiPwd".equals(ver.getCode())) {
					List<UiPwd> resultList = verSercice.getUiPwd();
					if (resultList != null && resultList.size() > 0) {
						for (UiPwd model : resultList) {
							UiPwdDto dto = new UiPwdDto();
							dto.setName(model.getName());
							dto.setVal(model.getVal());
							uiPwdList.add(dto);

							VerListDto verList = new VerListDto();
							verList.setName(uiPwdList);
							verList.setAllowGroup(ver.getAllowGroup());

							map.put(ver.getCode(), verList);
						}
					}

				}
				if (!"".equals(ver.getCode()) && "adbDebugServer".equals(ver.getCode())) {
					List<AdbDebugServer> resultList = verSercice.getAdbdebugserver();
					if (resultList != null && resultList.size() > 0) {
						for (AdbDebugServer model : resultList) {
							AdbDebugServerDto dto = new AdbDebugServerDto();
							dto.setIp(model.getIp());
							dto.setPort(model.getPort());
							dto.setKey(model.getAdbKey());
							adbDebugServerDtoList.add(dto);

							VerListDto verList = new VerListDto();
							verList.setName(adbDebugServerDtoList);
							verList.setAllowGroup(ver.getAllowGroup());

							map.put(ver.getCode(), verList);
						}
					}

				}
				if (!"".equals(ver.getCode()) && "realTimeControl".equals(ver.getCode())) {
					List<RealTimeControl> resultList = verSercice.getRealTimeControl();
					if (resultList != null && resultList.size() > 0) {
						for (RealTimeControl model : resultList) {
							RealTimeControlDto dto = new RealTimeControlDto();
							dto.setAdmin(model.getName());

							RealTimeControlDtoList.add(dto);

							VerListDto verList = new VerListDto();
							verList.setName(RealTimeControlDtoList);
							verList.setAllowGroup(ver.getAllowGroup());

							map.put(ver.getCode(), verList);
						}
					}
				}
			}
		}
		return map;
	}

}
