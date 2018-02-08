package com.framework.excelTools;

/**
 * 代码表类，是对CodeTable的抽象 (系统中的各种基础数据表)
 *
 * @author coast <coastcdl@hotmail.com>
 * @version $Id$
 * @copyright Copyright &copy; 2008 杭州源中通信技术有限公司
 * @license
 * @package com.tools.excel
 * @since 1.0
 */
public class DictionaryEntity {
    private String name = "";//代码表名称，对应数据库中主表名称
    private String primaryKey = "";//代码表主键，也就是字表的对应外键
    private String referenceColumn = "";//代码字段关联名称列，也就是我们导入时所依据的excel对应值
    private String condition = "";//相关条件

    public DictionaryEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getReferenceColumn() {
        return referenceColumn;
    }

    public void setReferenceColumn(String referenceColumn) {
        this.referenceColumn = referenceColumn;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
