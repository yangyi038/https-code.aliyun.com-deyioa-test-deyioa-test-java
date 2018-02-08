package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.Column;

/**
 * 栏目mapper
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface ColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    int insertSelective(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Column record);

    int updateByPrimaryKey(Column record);
    
    List<Map<String,Object>> getAllColumnInfo(Map<String, Object> p);
    
    int delColumnById(Integer id);
    
    int agreeColumnById(Integer id);
    
    int unAgreeColumnById(Integer id);
    
    List<Column> selectColumnClassify();
    
    List<Column> browseColumn(Map<String, Object> map); 
    
    int countColumn(Map<String, Object> map); 
    
    /**
     * 获取栏目列表
     * @return
     */
    List<Column> getRootColumnList(Map<String, Object> p);
    
    
}