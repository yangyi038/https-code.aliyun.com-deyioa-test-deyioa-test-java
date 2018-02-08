package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Parameter;

public interface ParameterMapper {

	/**
	 * 获取类型
	 * 
	 * @param map
	 * @return
	 */
	Parameter getParameter(Map<String, Object> map);

	/**
	 * 获取枚举列表
	 * 
	 * @param map
	 * @return
	 */
	List<Parameter> getParameterList(Map<String, Object> map);

	/**
	 * 删除枚举
	 * 
	 * @param id
	 * @return
	 */
	int deleteParameter(Integer id);

	int insert(Parameter record);

	/**
	 * 添加枚举配置
	 * 
	 * @param record
	 * @return
	 */
	int insertParameter(Parameter record);

	Parameter selectByPrimaryKey(Integer id);

	/**
	 * 修改枚举信息
	 * 
	 * @param record
	 * @return
	 */
	int updateParameter(Parameter record);

	int updateByPrimaryKey(Parameter record);
	
	/**
	 * 根据name获取类型
	 * 
	 * @param map
	 * @return
	 */
	public Parameter getParameterListByname(Parameter entity);
}