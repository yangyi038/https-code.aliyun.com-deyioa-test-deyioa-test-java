package com.fs.app.service;

import java.util.List;

import com.fs.comm.model.AdbDebugServer;
import com.fs.comm.model.Autoshutdown;
import com.fs.comm.model.LayoutSelectors;
import com.fs.comm.model.RealTimeControl;
import com.fs.comm.model.SysVer;
import com.fs.comm.model.UiPwd;

public interface VerSercice {
	
	public List<SysVer> getAllSysVer();
	
	public List<Autoshutdown> getAutoshutdown();
	
	public List<LayoutSelectors> getLayoutselectors();
	
	public List<UiPwd> getUiPwd();
	
	public List<AdbDebugServer> getAdbdebugserver();
	
	public List<RealTimeControl> getRealTimeControl();
}
