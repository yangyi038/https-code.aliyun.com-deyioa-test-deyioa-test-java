package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.AdbDebugServerMapper;
import com.fs.comm.mapper.AutoshutdownMapper;
import com.fs.comm.mapper.LayoutSelectorsMapper;
import com.fs.comm.mapper.RealTimeControlMapper;
import com.fs.comm.mapper.SysVerMapper;
import com.fs.comm.mapper.UiPwdMapper;
import com.fs.comm.model.AdbDebugServer;
import com.fs.comm.model.Autoshutdown;
import com.fs.comm.model.LayoutSelectors;
import com.fs.comm.model.RealTimeControl;
import com.fs.comm.model.SysVer;
import com.fs.comm.model.UiPwd;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("sysVerService")
public class SysVerServiceImpl implements SysVerService {
	
	@Resource
	private SysVerMapper sysVerMappper;
	
	@Resource
	private AutoshutdownMapper autoshutdownMapper;
	
	@Resource
	private LayoutSelectorsMapper layoutSelectorsMapper;
	
	@Resource
	private UiPwdMapper uiPwdMapper;
	
	@Resource
	private AdbDebugServerMapper adbDebugServerMapper;
	
	@Resource
	private RealTimeControlMapper realTimeControlMapper;

	@Override
	public int insertSysVer(SysVer model) {
		model.setCreateTime(new Date());
		model.setIsDelete("n");
		return sysVerMappper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllSysVerInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = sysVerMappper.getAllSysVerInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delSysVerById(Long id) {
		
		return sysVerMappper.delSysVerById(id);
	}

	@Override
	public SysVer getSysVerById(Long id) {
		
		return sysVerMappper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSysVerById(SysVer model) {
		return sysVerMappper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<SysVer> queryAllSysVerInfo() {
		List<SysVer> list = sysVerMappper.queryAllSysVerInfo();
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}

	
	
/*************************************autoshutdown   start******************************************************/
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	@Override
	public int insertAutoshutdown(Autoshutdown model) {
		model.setIsDelete("n");
		return autoshutdownMapper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllAutoshutdownInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = autoshutdownMapper.getAllAutoshutdownInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delAutoshutdownById(Long id) {
		
		return autoshutdownMapper.delAutoshutdownById(id);
	}

	@Override
	public Autoshutdown getAutoshutdownById(Long id) {
		return autoshutdownMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateAutoshutdownById(Autoshutdown model) {
		return autoshutdownMapper.updateByPrimaryKeySelective(model);
	}

	
	
	/*************************************autoshutdown   end******************************************************/
	
	/*************************************LayoutSelectors   start******************************************************/
	
	@Override
	public int insertLayoutSelectors(LayoutSelectors model) {
		model.setIsDelete("n");
		return layoutSelectorsMapper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllLayoutSelectorsInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = layoutSelectorsMapper.getAllLayoutSelectorsInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delLayoutSelectorsById(Long id) {
		
		return layoutSelectorsMapper.delLayoutSelectorsById(id);
	}

	@Override
	public LayoutSelectors getLayoutSelectorsById(Long id) {
		// TODO Auto-generated method stub
		return layoutSelectorsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateLayoutSelectorsById(LayoutSelectors model) {
		// TODO Auto-generated method stub
		return layoutSelectorsMapper.updateByPrimaryKeySelective(model);
	}

	
	
	/*************************************LayoutSelectors   end******************************************************/
	
	
	/*************************************UiPwd   start******************************************************/
	
	@Override
	public int insertUiPwd(UiPwd model) {
		model.setIsDetele("n");
		return uiPwdMapper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllUiPwdInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = uiPwdMapper.getAllUiPwdInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delUiPwdById(Long id) {
		
		return uiPwdMapper.delUiPwdById(id);
	}

	@Override
	public UiPwd getUiPwdById(Long id) {
		
		return uiPwdMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUiPwdById(UiPwd model) {
		return uiPwdMapper.updateByPrimaryKeySelective(model);
	}

	
	
	
	/*************************************UiPwd   end******************************************************/
	
	
	
	
	/*************************************AdbDebugServer   start******************************************************/
	
	
	@Override
	public int insertAdbDebugServer(AdbDebugServer model) {
		model.setIsDelete("n");
		return adbDebugServerMapper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllAdbDebugServerInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = adbDebugServerMapper.getAllAdbDebugServerInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delAdbDebugServerById(Long id) {
		
		return adbDebugServerMapper.delAdbDebugServerById(id);
	}

	@Override
	public AdbDebugServer getAdbDebugServerById(Long id) {
		// TODO Auto-generated method stub
		return adbDebugServerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateAdbDebugServerById(AdbDebugServer model) {
		
		return adbDebugServerMapper.updateByPrimaryKeySelective(model);
	}

	
	
	/*************************************AdbDebugServer   end******************************************************/
	
	
	/*************************************realTimeControl   start******************************************************/
	
	@Override
	public int insertRealTimeControl(RealTimeControl model) {
		model.setIsDelete("n");
		model.setCreateTime(new Date());
		return realTimeControlMapper.insert(model);
	}

	@Override
	public List<Map<String, Object>> getAllRealTimeControlInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = realTimeControlMapper.getAllRealTimeControlInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delRealTimeControlById(Long id) {
		// TODO Auto-generated method stub
		return realTimeControlMapper.delRealTimeControlById(id);
	}

	@Override
	public RealTimeControl getRealTimeControlById(Long id) {
		// TODO Auto-generated method stub
		return realTimeControlMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateRealTimeControlById(RealTimeControl model) {
		// TODO Auto-generated method stub
		return realTimeControlMapper.updateByPrimaryKeySelective(model);
	}
	
	
	/*************************************realTimeControl   end******************************************************/
	

}
