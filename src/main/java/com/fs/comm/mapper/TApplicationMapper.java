package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TApplication;

/**
 * 应用表映射接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TApplicationMapper {

	int deleteByPrimaryKey(Integer id);
    int insert(TApplication record);
    
    /**
     * 添加应用
     * @param record
     * @return
     */
    int insertApplication(TApplication record);
    
    
    TApplication selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TApplication record);
    int updateByPrimaryKey(TApplication record);
    
    /**
     * 浏览应用列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getApplicationList(Map<String, Object> p);
    
    int delAppManagerById(Integer id);
    
    int onlineAppManager(Integer id);
    
    int unlineAppManager(Integer id);
    
    int undercarriageAppManager(Integer id);
    
    List<String> queryAllParameterType();
    
    List<TApplication> queryAllName();
    
    TApplication queryAppManagerByName(String name);
    
    int updateAppManagerById(TApplication model);
}