package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.CardMapper;
import com.fs.comm.mapper.FixingMapper;
import com.fs.comm.model.Card;
import com.fs.comm.model.Fixing;
import com.fs.comm.model.Sysuser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("FixingService")
public class FixingServiceImpl implements FixingService {
	
	@Resource
	private FixingMapper fixingMapper;
	
	@Resource
	private CardMapper cardMapper;
	
	@Override
	public boolean addFixing(Fixing model) {
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Long companyid = sysuser.getCompanyid();
		model.setCompanyid(companyid);
		model.setIsDelete("n");
		int result = fixingMapper.insert(model);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getAllFixingInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Long companyid = sysuser.getCompanyid();
		p.put("companyid", companyid);
		List<Map<String,Object>>  list = fixingMapper.getAllFixingInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delFixingById(Long id) {
		int result = 0;
		if("".equals(id)||null==id){
			return 0;
		}else{
			result = fixingMapper.delFixingById(id);
		}
		return result;
	}

	@Override
	public Fixing queryFixingById(Long id) {
		return fixingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateFixingById(Fixing model) {
		return fixingMapper.updateByPrimaryKeySelective(model);
	}
	
	
	@Override
	public List<Fixing> queryAllFixing() {
		return fixingMapper.queryAllFixing();
	}
	@Override
	public int importFixing(List<Fixing> list) {
		int result = 0;
		if(list.size()>0 && list!=null){
			for(Fixing model:list){
				model.setIsDelete("n");
				result = fixingMapper.insert(model);
			}
		}
		return result;
	}


	@Override
	public boolean addCard(Card model) {
		model.setIsDelete("n");
		int result = cardMapper.insert(model);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getAllCardInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  list = cardMapper.getAllCardInfo(p);
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delCardById(Long id) {
		int result = 0;
		if("".equals(id)||null==id){
			return 0;
		}else{
			result = cardMapper.delCardById(id);
		}
		return result;
	}

	@Override
	public Card queryCardById(Long id) {
		return cardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateCardById(Card model) {
		return cardMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<Card> queryAllCardByFixingId(Long fixingId) {
		
		return cardMapper.getAllCardByFixingId(fixingId);
	}

	@Override
	public int importCard(List<Card> list,Long fixingId) {
		int result = 0;
		if(list.size()>0 && list!=null){
			for(Card model:list){
				model.setIsDelete("n");
				model.setFixingId(fixingId);
				result = cardMapper.insert(model);
			}
		}
		return result;
	}



}
