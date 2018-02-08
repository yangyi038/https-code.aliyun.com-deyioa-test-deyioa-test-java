package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.THotel;
import com.fs.comm.model.TStb;


/**
 * 閰掑簵淇℃伅锛屾暟鎹槧灏勬帴鍙�
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface THotelMapper {

	/**
	 * 获取酒店列表
	 * @param map
	 * @return
	 */
	List<THotel> getHotelList(Map<String, Object> map); 
	
	
	/**
	 * 鏂板閰掑簵
	 * @param hotel
	 * @return
	 */
	int insertHotel(THotel record);
	
	/**
	 * 鍒犻櫎閰掑簵
	 * @param sid
	 * @return
	 */
	int deleteHotel(Long sid);

	/**
	 * 鏌ヨ閰掑簵鏄惁瀛樺湪
	 * @param map
	 * @return
	 */
	int countHotel(Map<String, Object> map); 
	
	/**
	 * 修改酒店信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(THotel record);
	
    /**
     * 根据主键获取酒店信息
     * @param sid
     * @return
     */
	THotel selectByPrimaryKey(Long sid);

	/**
	 * 鑾峰彇鏈�澶х紪鍙�
	 * @return Integer
	 */
	Integer getMaxHotelNum();
    
    
    int insertSelective(THotel record);

    /**
     * 修改酒店信息
     * @param record
     * @return
     */
    int updateHotelInfo(THotel record);
    
    
	/**
	 * 根据查询条件获取唯一酒店信息
	 * 
	 * @return
	 */
	THotel getDistinctHotelByParams(TStb params );
	
	/*******************************jzb start**************************************************/
	/**
	 * 根据Mac地址查询
	 * @param mac
	 * @return
	 */
	THotel getTHotelById(Long sid);
	
	/*******************************jzb end**************************************************/
    
}