package com.fs.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.util.ThreeDES;
import com.fs.app.vomodel.MessageDto;
import com.fs.app.vomodel.ProduceDto;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.TBindMapper;
import com.fs.comm.mapper.THotelGroupMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.mapper.TStbGroupMapper;
import com.fs.comm.mapper.TStbLogMapper;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.mapper.UserTokenMapper;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.RegisterMessage;
import com.fs.comm.model.TBind;
import com.fs.comm.model.THotel;
import com.fs.comm.model.THotelGroup;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;
import com.fs.comm.model.TStbLog;

@Service("I_ThotelService")
/** 酒店管理业务逻辑接口实现 */
public class I_ThotelServiceImpl implements I_ThotelService {

	private final String code = "000000abcde";
	private final String key = "A1B2C3D4E5F60708";

	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private TStbMapper t_stbMapper;

	@Resource
	private THotelGroupMapper hotelGroupMapper;

	@Resource
	private UserTokenMapper userTokenMapper;

	@Resource
	private TStbGroupMapper tStbGroupMapper;

	@Resource
	private ParameterMapper parameterMapper;

	@Resource
	private TBindMapper tBindMapper;

	@Resource
	private TStbLogMapper logMapper;

	/**
	 * 获取详情
	 */
	public THotel selectByPrimaryKey(long sid) {
		THotel hotel = t_hotelMapper.selectByPrimaryKey(sid);
		Map<String, Object> p;
		// 获取证件类型和用户类型的名称
		if (hotel.getCardtype() != null) {
			p = new HashMap<>();
			p.put("ptype", "cardtype");
			p.put("pcode", hotel.getCardtype());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null) {
				hotel.setCardtypeStr(par.getPname());
			}
		}
		if (hotel.getHoteltype() != null) {
			p = new HashMap<>();
			p.put("ptype", "hoteltype");
			p.put("pcode", hotel.getHoteltype());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null) {
				hotel.setHoteltypeStr(par.getPname());
			}
		}

		return hotel;
	}

	@Override
	public MessageDto getHotelInfoByMac(String mac) {
		TStb tstb = t_stbMapper.getTStbByMac(mac);
		RegisterMessage model = new RegisterMessage();
		MessageDto message = new MessageDto();
		if (null != tstb) {
			model.setUserid(tstb.getStbnum().toString());
			TBind bind = tBindMapper.selectBindByStbNum(tstb.getStbnum());
			if (null != bind) {
				model.setHotelid(bind.getSid().toString());
				model.setHostName(bind.getHotelname());
				THotelGroup hotelGroup = hotelGroupMapper.getTHoteGrouplByNum(bind.getHotelnum());
				if (null != hotelGroup) {
					model.setUserGroup(hotelGroup.getGroupname());
				}
			}
			Integer status = tstb.getStbstatus();
			if (status != null) {
				model.setStatus(status);
			}
			model.setPassword(StringUtils.isNotBlank(tstb.getPwd()) ? tstb.getPwd() : null);
			TStbGroup tStbGroup = tStbGroupMapper.getTStbGroupByStbGroup(tstb.getStbgroup());
			if (null != tStbGroup) {
				model.setIptvGroupCode(tStbGroup.getGroupnum().toString());
			}
		} else {
			message.setInfo("没有该MAC地址的信息");
			message.setCode(103);
			return message;
		}

		message.setInfo("已注册");
		message.setCode(102);
		message.setData(model);
		return message;
	}

	@Override
	public MessageDto getUserToken(String sid) {
		TStb model = t_stbMapper.queryTsbById(sid);
		MessageDto message = new MessageDto();
		String encoded = null;
		if (null != model) {
			Date dt = new Date();
			String temptoken = code + dt.getTime();
			ThreeDES des = new ThreeDES();
			try {
				encoded = des.encrypt(temptoken, key);
				encoded = encoded.replaceAll("\\+", "%2B");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// 更新token
			TStb stb = new TStb();
			stb.setSid(model.getSid());
			stb.setTokencode(encoded);
			try {
				int i = t_stbMapper.updateByPrimaryKeySelective(stb);
				if (i > 0) {
					System.err.println("stb更新token成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message.setInfo("没有该机顶盒的信息");
			message.setCode(103);
			return message;
		}

		message.setInfo("token获取成功");
		message.setCode(102);
		message.setData(encoded);
		return message;
	}

	/**
	 * userid 机顶盒账号 userToken 验证token
	 */
	@Override
	public MessageDto stbHeart(String userid, String userToken, String playStatus) {
		TStb stb = t_stbMapper.queryTsbById(userid);
		MessageDto dto = new MessageDto();
		if (null != stb) {
			// 获取token
			String token = stb.getTokencode();
			token = token.replaceAll("%2B", "+");
			// 验证token
			if (StringUtils.isNotBlank(userToken) && token.equals(new String(userToken))) {
				TStbLog log = logMapper.getStblogByPrimaryKey(stb.getStbnum());
				// 添加或更新日志信息
				if (log != null) {// 添加
					log.setLastonlinetime(new Date());// 记录心跳时间
				} else {
					dto.setInfo("没有该机顶盒的日志信息");
					dto.setCode(103);
					return dto;
				}
				// 更新机顶盒日志的心跳时间
				if (logMapper.updateStblog(log) != 1) {
					dto.setInfo("机顶盒日志更新失败！");
					dto.setCode(103);
					return dto;
				}
			} else {
				dto.setInfo("token不合法");
				dto.setCode(104);
				return dto;
			}
		} else {
			dto.setInfo("没有该机顶盒的信息");
			dto.setCode(103);
			return dto;
		}

		dto.setInfo("成功");
		dto.setCode(102);
		dto.setData(null);
		return dto;
	}

	@Override
	public ProduceDto producemac(String type, String appVersion, String firmware, String mac, String chipid) {

		TStb stb = new TStb();
		ProduceDto dto = new ProduceDto();
		List<TStb> list = t_stbMapper.getStbList(null);
		if (list != null && list.size() > 0) {
			for (TStb model : list) {
				if (!mac.equals(model.getMac())) {
					stb.setMac(mac);
				} else {
					dto.setStatus(0);
					dto.setInfo("MAC地址重复");
					return dto;
				}
			}
		}

		stb.setApptype(type);
		stb.setApkversion(appVersion);
		stb.setRomfirmware(firmware);
		stb.setStbid(chipid);
		stb.setStbstatus(1);

		// 設置創建時間
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stb.setCreatetime(sdf.format(new Date()));

		// 設置到期時間
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);
		Date date = curr.getTime();
		stb.setValiddate(date);

		stb.setPwd(mac);
		stb.setPwdsure(mac);

		mac = mac.replace(":", "");
		stb.setStbnum(mac);

		int result = 0;
		try {
			result = t_stbMapper.insertStb(stb);
		} catch (Exception e) {
			e.printStackTrace();
			dto.setStatus(1);
			dto.setInfo("OK");
		}
		/*
		 * if(result>0){ dto.setStatus(1); dto.setInfo("OK"); }else{
		 * dto.setStatus(0); dto.setInfo("登记失败"); }
		 */
		return dto;
	}

}
