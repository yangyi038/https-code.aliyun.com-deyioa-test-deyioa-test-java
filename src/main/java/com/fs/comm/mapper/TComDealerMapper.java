package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TComDealer;

/**
 * 经销商
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TComDealerMapper {
	
	/**
	 * 浏览经销商列表
	 * @param p
	 * @return
	 */
	List<TComDealer> getDealerList(Map<String, Object> p);
	
	/**
	 * 删除经销商
	 * @param sid
	 * @return
	 */
    int deleteDealer(Long sid);

    int insert(TComDealer record);

    /**
     * 添加经销商
     * @param record
     * @return
     */
    int insertDealer(TComDealer record);

    /**
     * 主键获取经销商
     * @param sid
     * @return
     */
    TComDealer selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TComDealer record);

    int updateByPrimaryKey(TComDealer record);
}