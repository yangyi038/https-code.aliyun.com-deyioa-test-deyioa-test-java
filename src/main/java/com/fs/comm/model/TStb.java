package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 机顶盒信息
 * 
 * @author pzj
 *
 */
@Data
public class TStb {

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
	public String getStbnum() {
		return stbnum;
	}
	public void setStbnum(String stbnum) {
		this.stbnum = stbnum;
	}
	public String getStbgroup() {
		return stbgroup;
	}
	public void setStbgroup(String stbgroup) {
		this.stbgroup = stbgroup;
	}
	public String getStbgroupname() {
		return stbgroupname;
	}
	public void setStbgroupname(String stbgroupname) {
		this.stbgroupname = stbgroupname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwdsure() {
		return pwdsure;
	}
	public void setPwdsure(String pwdsure) {
		this.pwdsure = pwdsure;
	}
	public String getEpgroot() {
		return epgroot;
	}
	public void setEpgroot(String epgroot) {
		this.epgroot = epgroot;
	}
	public String getEpgprivate() {
		return epgprivate;
	}
	public void setEpgprivate(String epgprivate) {
		this.epgprivate = epgprivate;
	}
	public String getTelroot() {
		return telroot;
	}
	public void setTelroot(String telroot) {
		this.telroot = telroot;
	}
	public Long getDeposit() {
		return deposit;
	}
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Long getHotelsid() {
		return hotelsid;
	}
	public void setHotelsid(Long hotelsid) {
		this.hotelsid = hotelsid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	public String getPaytypeStr() {
		return paytypeStr;
	}
	public void setPaytypeStr(String paytypeStr) {
		this.paytypeStr = paytypeStr;
	}
	public Integer getStbstatus() {
		return stbstatus;
	}
	public void setStbstatus(Integer stbstatus) {
		this.stbstatus = stbstatus;
	}
	public String getStbstatusStr() {
		return stbstatusStr;
	}
	public void setStbstatusStr(String stbstatusStr) {
		this.stbstatusStr = stbstatusStr;
	}
	public String getInstalladdress() {
		return installaddress;
	}
	public void setInstalladdress(String installaddress) {
		this.installaddress = installaddress;
	}
	public Date getValiddate() {
		return validdate;
	}
	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}
	public String getValiddateStr() {
		return validdateStr;
	}
	public void setValiddateStr(String validdateStr) {
		this.validdateStr = validdateStr;
	}
	public Integer getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}
	public String getAccounttypeStr() {
		return accounttypeStr;
	}
	public void setAccounttypeStr(String accounttypeStr) {
		this.accounttypeStr = accounttypeStr;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getStbid() {
		return stbid;
	}
	public void setStbid(String stbid) {
		this.stbid = stbid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getApkversion() {
		return apkversion;
	}
	public void setApkversion(String apkversion) {
		this.apkversion = apkversion;
	}
	public String getRomversion() {
		return romversion;
	}
	public void setRomversion(String romversion) {
		this.romversion = romversion;
	}
	public String getRomfirmware() {
		return romfirmware;
	}
	public void setRomfirmware(String romfirmware) {
		this.romfirmware = romfirmware;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public Integer getNumFlag() {
		return numFlag;
	}
	public void setNumFlag(Integer numFlag) {
		this.numFlag = numFlag;
	}
	public Integer getStbtype() {
		return stbtype;
	}
	public void setStbtype(Integer stbtype) {
		this.stbtype = stbtype;
	}
	public String getStbtypeStr() {
		return stbtypeStr;
	}
	public void setStbtypeStr(String stbtypeStr) {
		this.stbtypeStr = stbtypeStr;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getWifimac() {
		return wifimac;
	}
	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}
	private Long sid;

	// 运营商id
	private Long companyid;
	private String companyname;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	// 机顶盒编号
	private String stbnum;
	// 机顶盒组
	private String stbgroup;
	private String stbgroupname;

	// 密码
	private String pwd;
	// 确认密码
	private String pwdsure;

	// epg栏目主根节点
	private String epgroot;
	// epg栏目定制根节点
	private String epgprivate;
	// 手机栏目根节点
	private String telroot;

	// 押金
	private Long deposit;

	// 用户ID
	private Long hotelsid;
	// 用户名称
	private String hotelname;

	// 支付类型
	private Integer paytype;
	private String paytypeStr;

	// 机顶盒状态
	private Integer stbstatus;
	private String stbstatusStr;

	// 安装地址
	private String installaddress;
	// 有效期
	private Date validdate;
	private String validdateStr;

	// 账户类型
	private Integer accounttype;
	private String accounttypeStr;

	// 余额
	private Long balance;
	// 创建时间
	private String createtime;
	// 机顶盒ID
	private String stbid;
	// mac地址
	private String mac;
	// 应用包版本
	private String apkversion;
	// rom版本
	private String romversion;
	// rom固件版本
	private String romfirmware;
	// app类型
	private String apptype;

	private String tokencode;
	private Integer numFlag;

	// 机顶盒类型
	private Integer stbtype;
	private String stbtypeStr;

	private String welcome;
	private String wifimac;

}