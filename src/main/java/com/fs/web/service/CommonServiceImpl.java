package com.fs.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.model.Parameter;

@Service("CommonService")
/** 公共逻辑接口实现 */
public class CommonServiceImpl implements CommonService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private ParameterMapper parameterMapper;

	/**
	 * 获取枚举类型信息 
	 * 枚举类型/编号/名称
	 */
	public Parameter getParameter(String ptype, Integer pcode, String pname) {
		Map<String, Object> p = new HashMap<>();
		p.put("ptype", ptype);// "hoteltype"
		p.put("pcode", pcode);
		p.put("pname", pname);
		Parameter par = parameterMapper.getParameter(p);
		return par;
	}

}
