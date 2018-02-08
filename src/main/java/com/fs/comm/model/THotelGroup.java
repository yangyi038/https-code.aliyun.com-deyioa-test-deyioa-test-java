package com.fs.comm.model;

import lombok.Data;

@Data
public class THotelGroup {

	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Long getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public Long getDealerid() {
		return dealerid;
	}
	public void setDealerid(Long dealerid) {
		this.dealerid = dealerid;
	}
	public String getDealername() {
		return dealername;
	}
	public void setDealername(String dealername) {
		this.dealername = dealername;
	}
	public Integer getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(Integer groupnum) {
		this.groupnum = groupnum;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Integer getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(Integer grouptype) {
		this.grouptype = grouptype;
	}
	public String getGrouptypeStr() {
		return grouptypeStr;
	}
	public void setGrouptypeStr(String grouptypeStr) {
		this.grouptypeStr = grouptypeStr;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEpgfirst() {
		return epgfirst;
	}
	public void setEpgfirst(String epgfirst) {
		this.epgfirst = epgfirst;
	}
	public String getEpgsecond() {
		return epgsecond;
	}
	public void setEpgsecond(String epgsecond) {
		this.epgsecond = epgsecond;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getWifipwd() {
		return wifipwd;
	}
	public void setWifipwd(String wifipwd) {
		this.wifipwd = wifipwd;
	}
	public String getEpghome() {
		return epghome;
	}
	public void setEpghome(String epghome) {
		this.epghome = epghome;
	}
	public String getVod() {
		return vod;
	}
	public void setVod(String vod) {
		this.vod = vod;
	}
	public String getApplicationup() {
		return applicationup;
	}
	public void setApplicationup(String applicationup) {
		this.applicationup = applicationup;
	}
	public String getAppblacklist() {
		return appblacklist;
	}
	public void setAppblacklist(String appblacklist) {
		this.appblacklist = appblacklist;
	}
	public String getOpenpic() {
		return openpic;
	}
	public void setOpenpic(String openpic) {
		this.openpic = openpic;
	}
	public String getOpenvideo() {
		return openvideo;
	}
	public void setOpenvideo(String openvideo) {
		this.openvideo = openvideo;
	}
	public String getOpenlogo() {
		return openlogo;
	}
	public void setOpenlogo(String openlogo) {
		this.openlogo = openlogo;
	}
	public String getRomup() {
		return romup;
	}
	public void setRomup(String romup) {
		this.romup = romup;
	}
	public String getBackupserver() {
		return backupserver;
	}
	public void setBackupserver(String backupserver) {
		this.backupserver = backupserver;
	}
	public String getNtp() {
		return ntp;
	}
	public void setNtp(String ntp) {
		this.ntp = ntp;
	}
	public String getRouterpower() {
		return routerpower;
	}
	public void setRouterpower(String routerpower) {
		this.routerpower = routerpower;
	}
	public String getPresn() {
		return presn;
	}
	public void setPresn(String presn) {
		this.presn = presn;
	}
	public String getMediaserverid() {
		return mediaserverid;
	}
	public void setMediaserverid(String mediaserverid) {
		this.mediaserverid = mediaserverid;
	}
	public String getLogserver() {
		return logserver;
	}
	public void setLogserver(String logserver) {
		this.logserver = logserver;
	}
	public String getNoticepic() {
		return noticepic;
	}
	public void setNoticepic(String noticepic) {
		this.noticepic = noticepic;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	// 主键
	private Long sid;
	// 运营商ID
	private Long companyid;
	private String companyname;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	// 组编号
	private Integer groupnum;
	// 组名称
	private String groupname;
	// 组类型
	private Integer grouptype;
	private String grouptypeStr;

	// 描述
	private String comm;
	// 创建时间
	private String createtime;

	// =========================== 设置信息 ===============================
	/**
	 * epg主栏目
	 */
	private String epgfirst;
	private String epgsecond;

	private String wifi;// 热点名称
	private String wifipwd;// 热点密码
	private String epghome;// epg首页
	private String vod;// vod地址
	private String applicationup;// 应用升级地址
	private String appblacklist;// 应用黑名单地址
	private String openpic;// 开机图片地址
	private String openvideo;// 开机动画地址
	private String openlogo;// 开机logo
	private String romup;// rom升级地址
	private String backupserver;// 备份服务器地址
	private String ntp;// NTP地址
	private String routerpower;// 路由授权号
	private String presn;// 前端设备SN
	private String mediaserverid;// 流媒体服务器ID
	private String logserver;// 日志服务器地址
	private String noticepic;// 维修通知图片地址
	private String welcome; // 欢迎词

}