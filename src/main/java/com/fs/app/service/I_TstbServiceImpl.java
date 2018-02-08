package com.fs.app.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fantastic.DateUtilsEx;
import com.fs.app.mapper.I_TStbMapper;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.mapper.TStbGroupMapper;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;

@Service("I_TstbService")
/** 机顶盒接口server */
public class I_TstbServiceImpl implements I_TstbService {

	@Resource
	private I_TStbMapper stbMapper;

	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private TStbGroupMapper t_stb_groupMapper;

	@Resource
	private ParameterMapper parameterMapper;

	/**
	 * 获取机顶盒信息
	 * 
	 * @return
	 */
	public TStb getStbInfo(Map<String, Object> param) {
		TStb stb = stbMapper.getStb(param);
		if (stb != null) {
			Map<String, Object> p;
			// 格式化有效期
			if (StringUtils.isNotEmpty(stb.getValiddate() + "")) {
				stb.setValiddateStr(DateUtilsEx.date2Str(stb.getValiddate(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
			}
			// 获取账户类型，支付类型，机顶盒状态
			if (stb.getAccounttype() != null) {
				p = new HashMap<>();
				p.put("ptype", "accounttype");
				p.put("pcode", stb.getAccounttype());
				Parameter par = parameterMapper.getParameter(p);
				stb.setAccounttypeStr(par.getPname());
			}
			if (stb.getPaytype() != null) {
				p = new HashMap<>();
				p.put("ptype", "paytype");
				p.put("pcode", stb.getPaytype());
				Parameter par = parameterMapper.getParameter(p);
				stb.setPaytypeStr(par.getPname());
			}
			if (stb.getStbstatus() != null) {
				p = new HashMap<>();
				p.put("ptype", "stbstatus");
				p.put("pcode", stb.getStbstatus());
				Parameter par = parameterMapper.getParameter(p);
				stb.setStbstatusStr(par.getPname());
			}
			if (stb.getStbtype() != null) {
				p = new HashMap<>();
				p.put("ptype", "terminaltype");
				p.put("pcode", stb.getStbtype());
				Parameter par = parameterMapper.getParameter(p);
				stb.setStbtypeStr(par.getPname());
			}
			// 获取机顶盒所属组名称
			if (stb.getStbgroup() != null) {
				TStbGroup stbgrouup = t_stb_groupMapper.selectByPrimaryKey(Long.parseLong(stb.getStbgroup()));
				if (stbgrouup != null && stbgrouup.getGroupname() != null) {
					stb.setStbgroupname(stbgrouup.getGroupname());
				}
			}

		}
		return stb;
	}

	/**
	 * 修改机顶盒信息
	 * 
	 * @return
	 */
	public boolean updateStbById(TStb stb) {
		try {
			int cc = stbMapper.updateStbInfo(stb);
			if (cc > 0 || cc == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
