package com.fs.comm.mapper;

import java.util.List;

import com.fs.comm.model.CateEntity;

public interface CateEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(CateEntity record);

    int insertSelective(CateEntity record);

    CateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CateEntity record);

    int updateByPrimaryKey(CateEntity record);
    
	/**
	 * 获取商店列表
	 * 
	 * @param map
	 * @return
	 */
	public List<CateEntity> getCateList();
}