package com.fs.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Department;
import com.fs.comm.model.ShopEntity;

@Service
public interface ShopService {

	/**
	 * 获取商店列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ShopEntity> selectShopList(JqGridPager jqGridPager, Map<String, Object> map);
	
    /**
     * 添加商铺
     * 
     * @param record
     * @return
     */
    public boolean insertSelective(ShopEntity record);
    
	/**
	 * 通过主键更新商店
	 * 
	 * @param entity
	 * @return
	 */
	public boolean updateShop(ShopEntity entity);
	
	/**
	 * 通过主键删除商铺
	 * 
	 * @param entity
	 * @return
	 */
	public boolean deleteByid(ShopEntity entity);
	
	/**
	 * 获取商店部分列表
	 * 
	 * @return
	 */
	public List<Department> selectDepartment();
	
	/**
	 * 通过主键恢复商铺
	 * 
	 * @param entity
	 * @return
	 */
	public boolean recoveryByid(ShopEntity entity);
	
	/**
	 * 获取已删除商店列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ShopEntity> selectRecoveryShopList(JqGridPager jqGridPager, Map<String, Object> map);
	
	/**
	 * 通过商铺名称获取商店信息
	 * 
	 * @param entity
	 * @return
	 */
	public ShopEntity selectByname(ShopEntity entity);
}
