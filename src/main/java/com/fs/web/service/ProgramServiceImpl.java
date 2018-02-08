package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.Sysuser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ProgramService")
public class ProgramServiceImpl implements ProgramService {

	@Resource
	private ProgramMapper programMapper;

	@Resource
	private ColumnTypeMapper columnTypeMapper;

	@Override
	public List<Map<String, Object>> getAlltitleColumn(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> queryT_title = null;
		try {
			queryT_title = programMapper.getAllTitleColumn(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageInfo page = new PageInfo(queryT_title);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryT_title;
	}

	@Override
	public List<Map<String, Object>> getAlltitleColumnType(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Long companyid = sysuser.getCompanyid();
		p.put("companyid", companyid);
		List<Map<String, Object>> query_columnType = columnTypeMapper.getAllColumnType(p);
		PageInfo page = new PageInfo(query_columnType);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return query_columnType;
	}

	@Override
	public ColumnType getColumnTypeById(Integer id) {

		return columnTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertProgram(Program model) {
		int result = 0;
		try {
			result = programMapper.insert(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Program getProgramByColumnId(Integer id, String name) {
		Program program = new Program();
		program.setColumnId(id);
		program.setName(name);
		try {
			program = programMapper.getProgramByColumnId(program);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return program;
	}

	@Override
	public int delProgramById(Integer id) {
		return programMapper.delProgramById(id);
	}

	@Override
	public Program getProgramById(Integer id) {
		long programId = id;
		return programMapper.selectByPrimaryKey(programId);
	}

	@Override
	public int updateProgramById(Program model) {
		// TODO Auto-generated method stub
		return programMapper.updateByPrimaryKeySelective(model);
	}

	/**
	 * 根据栏目ID查询其下绑定的节目列表
	 * @param ColumnId
	 * @return
	 */
	public List<Program> getProgramListByColumnId(Map<String, Object> map){
		List<Program> proList=programMapper.getProgramListByColumnId(map);
				
		return proList;
	}
}
