package com.fs.comm.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orderinfo {
	private String order_id;//订单详细信息生成id
	private String user_id;//用户id，同px_users的user_id
	private String order_status;//订单状态(0待支付1已支付2处理中3已处理4已取消5已完成6退货中7退货失败8已退货)
	private String pay_status;//支付状态(0未付款1付款中2已付款)
	private String consignee;//收货人的姓名，用户页面填写，默认取值于表px_user_address
	private String province;//收货人的省份，用户页面填写，默认取值于表px_user_address
	private String city;//收货人的城市，用户页面填写，默认取值于表px_user_address
	private String district;//收货人的地区，用户页面填写，默认取值于表px_user_address
	private String village;//收货人的小区，用户页面填写，默认取值于表px_user_address
	private String address;//收货人的详细地址，用户页面填写，默认取值于表px_user_address
	private String zipcode;//邮编
	private String tel;//固定电话
	private String mobile;//手机
	private String totime;//送货时间，获取送货时间表数据
	private String postscript;//订单附言，由用户提交订单前填写
	private String pay_id;//用户选择的支付方式的id，取值表px_payment
	private String pay_name;//用户选择的支付方式的名称，取值表px_payment
	private String inv_payee;//发票抬头，用户页面填写
	private String inv_content;//发票内容(保留字段)
	private BigDecimal goods_amount;//商品总金额
	private String order_weight;//重量
	private BigDecimal order_amount;//应付款金额
	private BigDecimal shipping_fee;//配送费用
	private BigDecimal integral;//使用的积分的数量，取用户使用积分，商品可用积分，用户拥有积分中最小者
	private BigDecimal integral_money;//使用积分金额
	private String bonus_id;//红包的id，px_user_bonus的bonus_id
	private BigDecimal bonus;//使用红包金额
	private String from_ad;//订单由某广告带来的广告id，应该取值于px_ad
	private String referer;//订单的来源页面
	private Date add_time;//订单生成时间
	private Date confirm_time;//订单确认时间
	private Date pay_time;//订单支付时间
	private Date shipping_time;//订单配送时间
	private String invoice_no;//发货单号，发货时填写，可在订单查询查看，获取wms中的信息
	private String extension_id;//通过活动购买的物品的id，取值px_goods_activity；如果是正常普通商品，该处为空
	private String parent_id;//能获得推荐分成的用户id，id取值于表px_users
	private String is_separate;//0，未分成或等待分成；1，已分成；2，取消分成；
	private BigDecimal discount;//折扣金额
	private String trade_no;//支付交易号
	private String ktrade_no;//扩展支付交易号
	private String cupboard;//收货人的柜号，用户页面填写，默认取值于表px_user_address
	private String parent_type;//推荐分成类型，exa:按订单分，按金额分
	private BigDecimal parent_integral;//推荐分成所得的积分
	private String cou_id;//优惠劵id
	private BigDecimal cous;//优惠劵金额
	private String order_sn;//订单编号
	private String isdelete;//1删除
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTotime() {
		return totime;
	}
	public void setTotime(String totime) {
		this.totime = totime;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	public String getInv_payee() {
		return inv_payee;
	}
	public void setInv_payee(String inv_payee) {
		this.inv_payee = inv_payee;
	}
	public String getInv_content() {
		return inv_content;
	}
	public void setInv_content(String inv_content) {
		this.inv_content = inv_content;
	}
	public BigDecimal getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(BigDecimal goods_amount) {
		this.goods_amount = goods_amount;
	}
	public String getOrder_weight() {
		return order_weight;
	}
	public void setOrder_weight(String order_weight) {
		this.order_weight = order_weight;
	}
	public BigDecimal getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}
	public BigDecimal getShipping_fee() {
		return shipping_fee;
	}
	public void setShipping_fee(BigDecimal shipping_fee) {
		this.shipping_fee = shipping_fee;
	}
	public BigDecimal getIntegral() {
		return integral;
	}
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	public BigDecimal getIntegral_money() {
		return integral_money;
	}
	public void setIntegral_money(BigDecimal integral_money) {
		this.integral_money = integral_money;
	}
	public String getBonus_id() {
		return bonus_id;
	}
	public void setBonus_id(String bonus_id) {
		this.bonus_id = bonus_id;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public String getFrom_ad() {
		return from_ad;
	}
	public void setFrom_ad(String from_ad) {
		this.from_ad = from_ad;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public Date getConfirm_time() {
		return confirm_time;
	}
	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Date getShipping_time() {
		return shipping_time;
	}
	public void setShipping_time(Date shipping_time) {
		this.shipping_time = shipping_time;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getExtension_id() {
		return extension_id;
	}
	public void setExtension_id(String extension_id) {
		this.extension_id = extension_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIs_separate() {
		return is_separate;
	}
	public void setIs_separate(String is_separate) {
		this.is_separate = is_separate;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getKtrade_no() {
		return ktrade_no;
	}
	public void setKtrade_no(String ktrade_no) {
		this.ktrade_no = ktrade_no;
	}
	public String getCupboard() {
		return cupboard;
	}
	public void setCupboard(String cupboard) {
		this.cupboard = cupboard;
	}
	public String getParent_type() {
		return parent_type;
	}
	public void setParent_type(String parent_type) {
		this.parent_type = parent_type;
	}
	public BigDecimal getParent_integral() {
		return parent_integral;
	}
	public void setParent_integral(BigDecimal parent_integral) {
		this.parent_integral = parent_integral;
	}
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public BigDecimal getCous() {
		return cous;
	}
	public void setCous(BigDecimal cous) {
		this.cous = cous;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
}
