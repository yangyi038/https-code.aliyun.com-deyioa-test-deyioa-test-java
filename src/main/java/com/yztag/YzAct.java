// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class YzAct extends TagSupport
{
	 protected String id;

   
    public int doStartTag()
            throws JspException
        {
    	JspWriter jw = this.pageContext.getOut(); 
    		 StringBuffer sb=new StringBuffer();
    		 sb.append("<script type=\"text/javascript\">var op=[{"+id+":[");
    		 try {
    				jw.write(sb.toString());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	        return EVAL_BODY_INCLUDE;
        }
    public int doEndTag() throws JspException {
    	JspWriter jw = this.pageContext.getOut(); 
		 StringBuffer sb=new StringBuffer();
		 sb.append("{}]}];</script>");
		 try {
				jw.write(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return EVAL_PAGE; 
    }


	public void setId(String id) {
		this.id = id;
	}






	
}
