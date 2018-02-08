package com.fs.comm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * 栏目
 * 
 * @author pzj
 *
 */
@Data
public class Column {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	public Long getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public Long getDealerid() {
		return dealerid;
	}
	public void setDealerid(Long dealerid) {
		this.dealerid = dealerid;
	}
	public String getDealername() {
		return dealername;
	}
	public void setDealername(String dealername) {
		this.dealername = dealername;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShowPicture() {
		return showPicture;
	}
	public void setShowPicture(String showPicture) {
		this.showPicture = showPicture;
	}
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	public String getUiModel() {
		return uiModel;
	}
	public void setUiModel(String uiModel) {
		this.uiModel = uiModel;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescribeContext() {
		return describeContext;
	}
	public void setDescribeContext(String describeContext) {
		this.describeContext = describeContext;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getCnum() {
		return cnum;
	}
	public void setCnum(Integer cnum) {
		this.cnum = cnum;
	}
	public Column getParentColumn() {
		return parentColumn;
	}
	public void setParentColumn(Column parentColumn) {
		this.parentColumn = parentColumn;
	}
	public Set<Column> getChildDepartment() {
		return childDepartment;
	}
	public void setChildDepartment(Set<Column> childDepartment) {
		this.childDepartment = childDepartment;
	}
	private Integer id;
	private Long companyid;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	private String classify;
	private String name;
	private Integer number;
	private String type;
	private String status;
	private String showPicture;
	private String play;
	private String priority;
	private String showType;
	private String code;
	private String operation;
	private String identifying;
	private String uiModel;
	private String label;
	private String describeContext;
	private Date createTime;
	private String isDelete;
	private Integer parentId;
	private String levels;
	private String path;
	
	private Integer cnum;// 子集合数量
	private Column parentColumn;
	private Set<Column> childDepartment = new HashSet<Column>();

}