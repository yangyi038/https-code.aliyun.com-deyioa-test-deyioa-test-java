package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fantastic.TBaseExample;
import com.fs.comm.model.TBind;

/**
 * 閰掑簵缁堢缁戝畾涓氬姟锛屾暟鎹槧灏勬帴鍙�
 * 
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TBindMapper {

	/**
	 * 获取酒店机顶盒绑定列表
	 * 
	 * @param map
	 * @return
	 */
	List<TBind> getBindList(Map<String, Object> map);

	/**
	 * 绮剧‘鏌ユ壘缁戝畾
	 * 
	 * @param example
	 * @return
	 */
	List<TBind> getBind(TBaseExample example);

	/**
	 * 绑定
	 * 
	 * @param bind
	 * @return
	 */
	int insertBind(TBind record);

	/**
	 * 解绑
	 * 
	 * @param sid
	 * @return
	 */
	int deleteBind(Long sid);

	/**
	 * 查询绑定关系是否存在
	 * 
	 * @param map
	 * @return
	 */
	int countBind(Map<String, Object> map);

	/**
	 * 淇敼缁戝畾
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(TBind record);

	/**
	 * 鑾峰彇缁戝畾璇︽儏
	 * 
	 * @param sid
	 * @return
	 */
	TBind selectByPrimaryKey(Long sid);

	int insertSelective(TBind record);

	/**
	 * 修改绑定信息
	 * 
	 * @param record
	 * @return
	 */
	int updateBindInfo(TBind record);
	
	/**
	 * 根据机顶盒账号查询
	 * @param stbNum
	 * @return
	 */
	TBind selectBindByStbNum(String stbNum);

}