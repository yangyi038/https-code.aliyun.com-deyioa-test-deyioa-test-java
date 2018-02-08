package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TComOperator;

/**
 * 下级运营商
 * 
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TComOperatorMapper {

	/**
	 * 浏览二级运营商列表
	 * 
	 * @param p
	 * @return
	 */
	List<TComOperator> getOperatorList(Map<String, Object> p);

	/**
	 * 删除
	 * 
	 * @param sid
	 * @return
	 */
	int deleteOperator(Long sid);

	int insert(TComOperator record);

	/**
	 * 添加二级运营商
	 * 
	 * @param record
	 * @return
	 */
	int insertOperator(TComOperator record);

	/**
	 * 主键获取详情
	 * 
	 * @param sid
	 * @return
	 */
	TComOperator selectByPrimaryKey(Long sid);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateOperator(TComOperator record);

	int updateByPrimaryKey(TComOperator record);
}