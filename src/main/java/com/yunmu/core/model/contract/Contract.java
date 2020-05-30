package com.yunmu.core.model.contract;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.project_id
     *
     * @mbggenerated
     */
    private String projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.room_code
     *
     * @mbggenerated
     */
    private String roomCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.customer_code
     *
     * @mbggenerated
     */
    private String customerCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.personnel_code
     *
     * @mbggenerated
     */
    private String personnelCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_code
     *
     * @mbggenerated
     */
    private String contractCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_type
     *
     * @mbggenerated
     */
    private Integer contractType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_status
     *
     * @mbggenerated
     */
    private Integer contractStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.operative_way
     *
     * @mbggenerated
     */
    private Integer operativeWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_start_time
     *
     * @mbggenerated
     */
    private Date contractStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_end_time
     *
     * @mbggenerated
     */
    private Date contractEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_desc
     *
     * @mbggenerated
     */
    private String contractDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.rent_amount
     *
     * @mbggenerated
     */
    private String rentAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.rent_free_start_time
     *
     * @mbggenerated
     */
    private Date rentFreeStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.rent_free_end_time
     *
     * @mbggenerated
     */
    private Date rentFreeEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.rent_increase_way
     *
     * @mbggenerated
     */
    private String rentIncreaseWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.pay_type
     *
     * @mbggenerated
     */
    private Integer payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.contract_time
     *
     * @mbggenerated
     */
    private Date contractTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_contract.company_code
     *
     * @mbggenerated
     */
    private String companyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_contract
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.id
     *
     * @return the value of yunmu_contract.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.id
     *
     * @param id the value for yunmu_contract.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.project_id
     *
     * @return the value of yunmu_contract.project_id
     *
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.project_id
     *
     * @param projectId the value for yunmu_contract.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.room_code
     *
     * @return the value of yunmu_contract.room_code
     *
     * @mbggenerated
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.room_code
     *
     * @param roomCode the value for yunmu_contract.room_code
     *
     * @mbggenerated
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.customer_code
     *
     * @return the value of yunmu_contract.customer_code
     *
     * @mbggenerated
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.customer_code
     *
     * @param customerCode the value for yunmu_contract.customer_code
     *
     * @mbggenerated
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.personnel_code
     *
     * @return the value of yunmu_contract.personnel_code
     *
     * @mbggenerated
     */
    public String getPersonnelCode() {
        return personnelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.personnel_code
     *
     * @param personnelCode the value for yunmu_contract.personnel_code
     *
     * @mbggenerated
     */
    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode == null ? null : personnelCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_code
     *
     * @return the value of yunmu_contract.contract_code
     *
     * @mbggenerated
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_code
     *
     * @param contractCode the value for yunmu_contract.contract_code
     *
     * @mbggenerated
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_type
     *
     * @return the value of yunmu_contract.contract_type
     *
     * @mbggenerated
     */
    public Integer getContractType() {
        return contractType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_type
     *
     * @param contractType the value for yunmu_contract.contract_type
     *
     * @mbggenerated
     */
    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_status
     *
     * @return the value of yunmu_contract.contract_status
     *
     * @mbggenerated
     */
    public Integer getContractStatus() {
        return contractStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_status
     *
     * @param contractStatus the value for yunmu_contract.contract_status
     *
     * @mbggenerated
     */
    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.operative_way
     *
     * @return the value of yunmu_contract.operative_way
     *
     * @mbggenerated
     */
    public Integer getOperativeWay() {
        return operativeWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.operative_way
     *
     * @param operativeWay the value for yunmu_contract.operative_way
     *
     * @mbggenerated
     */
    public void setOperativeWay(Integer operativeWay) {
        this.operativeWay = operativeWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_start_time
     *
     * @return the value of yunmu_contract.contract_start_time
     *
     * @mbggenerated
     */
    public Date getContractStartTime() {
        return contractStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_start_time
     *
     * @param contractStartTime the value for yunmu_contract.contract_start_time
     *
     * @mbggenerated
     */
    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_end_time
     *
     * @return the value of yunmu_contract.contract_end_time
     *
     * @mbggenerated
     */
    public Date getContractEndTime() {
        return contractEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_end_time
     *
     * @param contractEndTime the value for yunmu_contract.contract_end_time
     *
     * @mbggenerated
     */
    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.create_time
     *
     * @return the value of yunmu_contract.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.create_time
     *
     * @param createTime the value for yunmu_contract.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.create_by
     *
     * @return the value of yunmu_contract.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.create_by
     *
     * @param createBy the value for yunmu_contract.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.update_time
     *
     * @return the value of yunmu_contract.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.update_time
     *
     * @param updateTime the value for yunmu_contract.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.update_by
     *
     * @return the value of yunmu_contract.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.update_by
     *
     * @param updateBy the value for yunmu_contract.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_desc
     *
     * @return the value of yunmu_contract.contract_desc
     *
     * @mbggenerated
     */
    public String getContractDesc() {
        return contractDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_desc
     *
     * @param contractDesc the value for yunmu_contract.contract_desc
     *
     * @mbggenerated
     */
    public void setContractDesc(String contractDesc) {
        this.contractDesc = contractDesc == null ? null : contractDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.rent_amount
     *
     * @return the value of yunmu_contract.rent_amount
     *
     * @mbggenerated
     */
    public String getRentAmount() {
        return rentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.rent_amount
     *
     * @param rentAmount the value for yunmu_contract.rent_amount
     *
     * @mbggenerated
     */
    public void setRentAmount(String rentAmount) {
        this.rentAmount = rentAmount == null ? null : rentAmount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.rent_free_start_time
     *
     * @return the value of yunmu_contract.rent_free_start_time
     *
     * @mbggenerated
     */
    public Date getRentFreeStartTime() {
        return rentFreeStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.rent_free_start_time
     *
     * @param rentFreeStartTime the value for yunmu_contract.rent_free_start_time
     *
     * @mbggenerated
     */
    public void setRentFreeStartTime(Date rentFreeStartTime) {
        this.rentFreeStartTime = rentFreeStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.rent_free_end_time
     *
     * @return the value of yunmu_contract.rent_free_end_time
     *
     * @mbggenerated
     */
    public Date getRentFreeEndTime() {
        return rentFreeEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.rent_free_end_time
     *
     * @param rentFreeEndTime the value for yunmu_contract.rent_free_end_time
     *
     * @mbggenerated
     */
    public void setRentFreeEndTime(Date rentFreeEndTime) {
        this.rentFreeEndTime = rentFreeEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.rent_increase_way
     *
     * @return the value of yunmu_contract.rent_increase_way
     *
     * @mbggenerated
     */
    public String getRentIncreaseWay() {
        return rentIncreaseWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.rent_increase_way
     *
     * @param rentIncreaseWay the value for yunmu_contract.rent_increase_way
     *
     * @mbggenerated
     */
    public void setRentIncreaseWay(String rentIncreaseWay) {
        this.rentIncreaseWay = rentIncreaseWay == null ? null : rentIncreaseWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.pay_type
     *
     * @return the value of yunmu_contract.pay_type
     *
     * @mbggenerated
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.pay_type
     *
     * @param payType the value for yunmu_contract.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.del_flag
     *
     * @return the value of yunmu_contract.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.del_flag
     *
     * @param delFlag the value for yunmu_contract.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.contract_time
     *
     * @return the value of yunmu_contract.contract_time
     *
     * @mbggenerated
     */
    public Date getContractTime() {
        return contractTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.contract_time
     *
     * @param contractTime the value for yunmu_contract.contract_time
     *
     * @mbggenerated
     */
    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_contract.company_code
     *
     * @return the value of yunmu_contract.company_code
     *
     * @mbggenerated
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_contract.company_code
     *
     * @param companyCode the value for yunmu_contract.company_code
     *
     * @mbggenerated
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }
}