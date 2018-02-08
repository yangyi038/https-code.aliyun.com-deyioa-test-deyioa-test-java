package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fs.comm.model.Department;

public interface DepartmentMapper {

	/**
	 * 添加
	 * @param department
	 * @return
	 */
	int saveDepartment(Department department);

	/**
	 * 修改
	 * @param department
	 * @return
	 */
	int updateDepartment(Department department);

	/**
	 * 浏览
	 * @param map
	 * @return
	 */
	List<Department> browseDepartment(Map<String, Object> map);

	Department loadDepartment(@Param(value = "id") Integer id);
	
	/**
	 * 获取商店部分列表
	 * 
	 * @return
	 */
	public List<Department> selectDepartment();

	int delById(@Param(value = "id") Integer id);

	int countDepartment(Map<String, Object> map);
}