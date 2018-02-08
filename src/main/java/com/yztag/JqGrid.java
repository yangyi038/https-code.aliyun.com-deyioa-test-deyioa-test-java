// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
// Referenced classes of package com.lbs.cp.taglib:
//            ViewTableTag, Formatter

public class JqGrid extends TagSupport {
	protected String name;// 列表名字
	protected String url;// 提交url
	protected String mtype;// 提交方式
	protected String rowList;// 一页显示条数列表
	protected String rowNum;// 一页显示条数
	protected String id;// 组件id
	protected String pageid;// page工具栏id
	protected String dourl;// 操作url，例如：edit、add
	protected String delurl;// 操作url
	protected String prmNames;// 传递编辑参数
	protected String sortname;// 默认排序字段
	protected String multiselect;// 复选框

	// pzj add at 20170620
	protected String multiboxonly;
	protected String beforeSelectRow;
	protected String height;

	protected String ondblClickRow;// 双击操作
	protected String del;// 删除按钮
	protected String search;// 查询按钮
	protected String hidegrid;// 组件隐藏
	protected String loadComplete;// 当从服务器返回响应时执行
	protected String autowidth;// 自适应宽度
	protected String autoheight;// 自适应高度
	protected String export;// 自适应宽度

	protected String add;// 新增按钮
	protected String edit;// 编辑按钮
	protected String postData;// post参数
	protected String datatype;// datatype参数,作用:设置为local后,grid不自动加载数据
	protected String afterInsertRow;// 数据加载触发
	protected String onSelectRow;// 选中事件
	protected String onSelectAll;

	public void setMultiboxonly(String multiboxonly) {
		this.multiboxonly = multiboxonly;
	}

