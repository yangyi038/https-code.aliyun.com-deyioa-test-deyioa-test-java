package com.framework.excelTools;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

/**
 * 配置文件解析
 *
 * @author coast <coastcdl@hotmail.com>
 * @version $Id$
 * @license
 * @package com.tools.excel
 * @since 1.0
 */
public class Config {

    private String endFlag = "RowBlank";//Excel结束标志  默认为空行
    private int headerIndex = 1;//列头所在行
    private int dataIndex = 2;//数据行起始位置
    private Entity entity = null;//配置类对应的实体类  
    private Document document;
    public Config(String excelConfigFullName) throws MalformedURLException {
        //读取配置文件，类初始化
        Init(excelConfigFullName);
    }

    //初始化配置对象
    private void Init(String excelConfigFullName) throws MalformedURLException {
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(new File(excelConfigFullName));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElm = document.getRootElement();
        endFlag = rootElm.attributeValue("EndTag", "RowBlank");
        headerIndex = Integer.parseInt(rootElm.attributeValue("HeaderIndex", "1"));
        dataIndex = Integer.parseInt(rootElm.attributeValue("DataIndex", "2"));
        // TODO
        Element table = (Element) document.selectSingleNode("/Config/Table");
        entity = new Entity(table.attributeValue("Name"));
        entity.setDealRepeat(table.attributeValue("DealRepeat", "ignore"));
        entity.setEmptycol(table.attributeValue("Emptycol"));
        Iterator iterator = table.elementIterator();
        if (!iterator.hasNext()) {
            // 没有列配置
            String excludedColumns = table.attributeValue("ExcludedColumns");
            if (excludedColumns!=null&&!excludedColumns.equals("")) {
                for (String col : excludedColumns.split(",")) {
                    entity.getExcludedColumns().add(col);
                }
            }
            return;
        }
        while (iterator.hasNext()) {
            Element column = (Element) iterator.next();
            Property property = new Property();
            property.setPrimaryKey(Boolean.parseBoolean(column.attributeValue("IsPrimaryKey", "false")));
            property.setColumnName(column.attributeValue("ColumnName"));
            property.setHeaderText(column.attributeValue("HeaderText", ""));
            property.setRequired(Boolean.parseBoolean(column.attributeValue("Required", "false")));
            property.setDataType(column.attributeValue("DataType", "string"));
            property.setDataLength(Integer.parseInt(column.attributeValue("DataLength", "5000")));
            property.setComment(column.attributeValue("Comment", ""));
            property.setDefaultValue(column.attributeValue("DefaultValue", ""));
            property.setSeq(column.attributeValue("SEQ", ""));
            property.setExcelNum(column.attributeValue("excelNum", ""));
            Iterator iterator_ = column.elementIterator();
            while (iterator_.hasNext()){
                // 字段对应代码表
                Element codeTable = (Element)iterator_.next();               
                if(codeTable.getName().equals("parameter")){
                	Parameter parameter=new Parameter();
                	parameter.setPtype(codeTable.attributeValue("Ptype"));
                	property.setParameter(parameter);
                }
            }
            
            entity.getPropertys().add(property);
        }
        entity.toString();
        return;
    }
    public static void main(String[] args) throws MalformedURLException {
    	
    	Config config=new Config("E:/workspace/jl_xypt/src/com/yz/webmanage/excel/person.xml");

    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public int getHeaderIndex() {
        return headerIndex;
    }

    public void setHeaderIndex(int headerIndex) {
        this.headerIndex = headerIndex;
    }

    public int getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
}
