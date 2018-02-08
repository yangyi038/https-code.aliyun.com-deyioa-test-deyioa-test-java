package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.DepartmentMapper;
import com.fs.comm.model.Department;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("departmentService")
/** 部门管理业务逻辑接口实现 */
public class DepartmentServiceImpl implements DepartmentService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private DepartmentMapper departmentMapper;

	/** 新增部门 */	
	public boolean saveDepartment(Department department){
		try {
			if (departmentMapper.saveDepartment(department) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/** 修改部门 */	
	public boolean updateDepartment(Department department){
		try{
		if(departmentMapper.updateDepartment(department)>0){
			return true;
		}else{
			return false;
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}	
	}

	/** 浏览部门 */
	public List<Department> browseDepartment(JqGridPager jqGridPager,Map<String, Object> p){
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Department>  queryDepartment=departmentMapper.browseDepartment(p);
		PageInfo page = new PageInfo(queryDepartment);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryDepartment;
	}
	
	/** 浏览部门 */
	public List<Department> browseDepartment(Map<String, Object> p){
		List<Department>  deps=departmentMapper.browseDepartment(p);
		return deps;
	}
	
	/** 删除指定的部门 */
	public boolean delDepartment(Integer id){
		try{
			if(departmentMapper.delById(id)>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}	
	}
	
	
	/** 统计指定类的查询结果 */
	@SuppressWarnings("unchecked")
	public int countDepartment(Map<String, Object> p){
		return departmentMapper.countDepartment(p);
	}
	
	/** 装载指定的部门 */
	public Department loadDepartment(Integer id){
		return departmentMapper.loadDepartment(id);
	}	


}
