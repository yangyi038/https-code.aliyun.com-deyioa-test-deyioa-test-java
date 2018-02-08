package com.fs.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fantastic.DateUtilsEx;
import com.fantastic.TBaseExample;
import com.fantastic.TBaseExample.Criteria;
import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.TBindMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.THotelGroupMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.TBind;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.THotel;
import com.fs.comm.model.THotelGroup;
import com.fs.web.vomodel.Group_SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_hotelService")
/** 閰掑簵绠＄悊涓氬姟閫昏緫鎺ュ彛瀹炵幇 */
public class T_hotelServiceImpl implements T_hotelService {
	/** 閫氳繃渚濊禆娉ㄥ叆Mapper缁勪欢瀹炰緥 */
	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private THotelGroupMapper t_hotel_groupMapper;

	@Resource
	private ParameterMapper parameterMapper;

	@Resource
	private CommonService commonService;

	@Resource
	private TCompanyMapper companyMapper;

	@Resource
	private TBindMapper t_bindMapper;

	/**
	 * 获取酒店列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<THotel> browseHotelList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<THotel> hotelList = t_hotelMapper.getHotelList(p);
		if (hotelList != null && hotelList.size() > 0) {
			for (THotel tHotel : hotelList) {
				// 获取组名称
				if (StringUtils.isNotEmpty(tHotel.getHotelgroup())) {
					Group_SearchVo vo = new Group_SearchVo();
					vo.setGroupNum(tHotel.getHotelgroup());
					List<THotelGroup> group = t_hotel_groupMapper.getGroupInfoByCondition(vo);
					if (group != null && group.size() > 0 && StringUtils.isNotEmpty(group.get(0).getGroupname())) {
						tHotel.setHotelgroup(group.get(0).getGroupname());
					}
				}
				// 获取运营商名称
				if (tHotel.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(tHotel.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname())) {
						tHotel.setCompanyname(company.getCompanyname());
					}
				}
				// 该酒店下机顶盒到期时间
				if (StringUtils.isNotEmpty(tHotel.getValiddate() + "")) {
					tHotel.setValiddateStr(
							DateUtilsEx.date2Str(tHotel.getValiddate(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				}
				// 该酒店下机顶盒数量
				p = new HashMap<String, Object>();
				p.put("hotelsid", tHotel.getSid());
				List<TBind> bindList = t_bindMapper.getBindList(p);
				tHotel.setStbcount(bindList == null ? 0 : bindList.size());
			}
		}
		PageInfo page = new PageInfo(hotelList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return hotelList;
	}

	/** 获取酒店列表-不分页 */
	public List<THotel> getHotelList(Map<String, Object> p) {
		List<THotel> hotelList = t_hotelMapper.getHotelList(p);
		if (hotelList != null && hotelList.size() > 0) {
			for (THotel tHotel : hotelList) {
				if (StringUtils.isNotEmpty(tHotel.getHotelgroup())) {
					Group_SearchVo vo = new Group_SearchVo();
					vo.setGroupNum(tHotel.getHotelgroup());
					List<THotelGroup> group = t_hotel_groupMapper.getGroupInfoByCondition(vo);
					tHotel.setHotelgroup(group != null && group.size() > 0 ? group.get(0).getGroupname() : "");
				}
				if (tHotel.getHoteltype() != null) {
					Map<String, Object> map = new HashMap<>();
					map.put("ptype", "hoteltype");
					map.put("pcode", tHotel.getHoteltype());
					Parameter par = parameterMapper.getParameter(map);
					tHotel.setHoteltypeStr(par.getPname());
				}
			}
		}
		return hotelList;
	}

	/**
	 * 绮剧‘鏌ヨ閰掑簵
	 * 
	 * @param p
	 * @return
	 */
	public List<THotel> selectHotel(Map<String, Object> p) {
		List<THotel> hotelList = t_hotelMapper.getHotelList(p);
		return hotelList;
	}

