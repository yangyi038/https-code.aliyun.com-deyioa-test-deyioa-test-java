package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompanyAdmin;
import com.fs.comm.model.THotel;

/** 运营商管理员管理业务逻辑接口 */
public interface T_com_operatorService {

	/** 浏览二级运营商列表 */
	public List<TComOperator> browseOperatorList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/** 获取二级运营商列表 */
	public List<TComOperator> getOperatorList( Map<String, Object> p);
	
	/**
	 * 添加
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean insertOperator(TComOperator admin);
	
	/**
	 * 删除
	 * @param sid
	 * @return
	 */
	public boolean delOperator(long sid);
	
	
	/**
	 * 主键获取二级运营商
	 * @param sid
	 * @return
	 */
	public TComOperator selectByPrimaryKey(Long sid);
	
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	public boolean  updateOperator(TComOperator record);

}
