package com.fs.app.service;

import com.fs.app.vomodel.MessageDto;

public interface I_EPGService {
	
	/**
	 * 获取栏目列表
	 * @param cid
	 * @param userToken
	 * @return
	 */
	public MessageDto getAllColumnInfoById(String cid,String userToken,String userId,String companyname);
}
