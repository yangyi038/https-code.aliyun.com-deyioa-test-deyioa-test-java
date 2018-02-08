package com.fs.app.service;

import com.fs.app.vomodel.MessageDto;
import com.fs.app.vomodel.ProduceDto;
import com.fs.comm.model.THotel;

/** 酒店管理业务逻辑接口 */
public interface I_ThotelService {

	/**
	 * 查询酒店详情
	 * 
	 * @param sid
	 * @return
	 */
	public THotel selectByPrimaryKey(long sid);

	/**
	 * 检查用户是否注册
	 * 
	 * @param sid
	 * @return
	 */
	public MessageDto getHotelInfoByMac(String mac);

	/**
	 * 查询机顶盒信息
	 * 
	 * @param sid
	 * @return
	 */
	public MessageDto getUserToken(String sid);

	/**
	 * 心跳接口
	 * 
	 * @param userid
	 * @param userToken
	 * @param playStatus
	 * @return
	 */
	public MessageDto stbHeart(String userid, String userToken, String playStatus);

	/**
	 * 出厂登记
	 * 
	 * @param type
	 * @param appVersion
	 * @param firmware
	 * @param mac
	 * @param chipid
	 * @param iptvGroupCode
	 * @return
	 */
	public ProduceDto producemac(String type, String appVersion, String firmware, String mac, String chipid);

}
