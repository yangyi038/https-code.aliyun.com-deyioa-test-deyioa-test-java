// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DepTag extends TagSupport
{
	 protected String id;
	 protected String hiddenid;
	 protected String path;
	 protected String hiddenvalue;
	 protected String height="230";
	 protected String level;
	 protected String isall;
	public DepTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
    	JspWriter jw = this.pageContext.getOut(); 
    	try {

			jw.write("<div class=\"dep\" style=\"height:"+height+"px;display:none;position:absolute;\" id=\""+id+"_depDiv\"><iframe src=\""+this.pageContext.getServletContext().getContextPath()+"/common/department.jsp?id="+id+"&hiddenid="+hiddenid+"&path="+path+"&level="+level+"&isall="+isall+"\" style=\"display:inline\" id=\""+id+"\" name=\"areaName\" frameborder=\"0\" width=\"100%\" height=\"100%\" scrolling=\"no\" framespacing=\"0\"></iframe></div>");
		
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 1;
    }

    public void release()
    {
        super.release();
    }

	public String getHiddenid() {
		return hiddenid;
	}

	public void setHiddenid(String hiddenid) {
		this.hiddenid = hiddenid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	 
    public void setPath(String path) {
		this.path = path;
	}

	public void setHiddenvalue(String hiddenvalue) {
		this.hiddenvalue = hiddenvalue;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setIsall(String isall) {
		this.isall = isall;
	}
	
}
