package com.framework.excelTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类，对配置文件中Table的抽象，对应数据库中的表
 *
 * @author coast <coastcdl@hotmail.com>
 * @version $Id$
 * @license
 * @package com.tools.excel
 * @since 1.0
 */
public class Entity {
    private String name = "";//表名称
    private String dealRepeat = "ignore";//重复数据处理方式（默认为ignore忽略，可设置 update, delete）
    private List<Property> propertys = null;//属性集合（每个实体对应多个Property）
    private List<String> excludedColumns = null;//排除字段（也就是指明哪些字段不用导入）
    private String emptycol;//判断空行字段
    public Entity() {
        propertys = new ArrayList<Property>();
        excludedColumns = new ArrayList<String>();
    }

    public Entity(String name) {
        this.name = name;
        excludedColumns = new ArrayList<String>();
        propertys = new ArrayList<Property>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDealRepeat() {
        return dealRepeat;
    }

    public void setDealRepeat(String dealRepeat) {
        this.dealRepeat = dealRepeat;
    }

    public List<Property> getPropertys() {
        return propertys;
    }

    public void setPropertys(List<Property> propertys) {
        this.propertys = propertys;
    }

    public List<String> getExcludedColumns() {
        return excludedColumns;
    }

    public void setExcludedColumns(List<String> excludedColumns) {
        this.excludedColumns = excludedColumns;
    }

	public String getEmptycol() {
		return emptycol;
	}

	public void setEmptycol(String emptycol) {
		this.emptycol = emptycol;
	}
    
}
