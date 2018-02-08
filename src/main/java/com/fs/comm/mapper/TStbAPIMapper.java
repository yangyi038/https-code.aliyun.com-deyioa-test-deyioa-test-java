package com.fs.comm.mapper;

import org.springframework.stereotype.Repository;
import com.fs.comm.model.TStb;

/**
 * 机顶盒管理，数据映射接口
 * 
 * @author
 */
@Repository
@SuppressWarnings("javadoc")
public interface TStbAPIMapper {

	
	/**
	 * 根据ID查询
	 * @param stbnum
	 * @return
	 */
	TStb queryTsbById(String stbnum);
	

}