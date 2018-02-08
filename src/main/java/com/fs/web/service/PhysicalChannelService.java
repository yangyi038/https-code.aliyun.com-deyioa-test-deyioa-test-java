package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Channel;
import com.fs.comm.model.PhysicalChannel;


public interface PhysicalChannelService {
	
	/**
	 * 添加频道信息
	 * @param model
	 * @return
	 */
	public boolean addPhysicalChannel(PhysicalChannel model);
	
	/**
	 * 查询所以的频道信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllPhysicalChannelInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除物理频道信息
	 * @param id
	 * @return
	 */
	public int delPhsicalChannelById(Integer id);
	
	

	
	/**
	 * 根据ID激活
	 * @param id
	 * @return
	 */
	public int activateChannel(Integer id);
	
	/**通过ID停止
	 * 
	 * @param id
	 * @return
	 */
	public int stopChannel(Integer id);
	
	/**
	 * 根据ID设定无效
	 * @param id
	 * @return
	 */
	public int unEffectiveChannel(Integer id);
	
	/**
	 * 根据ID设定有效
	 * @param id
	 * @return
	 */
	public int effectiveChannel(Integer id);
	
	/**
	 * 废弃
	 * @param id
	 * @return
	 */
	public int scrapChannel(Integer id);
	
	/**
	 * 根据ID查询物理频道信息
	 * @param id
	 * @return
	 */
	public PhysicalChannel getPhysicalChannelById(Integer id);
	
	/**
	 * 根据ID修改物理频道信息
	 * @param id
	 * @return
	 */
	public int updatePhysicalChannelById(PhysicalChannel model);
	
	/**
	 * 根据ID修改频道信息
	 * @return
	 */
	public int updateChannelById(Channel model);
	
	/**
	 * 查询所以的频道号
	 * @return
	 */
	public List<Channel> queryAllChannelNumber();

}
