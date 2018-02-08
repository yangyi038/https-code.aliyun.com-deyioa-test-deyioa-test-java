package com.fs.app.service;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.TBind;

/** 酒店终端绑定业务逻辑接口 */
public interface I_TbindService {
	
	
	/**
	 * 精确查询绑定
	 * @param p
	 * @return
	 */
	public List<TBind> selectBind(Map<String, Object> p);
	
	
	
	
}
