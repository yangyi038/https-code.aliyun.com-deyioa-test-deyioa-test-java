package com.fs.app.service;

import java.util.Map;

import com.fs.comm.model.TStb;

/** 机顶盒管理业务逻辑接口 */
public interface I_TstbService {

	
	/**
	 * 获取机顶盒信息
	 * 
	 * @return
	 */
	public TStb getStbInfo( Map<String, Object> p);
	
	/**
	 * 修改机顶盒信息
	 * 
	 * @return
	 */
	public boolean updateStbById(TStb stb);

}
