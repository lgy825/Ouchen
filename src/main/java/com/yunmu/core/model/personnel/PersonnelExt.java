package com.yunmu.core.model.personnel;

import java.io.Serializable;
import java.util.Date;

public class PersonnelExt implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.company_code
     *
     * @mbggenerated
     */
    private String companyCode;

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_name
     *
     * @mbggenerated
     */
    private String personnelName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_age
     *
     * @mbggenerated
     */
    private Integer personnelAge;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_sex
     *
     * @mbggenerated
     */
    private Integer personnelSex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_id_card
     *
     * @mbggenerated
     */
    private String personnelIdCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_tel
     *
     * @mbggenerated
     */
    private String personnelTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_email
     *
     * @mbggenerated
     */
    private String personnelEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_addr
     *
     * @mbggenerated
     */
    private String personnelAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.personnel_status
     *
     * @mbggenerated
     */
    private Integer personnelStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.joined_date
     *
     * @mbggenerated
     */
    private Date joinedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_personnel.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_personnel
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.id
     *
     * @return the value of yunmu_personnel.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.id
     *
     * @param id the value for yunmu_personnel.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.company_code
     *
     * @return the value of yunmu_personnel.company_code
     *
     * @mbggenerated
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.company_code
     *
     * @param companyCode the value for yunmu_personnel.company_code
     *
     * @mbggenerated
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_name
     *
     * @return the value of yunmu_personnel.personnel_name
     *
     * @mbggenerated
     */
    public String getPersonnelName() {
        return personnelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_name
     *
     * @param personnelName the value for yunmu_personnel.personnel_name
     *
     * @mbggenerated
     */
    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName == null ? null : personnelName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_age
     *
     * @return the value of yunmu_personnel.personnel_age
     *
     * @mbggenerated
     */
    public Integer getPersonnelAge() {
        return personnelAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_age
     *
     * @param personnelAge the value for yunmu_personnel.personnel_age
     *
     * @mbggenerated
     */
    public void setPersonnelAge(Integer personnelAge) {
        this.personnelAge = personnelAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_sex
     *
     * @return the value of yunmu_personnel.personnel_sex
     *
     * @mbggenerated
     */
    public Integer getPersonnelSex() {
        return personnelSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_sex
     *
     * @param personnelSex the value for yunmu_personnel.personnel_sex
     *
     * @mbggenerated
     */
    public void setPersonnelSex(Integer personnelSex) {
        this.personnelSex = personnelSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_id_card
     *
     * @return the value of yunmu_personnel.personnel_id_card
     *
     * @mbggenerated
     */
    public String getPersonnelIdCard() {
        return personnelIdCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_id_card
     *
     * @param personnelIdCard the value for yunmu_personnel.personnel_id_card
     *
     * @mbggenerated
     */
    public void setPersonnelIdCard(String personnelIdCard) {
        this.personnelIdCard = personnelIdCard == null ? null : personnelIdCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_tel
     *
     * @return the value of yunmu_personnel.personnel_tel
     *
     * @mbggenerated
     */
    public String getPersonnelTel() {
        return personnelTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_tel
     *
     * @param personnelTel the value for yunmu_personnel.personnel_tel
     *
     * @mbggenerated
     */
    public void setPersonnelTel(String personnelTel) {
        this.personnelTel = personnelTel == null ? null : personnelTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_email
     *
     * @return the value of yunmu_personnel.personnel_email
     *
     * @mbggenerated
     */
    public String getPersonnelEmail() {
        return personnelEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_email
     *
     * @param personnelEmail the value for yunmu_personnel.personnel_email
     *
     * @mbggenerated
     */
    public void setPersonnelEmail(String personnelEmail) {
        this.personnelEmail = personnelEmail == null ? null : personnelEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_addr
     *
     * @return the value of yunmu_personnel.personnel_addr
     *
     * @mbggenerated
     */
    public String getPersonnelAddr() {
        return personnelAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_addr
     *
     * @param personnelAddr the value for yunmu_personnel.personnel_addr
     *
     * @mbggenerated
     */
    public void setPersonnelAddr(String personnelAddr) {
        this.personnelAddr = personnelAddr == null ? null : personnelAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.personnel_status
     *
     * @return the value of yunmu_personnel.personnel_status
     *
     * @mbggenerated
     */
    public Integer getPersonnelStatus() {
        return personnelStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.personnel_status
     *
     * @param personnelStatus the value for yunmu_personnel.personnel_status
     *
     * @mbggenerated
     */
    public void setPersonnelStatus(Integer personnelStatus) {
        this.personnelStatus = personnelStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.joined_date
     *
     * @return the value of yunmu_personnel.joined_date
     *
     * @mbggenerated
     */
    public Date getJoinedDate() {
        return joinedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.joined_date
     *
     * @param joinedDate the value for yunmu_personnel.joined_date
     *
     * @mbggenerated
     */
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.create_time
     *
     * @return the value of yunmu_personnel.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.create_time
     *
     * @param createTime the value for yunmu_personnel.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.create_by
     *
     * @return the value of yunmu_personnel.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.create_by
     *
     * @param createBy the value for yunmu_personnel.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.update_time
     *
     * @return the value of yunmu_personnel.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.update_time
     *
     * @param updateTime the value for yunmu_personnel.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.update_by
     *
     * @return the value of yunmu_personnel.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.update_by
     *
     * @param updateBy the value for yunmu_personnel.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_personnel.del_flag
     *
     * @return the value of yunmu_personnel.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_personnel.del_flag
     *
     * @param delFlag the value for yunmu_personnel.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}