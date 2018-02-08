package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Department;
/** 部门管理业务逻辑接口 */
public interface DepartmentService {
	/** 浏览部门 */
	public List<Department> browseDepartment(JqGridPager jqGridPager,Map<String, Object> p);
	/** 浏览部门 */
	public List<Department> browseDepartment(Map<String, Object> p);
	/** 装载指定的部门 */	
	public Department loadDepartment( Integer id);	
	/** 删除指定的部门 */	
	public boolean delDepartment(Integer id);	
	/** 新增部门 */
	public boolean saveDepartment(Department department);
	/** 修改部门 */
	public boolean updateDepartment(Department department);
	/**统计指定类的查询结果*/
	public int countDepartment(Map<String, Object> p);	
	
}
