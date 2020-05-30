package com.yunmu.core.model.sys;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.role_name
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.role_en_name
     *
     * @mbggenerated
     */
    private String roleEnName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.role_type
     *
     * @mbggenerated
     */
    private Integer roleType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.role_desc
     *
     * @mbggenerated
     */
    private String roleDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_role.company_code
     *
     * @mbggenerated
     */
    private String companyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_sys_role
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.id
     *
     * @return the value of yunmu_sys_role.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.id
     *
     * @param id the value for yunmu_sys_role.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.role_name
     *
     * @return the value of yunmu_sys_role.role_name
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.role_name
     *
     * @param roleName the value for yunmu_sys_role.role_name
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.role_en_name
     *
     * @return the value of yunmu_sys_role.role_en_name
     *
     * @mbggenerated
     */
    public String getRoleEnName() {
        return roleEnName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.role_en_name
     *
     * @param roleEnName the value for yunmu_sys_role.role_en_name
     *
     * @mbggenerated
     */
    public void setRoleEnName(String roleEnName) {
        this.roleEnName = roleEnName == null ? null : roleEnName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.role_type
     *
     * @return the value of yunmu_sys_role.role_type
     *
     * @mbggenerated
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.role_type
     *
     * @param roleType the value for yunmu_sys_role.role_type
     *
     * @mbggenerated
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.role_desc
     *
     * @return the value of yunmu_sys_role.role_desc
     *
     * @mbggenerated
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.role_desc
     *
     * @param roleDesc the value for yunmu_sys_role.role_desc
     *
     * @mbggenerated
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.create_by
     *
     * @return the value of yunmu_sys_role.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.create_by
     *
     * @param createBy the value for yunmu_sys_role.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.create_time
     *
     * @return the value of yunmu_sys_role.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.create_time
     *
     * @param createTime the value for yunmu_sys_role.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.update_by
     *
     * @return the value of yunmu_sys_role.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.update_by
     *
     * @param updateBy the value for yunmu_sys_role.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.update_time
     *
     * @return the value of yunmu_sys_role.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.update_time
     *
     * @param updateTime the value for yunmu_sys_role.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.del_flag
     *
     * @return the value of yunmu_sys_role.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.del_flag
     *
     * @param delFlag the value for yunmu_sys_role.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.status
     *
     * @return the value of yunmu_sys_role.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.status
     *
     * @param status the value for yunmu_sys_role.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_role.company_code
     *
     * @return the value of yunmu_sys_role.company_code
     *
     * @mbggenerated
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_role.company_code
     *
     * @param companyCode the value for yunmu_sys_role.company_code
     *
     * @mbggenerated
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }
}