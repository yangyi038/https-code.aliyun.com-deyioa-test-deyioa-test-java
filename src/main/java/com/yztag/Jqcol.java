// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.framework.OptionDictSupport;

// Referenced classes of package com.lbs.cp.taglib:
//            ViewTableTag, Formatter

public class Jqcol extends TagSupport {
	protected String name;// 字段名字
	protected String index;// 传到服务器端用来排序用的列名称
	protected String width;// 列宽度
	protected String align;// 对齐方式；
	protected String sortable;// 是否可以排序
	protected String hidden;// 是否隐藏
	protected String formatter;// 数据格式
	protected String type;// 数据类型：参数或者字符
	protected String editoptions;// 常量格式数据，如：下拉框
	protected String autowidth;// by lqf

	public void setAutowidth(String autowidth) {
		this.autowidth = autowidth;
	}

	public Jqcol() {
	}

	public int doStartTag() throws JspException {
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append("{name:'" + name + "',index:'" + index + "'");
		if (width != null) {
			stringbuffer.append(",width:'" + width + "'");
		}
		if (autowidth != null) {
			stringbuffer.append(",autowidth:'" + autowidth + "'");
		}
		if (align != null && !align.equals("")) {
			stringbuffer.append(",align:'" + align + "'");
		} else {
			stringbuffer.append(",align:'center'");//默认对齐：left
		}
		if (hidden != null) {
			stringbuffer.append(",hidden:" + hidden + "");
		}
		if (sortable != null) {
			stringbuffer.append(",sortable:'" + sortable + "'");
		}
		if (formatter != null) {
			stringbuffer.append(",formatter:'" + formatter + "'");
		}
		if (editoptions != null) {
			stringbuffer.append(",editoptions: { value: {'':''," + editoptions + "");
			stringbuffer.append("}");
			stringbuffer.append("}");
		} else {

			if (type != null && !type.equals("")) {
				stringbuffer.append(",formatter: \"select\",editoptions: { value: {'':''");
				// TreeMap<String, TreeMap> treeMap=(TreeMap<String,
				// TreeMap>)pageContext.getServletContext().getAttribute("parameter");
				TreeMap<String, String> treeMapp = OptionDictSupport.getNameMap(pageContext.getServletContext(), type);
				// TreeMap<String, String> treeMapp=(TreeMap<String,
				// String>)treeMap.get(type);
				if (treeMapp != null) {
					Iterator iter = treeMapp.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						Object key = entry.getKey();
						stringbuffer.append(",'" + key + "':'" + entry.getValue() + "'");

					}
					stringbuffer.append("}");
					stringbuffer.append("}");
				}
			}
		}
		stringbuffer.append("},");
		JspWriter jw = this.pageContext.getOut();
		try {
			jw.write(stringbuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_PAGE;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	// public void setStype(String stype) {
	// this.stype = stype;
	// }
	//
	public void setType(String type) {
		this.type = type;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public void setEditoptions(String editoptions) {
		this.editoptions = editoptions;
	}

	// public void setSopt(String sopt) {
	// this.sopt = sopt;
	// }
	//
	// public void setEditable(String editable) {
	// this.editable = editable;
	// }
	//
	// public void setEditsize(String editsize) {
	// this.editsize = editsize;
	// }
	//
	// public void setEditrules(String editrules) {
	// this.editrules = editrules;
	// }
	//
	// public void setFormoptions(String formoptions) {
	// this.formoptions = formoptions;
	// }

}
