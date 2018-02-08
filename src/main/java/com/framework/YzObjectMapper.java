package com.framework;

import java.io.IOException;
import java.sql.Clob;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.framework.util.Tools;

public class YzObjectMapper extends ObjectMapper  {
	public YzObjectMapper(){
		super();
		//设置null转换""
		//设置日期转换yyyy-MM-dd HH:mm:ss
		 SimpleModule module = new SimpleModule("JSONModule", new Version(2, 0, 0, null, null, null));
	        module.addSerializer(Clob.class, new ClobSerializer());
	        registerModule(module);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	//null的JSON序列
	private class ClobSerializer extends JsonSerializer<Object> {
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			jgen.writeString(Tools.clob2Str((Clob)value));
		}
	}
}
