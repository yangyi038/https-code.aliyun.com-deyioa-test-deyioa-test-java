package com.fs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.ChannelMapper;
import com.fs.comm.mapper.ColumnTypeMapper;
import com.fs.comm.mapper.ProgramMapper;
import com.fs.comm.mapper.TComDealerMapper;
import com.fs.comm.mapper.TComOperatorMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.Channel;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;
import com.fs.comm.model.TComDealer;
import com.fs.comm.model.TComOperator;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

	@Resource
	private ChannelMapper channelMapper;

	@Resource
	private ColumnTypeMapper columnTypeMapper;

	@Resource
	private ProgramMapper programMapper;

	@Resource
	private TCompanyMapper companyMapper;

	@Resource
	private TComOperatorMapper operatorMapper;

	@Resource
	private TComDealerMapper dealerMapper;

	/**
	 * 添加直播频道
	 */
	@Override
	public boolean addChannel(Channel model) {
		model.setIsDelete("n");
		model.setStatus("未审核");
		model.setLineStatus("未上线");
		model.setCreateDate(new Date());
		int result = 0;
		try {
			result = channelMapper.insertChannel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnType("直播节目");
			columnType.setName(model.getName());
			columnType.setCompanyid(model.getCompanyid());
			columnType.setContent(model.getTvDescribe());
			columnType.setCreateTime(new Date());
			columnType.setFlag("3");
			columnType.setIsDelete("n");
			columnType.setStatus("未上线");
			columnType.setColumnId(model.getId());
			result = columnTypeMapper.insert(columnType);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}
	}

	/**
	 * 浏览直播频道列表
	 */
	@Override
	public List<Map<String, Object>> getAllChannelInfo(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Map<String, Object>> query_channel = channelMapper.getAllChannelInfo(p);
		if (query_channel != null && query_channel.size() > 0) {
			for (Map<String, Object> map : query_channel) {
				// 获取运营商名称
				Long companyid = (Long) map.get("companyid");
				Long operatorid = (Long) map.get("operatorid");
				Long dealerid = (Long) map.get("dealerid");
				if (companyid != null) {
					TCompany company = companyMapper.selectByPrimaryKey(companyid);
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						map.put("companyname", company.getCompanyname());
				}
				if (operatorid != null) {// 二级运营商名称
					TComOperator ope = operatorMapper.selectByPrimaryKey(operatorid);
					if (ope != null && StringUtils.isNotBlank(ope.getName()))
						map.put("operatorname", ope.getName());
				}
				if (dealerid != null) {// 经销商名称
					TComDealer dea = dealerMapper.selectByPrimaryKey(dealerid);
					if (dea != null && StringUtils.isNotBlank(dea.getName()))
						map.put("dealername", dea.getName());
				}
			}
		}
		PageInfo page = new PageInfo(query_channel);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return query_channel;
	}

	@Override
	public int delChannelById(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = channelMapper.delChannelById(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");
			result = columnTypeMapper.deleteColumnTypeById(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
		}
		return result;
	}

	@Override
	public int unAgreeChannel(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = channelMapper.unAgreeChannel(id);
		}
		return result;
	}

	@Override
	public int agreeChannel(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = channelMapper.agreeChannel(id);
		}
		return result;
	}

	@Override
	public int onlineChannel(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = channelMapper.onlineChannel(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");
			result = columnTypeMapper.agreeOnline(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
		}
		return result;
	}

	@Override
	public int unlineChannel(Integer id) {
		int result = 0;
		if (id <= 0) {
			return result;
		} else {
			result = channelMapper.unlineChannel(id);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");
			result = columnTypeMapper.unAgreeOnline(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(id);
			columnType.setColumnType("直播节目");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				result = programMapper.agreeOnline(program);
			}
		}
		return result;
	}

	@Override
	public Channel getChannelById(Integer id) {
		return channelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateChannelById(Channel model) {
		int result = channelMapper.updateByPrimaryKeySelective(model);
		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("直播节目");
			columnType.setName(model.getName());
			columnType.setContent(model.getTvDescribe());
			result = columnTypeMapper.updateColumnTypeSelective(columnType);
		}

		if (result > 0) {
			ColumnType columnType = new ColumnType();
			columnType.setColumnId(model.getId());
			columnType.setColumnType("直播节目");

			columnType = columnTypeMapper.queryColumnTypeByModel(columnType);

			Program program = new Program();
			program.setColumntypeId((long) columnType.getId());
			program.setProgramType(columnType.getColumnType());
			List<Program> list = programMapper.getProgramByModel(program);
			if (list == null || list.size() <= 0) {
				return result;
			} else {
				program.setContent(columnType.getName());
				result = programMapper.updateProgramSelective(program);
			}
		}
		return result;
	}

	@Override
	public List<Channel> queryAllChannelNumber() {
		return channelMapper.selectAllChannelNo();
	}

	@Override
	public Channel getChannelByNumber(Integer number) {
		return channelMapper.selectByNumber(number);
	}

}
