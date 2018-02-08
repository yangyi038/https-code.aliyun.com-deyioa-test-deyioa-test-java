package com.framework;
import javax.servlet.ServletContextEvent;   
import javax.servlet.ServletContextListener;   

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitListener implements ServletContextListener {  
	private static final Log log = LogFactory.getLog(InitListener.class);
	   public void contextDestroyed(ServletContextEvent sce) {   
		   log.info("web exit ... ");   
	    }
	    public void contextInitialized(ServletContextEvent sce) {
	    	
	    	 int errCode = 0;
	    	 log.info("开始加载参数");
	         try{
	        	 SqlSessionTemplate sqlSession = (SqlSessionTemplate)WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getBean("sqlSessionTemplate");;
	             // 加载公共参数
	        	 OptionDictSelect optionDictSelect=new OptionDictSelect();
	        	optionDictSelect.init(sqlSession,sce);
	         }catch(Exception e){
	             System.out.println("系统启动时初始化环境变量出错：");
	             e.printStackTrace();
	         }
	         log.info("结束加载参数");
	         
	    }   
	  
}  

