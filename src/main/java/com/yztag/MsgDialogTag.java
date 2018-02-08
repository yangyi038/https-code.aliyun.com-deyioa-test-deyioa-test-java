package com.yztag;

import java.io.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.framework.util.Tools;

/** 带遮罩的网页对话框自定义标签类 */
public class MsgDialogTag extends SimpleTagSupport {

	String title = "温馨提示"; //对话框标题文字
	String boxwidth="";//对话框的宽度
	String boxheigth="";//对话框的宽度
	String basepath =""; 	//基本URL
	String tmpid = null;	//临时ID后缀,避免ID冲突,默认为系统时间的毫秒数
	String callback = null;
	/** 标签体处理 */
    public void doTag() throws JspException, IOException{
   if (tmpid==null)tmpid = String.valueOf(System.currentTimeMillis());//临时ID后缀,避免ID冲突
    	
    	//取得现有标签体的内容
    	JspFragment body = this.getJspBody();
    	StringWriter writer = new StringWriter();
    	StringBuffer buff = writer.getBuffer();
    	body.invoke(writer);

//    	//构造带遮罩的网页对话框
   	StringBuffer sb = new StringBuffer();

   	if(callback==null||callback.equals("")){
   		callback="";
   	}
   	String meg=Tools.replaceHtml(writer.toString().trim()).replaceAll("\n", "").replaceAll("\t", "");
   	if(meg==null||meg.equals("")){
   	 sb.append("<script type=\"text/javascript\">"+callback+"</script>");
 		
   	}else{
   		sb.append("<script type=\"text/javascript\">modalDialogContent('"+tmpid+"','"+title+"','"+boxwidth+"','"+boxheigth+"','"+meg+"',function(){closeDialog('"+tmpid+"');"+callback+"})</script>");
   	}
    	//输出处理结果到页面上
    	getJspContext().getOut().println(sb);    	
    }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}


	public String getBoxwidth() {
		return boxwidth;
	}

	public void setBoxwidth(String boxwidth) {
		this.boxwidth = boxwidth;
	}

	public String getTmpid() {
		return tmpid;
	}

	public void setTmpid(String tmpid) {
		this.tmpid = tmpid;
	}


	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
