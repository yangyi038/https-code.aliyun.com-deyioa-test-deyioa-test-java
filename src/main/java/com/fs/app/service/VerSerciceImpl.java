package com.fs.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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


@Service("verSercice")
public class VerSerciceImpl implements VerSercice {

	@Resource
	private SysVerMapper sysVerMapper;
	
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
	public List<SysVer> getAllSysVer() {
		List<SysVer> list = sysVerMapper.queryAllSysVerInfo();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<Autoshutdown> getAutoshutdown() {
		List<Autoshutdown> list = autoshutdownMapper.getAutoshutdown();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<LayoutSelectors> getLayoutselectors() {
		 List<LayoutSelectors> list = layoutSelectorsMapper.getLayoutSelectors();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<UiPwd> getUiPwd() {
		List<UiPwd> list = uiPwdMapper.getUiPwd();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}	}

	@Override
	public List<AdbDebugServer> getAdbdebugserver() {
		List<AdbDebugServer> list = adbDebugServerMapper.getAdbdebugserver();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}	}

	@Override
	public List<RealTimeControl> getRealTimeControl() {
		List<RealTimeControl> list = realTimeControlMapper.getRealTimeControl();
		if(list.size()>0&&list!=null){
			return list;
		}else{
			return null;
		}	
	}

	
}
