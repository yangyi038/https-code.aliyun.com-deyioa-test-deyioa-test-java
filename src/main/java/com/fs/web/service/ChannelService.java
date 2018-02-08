package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.TApplication;
import com.fs.comm.model.Channel;
import com.fs.comm.model.ImageLable;
import com.fs.comm.model.ImageText;
import com.fs.comm.model.Title;

public interface ChannelService {
	
	/**
	 * 添加频道信息
	 * @param model
	 * @return
	 */
	public boolean addChannel(Channel model);
	
	/**
	 * 查询所以的频道信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllChannelInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除频道信息
	 * @param id
	 * @return
	 */
	public int delChannelById(Integer id);
	
	/**通过ID取消审核
	 * 
	 * @param id
	 * @return
	 */
	public int unAgreeChannel(Integer id);
	
	/**
	 * 根据ID通过审核
	 * @param id
	 * @return
	 */
	public int agreeChannel(Integer id);
	
	/**
	 * 根据ID上线
	 * @param id
	 * @return
	 */
	public int onlineChannel(Integer id);
	
	/**
	 * 根据ID下线
	 * @param id
	 * @return
	 */
	public int unlineChannel(Integer id);
	
	/**
	 * 根据ID查询直播信息
	 * @param id
	 * @return
	 */
	public Channel getChannelById(Integer id);
	
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
	
	/**
	 * 根据ID查询直播信息
	 * @param id
	 * @return
	 */
	public Channel getChannelByNumber(Integer number);
}
