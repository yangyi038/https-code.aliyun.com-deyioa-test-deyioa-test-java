package com.fs.app.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.framework.util.ThreeDES;
import com.fs.app.vomodel.MessageDto;
import com.fs.comm.mapper.TCompanyAPIMapper;
import com.fs.comm.mapper.TStbAPIMapper;
import com.fs.comm.model.TCompany;
import com.fs.comm.model.TStb;

@Service("epgService")
public class I_EPGServiceImpl implements I_EPGService{
	
	private final String key = "A1B2C3D4E5F60708";
	
	@Resource
	private TStbAPIMapper stbMapper;
	
	@Resource
	private TCompanyAPIMapper companyAPIMapper;

	@Override
	public MessageDto getAllColumnInfoById(String cid, String userToken,String userId,String companyname) {
		TStb model = stbMapper.queryTsbById(userId);
		MessageDto dto = new MessageDto();
		if (null != model) {
			String token = model.getTokencode();
			String tempToken = null;
			try {
				tempToken = ThreeDES.decrypt(userToken, key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtils.isEmpty(tempToken) && !token.equals(new String(tempToken))) {
				dto.setInfo("token不合法");
				dto.setCode(104);
				return dto;

			}
		}else{
			dto.setInfo("没有该机顶盒的信息");
			dto.setCode(103);
			return dto;
		}
		
		TCompany company = companyAPIMapper.getTCompanyByName(companyname);
		if(null==company){
			dto.setInfo("没有该运营商的信息");
			dto.setCode(103);
			return dto;
		}
		
		
		return null;
	}

}
