package com.ouchen.core.model.hourse;

import java.io.Serializable;
import java.util.Date;

public class HourseExt implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.owner_code
     *
     * @mbggenerated
     */
    private String ownerCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.type_code
     *
     * @mbggenerated
     */
    private String typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.area_code
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.hourse_number
     *
     * @mbggenerated
     */
    private String hourseNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.hourse_desc
     *
     * @mbggenerated
     */
    private String hourseDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.hourse_area
     *
     * @mbggenerated
     */
    private String hourseArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_hourse.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    private String ownerName;
    private String typeName;

    private String projectId;
    private String projectName;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.id
     *
     * @return the value of yunmu_hourse.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.id
     *
     * @param id the value for yunmu_hourse.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.owner_code
     *
     * @return the value of yunmu_hourse.owner_code
     *
     * @mbggenerated
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.owner_code
     *
     * @param ownerCode the value for yunmu_hourse.owner_code
     *
     * @mbggenerated
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.type_code
     *
     * @return the value of yunmu_hourse.type_code
     *
     * @mbggenerated
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.type_code
     *
     * @param typeCode the value for yunmu_hourse.type_code
     *
     * @mbggenerated
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.area_code
     *
     * @return the value of yunmu_hourse.area_code
     *
     * @mbggenerated
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.area_code
     *
     * @param areaCode the value for yunmu_hourse.area_code
     *
     * @mbggenerated
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.hourse_number
     *
     * @return the value of yunmu_hourse.hourse_number
     *
     * @mbggenerated
     */
    public String getHourseNumber() {
        return hourseNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.hourse_number
     *
     * @param hourseNumber the value for yunmu_hourse.hourse_number
     *
     * @mbggenerated
     */
    public void setHourseNumber(String hourseNumber) {
        this.hourseNumber = hourseNumber == null ? null : hourseNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.hourse_desc
     *
     * @return the value of yunmu_hourse.hourse_desc
     *
     * @mbggenerated
     */
    public String getHourseDesc() {
        return hourseDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.hourse_desc
     *
     * @param hourseDesc the value for yunmu_hourse.hourse_desc
     *
     * @mbggenerated
     */
    public void setHourseDesc(String hourseDesc) {
        this.hourseDesc = hourseDesc == null ? null : hourseDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.hourse_area
     *
     * @return the value of yunmu_hourse.hourse_area
     *
     * @mbggenerated
     */
    public String getHourseArea() {
        return hourseArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.hourse_area
     *
     * @param hourseArea the value for yunmu_hourse.hourse_area
     *
     * @mbggenerated
     */
    public void setHourseArea(String hourseArea) {
        this.hourseArea = hourseArea == null ? null : hourseArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.create_by
     *
     * @return the value of yunmu_hourse.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.create_by
     *
     * @param createBy the value for yunmu_hourse.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.create_time
     *
     * @return the value of yunmu_hourse.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.create_time
     *
     * @param createTime the value for yunmu_hourse.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.update_by
     *
     * @return the value of yunmu_hourse.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.update_by
     *
     * @param updateBy the value for yunmu_hourse.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_hourse.update_time
     *
     * @return the value of yunmu_hourse.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_hourse.update_time
     *
     * @param updateTime the value for yunmu_hourse.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}