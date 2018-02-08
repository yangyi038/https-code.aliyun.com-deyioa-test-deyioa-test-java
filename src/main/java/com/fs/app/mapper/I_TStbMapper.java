package com.fs.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TStb;

/**
 * 机顶盒管理，数据映射接口
 * 
 * @author pzj
 */
@Repository
@SuppressWarnings("javadoc")
public interface I_TStbMapper {


	/**
	 * 精确查找机顶盒
	 * 
	 * @return
	 */
	TStb getStb(Map<String, Object> p);
	
	/**
	 * 修改机顶盒信息
	 * 
	 * @param record
	 * @return
	 */
	int updateStbInfo(TStb record);


}