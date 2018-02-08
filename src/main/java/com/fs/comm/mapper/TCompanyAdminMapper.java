package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.TCompanyAdmin;

/**
 * 运营商管理员接口
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface TCompanyAdminMapper {
	
	/**
	 * 获取运营商管理员列表
	 * @param p
	 * @return
	 */
	List<TCompanyAdmin> getAdminList(Map<String, Object> p);
	
	/**
	 * 添加运营商管理员
	 * @param record
	 * @return
	 */
	int insertAdmin(TCompanyAdmin record);
	
	/**
	 * 删除管理员
	 * @param sid
	 * @return
	 */
	int deleteAdmin(Long sid);
	
	/**
	 * 获取管理员信息
	 * @param p
	 * @return
	 */
	TCompanyAdmin getAdmin(Map<String, Object> p);
	

    int insert(TCompanyAdmin record);

    TCompanyAdmin selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TCompanyAdmin record);

    int updateByPrimaryKey(TCompanyAdmin record);
}