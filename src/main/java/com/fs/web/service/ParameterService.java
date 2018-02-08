package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Parameter;

public interface ParameterService {

	/**
	 * 添加枚举配置
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertParameter(Parameter model);

	/**
	 * 浏览枚举列表
	 */
	public List<Parameter> browseParameter(JqGridPager jqGridPager, Map<String, Object> p);

	/**
	 * 获取参数列表
	 * 
	 * @param p
	 * @return
	 */
	public List<Parameter> getParameterList(Map<String, Object> p);

	/**
	 * 获取枚举信息
	 * 
	 * @param id
	 * @return
	 */
	public Parameter getParameter(Map<String, Object> map);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delParameter(Integer id);

	/**
	 * 修改枚举信息
	 * 
	 * @param par
	 * @return
	 */
	public boolean updateParameter(Parameter par);

}
