/*
 * Created on 2003-6-18
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.framework.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.YzObjectMapper;
import com.framework.jqgrid.JqGridPager;
/**
 * String鐨勭浉鍏冲鐞嗙被
 * 
 * @author zqb
 */
public abstract class JsonUtil {


	/**
	 *List褰㈠紡鐨勬暟鎹泦
	 * @param listdata
	 * @param jqGridPager
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String spanJson(List<?> datas,JqGridPager jqGridPager) throws IllegalArgumentException, IllegalAccessException {
		 StringBuffer sb = new StringBuffer();
		 sb.append("{\"total\":\""+jqGridPager.getTotal()+"\",\"page\":\""+jqGridPager.getPager()+"\",\"records\":\""+jqGridPager.getRecords()+"\",\"rows\":");
		 if(datas!=null){
			 ObjectMapper mapper = new YzObjectMapper(); 
			 mapper.setVisibility(PropertyAccessor.FIELD,     
					    JsonAutoDetect.Visibility.ANY);
			 String jsonlist="";
			try {
				jsonlist = mapper.writeValueAsString(datas);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			 sb.append(jsonlist);
		 }else{
			 sb.append("[]");
		 }
		 sb.append("}");
		   return sb.toString();
	}
	 /**   
     * 浠巎son HASH琛ㄨ揪寮忎腑鑾峰彇涓�涓猰ap锛屾敼map鏀寔宓屽鍔熻兘   
     * @param jsonString   
     * @return   
     */   
    public static Map getJsonToMap(String jsonString){
    	Map valueMap = new HashMap();
    	if(jsonString==null){
    		return valueMap; 
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			valueMap=mapper.readValue(jsonString, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
            
        return valueMap;    
    }    
   public static String getMapToJson(Map<String, Object> map){
	   ObjectMapper mapper = new ObjectMapper(); 
	   try {
		String jsonstring=mapper.writeValueAsString(map);
		return jsonstring;
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	   
   }
	/**
	 * 鏌ヨ鏉′欢sql鎷艰
	 * 
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static void getQueryParameters(String jsonString,
			JqGridPager jqGridPager) throws UnsupportedEncodingException {
//		if (jsonString != null && !"".equals(jsonString)) {
//			StringBuffer sb = new StringBuffer();
//			Map map = getMap4Json(jsonString);
//			JSONArray rules = (JSONArray) map.get("rules");
//			String[] stringArray = getStringArray4Json(rules);
//			int i = 0;
//			for (String objmap : stringArray) {
//				i++;
//				Map map_ = getMap4Json(objmap);
//			if (!"".equals(URLDecoder.decode((String) map_.get("data"),
//						"utf-8"))) {
//
//					if (i == 1) {
//						sb.append(" "
//								+ (String) map_.get("field")
//								+ " "
//								+ getOp((String) map_.get("op"))
//								+ " "
//								+ (DateTool.isValidDate(URLDecoder.decode((String) map_.get("data"),
//										"utf-8") , "yyyy-MM-dd")?"to_date('"+URLDecoder.decode((String) map_.get("data"),
//												"utf-8")+"','yyyy-MM-dd')":"'"+(((String) map_.get("op")).equals("cn")?"%":"")+URLDecoder.decode((String) map_.get("data"),
//												"utf-8")+(((String) map_.get("op")).equals("cn")?"%":"")+ "'"));
//					} else {
//						sb.append(" "
//								+ (String) map.get("groupOp")
//								+ " "
//								+ (String) map_.get("field")
//								+ " "
//								+ getOp((String) map_.get("op"))
//								+ " "
//								+ (DateTool.isValidDate(URLDecoder.decode((String) map_.get("data"),
//										"utf-8") , "yyyy-MM-dd")?"to_date('"+URLDecoder.decode((String) map_.get("data"),
//												"utf-8")+"','yyyy-MM-dd')":"'"+(((String) map_.get("op")).equals("cn")?"%":"")+URLDecoder.decode((String) map_.get("data"),
//												"utf-8")+(((String) map_.get("op")).equals("cn")?"%":"")+ "'"));
//					}
//				}else{
//					sb.append(" "
//							+ (String) map_.get("field")+" is null");
//				}
//			}
//			if(jqGridPager.getWhere()!=null&&!(jqGridPager.getWhere()).equals("")){
//				jqGridPager.setWhere(jqGridPager.getWhere()+" and "+sb.toString());
//			}else{
//				jqGridPager.setWhere(sb.toString());
//			}
//			
//		}

	}
    /**   
     * 鏌ヨ绫诲瀷瑙ｆ瀽   
     * @param String   
     * @return   
     */   
    public static String getOp(String op){      
    	String op_="";
    	if(op.equalsIgnoreCase("eq")){//绛変簬
    		op_="=";
    	}else if(op.equalsIgnoreCase("ne")){//涓嶇瓑浜�
    		op_="!=";
    	}else if(op.equalsIgnoreCase("lt")){//灏忎簬
    		op_="<";
    	}else if(op.equalsIgnoreCase("le")){//灏忎簬绛変簬
    		op_="<=";
    	}else if(op.equalsIgnoreCase("gt")){//澶т簬
    		op_=">";
    	}else if(op.equalsIgnoreCase("ge")){//澶т簬绛変簬
    		op_=">=";
    	}else if(op.equalsIgnoreCase("cn")){//鍖呭惈
    		op_="like";
    	}
            
        return op_;    
    } 
    /**
     * list褰㈠紡鐨勬暟鎹泦杞琷son瀛楃涓�
     * @param mapdata
     * @return
     * @author dingyulei
     */
    public static String spanJson(List<?> listdata){
    	StringBuffer sb = new StringBuffer();
    	 if(listdata!=null){
    		 ObjectMapper mapper = new ObjectMapper(); 
			 String jsonlist="";
			try {
				jsonlist = mapper.writeValueAsString(listdata);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
				sb.append(jsonlist);
		 }else{
			 sb.append("[]");
		 }
		   return sb.toString();
    }
    /**
     * json杞琹ist
     * @param json
     * @param beanClass
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> beanClass) {  
        try {  
        	 ObjectMapper objectMapper = new ObjectMapper();   
        	 JavaType javaType=objectMapper.getTypeFactory().constructParametricType(List.class, beanClass);
            return (List<T>)objectMapper.readValue(json, javaType);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    public static void main(String[] args){
        /*  String json="[{\"name\":\"寮犱笁_testMap2Json\",\"gender\":\"鐢穃",\"age\":\"22\"},{\"name\":\"寮犱笁_testMap2Json\",\"gender\":\"鐢穃",\"age\":\"22\"}]";
         jsonToList(json, HashMap.class);*/
    	
         return;  
    	
	 }
}
