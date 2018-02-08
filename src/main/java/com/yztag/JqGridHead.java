// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;



import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


// Referenced classes of package com.lbs.cp.taglib:
//            ViewTableTag, Formatter

public class JqGridHead extends TagSupport
{
	protected String headvalue;//列表头，字段数组
	protected String userDataOnFooter; //by lqf页脚统计
    public void setUserDataOnFooter(String userDataOnFooter) {
		this.userDataOnFooter = userDataOnFooter;
	}

    public int doStartTag()
        throws JspException
    {
    	StringBuffer stringbuffer = new StringBuffer("");
    	stringbuffer.append("colNames:["+headvalue+"],");
    	stringbuffer.append("colModel:[");	
    	JspWriter jw = this.pageContext.getOut(); 
    	try {
			jw.write(stringbuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return EVAL_BODY_INCLUDE;
    }
    public int doEndTag() throws JspException {
    	StringBuffer stringbuffer = new StringBuffer("");
    	stringbuffer.append("],");
    	
    	if (userDataOnFooter != null && userDataOnFooter.trim().length()>0){
    		if (!userDataOnFooter.trim().equalsIgnoreCase("false")){
        		stringbuffer.append("footerrow : true,");
        		stringbuffer.append("userDataOnFooter : true,");
        		stringbuffer.append("altRows : true,");
    		}

    	}
    	
    	JspWriter jw = this.pageContext.getOut(); 
    	try {
			jw.write(stringbuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return EVAL_PAGE;  

    }
	public void setHeadvalue(String headvalue) {
		this.headvalue = headvalue;
	}



}
