package com.yunmu.core.model.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Pay implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.pay_id
     *
     * @mbggenerated
     */
    private String payId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.pay_name
     *
     * @mbggenerated
     */
    private String payName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.pay_amount
     *
     * @mbggenerated
     */
    private BigDecimal payAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.pay_desc
     *
     * @mbggenerated
     */
    private String payDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.pay_type
     *
     * @mbggenerated
     */
    private Integer payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_pay.project_id
     *
     * @mbggenerated
     */
    private String projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_pay
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.pay_id
     *
     * @return the value of yunmu_pay.pay_id
     *
     * @mbggenerated
     */
    public String getPayId() {
        return payId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.pay_id
     *
     * @param payId the value for yunmu_pay.pay_id
     *
     * @mbggenerated
     */
    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.pay_name
     *
     * @return the value of yunmu_pay.pay_name
     *
     * @mbggenerated
     */
    public String getPayName() {
        return payName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.pay_name
     *
     * @param payName the value for yunmu_pay.pay_name
     *
     * @mbggenerated
     */
    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.pay_amount
     *
     * @return the value of yunmu_pay.pay_amount
     *
     * @mbggenerated
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.pay_desc
     *
     * @return the value of yunmu_pay.pay_desc
     *
     * @mbggenerated
     */
    public String getPayDesc() {
        return payDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.pay_desc
     *
     * @param payDesc the value for yunmu_pay.pay_desc
     *
     * @mbggenerated
     */
    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.pay_type
     *
     * @return the value of yunmu_pay.pay_type
     *
     * @mbggenerated
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.pay_type
     *
     * @param payType the value for yunmu_pay.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.create_by
     *
     * @return the value of yunmu_pay.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.create_by
     *
     * @param createBy the value for yunmu_pay.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.update_by
     *
     * @return the value of yunmu_pay.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.update_by
     *
     * @param updateBy the value for yunmu_pay.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.create_time
     *
     * @return the value of yunmu_pay.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.create_time
     *
     * @param createTime the value for yunmu_pay.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.update_time
     *
     * @return the value of yunmu_pay.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.update_time
     *
     * @param updateTime the value for yunmu_pay.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.del_flag
     *
     * @return the value of yunmu_pay.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.del_flag
     *
     * @param delFlag the value for yunmu_pay.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_pay.project_id
     *
     * @return the value of yunmu_pay.project_id
     *
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_pay.project_id
     *
     * @param projectId the value for yunmu_pay.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
}