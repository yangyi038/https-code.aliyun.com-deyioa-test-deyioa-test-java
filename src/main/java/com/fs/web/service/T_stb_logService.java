package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TStbLog;

/** 机顶盒日志管理业务逻辑接口 */
public interface T_stb_logService {

	/** 获取机顶盒日志列表 */
	public List<TStbLog> browseStbLogList(JqGridPager jqGridPager, Map<String, Object> p);
	
	
	/**
     * 添加机顶盒在线日志
     * @param record
     * @return
     */
	public boolean insertStbLog(TStbLog record);
	
	/**
	 * 根据主键获取机顶盒信息
	 * @param stbnum
	 * @return
	 */
	public TStbLog getStblogByPrimaryKey(String stbnum);

	/**
     * 修改机顶盒日志信息
     * @param record
     * @return
     */
    public boolean updateStblog(TStbLog record);

}
