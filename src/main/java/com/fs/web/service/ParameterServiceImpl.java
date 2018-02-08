package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.model.Parameter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ParameterService")
public class ParameterServiceImpl implements ParameterService {

	@Resource
	private ParameterMapper parameterMapper;

	/**
	 * 获取枚举信息
	 */
	@Override
	public Parameter getParameter(Map<String, Object> map) {
		Parameter en = parameterMapper.getParameter(map);
		return en;
	}

	/**
	 * 浏览枚举列表
	 */
	public List<Parameter> browseParameter(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Parameter> list = parameterMapper.getParameterList(p);
		if (list != null && list.size() > 0) {
			for (Parameter par : list) {

			}
		}
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	/**
	 * 获取参数列表
	 * 
	 * @param p
	 * @return
	 */
	public List<Parameter> getParameterList(Map<String, Object> p) {
		List<Parameter> list = parameterMapper.getParameterList(p);
		if (list != null && list.size() > 0) {
			for (Parameter par : list) {

			}
		}
		return list;
	}

	/**
	 * 添加枚举配置
	 */
	public boolean insertParameter(Parameter par) {
		try {
			int num = parameterMapper.insertParameter(par);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除枚举
	 * 
	 * @param id
	 * @return
	 */
	public boolean delParameter(Integer id) {
		try {
			int num = parameterMapper.deleteParameter(id);
			if (num > 0 || num == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改枚举信息
	 * 
	 * @param par
	 * @return
	 */
	public boolean updateParameter(Parameter par) {
		try {
			int cc = parameterMapper.updateParameter(par);
			if (cc > 0 || cc == org.apache.ibatis.executor.BatchExecutor.BATCH_UPDATE_RETURN_VALUE) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
