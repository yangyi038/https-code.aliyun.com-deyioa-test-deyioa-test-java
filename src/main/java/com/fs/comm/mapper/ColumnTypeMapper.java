package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.ColumnType;

@Repository
@SuppressWarnings("javadoc")
public interface ColumnTypeMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(ColumnType record);
    int insertSelective(ColumnType record);
    ColumnType selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(ColumnType record);
    int updateByPrimaryKey(ColumnType record);
	
	List<Map<String,Object>> getAllColumnType(Map<String, Object> p);
	
	int deleteColumnTypeById(ColumnType record);
	
	ColumnType queryColumnTypeByModel(ColumnType record);
	
	int agreeCheckColumnType(ColumnType record);
	
	int unAgreeCheckColumnType(ColumnType record);
	
	int agreeOnline(ColumnType record);
	
	int unAgreeOnline(ColumnType record);
	
	int updateColumnTypeSelective(ColumnType record);
}