package com.fs.web.service;

import java.util.Date;
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
import com.fs.comm.mapper.TBindMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.model.TBind;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.THotel;
import com.fs.comm.model.TStb;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_bindService")
public class T_bindServiceImpl implements T_bindService {

	@Resource
	private TBindMapper t_bindMapper;

	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private TStbMapper t_stbMapper;

	@Resource
	private TCompanyMapper companyMapper;

	/**
	 * 获取绑定列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TBind> browseBindList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TBind> bindList = t_bindMapper.getBindList(p);
		if (bindList != null && bindList.size() > 0) {
			for (TBind tBind : bindList) {
				tBind.setCreatetimeStr(DateUtilsEx.date2Str(tBind.getCreatetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				// 获取运营商名称
				if (tBind.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(tBind.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						tBind.setCompanyname(company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(bindList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return bindList;
	}

	/**
	 * 获取绑定列表
	 * 
	 * @param p
	 * @return
	 */
	public List<TBind> selectBind(Map<String, Object> p) {
		List<TBind> bindList = t_bindMapper.getBindList(p);
		return bindList;
	}

	/**
	 * 酒店设备绑定
	 * 
	 * @param bind
	 * @return
	 */
	public boolean insertBind(TBind bind) {
		try {
			// 获取酒店名称
			if (bind.getHotelsid() != null) {
				THotel hotel = t_hotelMapper.selectByPrimaryKey(Long.parseLong(bind.getHotelsid() + ""));
				if (hotel != null) {
					bind.setHotelnum(hotel.getHotelnum());
					bind.setHotelname(hotel.getHotelname());
				}
			}
			// 获取机顶盒编号
			if (bind.getStbsid() != null) {
				TStb stb = t_stbMapper.selectByPrimaryKey(Long.parseLong(bind.getStbsid() + ""));
				if (stb != null) {
					bind.setStbnum(stb.getStbnum());
				}
			}
			bind.setCreatetime(new Date());

			int num = t_bindMapper.insertBind(bind);
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
	 * 批量导入酒店机顶盒绑定关系
	 */
	public int importBind(List<TBind> list) {
		int result = 0;
		for (TBind bind : list) {
			// 创建时间
			bind.setCreatetime(new Date());

			result = t_bindMapper.insertBind(bind);
		}
		return result;
	}

	/**
	 * 鍒犻櫎缁戝畾
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delBind(long sid) {
		try {
			int num = t_bindMapper.deleteBind(sid);
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
	 * 查询绑定关系是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countBind(Map<String, Object> p) {
		return t_bindMapper.countBind(p);
	}

	/**
	 * 鏌ヨ缁戝畾璇︽儏
	 */
	public TBind selectByPrimaryKey(long sid) {
		TBind bind = t_bindMapper.selectByPrimaryKey(sid);
		if (bind != null && bind.getCreatetime() != null) {
			bind.setCreatetimeStr(DateUtilsEx.date2Str(bind.getCreatetime(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
		}
		return bind;
	}

	/**
	 * 淇敼缁戝畾
	 * 
	 * @param bind
	 * @return
	 */
	public boolean updateByPrimaryKey(TBind bind) {
		try {
			int cc = t_bindMapper.updateBindInfo(bind);
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
