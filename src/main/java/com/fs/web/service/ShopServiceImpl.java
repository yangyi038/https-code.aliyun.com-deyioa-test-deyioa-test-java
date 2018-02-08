package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.DepartmentMapper;
import com.fs.comm.mapper.ShopEntityMapper;
import com.fs.comm.model.Department;
import com.fs.comm.model.ShopEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ShopService")
public class ShopServiceImpl implements ShopService {

	@Resource
	private ShopEntityMapper shopEntityMapper;
	
	@Resource
	private DepartmentMapper departmentMapper;
	/**
	 * 获取商店列表
	 *  
	 * @param map
	 * @return
	 */
	@Override
	public List<ShopEntity> selectShopList(JqGridPager jqGridPager, Map<String, Object> map) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<ShopEntity> shopList = shopEntityMapper.selectShopList(map);

		PageInfo page = new PageInfo(shopList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		
		return shopList;
	}

	 /**
     * 添加商铺
     * 
     * @param record
     * @return
     */
	@Override
	public boolean insertSelective(ShopEntity record) {
		int i = shopEntityMapper.insertSelective(record);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 通过主键更新商店
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateShop(ShopEntity entity) {
		int i = shopEntityMapper.updateShop(entity);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 通过主键删除商铺
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean deleteByid(ShopEntity entity) {
		int i = shopEntityMapper.deleteByid(entity);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取商店部分列表
	 * 
	 * @return
	 */
	@Override
	public List<Department> selectDepartment() {
		return departmentMapper.selectDepartment();
	}

	/**
	 * 通过主键恢复商铺
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean recoveryByid(ShopEntity entity) {
		int i = shopEntityMapper.recoveryByid(entity);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取已删除商店列表
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<ShopEntity> selectRecoveryShopList(JqGridPager jqGridPager, Map<String, Object> map) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<ShopEntity> shopList = shopEntityMapper.selectRecoveryShopList(map);

		PageInfo page = new PageInfo(shopList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		
		return shopList;
	}

	/**
	 * 通过商铺名称获取商店信息
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public ShopEntity selectByname(ShopEntity entity) {
		ShopEntity shop = shopEntityMapper.selectByname(entity);
		return shop;
	}
}
