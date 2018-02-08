package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.THotelGroup;
import com.fs.web.vomodel.Group_SearchVo;

/**
 * 酒店分组业务，数据映射接口
 * 
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface THotelGroupMapper {

	/**
	 * 根据条件查询组信息
	 * 
	 * @param vo
	 * @return
	 */
	List<THotelGroup> getGroupInfoByCondition(Group_SearchVo vo);

	/**
	 * 获取分组列表
	 * 
	 * @param map
	 * @return
	 */
	List<THotelGroup> getGroupList();

	/**
	 * 精确查找分组
	 * 
	 * @param example
	 * @return
	 */
	List<THotelGroup> getGroup(Map<String, Object> p);

	/**
	 * 新增分组
	 * 
	 * @param record
	 * @return
	 */
	int insertGroup(THotelGroup record);

	/**
	 * 删除分组
	 * 
	 * @param sid
	 * @return
	 */
	int deleteGroup(Long sid);

	/**
	 * 查询分组是否存在
	 * 
	 * @param map
	 * @return
	 */
	int countGroup(Map<String, Object> map);

	/**
	 * 修改分组
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(THotelGroup record);
	
	/**
	 * 修改分组
	 * 
	 * @param record
	 * @return
	 */
	int updateHotelGroupInfo(THotelGroup record);

	/**
	 * 获取分组详情
	 * 
	 * @param sid
	 * @return
	 */
	THotelGroup selectByPrimaryKey(Long sid);

	/**
	 * 最大组编号
	 * 
	 * @return
	 */
	Integer getMaxGroupNum();
	
	
	/*******************************jzb start**************************************************/
	/**
	 * 根据Mac地址查询
	 * @param mac
	 * @return
	 */
	THotelGroup getTHoteGrouplByNum(Integer groupnum);
	
	/*******************************jzb end**************************************************/

}