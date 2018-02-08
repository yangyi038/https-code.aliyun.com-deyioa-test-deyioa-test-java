package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.THotel;

/** 酒店管理业务逻辑接口 */
public interface T_hotelService {

	/** 获取酒店列表 */
	public List<THotel> browseHotelList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/** 获取酒店列表-不分页 */
	public List<THotel> getHotelList( Map<String, Object> p);

	/**
	 * 精确查询酒店
	 * 
	 * @param p
	 * @return
	 */
	public List<THotel> selectHotel(Map<String, Object> p);

	/**
	 * 新增酒店
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean insertHotel(THotel hotel);

	/**
	 * 删除酒店
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delHotel(long sid);

	/**
	 * 查询酒店是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int count_Hotel(Map<String, Object> p);

	/**
	 * 查询酒店详情
	 * 
	 * @param sid
	 * @return
	 */
	public THotel selectByPrimaryKey(long sid);

	/**
	 * 修改酒店信息
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean updateByPrimaryKey(THotel hotel);

}
