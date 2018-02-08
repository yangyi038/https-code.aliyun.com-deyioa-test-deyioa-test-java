package com.fs.web.service;


import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.SysParameter;

public interface SysParameterManagerService {
	
	/**
	 * 添加系统配置
	 * @param model
	 * @return
	 */
	public int insertSysParameter(SysParameter model);
	
	/**
	 * 获取系统参数列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllSysParameterInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public SysParameter getSysParameterById(Long id);
	
	/**
	 * 根据ID修改
	 * @param model
	 * @return
	 */
	public int updateSysParameterById(SysParameter model);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delSysParameter(Long id);
	
	/**
	 * 获取系统参数
	 * @param id
	 * @return
	 */
	public SysParameter getSysParameter(Map<String, Object> map);
	
	
}
