package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fs.app.vomodel.I_Hotel_SearchVo;
import com.fs.comm.model.TStb;

/**
 * 机顶盒管理，数据映射接口
 * 
 * @author pzj
 */
@Repository
@SuppressWarnings("javadoc")
public interface TStbMapper {

	/**
	 * 获取机顶盒列表
	 * 
	 * @param map
	 * @return
	 */
	List<TStb> getStbList(Map<String, Object> map);

	/**
	 * 精确查找机顶盒
	 * 
	 * @return
	 */
	TStb getStb(Map<String, Object> p);

	/**
	 * 新增机顶盒
	 * 
	 * @param record
	 * @return
	 */
	int insertStb(TStb record);

	/**
	 * 删除机顶盒
	 * 
	 * @param sid
	 * @return
	 */
	int deleteStb(Long sid);

	/**
	 * 查询机顶盒是否存在
	 * 
	 * @param map
	 * @return
	 */
	int countStb(Map<String, Object> map);

	/**
	 * 修改机顶盒信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(TStb record);

	/**
	 * 修改机顶盒信息
	 * 
	 * @param record
	 * @return
	 */
	int updateStbInfo(TStb record);

	/**
	 * 获取机顶盒详情
	 * 
	 * @param sid
	 * @return
	 */
	TStb selectByPrimaryKey(Long sid);

	/**
	 * 根据查询条件获取唯一机顶盒信息
	 * 
	 * @return
	 */
	TStb getDistinctStbByParams(I_Hotel_SearchVo params);

	/*******************************
	 * jzb start
	 **************************************************/
	/**
	 * 根据Mac地址查询
	 * 
	 * @param mac
	 * @return
	 */
	TStb getTStbByMac(@Param(value = "mac") String mac);

	int updateByPrimaryKeySelective(TStb record);

	TStb queryTsbById(String stbnum);

	/*******************************
	 * jzb end
	 **************************************************/

}