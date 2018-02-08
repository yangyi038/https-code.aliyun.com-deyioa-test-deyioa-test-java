package com.fs.app.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fs.comm.mapper.TBindMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.model.TBind;

@Service("I_TbindService")
/** 閰掑簵缁堢缁戝畾涓氬姟閫昏緫鎺ュ彛瀹炵幇 */
public class I_TbindServiceImpl implements I_TbindService {
	/** 閫氳繃渚濊禆娉ㄥ叆Mapper缁勪欢瀹炰緥 */
	@Resource
	private TBindMapper t_bindMapper;

	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private TStbMapper t_stbMapper;


	/**
	 * 获取绑定列表
	 * 
	 * @param p
	 * @return
	 */
	public List<TBind> selectBind(Map<String, Object> p) {
		List<TBind> bindList = t_bindMapper.getBindList(p);
		return bindList;
	}


}
