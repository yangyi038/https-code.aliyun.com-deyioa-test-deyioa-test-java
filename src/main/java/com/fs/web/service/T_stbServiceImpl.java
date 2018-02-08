package com.fs.web.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fantastic.DateUtilsEx;
import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.THotelMapper;
import com.fs.comm.mapper.TStbGroupMapper;
import com.fs.comm.mapper.TStbMapper;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.TBind;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.THotel;
import com.fs.comm.model.TStb;
import com.fs.comm.model.TStbGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_stbService")
/** 鏈洪《鐩掔鐞嗕笟鍔￠�昏緫鎺ュ彛瀹炵幇 */
public class T_stbServiceImpl implements T_stbService {

	@Resource
	private TStbMapper t_stbMapper;

	@Resource
	private THotelMapper t_hotelMapper;

	@Resource
	private TStbGroupMapper t_stb_groupMapper;

	@Resource
	private ParameterMapper parameterMapper;

	@Resource
	private CommonService commonService;

	@Resource
	private T_bindService t_bindService;

	@Resource
	private TCompanyMapper companyMapper;

	/**
	 * 获取机顶盒列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TStb> browseStbList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TStb> stbList = t_stbMapper.getStbList(p);
		if (stbList != null && stbList.size() > 0) {
			for (TStb tStb : stbList) {
				// 获取机顶盒所属组名称
				if (StringUtils.isNotEmpty(tStb.getStbgroup())) {
					TStbGroup group = t_stb_groupMapper.selectByPrimaryKey(Long.parseLong(tStb.getStbgroup()));
					tStb.setStbgroup(group.getGroupname());
				}
				if (StringUtils.isNotEmpty(tStb.getValiddate() + "")) {
					tStb.setValiddateStr(DateUtilsEx.date2Str(tStb.getValiddate(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				}
				// 获取机顶盒所属酒店名称
				p = new HashMap<String, Object>();
				p.put("stbsid", tStb.getSid());
				List<TBind> bindList = t_bindService.selectBind(p);
				if (bindList != null && bindList.size() > 0 && StringUtils.isNotEmpty(bindList.get(0).getHotelname())) {
					tStb.setHotelname(bindList.get(0).getHotelname());
				}
				// 获取运营商名称
				if (tStb.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(tStb.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						tStb.setCompanyname(company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(stbList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return stbList;
	}

	/**
	 * 获取机顶盒列表
	 * 
	 * @return
	 */
	public List<TStb> getStbList(Map<String, Object> param) {
		List<TStb> stbList = t_stbMapper.getStbList(param);
		if (stbList != null && stbList.size() > 0) {
			Map<String, Object> p;
			for (TStb stb : stbList) {
				// 格式化有效期
				if (StringUtils.isNotEmpty(stb.getValiddate() + "")) {
					stb.setValiddateStr(DateUtilsEx.date2Str(stb.getValiddate(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
				}
				// 获取账户类型，支付类型，机顶盒状态
				if (stb.getAccounttype() != null) {
					p = new HashMap<>();
					p.put("ptype", "accounttype");
					p.put("pcode", stb.getAccounttype());
					Parameter par = parameterMapper.getParameter(p);
					stb.setAccounttypeStr(par.getPname());
				}
				if (stb.getPaytype() != null) {
					p = new HashMap<>();
					p.put("ptype", "paytype");
					p.put("pcode", stb.getPaytype());
					Parameter par = parameterMapper.getParameter(p);
					stb.setPaytypeStr(par.getPname());
				}
				if (stb.getStbstatus() != null) {
					p = new HashMap<>();
					p.put("ptype", "stbstatus");
					p.put("pcode", stb.getStbstatus());
					Parameter par = parameterMapper.getParameter(p);
					stb.setStbstatusStr(par.getPname());
				}
				// 获取机顶盒所属组名称
				if (stb.getStbgroup() != null) {
					TStbGroup stbgrouup = t_stb_groupMapper.selectByPrimaryKey(Long.parseLong(stb.getStbgroup()));
					if (stbgrouup != null && stbgrouup.getGroupname() != null) {
						stb.setStbgroupname(stbgrouup.getGroupname());
					}
				}

			}
		}
		return stbList;
	}

	/**
	 * 精确查询机顶盒
	 * 
	 * @param p
	 * @return
	 */
	public TStb selectStb(Map<String, Object> pram) {
		TStb stbList = t_stbMapper.getStb(pram);
		return stbList;
	}

	/**
	 * 添加
	 * 
	 * @param stb
	 * @return
	 */
	public boolean insertStb(TStb stb) {
		try {
			// 格式化创建时间
			if (stb.getCreatetime() == null || stb.getCreatetime().equals("")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				stb.setCreatetime(sdf.format(new Date()));
			}
			// 获取酒店名称
			if (stb.getHotelsid() != null) {
				THotel hotel = t_hotelMapper.selectByPrimaryKey(stb.getHotelsid());
				stb.setHotelname(
						hotel != null && StringUtils.isNotEmpty(hotel.getHotelname()) ? hotel.getHotelname() : "");
			}
			//如密码没设置，则默认123456
			if(StringUtils.isBlank(stb.getPwd())){
				stb.setPwd("123456");
			}
			stb.setPwdsure(StringUtils.isBlank(stb.getPwd())? "123456":stb.getPwd());
			
			// 添加机顶盒
			int num = t_stbMapper.insertStb(stb);
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
	 * 鍒犻櫎鏈洪《鐩�
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delStb(long sid) {
		try {
			int num = t_stbMapper.deleteStb(sid);
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
	 * 鏌ヨ鏈洪《鐩掓槸鍚﹀瓨鍦�
	 * 
	 * @param p
	 * @return
	 */
	public int countStb(Map<String, Object> p) {
		return t_stbMapper.countStb(p);
	}

	/**
	 * 鏌ヨ鏈洪《鐩掍俊鎭�
	 */
	public TStb getStbById(long sid) {
		TStb stb = t_stbMapper.selectByPrimaryKey(sid);
		if (stb != null) {
			// 格式化有效期
			if (StringUtils.isNotEmpty(stb.getValiddate() + "")) {
				stb.setValiddateStr(DateUtilsEx.date2Str(stb.getValiddate(), DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
			}
			Map<String, Object> p;
			// 获取账户类型，支付类型，机顶盒状态
			if (stb.getAccounttype() != null) {
				p = new HashMap<>();
				p.put("ptype", "accounttype");
				p.put("pcode", stb.getAccounttype());
				Parameter par = parameterMapper.getParameter(p);
				stb.setAccounttypeStr(par.getPname());
			}
			if (stb.getPaytype() != null) {
				p = new HashMap<>();
				p.put("ptype", "paytype");
				p.put("pcode", stb.getPaytype());
				Parameter par = parameterMapper.getParameter(p);
				stb.setPaytypeStr(par.getPname());
			}
			if (stb.getStbstatus() != null) {
				p = new HashMap<>();
				p.put("ptype", "stbstatus");
				p.put("pcode", stb.getStbstatus());
				Parameter par = parameterMapper.getParameter(p);
				stb.setStbstatusStr(par.getPname());
			}
			// 获取机顶盒所属组名称
			if (stb.getStbgroup() != null) {
				TStbGroup stbgrouup = t_stb_groupMapper.selectByPrimaryKey(Long.parseLong(stb.getStbgroup()));
				if (stbgrouup != null && stbgrouup.getGroupname() != null) {
					stb.setStbgroupname(stbgrouup.getGroupname());
				}
			}
		}
		return stb;
	}

	/**
	 * 修改机顶盒信息
	 * 
	 * @return
	 */
	public boolean updateStbById(TStb stb) {

		if (stb.getAccounttypeStr() != null) {
			Parameter par = commonService.getParameter("accounttype", null, stb.getAccounttypeStr());
			if (par != null && par.getPcode() != null) {
				stb.setAccounttype(par.getPcode());
			}
		}
		if (stb.getStbstatusStr() != null) {
			Parameter par = commonService.getParameter("stbstatus", null, stb.getStbstatusStr());
			if (par != null && par.getPcode() != null) {
				stb.setStbstatus(par.getPcode());
			}
		}
		if (stb.getPaytypeStr() != null) {
			Parameter par = commonService.getParameter("paytype", null, stb.getPaytypeStr());
			if (par != null && par.getPcode() != null) {
				stb.setPaytype(par.getPcode());
			}
		}
		// 获取机顶盒类型
		if (StringUtils.isNotEmpty(stb.getStbtypeStr())) {
			Parameter par = commonService.getParameter("terminaltype", null, stb.getStbtypeStr());
			if (par != null && par.getPcode() != null) {
				stb.setStbtype(par.getPcode());
			}
		}

		try {
			int cc = t_stbMapper.updateStbInfo(stb);
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

	/**
	 * Excel批量导入机顶盒
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int importStb(List<TStb> list) {
		int result = 0;
		for (TStb stb : list) {
			stb.setStbstatus(1);

			// 创建时间
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stb.setCreatetime(sdf.format(new Date()));

			// 到期时间
			Calendar curr = Calendar.getInstance();
			curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);
			Date date = curr.getTime();
			stb.setValiddate(date);
			//如密码没设置，则默认123456
			if(StringUtils.isBlank(stb.getPwd())){
				stb.setPwd("123456");
			}
			stb.setPwdsure(StringUtils.isBlank(stb.getPwd())? "123456":stb.getPwd());
			
			result = t_stbMapper.insertStb(stb);
		}
		return result;
	}

}