	/**
	 * 鏂板閰掑簵
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean insertHotel(THotel hotel) {
		try {
			// 酒店编号（从10001开始）
			if (hotel.getHotelnum() == null) {
				if (t_hotelMapper.getMaxHotelNum() == null || t_hotelMapper.getMaxHotelNum() == 0
						|| t_hotelMapper.getMaxHotelNum() < 10000) {
					hotel.setHotelnum(10000 + 1);
				} else {
					hotel.setHotelnum(t_hotelMapper.getMaxHotelNum() + 1);
				}
			}
			// 格式化创建时间
			if (hotel.getCreatetime() == null || hotel.getCreatetime().equals("")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setLenient(false);
				hotel.setCreatetime(sdf.format(new Date()));
			}
			// 检测省市区县合法性
			if (StringUtils.isNotEmpty(hotel.getProvince()) && hotel.getProvince().equals("--请选择省--,")) {
				hotel.setProvince("");
			}
			if (StringUtils.isNotEmpty(hotel.getCity()) && hotel.getCity().equals("--请选择市--,")) {
				hotel.setCity("");
			}
			if (StringUtils.isNotEmpty(hotel.getArea()) && hotel.getArea().equals("--请选择区县--,")) {
				hotel.setArea("");
			}

			int num = t_hotelMapper.insertHotel(hotel);
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
	 * 鍒犻櫎閰掑簵
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delHotel(long sid) {
		try {
			int num = t_hotelMapper.deleteHotel(sid);
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
	 * 鏌ヨ閰掑簵鏄惁瀛樺湪
	 * 
	 * @param p
	 * @return
	 */
	public int count_Hotel(Map<String, Object> p) {
		return t_hotelMapper.countHotel(p);
	}

	/**
	 * 获取详情
	 */
	public THotel selectByPrimaryKey(long sid) {
		THotel hotel = t_hotelMapper.selectByPrimaryKey(sid);
		Map<String, Object> p;
		// 获取证件类型和用户类型的名称
		if (hotel.getCardtype() != null) {
			p = new HashMap<>();
			p.put("ptype", "cardtype");
			p.put("pcode", hotel.getCardtype());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null) {
				hotel.setCardtypeStr(par.getPname());
			}
		}
		if (hotel.getHoteltype() != null) {
			p = new HashMap<>();
			p.put("ptype", "hoteltype");
			p.put("pcode", hotel.getHoteltype());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null) {
				hotel.setHoteltypeStr(par.getPname());
			}
		}

		return hotel;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean updateByPrimaryKey(THotel hotel) {
		// 检测省市区县合法性
		if (StringUtils.isNotEmpty(hotel.getProvince()) && hotel.getProvince().indexOf("--请选择省--,") != -1) {
			hotel.setProvince(hotel.getProvince().replace("--请选择省--,", ""));
		}
		if (StringUtils.isNotEmpty(hotel.getCity()) && hotel.getCity().indexOf("--请选择市--,") != -1) {
			hotel.setCity(hotel.getCity().replace("--请选择市--,", ""));
		}
		if (StringUtils.isNotEmpty(hotel.getArea()) && hotel.getArea().indexOf("--请选择区县--,") != -1) {
			hotel.setArea(hotel.getArea().replace("--请选择区县--,", ""));
		}
		// 获取用户类型
		if (StringUtils.isNotEmpty(hotel.getHoteltypeStr())) {
			Parameter par = commonService.getParameter("hoteltype", null, hotel.getHoteltypeStr());
			if (par != null && par.getPcode() != null) {
				hotel.setHoteltype(par.getPcode());
			}
		}
		// 获取证件类型
		if (StringUtils.isNotEmpty(hotel.getCardtypeStr())) {
			Parameter par = commonService.getParameter("cardtype", null, hotel.getCardtypeStr());
			if (par != null && par.getPcode() != null) {
				hotel.setCardtype(par.getPcode());
			}
		}
		try {
			int cc = t_hotelMapper.updateHotelInfo(hotel);
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

	// =========================== 绉佹湁鏂规硶
	// =================================================================

	private TBaseExample createMap(Map<String, Object> p) {
		TBaseExample em = new TBaseExample();
		Criteria c = em.createCriteria();
		if (p != null && p.size() > 0) {
			Set<Entry<String, Object>> e = p.entrySet();
			for (Entry<String, Object> entry : e) {
				Object vo = entry.getValue();
				String v = null;
				if (vo != null) {
					v = String.valueOf(vo);
				}
				if (v != null && v.trim().length() > 0) {
					c.addCriterion(entry.getKey() + "='" + entry.getValue() + "' ");
				}
			}
		}
		return em;
	}

}
