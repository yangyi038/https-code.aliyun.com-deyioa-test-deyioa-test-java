package com.fs.web.service;

import com.fs.comm.model.Parameter;

/** 公共逻辑接口 */
public interface CommonService {

	/** 获取枚举类型信息 */
	public Parameter getParameter(String ptype, Integer pcode, String pname);

}
