package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Streaming;

public interface StreamingService {

	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public boolean addStreaming(Streaming model);
	
	/**
	 * 查询所有
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllStreamingInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delStreamingById(Long id);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Streaming queryStreamingById(Long id); 
	
	/**
	 * 根据ID修改
	 * @param id
	 * @return
	 */
	public int updateStreamingById(Streaming model);
}
