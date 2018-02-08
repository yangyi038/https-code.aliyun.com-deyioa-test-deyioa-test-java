package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Card;
import com.fs.comm.model.Fixing;

public interface FixingService {

	/**
	 * 添加前端设备
	 * @param model
	 * @return
	 */
	public boolean addFixing(Fixing model);
	
	/**
	 * 查询所有的前端设备
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllFixingInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delFixingById(Long id);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Fixing queryFixingById(Long id); 
	
	/**
	 * 根据ID修改
	 * @param id
	 * @return
	 */
	public int updateFixingById(Fixing model);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Fixing> queryAllFixing();
	
	/**
	 * 导入
	 * @param list
	 * @return
	 */
	public int importFixing(List<Fixing> list);
	
	/*********************************************************************************************/
	/**
	 * 添加采集卡
	 * @param model
	 * @return
	 */
	public boolean addCard(Card model);
	
	
	/**
	 * 查询所有的采集卡
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllCardInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delCardById(Long id);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Card queryCardById(Long id); 
	
	/**
	 * 根据ID修改
	 * @param id
	 * @return
	 */
	public int updateCardById(Card model);
	
	/**
	 * 查询
	 * @param fixingId
	 * @return
	 */
	public List<Card> queryAllCardByFixingId(Long fixingId);
	
	/**
	 * 导入
	 * @param list
	 * @return
	 */
	public int importCard(List<Card> list,Long fixingId);

}
