package com.fs.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.CategoryMapper;
import com.fs.comm.model.Category;
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo;
@Service("CategoryService")
/** 分类管理业务逻辑接口实现 */
public class CategoryServiceImpl implements CategoryService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private CategoryMapper categoryMapper;

	/** 新增分类 */
	public boolean saveCategory(Category category){
		try{
		if(categoryMapper.saveCategory(category)>0){
			return true;
		}else{
			return false;
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}	
	}
	public boolean updateCategorylj(Map<String,Object> p){
		try{
			if(categoryMapper.updateCategorylj(p)>0){
				return true;
			}else{
				return false;
			}
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}	
	}
	 /** 修改分类 */ 
	public boolean updateCategory(Category category){
		try{
		if(categoryMapper.updateCategory(category)>0){
			return true;
		}else{
			return false;
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}	
	}
 
	/** 浏览分类 */ 
	public List<Category> browseCategory(JqGridPager jqGridPager,Map<String, Object> p){
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Category>  queryCategory=categoryMapper.browseCategory(p);
		PageInfo page = new PageInfo(queryCategory);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryCategory;
	}
	/** 浏览分类 */
	public List<Category> browseCategory(Map<String, Object> p){
		List<Category>  queryCategory=categoryMapper.browseCategory(p);
		return queryCategory;
	}
	 /** 删除指定的分类 */
	public boolean delCategory(String cat_id){
		try{
			if(categoryMapper.delCategory(cat_id)>0){
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
	public int countCategory(Map<String, Object> p){
		return categoryMapper.countCategory(p);
	}
	 /** 装载指定的分类 */ 
	public Category loadCategory(String cat_id){
		return categoryMapper.loadCategory(cat_id);
	}	 


}
