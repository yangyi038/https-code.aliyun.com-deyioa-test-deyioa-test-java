package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 用户表
 * 
 * @author pzj
 *
 */
@Data
public class THotel {
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

	public Integer getHotelnum() {
		return hotelnum;
	}

	public void setHotelnum(Integer hotelnum) {
		this.hotelnum = hotelnum;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public String getHotelshort() {
		return hotelshort;
	}

	public void setHotelshort(String hotelshort) {
		this.hotelshort = hotelshort;
	}

	public String getHotelgroup() {
		return hotelgroup;
	}

	public void setHotelgroup(String hotelgroup) {
		this.hotelgroup = hotelgroup;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardtypeStr() {
		return cardtypeStr;
	}

	public void setCardtypeStr(String cardtypeStr) {
		this.cardtypeStr = cardtypeStr;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getHoteltype() {
		return hoteltype;
	}

	public void setHoteltype(Integer hoteltype) {
		this.hoteltype = hoteltype;
	}

	public String getHoteltypeStr() {
		return hoteltypeStr;
	}

	public void setHoteltypeStr(String hoteltypeStr) {
		this.hoteltypeStr = hoteltypeStr;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getTxturl() {
		return txturl;
	}

	public void setTxturl(String txturl) {
		this.txturl = txturl;
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

	public Integer getStbcount() {
		return stbcount;
	}

	public void setStbcount(Integer stbcount) {
		this.stbcount = stbcount;
	}

	/**
	 * 主键
	 */
	private Long sid;

	/**
	 * 运营商ID
	 */
	private Long companyid;
	private String companyname;
	//二级运营商
	private Long operatorid;
	private String operatorname;
	//经销商
	private Long dealerid;
	private String dealername;
	

	/**
	 * 用户编号
	 */
	private Integer hotelnum;

	/**
	 * 用户名称
	 */
	private String hotelname;
	private String hotelshort;

	/**
	 * 用户组
	 */
	private String hotelgroup;

	/**
	 * 联系人
	 */
	private String linkman;

	/**
	 * 证件类型
	 */
	private Integer cardtype;
	private String cardtypeStr;

	/**
	 * 证件号码
	 */
	private String cardid;

	/**
	 * 手机号
	 */
	private String tel;

	/**
	 * 邮编
	 */
	private Integer postcode;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 用户类型
	 */
	private Integer hoteltype;
	private String hoteltypeStr;

	/**
	 * 头像
	 */
	private String logo;

	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 备注
	 */
	private String comm;

	/**
	 * 省
	 */
	private String province;
	private String city;
	private String area;

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
	private String welcome;// 欢迎词
	private String txturl;// 接口文档中的通知公告管理地址，现用于生成二维码的地址

	// 机顶盒到期时间
	private Date validdate;
	private String validdateStr;
	
	//酒店下机顶盒数量
	private Integer stbcount;

}