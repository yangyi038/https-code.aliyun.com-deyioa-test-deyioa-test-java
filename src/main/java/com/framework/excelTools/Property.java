package com.framework.excelTools;

/**
 * 配置属性类，抽象了配置文件的配置属性，对应于数据库中的字段
 *
 * @author coast <coastcdl@hotmail.com>
 * @version $Id$
 * @license
 * @package com.tools.excel
 * @since 1.0
 */
public class Property {
    private boolean isPrimaryKey = false;//主键
    private String columnName = "";//数据库列名称
    private String headerText = "";//对应的excel列头名称
    private boolean required = false;//必填
    private String dataType = "string";//数据类型（默认为string类型）
    private int dataLength = 5000;//数据长度（默认为5000）
    private String defaultValue = "";//默认值(对应excel此列的值如果为空则会使用此值来导入)
    private String comment = "";//字段说明信息（非必要属性）
    private DictionaryEntity codeTalbe = null;//代码表
    private Parameter parameter = null;//参数
    private String seq = ""; //
    private String excelNum=""; //是否为总结列，自动导入有用
    private Object value;
    public Property() {

    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DictionaryEntity getCodeTalbe() {
        return codeTalbe;
    }

    public void setCodeTalbe(DictionaryEntity codeTalbe) {
        this.codeTalbe = codeTalbe;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

	public String getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
    
}
