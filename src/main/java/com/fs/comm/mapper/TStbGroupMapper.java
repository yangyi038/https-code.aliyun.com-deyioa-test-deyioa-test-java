package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TStbGroup;

/**
 * 机顶盒分组业务，数据映射接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TStbGroupMapper {

	/**
	 * 获取分组列表
	 * @param map
	 * @return
	 */
	List<TStbGroup> getGroupList(Map<String, Object> map); 
	
	/**
	 * 精确查找分组
	 * @param example
	 * @return
	 */
	List<TStbGroup> getGroup(Map<String, Object> p);
	
	/**
	 * 新增分组
	 * @param record
	 * @return
	 */
	int insertGroup(TStbGroup record);
	
	/**
	 * 删除分组
	 * @param sid
	 * @return
	 */
	int deleteGroup(Long sid);

	/**
	 * 查询分组是否存在
	 * @param map
	 * @return
	 */
	int countGroup(Map<String, Object> map); 
	
	/**
	 * 修改分组
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(TStbGroup record);
	
	/**
	 * 修改机顶盒组信息
	 * @param record
	 * @return
	 */
	int updateStbGroupInfo(TStbGroup record);
	
    /**
     * 获取分组详情
     * @param sid
     * @return
     */
	TStbGroup selectByPrimaryKey(Long sid);
	
	/**
	 * 获取最大组编号
	 * @return
	 */
	Integer getMaxStbGroupNum();
	
	
	TStbGroup getTStbGroupByStbGroup(String groupnum);
	
	/**
	 * 根据组名称查询
	 * @param groupName
	 * @return
	 * @author jzb
	 */
	TStbGroup getTStbGroupByGroupName(String groupName);
	
	/**
	 * 查询全部
	 * @return
	 * @author jzb
	 */
	List<TStbGroup> getAllStbGroup();
}