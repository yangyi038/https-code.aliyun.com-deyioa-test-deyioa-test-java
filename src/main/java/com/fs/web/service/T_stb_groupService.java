package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TStbGroup;

/** 机顶盒分组管理业务逻辑接口 */
public interface T_stb_groupService {

	/** 获取分组列表 */
	public List<TStbGroup> browseStbGroupList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/**
	 * 获取机顶盒组列表
	 * 
	 * @param p
	 * @return
	 */
	public List<TStbGroup> getStbGroupList(Map<String, Object> p);

	/**
	 * 精确查找机顶盒组
	 * 
	 * @param p
	 * @return
	 */
	public List<TStbGroup> selectStbGroup(Map<String, Object> p);

	/**
	 * 添加分组
	 * 
	 * @param group
	 * @return
	 */
	public boolean insertStbGroup(TStbGroup group);

	/**
	 * 删除分组
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delStbGroup(long sid);

	/**
	 * 查询分组是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countStbGroup(Map<String, Object> p);

	/**
	 * 查询分组信息
	 * 
	 * @param sid
	 * @return
	 */
	public TStbGroup getStbGroupById(long sid);

	/**
	 * 修改分组信息
	 * 
	 * @param group
	 * @return
	 */
	public boolean updateStbGroupById(TStbGroup group);
	
	/**
	 * 根据组名称查询
	 * @param name
	 * @return
	 * @author jzb
	 */
	public TStbGroup getTStbGroupByGrpupName(String groupName);
	
	/**
	 * 查询所有信息
	 * @return
	 * @author jzb
	 * 
	 */
	public List<TStbGroup> getAllStbGroup();

}
