package com.fantastic;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Convert extends MappingJackson2HttpMessageConverter {

	@Override
	protected Object readInternal(Class<? extends Object> arg0, HttpInputMessage arg1)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return super.readInternal(arg0, arg1);
	}
	
	public void setObjectMapper(ObjectMapper objectMapper){
		super.setObjectMapper(objectMapper);
	}

}
