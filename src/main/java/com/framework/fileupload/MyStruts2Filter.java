package com.framework.fileupload;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
             
public class MyStruts2Filter extends CommonsMultipartResolver {

    @Override  
      public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {  
       String uri = request.getRequestURI();  
       System.out.println(uri);
       //过滤使用百度UEditor的URI  
       if (uri.endsWith("controller.jsp")) {   //此处拦截路径即为上面编写的controller路径
        System.out.println("commonsMultipartResolver 放行");
        return false;  
       }  
       System.out.println("commonsMultipartResolver 拦截");
       return super.isMultipart(request);  
      }  
}