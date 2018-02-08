package com.fs.web.service;

import java.util.List;
import java.util.Map; 
import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Category;
/** 分类管理业务逻辑接口 */
public interface CategoryService {
	/** 浏览分类 */
	public List<Category> browseCategory(JqGridPager jqGridPager,Map<String, Object> p);
	/** 浏览分类 */ 
	public List<Category> browseCategory(Map<String, Object> p);
 /** 查看分类详情 */ 
	public Category loadCategory( String cat_id);	
   /** 删除指定的分类 */ 
	public boolean delCategory(String cat_id);	
	/** 新增分类 */
	public boolean saveCategory(Category category);
/** 修改分类*/
	public boolean updateCategorylj(Map<String,Object> p);
	/** 修改分类*/
	public boolean updateCategory(Category category); 
	 /**统计指定类的查询结果*/
	public int countCategory(Map<String, Object> p);	
	
}
