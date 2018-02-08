package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TStbLog;

/**
 * 终端日志接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TStbLogMapper {
	
	/**
	 * 获取日志列表
	 * @param para
	 * @return
	 */
	List<TStbLog> getStbLogList(Map<String, Object> para);
	
	
	
    int deleteByPrimaryKey(String stbtoken);

    int insert(TStbLog record);

    /**
     * 添加机顶盒在线日志
     * @param record
     * @return
     */
    int insertStbLog(TStbLog record);

    /**
     * 主键获取机顶盒日志信息
     * @param stbnum
     * @return
     */
    TStbLog getStblogByPrimaryKey(String stbnum);

    /**
     * 修改机顶盒日志信息
     * @param record
     * @return
     */
    int updateStblog(TStbLog record);

    int updateByPrimaryKey(TStbLog record);
}