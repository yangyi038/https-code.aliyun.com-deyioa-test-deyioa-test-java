package com.fs.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fantastic.ContextHolderUtils;
import com.fantastic.DateUtilsEx;
import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.SysParameterMapper;
import com.fs.comm.mapper.TStbLogMapper;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.model.SysParameter;
import com.fs.comm.model.TStbLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_stb_logService")
/** 终端日志 */
public class T_stb_logServiceImpl implements T_stb_logService {

	@Resource
	private TStbMapper t_stbMapper;

	@Resource
	private TStbLogMapper t_stb_logMapper;

	@Resource
	private SysParameterMapper paraMapper;

	/**
	 * 获取机顶盒列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TStbLog> browseStbLogList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		
		p.put("companyid", ContextHolderUtils.getLoginUser().getCompanyid());
		List<TStbLog> stbLog = t_stb_logMapper.getStbLogList(p);
		if (stbLog != null && stbLog.size() > 0) {
			// 获取系统配置心跳周期
			p = new HashMap<>();
			p.put("companyid", ContextHolderUtils.getLoginUser().getCompanyid());
			p.put("name", "hearttime_expire");
			SysParameter sysPar = paraMapper.getSysParameter(p);
			if (sysPar == null || StringUtils.isBlank(sysPar.getParameterValue())) {
				System.out.println("error：系统心跳周期未配置，系统默认周期为30分钟");
				sysPar.setParameterValue("30");
			}
			for (TStbLog tStbLog : stbLog) {
				// 格式化登陆时间
				if (StringUtils.isNotEmpty(tStbLog.getLogintime() + "")) {
					tStbLog.setLogintimeStr(
							DateUtilsEx.date2Str(tStbLog.getLogintime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				}
				// 格式化上次心跳时间
				if (StringUtils.isNotEmpty(tStbLog.getLastonlinetime() + "")) {
					tStbLog.setLastonlinetimeStr(
							DateUtilsEx.date2Str(tStbLog.getLastonlinetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				}
				// 计算登陆时间和上次心跳时间的时间差 是否超过3个心跳周期 然后更新心跳状态
				// 现在时间-上次心跳时间 是否超过三个心跳周期 超过在线状态即中断，否则正常
				long minute = ((new Date()).getTime() - tStbLog.getLastonlinetime().getTime())/1000/60
						/ Long.parseLong(sysPar.getParameterValue());
				if (minute <= 3) {
					tStbLog.setOnlinestatus(2);// 在线
				} else {
					tStbLog.setOnlinestatus(1);// 中断
				}

				//根据心跳更新在线状态
				t_stb_logMapper.updateStblog(tStbLog);
			}
		}
		PageInfo page = new PageInfo(stbLog);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return stbLog;
	}

	/**
	 * 添加机顶盒登录日志
	 */
	public boolean insertStbLog(TStbLog record) {
		try {
			int num = t_stb_logMapper.insertStbLog(record);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改机顶盒日志信息
	 * 
	 * @param record
	 * @return
	 */
	public boolean updateStblog(TStbLog record) {
		try {
			int cc = t_stb_logMapper.updateStblog(record);
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

	/**
	 * 根据主键获取机顶盒信息
	 */
	public TStbLog getStblogByPrimaryKey(String stbnum) {
		TStbLog log = t_stb_logMapper.getStblogByPrimaryKey(stbnum);
		return log;
	}

}
