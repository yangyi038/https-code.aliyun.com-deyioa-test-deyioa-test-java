package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TBind;

/** 酒店终端绑定业务逻辑接口 */
public interface T_bindService {

	/** 获取绑定列表 */
	public List<TBind> browseBindList(JqGridPager jqGridPager, Map<String, Object> p);

	/**
	 * 精确查询绑定
	 * 
	 * @param p
	 * @return
	 */
	public List<TBind> selectBind(Map<String, Object> p);

	/**
	 * 新增绑定
	 * 
	 * @param bind
	 * @return
	 */
	public boolean insertBind(TBind order);

	/**
	 * 批量导入酒店机顶盒绑定关系
	 */
	public int importBind(List<TBind> list);

	/**
	 * 删除绑定
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delBind(long sid);

	/**
	 * 查询绑定是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countBind(Map<String, Object> p);

	/**
	 * 查询绑定详情
	 * 
	 * @param sid
	 * @return
	 */
	public TBind selectByPrimaryKey(long sid);

	/**
	 * 修改绑定
	 * 
	 * @param order
	 * @return
	 */
	public boolean updateByPrimaryKey(TBind bind);

}
