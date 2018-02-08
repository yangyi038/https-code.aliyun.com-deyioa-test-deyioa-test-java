package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.IndustryEntity;

public interface IndustryEntityMapper {
	
	/**
	 * 获取分类列表
	 * 
	 * @param map
	 * @return
	 */
	public List<IndustryEntity> getIndustryList(Map<String, Object> map);
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(IndustryEntity record);

	public IndustryEntity selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(IndustryEntity record);

	public int updateByPrimaryKey(IndustryEntity record);
}