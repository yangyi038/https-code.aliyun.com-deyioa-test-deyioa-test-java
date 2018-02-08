package com.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.framework.util.JsonUtil;
public class ActionUtil {
	public ActionUtil() {
	}

	private static final Log log = LogFactory.getLog(ActionUtil.class);
	public static int getIndex(java.lang.String as[], java.lang.String s) {
		if (as != null) {
			for (int i = 0; i < as.length; i++)
				if (s.equalsIgnoreCase(as[i]))
					return i;

			return -1;
		} else {
			return -1;
		}
	}
    public static void ajaxProxyRequestJson(
            javax.servlet.http.HttpServletResponse response,
            String s) throws java.lang.Exception {
    	 response.setContentType("text/json; charset=UTF-8");//传输xml时要用html 
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(s);
    }
    /**
     * ajax成功，返回datatype为json
     * @param info
     * @param data
     */
    public static Map<String, Object> ajaxSuccess(
           String info,String data){
    	return ajaxReturn(0, info, data);
    }
    /**
     * ajax错误，返回datatype为json
     * @param info
     * @param data
     */
    public static Map<String, Object> ajaxFail(
           String info,String data){
    	return ajaxReturn(1, info, data);
    }
    
    public static Map<String, Object> ajaxReturn(int status,String info,String data){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("status", status);
				map.put("info", info);
				map.put("data", data);
				return map;
    }
    /**
     * 返回给app移动端数据
     * @param response
     * @param code
     */
    public static void requestAppFailReturn( javax.servlet.http.HttpServletResponse response,int code){
    	Map<String, Object> retmap = new HashMap<String, Object>();
    	retmap.put("retcode", 1);
    	retmap.put("error", code);
    	requestAppReturn(response, retmap);
			
    }
    public static void requestAppSuccessReturn( javax.servlet.http.HttpServletResponse response,Map<String, Object> retmap){
    	retmap.put("retcode",0);
    	requestAppReturn(response, retmap);
			
    }
    public static void requestAppReturn( javax.servlet.http.HttpServletResponse response,Map<String, Object> retmap){
    	log.info("contentType=text/json");
		response.setContentType("text/json; charset=UTF-8"); 
			response.setCharacterEncoding("UTF-8");
			try {
				PrintWriter pw;
				pw = response.getWriter();
				pw.write(JsonUtil.getMapToJson(retmap));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    }
}
