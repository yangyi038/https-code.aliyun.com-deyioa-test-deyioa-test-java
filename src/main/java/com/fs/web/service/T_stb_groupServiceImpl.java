package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.TStbGroupMapper;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.TStbGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fantastic.TBaseExample;
import com.fantastic.TBaseExample.Criteria;

@Service("T_stb_groupService")
public class T_stb_groupServiceImpl implements T_stb_groupService {

	@Resource
	private TStbGroupMapper t_stb_groupMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Resource
	private TComOperatorMapper operatorMapper;
	
	@Resource
	private TComDealerMapper dealerMapper;

	/**
	 * 鑾峰彇鏈洪《鐩掔粍鍒楄〃
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<TStbGroup> browseStbGroupList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<TStbGroup> groupList = t_stb_groupMapper.getGroupList(p);
		if (groupList != null && groupList.size() > 0) {
			for (TStbGroup tStbGroup : groupList) {
				// 获取运营商名称
				if (tStbGroup.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(tStbGroup.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						tStbGroup.setCompanyname(company.getCompanyname());
				}
				if (tStbGroup.getOperatorid() != null) {//二级运营商名称
					TComOperator ope = operatorMapper.selectByPrimaryKey(tStbGroup.getOperatorid());
					if (ope != null && StringUtils.isNotBlank(ope.getName()))
						tStbGroup.setOperatorname(ope.getName());
				}
				if (tStbGroup.getDealerid() != null) {//经销商名称
					TComDealer dea = dealerMapper.selectByPrimaryKey(tStbGroup.getDealerid());
					if (dea != null && StringUtils.isNotBlank(dea.getName()))
						tStbGroup.setDealername(dea.getName());
				}
			}
		}
		PageInfo page = new PageInfo(groupList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return groupList;
	}

	/**
	 * 精确查找
	 * 
	 * @param p
	 * @return
	 */
	public List<TStbGroup> selectStbGroup(Map<String, Object> p) {
		List<TStbGroup> groupList = t_stb_groupMapper.getGroupList(p);
		return groupList;
	}

	/**
	 * 获取机顶盒组列表
	 * 
	 * @param p
	 * @return
	 */
	public List<TStbGroup> getStbGroupList(Map<String, Object> p) {
		List<TStbGroup> groupList = t_stb_groupMapper.getGroupList(p);
		return groupList;
	}

	/**
	 * 鏂板鏈洪《鐩掔粍
	 * 
	 * @param group
	 * @return
	 */
	public boolean insertStbGroup(TStbGroup group) {
		try {
			// 鏈洪《鐩掔粍璐﹀彿
			if (group.getGroupnum() == null) {
				if (t_stb_groupMapper.getMaxStbGroupNum() == null || t_stb_groupMapper.getMaxStbGroupNum() == 0
						|| t_stb_groupMapper.getMaxStbGroupNum() < 10000) {
					group.setGroupnum(10000 + 1);
				} else {
					group.setGroupnum(t_stb_groupMapper.getMaxStbGroupNum() + 1);
				}
			}
			// 鍒涘缓鏃堕棿
			if (group.getCreatetime() == null || group.getCreatetime().equals("")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				group.setCreatetime(sdf.format(new Date()));
			}
			int num = t_stb_groupMapper.insertGroup(group);
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
	 * 鍒犻櫎鏈洪《鐩掔粍
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delStbGroup(long sid) {
		try {
			int num = t_stb_groupMapper.deleteGroup(sid);
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
	 * 鏌ヨ缁勬槸鍚﹀瓨鍦�
	 * 
	 * @param p
	 * @return
	 */
	public int countStbGroup(Map<String, Object> p) {
		return t_stb_groupMapper.countGroup(p);
	}

	/**
	 * 鏌ヨ鏈洪《鐩掔粍
	 */
	public TStbGroup getStbGroupById(long sid) {
		TStbGroup group = t_stb_groupMapper.selectByPrimaryKey(sid);
		return group;
	}

	/**
	 * 修改
	 * 
	 * @param group
	 * @return
	 */
	public boolean updateStbGroupById(TStbGroup group) {
		try {
			int cc = t_stb_groupMapper.updateStbGroupInfo(group);
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

	@Override
	public TStbGroup getTStbGroupByGrpupName(String groupName) {
		return t_stb_groupMapper.getTStbGroupByGroupName(groupName);
	}

	@Override
	public List<TStbGroup> getAllStbGroup() {
		List<TStbGroup> list = null;
		try {
			list = t_stb_groupMapper.getAllStbGroup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
