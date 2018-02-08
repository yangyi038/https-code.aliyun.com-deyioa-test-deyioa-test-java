package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Channel;


/**
 * 直播频道 mapper
 * @author pzj
 *
 */
public interface ChannelMapper {
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 添加频道
     * @param record
     * @return
     */
    int insertChannel(Channel record);
    
    int insertSelective(Channel record);
    Channel selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Channel record);
    int updateByPrimaryKey(Channel record);
    
    /**
     * 浏览直播频道列表
     * @param p
     * @return
     */
    List<Map<String,Object>> getAllChannelInfo(Map<String, Object> p);
    
    int delChannelById(Integer id);
    
    int agreeChannel(Integer id);
    
    int unAgreeChannel(Integer id);
    
    int onlineChannel(Integer id);
    
    int unlineChannel(Integer id);
    
    List<Channel> selectAllChannelNo();
    
    Channel selectByNumber(Integer id);
    
}