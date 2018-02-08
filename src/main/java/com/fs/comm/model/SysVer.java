package com.fs.comm.model;

import java.util.Date;

public class SysVer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.allow_group
     *
     * @mbggenerated
     */
    private String allowGroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_ver.is_delete
     *
     * @mbggenerated
     */
    private String isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.id
     *
     * @return the value of t_sys_ver.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.id
     *
     * @param id the value for t_sys_ver.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.code
     *
     * @return the value of t_sys_ver.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.code
     *
     * @param code the value for t_sys_ver.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.name
     *
     * @return the value of t_sys_ver.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.name
     *
     * @param name the value for t_sys_ver.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.allow_group
     *
     * @return the value of t_sys_ver.allow_group
     *
     * @mbggenerated
     */
    public String getAllowGroup() {
        return allowGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.allow_group
     *
     * @param allowGroup the value for t_sys_ver.allow_group
     *
     * @mbggenerated
     */
    public void setAllowGroup(String allowGroup) {
        this.allowGroup = allowGroup == null ? null : allowGroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.create_time
     *
     * @return the value of t_sys_ver.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.create_time
     *
     * @param createTime the value for t_sys_ver.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_ver.is_delete
     *
     * @return the value of t_sys_ver.is_delete
     *
     * @mbggenerated
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_ver.is_delete
     *
     * @param isDelete the value for t_sys_ver.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}