	public void setBeforeSelectRow(String beforeSelectRow) {
		this.beforeSelectRow = beforeSelectRow;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public void setAutoheight(String autoheight) {
		this.autoheight = autoheight;
	}

	public void setAutowidth(String autowidth) {
		this.autowidth = autowidth;
	}
	public void setLoadComplete(String loadComplete) {
		this.loadComplete = loadComplete;
	}

	public JqGrid() {
	}

	public int doStartTag() throws JspException {
		JspWriter jw = this.pageContext.getOut();
		;
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append("<script type=\"text/javascript\">");
		stringbuffer.append("jQuery(function(){ jQuery(\"#" + id + "\").jqGrid({");
		stringbuffer.append("url:\"" + url + "\",");
		if (datatype != null && !datatype.equals("")) {
			stringbuffer.append("datatype: \"" + datatype + "\",");
		} else {
			stringbuffer.append("datatype: \"json\",");
		}
		if (datatype != null && !datatype.equals("")) {
			stringbuffer.append("mtype: '" + mtype + "',");
		} else {
			stringbuffer.append("mtype: 'POST',");
		}
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
		if (rowNum == null || rowNum.equals("")) {
			rowNum = "20";
		}
		stringbuffer.append(
				"rowNum:" + rowNum + ",gridComplete:function(){if(jQuery.isFunction(window.gridLayout))gridLayout(\""
						+ id + "\");" + "if (typeof(op)!=\"undefined\"){var ids = jQuery(\"#" + id + "\").getDataIDs();"
						+ "for(var i=0;i<ids.length;i++){" + "for(var o in op){" + "for(var oo in op[o]){"
						+ "var op_=\"\";" + "for(var j=0;j<op[o][oo].length-1;j++){" + "var i_qeFun=op[o][oo][j].event;"
						+ "if(i_qeFun!=undefined){" + "if(typeof i_qeFun == 'function'){" + "var rd =jQuery(\"#" + id
						+ "\").getRowData(ids[i]);" + "		if(i_qeFun(rd)||i_qeFun(rd)==undefined){"
						+ "op_=op_+\"&nbsp;&nbsp;<a href='javascript:void(0);' onclick='\"+op[o][oo][j].onclick+\"(\\\"\"+ids[i]+\"\\\")'\";"
						+ "if(op[o][oo][j].ioc){op_=op_+\" title='\"+op[o][oo][j].title+\"' >\"+op[o][oo][j].ioc+\"</a>\";}else{op_=op_+\" title='\"+op[o][oo][j].title+\"' >\"+op[o][oo][j].title+\"</a>\";"
						+

						"}" + "}}" + "}else{" + "var rd =jQuery(\"#" + id + "\").getRowData(ids[i]);"
						+ "op_=op_+\"&nbsp;&nbsp;<a href='javascript:void(0);' onclick='\"+op[o][oo][j].onclick+\"(\\\"\"+ids[i]+\"\\\")'\";"
						+ "if(op[o][oo][j].ioc){op_=op_+\" title='\"+op[o][oo][j].title+\"' >\"+op[o][oo][j].ioc+\"</a>\";}else{op_=op_+\" title='\"+op[o][oo][j].title+\"' >\"+op[o][oo][j].title+\"</a>\";"
						+

						"}" + "}" + "var act={};" + "act[oo]=op_;" + "}" + "jQuery(\"#" + id
						+ "\").jqGrid('setRowData',ids[i],act);" + "}" + "}" + "}}},");
		if (rowList != null && !rowList.equals("")) {
			stringbuffer.append("rowList:[" + rowList + "],");
		}
		if (afterInsertRow != null && !afterInsertRow.equals("")) {
			stringbuffer.append("afterInsertRow:" + afterInsertRow + ",");
		}
		if (postData != null && !postData.equals("")) {
			stringbuffer.append("postData:{" + postData + "},");
		}
		stringbuffer.append("pager: '#" + pageid + "',");
		stringbuffer.append("sortname: '" + sortname + "',");
		stringbuffer.append("multiselect: " + multiselect + ",");
		// pzj add at 20170620
		stringbuffer.append("multiboxonly: " + multiboxonly + ",");
		stringbuffer.append("beforeSelectRow: " + beforeSelectRow + ",");
		stringbuffer.append("height: " + height + ",");

		if (onSelectRow != null && !onSelectRow.equals("")) {
			stringbuffer.append("onSelectRow: function(id,status){ " + onSelectRow + "(id,status);},");
		}
		if (onSelectAll != null && !onSelectAll.equals("")) {
			stringbuffer.append("onSelectAll: function(ids,status){ " + onSelectAll + "(ids,status);},");
		}
		if (ondblClickRow != null && !ondblClickRow.equals("")) {
			stringbuffer.append("ondblClickRow: function(id){ " + ondblClickRow + "(id);},");
		}
		if (autowidth != null && !autowidth.equals("")) {
			stringbuffer.append("autowidth: " + autowidth + ",");
		}
		if (autoheight != null && !autoheight.equals("")) {
			stringbuffer.append("autoheight: " + autoheight + ",");
		}
		if (loadComplete != null && !loadComplete.equals("")) {
			stringbuffer.append("loadComplete: function(xhr){ " + loadComplete + "(xhr);},");
		}
		if (hidegrid != null && !hidegrid.equals("")) {
			stringbuffer.append("hidegrid:" + hidegrid + ",");
		}
		stringbuffer.append("viewrecords: true,");
		stringbuffer.append("onPaging: function(pgButton){");
		stringbuffer.append("records=jQuery(\"#" + id + "\").getGridParam('records');");
		stringbuffer.append("jQuery(\"#" + id + "\").setGridParam({url:\""
				+ (url.indexOf("?") > 0 ? url + "&" : url + "?") + "pgButton=\"+pgButton+\"&records=\"+records});");
		stringbuffer.append("},");
		stringbuffer.append("sortorder: \"desc\",");
		stringbuffer.append("jsonReader: {");
		stringbuffer.append("	repeatitems : false,");
		stringbuffer.append("	cell:\"\",");
		stringbuffer.append("	id: \"0\"");
		stringbuffer.append("},");
		if (name != null && !name.equals("")) {
			stringbuffer.append(
					"caption: \"" + name + "<img src='" + (this.pageContext).getServletContext().getContextPath()
							+ "/css/scol.png' width='10' height='10' id='" + id + "_setcolch'/>\",");
		}
		stringbuffer.append("editurl:'" + dourl + "',");
		stringbuffer.append("prmNames:" + prmNames + "");
		// stringbuffer.append("height: \"100%\"");
		stringbuffer.append("});");
		stringbuffer.append("jQuery(\"#" + id + "\").jqGrid('navGrid','#" + pageid + "',");
		stringbuffer.append("{");
		if (edit != null && !edit.equals("")) {
			stringbuffer.append("edit:" + edit);
		} else {
			stringbuffer.append("edit:false");
		}
		if (add != null && !add.equals("")) {
			stringbuffer.append(",add:" + add);
		} else {
			stringbuffer.append(",add:false");
		}
		if (del != null && !del.equals("")) {
			stringbuffer.append(",del:" + del);
		} else {
			stringbuffer.append(",del:false");
		}
		if (search != null && !search.equals("")) {
			stringbuffer.append(",search:" + search);
		} else {
			stringbuffer.append(",search:false");
		}
		stringbuffer.append(",refresh:true");
		stringbuffer.append("},");
		stringbuffer.append("{closeAfterEdit:true,reloadAfterSubmit:true}, ");
		stringbuffer.append("{closeAfterAdd:true,reloadAfterSubmit:true,onclickSubmit:addonclickSubmit}, ");
		stringbuffer.append(
				"{reloadAfterSubmit:true,jqModal: false,afterSubmit:function(response,postdata){var json = response.responseText;if(json!=\"\")alert(json); return [true];},");

		stringbuffer.append("closeOnEscape: true");
		if (delurl != null && !delurl.equals("")) {
			stringbuffer.append(",url:'" + delurl + "'");
		}
		stringbuffer.append("}, ");
		stringbuffer.append("{multipleSearch:true}");
		stringbuffer.append("); ");

		if (export == null || export.equals("") || export.equals("true")) {
			stringbuffer.append("jQuery(\"#" + id + "\").jqGrid('navButtonAdd','#" + pageid + "',{");
			stringbuffer.append("caption:\"\",title:\"导出excel\", ");
			stringbuffer.append("onClickButton : function () {");
			stringbuffer.append("var cl=jQuery(\"#" + id + "\").getGridParam(\"colModel\");");
			stringbuffer.append("var headermate=\"\";");
			stringbuffer.append("for(var i=0;i<cl.length;i++){");
			stringbuffer.append("if(i==cl.length-1){");
			stringbuffer.append("headermate=headermate+cl[i].name+\"@\"+cl[i].hidden;");
			stringbuffer.append("}else{");
			stringbuffer.append("headermate=headermate+cl[i].name+\"@\"+cl[i].hidden+\",\";");
			stringbuffer.append("}");
			stringbuffer.append("}");
			stringbuffer
					.append("modalDialogconfim(\"confim_excel_" + id + "\",\"请选择导出数据页\",[{name:\"当前页\",fn:function(){");
			stringbuffer.append("jQuery(\"#" + id + "\").excelExport({url:'"
					+ (url.indexOf("?") > 0 ? url + "&" : url + "?") + "export=excel&header='+jQuery(\"#" + id
					+ "\").getGridParam(\"colNames\")+\"&headermate=\"+headermate});");
			stringbuffer.append("closeDialog(\"confim_excel_" + id + "\");}},{name:\"全部\",fn:function(){");
			stringbuffer.append("jQuery(\"#" + id + "\").excelExport({url:'"
					+ (url.indexOf("?") > 0 ? url + "&" : url + "?") + "export=allexcel&header='+jQuery(\"#" + id
					+ "\").getGridParam(\"colNames\")+\"&headermate=\"+headermate});");
			stringbuffer.append("closeDialog(\"confim_excel_" + id + "\");}}]);");
			stringbuffer.append("} ");
			stringbuffer.append(" });");
		}

		stringbuffer.append("});");
		stringbuffer.append(" function addonclickSubmit(params, postdata) {");
		stringbuffer.append("postdata['id']='';");
		stringbuffer.append("return postdata;");
		stringbuffer.append(" };");
		// stringbuffer.append("jQuery(document).ready(function(){");
		// stringbuffer.append("jQuery(\"#"+id+"_setcolch\").attr('style','cursor:pointer');");
		// stringbuffer.append("jQuery(\"#"+id+"_setcolch\").click(function(){");
		// stringbuffer.append("jQuery(\"#"+id+"\").setColumns({top:20,colnameview:false,width:140,height:270,dataheight:200});");
		// stringbuffer.append("return false;");
		// stringbuffer.append("});");
		// stringbuffer.append("})");
		stringbuffer.append("</script>");
		JspWriter jw = this.pageContext.getOut();
		stringbuffer
				.append("<div style=\"float:left;width:100%\" id=\"div_" + id + "\"><table id=\"" + id + "\"></table>");
		stringbuffer.append("<div id=\"" + pageid + "\"></div></div>");
		try {
			jw.write(stringbuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EVAL_PAGE;

	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public void setRowList(String rowList) {
		this.rowList = rowList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPrmNames(String prmNames) {
		this.prmNames = prmNames;
	}

	public void setDourl(String dourl) {
		this.dourl = dourl;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public void setMultiselect(String multiselect) {
		this.multiselect = multiselect;
	}

	public void setOndblClickRow(String ondblClickRow) {
		this.ondblClickRow = ondblClickRow;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setHidegrid(String hidegrid) {
		this.hidegrid = hidegrid;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public void setDelurl(String delurl) {
		this.delurl = delurl;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public void setAfterInsertRow(String afterInsertRow) {
		this.afterInsertRow = afterInsertRow;
	}

	public void setOnSelectRow(String onSelectRow) {
		this.onSelectRow = onSelectRow;
	}

	public void setOnSelectAll(String onSelectAll) {
		this.onSelectAll = onSelectAll;
	}

}
