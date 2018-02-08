package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TCompany;

/**
 * 运营商业务接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TCompanyMapper {
	
	/**
	 * 获取运营商列表
	 * @param p
	 * @return
	 */
	List<TCompany>  getCompanyList(Map<String, Object> p);
	
	/**
	 * 添加运营商
	 * @param record
	 * @return
	 */
	int insertCompany(TCompany record);
	
	/** 
	 * 删除运营商
	 * @param sid
	 * @return
	 */
    int delCompany(Long sid);
    
    /**
     * 主键查询
     * @param sid
     * @return
     */
    TCompany selectByPrimaryKey(Long sid);
    
    

    int insert(TCompany record);

    /**
     * 修改运营商
     * @param record
     * @return
     */
    int updateCompany(TCompany record);

    int updateByPrimaryKey(TCompany record);
}