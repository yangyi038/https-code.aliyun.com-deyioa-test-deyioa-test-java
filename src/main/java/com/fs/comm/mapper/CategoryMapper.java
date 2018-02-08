package com.fs.comm.mapper;


import java.util.List;
import java.util.Map;
 
 



import org.apache.ibatis.annotations.Param;

import com.fs.comm.model.Category;

public interface CategoryMapper {
	//分类列表
	List<Category> browseCategory(Map<String, Object> map);  
	//分类详情 
	Category loadCategory(@Param(value="cat_id") String cat_id);
	//分类添加
	int saveCategory(Category category);
	//修改分类
	int updateCategorylj(Map<String,Object> p);
	//分类数量
	int countCategory(Map<String,Object> p);
	//分类删除
	int delCategory(@Param(value="cat_id") String  cat_id);
    //分类修改
	int updateCategory(Category category); 
}