package com.ouchen.core.model.project;

import java.io.Serializable;
import java.util.Date;

public class ProjectExt implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.project_name
     *
     * @mbggenerated
     */
    private String projectName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.project_addr
     *
     * @mbggenerated
     */
    private String projectAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.project_tel
     *
     * @mbggenerated
     */
    private String projectTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.project_type
     *
     * @mbggenerated
     */
    private Integer projectType;

    private String TypeCode;

    private Integer status;

    private String companyCode;

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.project_desc
     *
     * @mbggenerated
     */
    private String projectDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_project.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    private String typaName;

    public String getTypaName() {
        return typaName;
    }

    public void setTypaName(String typaName) {
        this.typaName = typaName;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_project
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.id
     *
     * @return the value of yunmu_project.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.id
     *
     * @param id the value for yunmu_project.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.project_name
     *
     * @return the value of yunmu_project.project_name
     *
     * @mbggenerated
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.project_name
     *
     * @param projectName the value for yunmu_project.project_name
     *
     * @mbggenerated
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.project_addr
     *
     * @return the value of yunmu_project.project_addr
     *
     * @mbggenerated
     */
    public String getProjectAddr() {
        return projectAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.project_addr
     *
     * @param projectAddr the value for yunmu_project.project_addr
     *
     * @mbggenerated
     */
    public void setProjectAddr(String projectAddr) {
        this.projectAddr = projectAddr == null ? null : projectAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.project_tel
     *
     * @return the value of yunmu_project.project_tel
     *
     * @mbggenerated
     */
    public String getProjectTel() {
        return projectTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.project_tel
     *
     * @param projectTel the value for yunmu_project.project_tel
     *
     * @mbggenerated
     */
    public void setProjectTel(String projectTel) {
        this.projectTel = projectTel == null ? null : projectTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.project_type
     *
     * @return the value of yunmu_project.project_type
     *
     * @mbggenerated
     */
    public Integer getProjectType() {
        return projectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.project_type
     *
     * @param projectType the value for yunmu_project.project_type
     *
     * @mbggenerated
     */
    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.project_desc
     *
     * @return the value of yunmu_project.project_desc
     *
     * @mbggenerated
     */
    public String getProjectDesc() {
        return projectDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.project_desc
     *
     * @param projectDesc the value for yunmu_project.project_desc
     *
     * @mbggenerated
     */
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.create_by
     *
     * @return the value of yunmu_project.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.create_by
     *
     * @param createBy the value for yunmu_project.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.create_time
     *
     * @return the value of yunmu_project.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.create_time
     *
     * @param createTime the value for yunmu_project.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.update_by
     *
     * @return the value of yunmu_project.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.update_by
     *
     * @param updateBy the value for yunmu_project.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.update_time
     *
     * @return the value of yunmu_project.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.update_time
     *
     * @param updateTime the value for yunmu_project.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_project.del_flag
     *
     * @return the value of yunmu_project.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_project.del_flag
     *
     * @param delFlag the value for yunmu_project.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}