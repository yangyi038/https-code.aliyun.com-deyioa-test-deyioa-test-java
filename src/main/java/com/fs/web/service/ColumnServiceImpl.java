package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnMapper;
import com.fs.comm.model.Column;
import com.fs.comm.model.Sysuser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service("ColumnService")
public class ColumnServiceImpl implements ColumnService {

	@Resource
	private ColumnMapper columnMapper;
	
	@Override
	public boolean insertColumn(Column model) {
		
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Long companyid = sysuser.getCompanyid();
		model.setIsDelete("n");
		model.setCreateTime(new Date());
		model.setCompanyid(companyid);
		Column column = columnMapper.selectByPrimaryKey(model.getParentId());
		int levels = Integer.parseInt( column.getLevels())+1;
		model.setLevels(Integer.toString(levels));
		int flag = columnMapper.insert(model);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getAllColumnInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  queryT_title=columnMapper.getAllColumnInfo(p);
		PageInfo page = new PageInfo(queryT_title);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_title;
	}

	@Override
	public int delColumnById(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = columnMapper.delColumnById(id);
		}
		return result;
	}

	@Override
	public List<Column> selectColumnClassify() {
		List<Column> list = null;
		try {
			list = columnMapper.selectColumnClassify();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Column queryColumnById(Integer id) {
		return columnMapper.selectByPrimaryKey(id);
	}

	@Override
	public int agreeColumnById(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = columnMapper.agreeColumnById(id);
		}
		return result;
	}

	@Override
	public int unAgreeColumnById(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = columnMapper.unAgreeColumnById(id);
		}
		return result;
	}
	
	@Override
	public int updateColumn(Column model){
		
		return columnMapper.updateByPrimaryKeySelective(model);
	}

	
	/*****************************************************************************/
	
	@Override
	public List<Column> browseColumn(Map<String, Object> p) {
		return columnMapper.browseColumn(p);
	}

	@Override
	public int countColumn(Map<String, Object> p) {
		return columnMapper.countColumn(p);
	}
	
	
	/**
	 * 获取栏目列表
	 * @param p
	 * @return
	 */
	@Override
	public List<Column> getRootColumnList(Map<String, Object> p) {
		return columnMapper.getRootColumnList(p);
	}
	
	
}
