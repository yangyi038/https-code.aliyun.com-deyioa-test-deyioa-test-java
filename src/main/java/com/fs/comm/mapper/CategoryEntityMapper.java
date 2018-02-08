package com.fs.comm.mapper;

import java.util.List;

import com.fs.comm.model.CategoryEntity;

public interface CategoryEntityMapper {
	
	/**
	 * 获取商店列表
	 * 
	 * @param map
	 * @return
	 */
	public List<CategoryEntity> getCategoryList();
	
    int deleteByPrimaryKey(String id);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);
}