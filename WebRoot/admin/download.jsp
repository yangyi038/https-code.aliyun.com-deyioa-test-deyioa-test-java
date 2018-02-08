<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@page language="java" contentType="application/x-msdownload"  pageEncoding="UTF-8"%><%
		 String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    //关于文件下载时采用文件流输出的方式处理：
    //加上response.reset()，并且所有的％>后面不要换行，包括最后一个；
    response.reset();//可以加也可以不加
    response.setContentType("application/x-download");
    String filedownload =request.getRealPath("")+(String)request.getAttribute("filedownload");
    String filedisplay = (String)request.getAttribute("filedisplay");
    filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
    response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
    OutputStream outp = null;
    FileInputStream in = null;
    try
    {
        outp = response.getOutputStream();
        in = new FileInputStream(filedownload);

        byte[] b = new byte[1024];
        int i = 0;

        while((i = in.read(b)) > 0)
        {
            outp.write(b, 0, i);
        }
        outp.flush();
    }
    catch(Exception e)
    {
        System.out.println("Error!");
        e.printStackTrace();
    }
    finally
    {
        if(in != null)
        {
            in.close();
            in = null;
        }
        if(outp != null)
        {
            outp.close();
            outp = null;
            out.clear();  
            out = pageContext.pushBody(); 

        }
    }
%>