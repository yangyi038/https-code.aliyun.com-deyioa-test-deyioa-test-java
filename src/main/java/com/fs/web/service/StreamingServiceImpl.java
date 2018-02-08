package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.StreamingMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.Streaming;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("streamingService")
public class StreamingServiceImpl implements StreamingService {

	@Resource
	private StreamingMapper streamingMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Override
	public boolean addStreaming(Streaming model) {
		model.setIsDelete("n");
		int result = streamingMapper.insert(model);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getAllStreamingInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> list = streamingMapper.getAllStreamingInfo(p);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				// 获取运营商名称
				Long companyid = (Long) map.get("companyid");
				if (companyid != null) {
					TCompany company = companyMapper.selectByPrimaryKey(companyid);
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						map.put("companyname", company.getCompanyname());
				}
			}
		}
		PageInfo page = new PageInfo(list);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return list;
	}

	@Override
	public int delStreamingById(Long id) {
		int result = 0;
		if ("".equals(id) || null == id) {
			return 0;
		} else {
			result = streamingMapper.delStreamingById(id);
		}
		return result;
	}

	@Override
	public Streaming queryStreamingById(Long id) {
		return streamingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateStreamingById(Streaming model) {
		return streamingMapper.updateByPrimaryKeySelective(model);
	}

}
