package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ChannelMapper;
import com.fs.comm.mapper.PhysicalChannelMapper;
import com.fs.comm.model.Channel;
import com.fs.comm.model.PhysicalChannel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("physicalChannelService")
public class PhysicalChannelServiceImpl implements PhysicalChannelService {


	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private PhysicalChannelMapper physicalChannelMapper;
	
	@Override
	public boolean addPhysicalChannel(PhysicalChannel model) {
		model.setIsDelete("n");
		model.setStatus("无效");
		model.setCreateTime(new Date());
		int result=0;
		try {
			result = physicalChannelMapper.insert(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0){
			return true;
		}else{
			return false;

		}
	}

	@Override
	public List<Map<String, Object>> getAllPhysicalChannelInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String,Object>>  query_channel =physicalChannelMapper.getAllPhysicalChannelInfo(p);
		PageInfo page = new PageInfo(query_channel);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return query_channel;
	}

	@Override
	public int delPhsicalChannelById(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.delPhsicalChannelById(id);
		}
		return result;
	}

	@Override
	public int stopChannel(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.stopChannel(id);
		}
		return result;
	}

	@Override
	public int activateChannel(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.activateChannel(id);
		}
		return result;
	}

	@Override
	public int effectiveChannel(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.effectiveChannel(id);
		}
		return result;
	}

	@Override
	public int unEffectiveChannel(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.unEffectiveChannel(id);
		}
		return result;
	}

	@Override
	public int scrapChannel(Integer id) {
		int result = 0;
		if(id<=0){
			return result;
		}else{
			result = physicalChannelMapper.scrapChannel(id);
		}
		return result;
	}

	
	@Override
	public PhysicalChannel getPhysicalChannelById(Integer id) {
		 PhysicalChannel model = null;
		try {
			model = physicalChannelMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public int updateChannelById(Channel model) {
		return channelMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<Channel> queryAllChannelNumber() {
		return channelMapper.selectAllChannelNo();
	}

	@Override
	public int updatePhysicalChannelById(PhysicalChannel model) {
		return physicalChannelMapper.updateByPrimaryKeySelective(model);
	}





}
