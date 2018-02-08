package com.fs.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.mapper.THotelGroupMapper;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.THotelGroup;
import com.fs.web.vomodel.Group_SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("T_hotel_groupService")
/** 酒店分组管理业务逻辑接口实现 */
public class T_hotel_groupServiceImpl implements T_hotel_groupService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private THotelGroupMapper t_hotel_groupMapper;

	@Resource
	private ParameterMapper parameterMapper;

	@Resource
	private TCompanyMapper companyMapper;

	/**
	 * 获取分组列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<THotelGroup> browseGroupList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<THotelGroup> groupList = t_hotel_groupMapper.getGroupList();
		if (groupList != null && groupList.size() > 0) {
			for (THotelGroup tHotelGroup : groupList) {
				if (tHotelGroup.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(tHotelGroup.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						tHotelGroup.setCompanyname(company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(groupList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return groupList;
	}

	/**
	 * 精确查询分组信息
	 * 
	 * @param p
	 * @return
	 */
	public List<THotelGroup> selectGroup(Map<String, Object> p) {
		List<THotelGroup> groupList = t_hotel_groupMapper.getGroupList();
		return groupList;
	}

	/**
	 * 新增分组
	 * 
	 * @param group
	 * @return
	 */
	public boolean insertGroup(THotelGroup group) {
		try {
			// 组编号
			if (group.getGroupnum() == null) {
				if (t_hotel_groupMapper.getMaxGroupNum() == null || t_hotel_groupMapper.getMaxGroupNum() == 0
						|| t_hotel_groupMapper.getMaxGroupNum() < 10000) {
					group.setGroupnum(10000 + 1);
				} else {
					group.setGroupnum(t_hotel_groupMapper.getMaxGroupNum() + 1);
				}
			}
			// 创建时间
			if (group.getCreatetime() == null || group.getCreatetime().equals("")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setLenient(false);// 是否宽松的
				group.setCreatetime(sdf.format(new Date()));
			}
			
			//添加用户组
			int num = t_hotel_groupMapper.insertGroup(group);
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
	 * 删除分组
	 * 
	 * @param sid
	 * @return
	 */
	public boolean delGroup(long sid) {
		try {
			int num = t_hotel_groupMapper.deleteGroup(sid);
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
	 * 查询分组是否存在
	 * 
	 * @param p
	 * @return
	 */
	public int countGroup(Map<String, Object> p) {
		return t_hotel_groupMapper.countGroup(p);
	}

	/**
	 * 查询分组详情
	 */
	public THotelGroup getGroupById(long sid) {
		THotelGroup group = t_hotel_groupMapper.selectByPrimaryKey(sid);
		if (group.getGrouptype() != null) {
			Map<String, Object> p = new HashMap<>();
			p.put("ptype", "hoteltype");
			p.put("pcode", group.getGrouptype());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null && StringUtils.isNotEmpty(par.getPname())) {
				group.setGrouptypeStr(par.getPname());
			}
		}
		return group;
	}

	/**
	 * 修改酒店分组
	 * 
	 * @param group
	 * @return
	 */
	public boolean updateGroupById(THotelGroup group) {
		// 根据类型名称获取类型编号
		if (group.getGrouptype() != null) {
			Map<String, Object> p = new HashMap<>();
			p.put("ptype", "hoteltype");
			p.put("pname", group.getGrouptypeStr());
			Parameter par = parameterMapper.getParameter(p);
			if (par != null && par.getPcode() != null) {
				group.setGrouptype(par.getPcode());
			}
		}
		try {
			int cc = t_hotel_groupMapper.updateHotelGroupInfo(group);
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
	 * 根据条件查询组信息
	 * 
	 * @param vo
	 * @return
	 */
	public List<THotelGroup> getGroupInfoByCondition(Group_SearchVo vo) {
		return t_hotel_groupMapper.getGroupInfoByCondition(vo);
	}

}
