/**
 * <p>标题: 初始化系统代码参数</p>
 * @author dyl
 * @version 1.0
 */

package com.framework;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContextEvent;

import org.mybatis.spring.SqlSessionTemplate;

import com.fs.comm.mapper.MianMapper;
import com.fs.comm.model.Parameter;

/**
 *
 */
public class OptionDictSelect  extends OptionDictSupport{
	public SqlSessionTemplate sqlSession;  
	/**
	 * 初始化所有选项字典
	 */
	public void init(SqlSessionTemplate sqlSession,ServletContextEvent sce) {
		this.sqlSession=sqlSession;
		try {
		initParameter(sce);//参数菜单
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}
	/*
	 * 初始化
	 */
	/*
	 * 初始化
	 */
	public void initParameter(ServletContextEvent sce) throws SQLException {
		try {
		 MianMapper um=sqlSession.getMapper(MianMapper.class);
		 Map<String, Object> p=new HashMap<String, Object>();
		 List<Parameter>  ps=um.getParameters(null);
		 TreeMap<String, TreeMap<String, Object>> treeMap=new TreeMap<String, TreeMap<String, Object>>();
		 TreeMap valueMap=null;
		 for(Parameter parameter:ps){
				if(treeMap.get(parameter.getPtype())!=null){
					valueMap=treeMap.get(parameter.getPtype());
					valueMap.put(parameter.getPcode().toString(), parameter.getPname());
				}else{
					valueMap = new TreeMap();
					valueMap.put("", "");
					valueMap.put(parameter.getPcode().toString(), parameter.getPname());
				}
				treeMap.put(parameter.getPtype(), valueMap);
		 }
		 GlobalName.parameter=treeMap;
		} catch(Exception e){
            System.out.println("初始化环境变量出错：");
            e.printStackTrace();
        }
	}
}
