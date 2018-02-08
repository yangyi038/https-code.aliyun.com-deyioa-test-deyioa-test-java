package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Sysmenu;

/**
 * 系统菜单数据接口
 * @author pzj
 *
 */
public interface SysmenuMapper {
	
	/**
	 * 浏览系统菜单列表
	 * @param map
	 * @return
	 */
	List<Sysmenu> browseSysmenu(Map<String, Object> map);  
	
	/**
	 * 获取菜单信息
	 * @param id
	 * @return
	 */
	Sysmenu selectByPrimaryKey(Integer id);
	
	/**
	 * 添加系统菜单
	 * @param record
	 * @return
	 */
	int insertSelectiveSysmenu(Sysmenu record);
	
	/**
	 * 删除系统菜单
	 * @param id
	 * @return
	 */
    int deleteSysmenuById(Integer id);
    
    /**
     * 修改菜单信息
     * @param record
     * @return
     */
    int updateSysmenu(Sysmenu record);
    
    /**
     * 统计指定查询结果
     * @param map
     * @return
     */
    int countSysmenu(Map<String, Object> map);
    
    

    int insert(Sysmenu record);

    int updateByPrimaryKey(Sysmenu record);
    
}