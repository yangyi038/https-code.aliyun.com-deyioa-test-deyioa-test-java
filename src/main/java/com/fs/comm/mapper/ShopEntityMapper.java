package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.ShopEntity;

@Repository
@SuppressWarnings("javadoc")
public interface ShopEntityMapper {
    int insert(ShopEntity record);

    /**
     * 添加商铺
     * 
     * @param record
     * @return
     */
    public int insertSelective(ShopEntity record);
    
	/**
	 * 获取商店列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ShopEntity> getShopList(Map<String, Object> map);
	
	/**
	 * 获取商店一览列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ShopEntity> selectShopList(Map<String, Object> map);
	
	/**
	 * 通过主键获取商店信息
	 * 
	 * @param entity
	 * @return
	 */
	public ShopEntity selectByid(ShopEntity entity);
	
	/**
	 * 通过主键更新商店
	 * 
	 * @param entity
	 * @return
	 */
	public int updateShop(ShopEntity entity);
	
	/**
	 * 通过主键删除商铺
	 * 
	 * @param entity
	 * @return
	 */
	public int deleteByid(ShopEntity entity);
	
	/**
	 * 通过主键恢复商铺
	 * 
	 * @param entity
	 * @return
	 */
	public int recoveryByid(ShopEntity entity);
	
	/**
	 * 获取已删除商店一览列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ShopEntity> selectRecoveryShopList(Map<String, Object> map);
	
	/**
	 * 通过商铺名称获取商店信息
	 * 
	 * @param entity
	 * @return
	 */
	public ShopEntity selectByname(ShopEntity entity);
}