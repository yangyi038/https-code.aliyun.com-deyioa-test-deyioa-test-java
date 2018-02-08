package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TStb;

/** 机顶盒管理业务逻辑接口 */
public interface T_stbService {

	/** 获取机顶盒列表 */
	public List<TStb> browseStbList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/**
	 * 获取机顶盒列表
	 * 
	 * @return
	 */
	public List<TStb> getStbList( Map<String, Object> p);

	/**
	 * 精确查询
	 * 
	 * @param p
	 * @return
	 */
	public TStb selectStb(Map<String, Object> p);

	/**
	 * 新增机顶盒
	 * 
	 * @return
	 */
	public boolean insertStb(TStb order);

	/**
	 * 删除机顶盒
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delStb(long sid);

	/**
	 * 查询机顶盒是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countStb(Map<String, Object> p);

	/**
	 * 查询机顶盒信息
	 * 
	 * @param sid
	 * @return
	 */
	public TStb getStbById(long sid);

	/**
	 * 修改机顶盒
	 * 
	 * @return
	 */
	public boolean updateStbById(TStb order);
	
	
	/**
	 * Excel批量导入机顶盒
	 * @param list
	 * @return
	 */
	public int importStb(List<TStb> list);

}
