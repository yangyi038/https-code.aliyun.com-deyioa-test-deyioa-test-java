package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.THotelGroup;
import com.fs.web.vomodel.Group_SearchVo;

/** 酒店组管理业务逻辑接口 */
public interface T_hotel_groupService {

	/** 获取分组列表 */
	public List<THotelGroup> browseGroupList(JqGridPager jqGridPager, Map<String, Object> p);

	/**
	 * 精确查询分组
	 * 
	 * @param p
	 * @return
	 */
	public List<THotelGroup> selectGroup(Map<String, Object> p);

	/**
	 * 新增分组
	 * 
	 * @param group
	 * @return
	 */
	public boolean insertGroup(THotelGroup group);

	/**
	 * 删除分组
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delGroup(long sid);

	/**
	 * 查询分组是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countGroup(Map<String, Object> p);

	/**
	 * 查询分组信息
	 * 
	 * @param sid
	 * @return
	 */
	public THotelGroup getGroupById(long sid);

	/**
	 * 修改分组信息
	 * 
	 * @param group
	 * @return
	 */
	public boolean updateGroupById(THotelGroup group);
	
	/**
	 * 根据条件查询组信息
	 * 
	 * @param vo
	 * @return
	 */
	public List<THotelGroup> getGroupInfoByCondition(Group_SearchVo vo);


}
