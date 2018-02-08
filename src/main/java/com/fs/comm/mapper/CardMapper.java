package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Card;

public interface CardMapper {
	
    int deleteByPrimaryKey(Long id);
    int insert(Card record);
    int insertSelective(Card record);
    Card selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Card record);
    int updateByPrimaryKey(Card record);
    
    
    List<Map<String,Object>> getAllCardInfo(Map<String, Object> p);
    
    int delCardById(Long id);
    
    List<Card> getAllCardByFixingId(Long fixingId);
}