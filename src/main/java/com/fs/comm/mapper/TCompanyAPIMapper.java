package com.fs.comm.mapper;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TCompany;

/**
 * 运营商业务接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TCompanyAPIMapper {
	
	TCompany getTCompanyByName(String companyname);
	
}