package com.fantastic;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.framework.GlobalName;

  // com.fasterxml.jackson.databind.ObjectMapper
//Ref :http://www.cnblogs.com/yhtboke/p/5653895.html
//http://devjav.com/spring-mvc-custom-converter-for-json-data/
/** 
 * @description 解决Date类型返回json格式为自定义格式 
 * @author aokunsang 
 * @date 2013-5-28 
 */  
@SuppressWarnings("serial")
public class CustomObjectMapper extends ObjectMapper {  

    public CustomObjectMapper(){  
        super();  
//20161028 修改,什么都不做!!!现在用jqgrid的标签实现了!
//        SimpleModule module = new SimpleModule();
//        //module.setSerializerModifier(new MyBeanSerializerModifier());
//        module.setSerializerModifier(new MyBeanSerializerModifier());
//        this.registerModule(module);
        //设置日期转换yyyy-MM-dd HH:mm:ss  
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
        //设置null转换""  
        getSerializerProvider().setNullValueSerializer(new NullSerializer());  
    }  
      
    //null的JSON序列  
    private class NullSerializer extends JsonSerializer<Object> {  
        public void serialize(Object value, JsonGenerator jgen,  
                SerializerProvider provider) throws IOException,  
                JsonProcessingException {  
            jgen.writeString("");  
        }  
    }  
    
    //null的JSON序列  
	private static class MySerializer extends JsonSerializer<Object> {
		static SimpleDateFormat datefmt  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider provider)
					throws IOException, JsonProcessingException {
				if (value == null) { jg.writeNull(); return;}
				jg.writeStartObject();
				String TBL = value.getClass().getSimpleName().toUpperCase();
				Field[] f = value.getClass().getDeclaredFields();
				for (Field field : f) {
					try {
						String v = field.getName().toUpperCase();
						if (v.startsWith("V")){
							//警告特殊定制,驼峰
							v = "V_"+ v.substring(1, v.length());
						}
						if (v.startsWith("N")){
							//警告特殊定制,驼峰
							v = "N_"+ v.substring(1, v.length());
						}
						String fff = TBL + "." +v;
						field.setAccessible(true);
						final Object srcValue = field.get(value);
						if (srcValue==null) {
							jg.writeStringField(field.getName()        ,"");
							continue;
						}
						if (!GlobalName.parameter.containsKey(fff)){
					        if (field.getType().isAssignableFrom(Date.class)) {
							    jg.writeStringField(field.getName()        ,datefmt.format((java.util.Date)srcValue));
					        	
					        }else{
							    jg.writeStringField(field.getName()        ,srcValue.toString());
					        	
					        }
						}else{
						    jg.writeStringField(field.getName()        ,""+GlobalName.parameter.get(fff).get(""+srcValue));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		        
				jg.writeEndObject();
	
			}
		}
	
	//-------------------------------
    //http://stackoverflow.com/questions/25509699/change-object-just-before-json-serialization
    private static class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public JsonSerializer<?> modifySerializer(
                final SerializationConfig serializationConfig,
                final BeanDescription beanDescription,
                final JsonSerializer<?> jsonSerializer) {
            return new ModifyingSerializer((JsonSerializer<Object>) jsonSerializer);
        }
    }

    private static class ModifyingSerializer extends JsonSerializer<Object> {
        private final JsonSerializer<Object> serializer;
        private final JsonSerializer<Object> MySerializer;

        public ModifyingSerializer(final JsonSerializer<Object> jsonSerializer) {
            this.serializer  = jsonSerializer;
            this.MySerializer = new MySerializer();
        }

        @Override
        public void serialize(
                final Object o,
                final JsonGenerator jsonGenerator,
                final SerializerProvider serializerProvider)
        throws IOException {
        	if (com.framework.GlobalName.ClassHasParameter.containsKey(o.getClass().getSimpleName().toUpperCase())){
        		//该表有代码,注意字段对应:用定制的MySerializer
        		MySerializer.serialize(o, jsonGenerator, serializerProvider);
        	}else{
                serializer.serialize(o, jsonGenerator, serializerProvider);
        	}
        }
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        SimpleModule module = new SimpleModule();
//        module.setSerializerModifier(new MyBeanSerializerModifier());
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(module);
//        System.out.println(mapper.writeValueAsString(new T_charge_exception("abc")));
//    }

    
}  
