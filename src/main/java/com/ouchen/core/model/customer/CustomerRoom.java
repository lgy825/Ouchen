package com.ouchen.core.model.customer;

import java.io.Serializable;
import java.util.Date;

public class CustomerRoom implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.project_id
     *
     * @mbggenerated
     */
    private String projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.customer_code
     *
     * @mbggenerated
     */
    private String customerCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.type_code
     *
     * @mbggenerated
     */
    private String typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.room_serial_code
     *
     * @mbggenerated
     */
    private String roomSerialCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.room_number
     *
     * @mbggenerated
     */
    private String roomNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.room_desc
     *
     * @mbggenerated
     */
    private String roomDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.room_area
     *
     * @mbggenerated
     */
    private String roomArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_customer_room.room_addr
     *
     * @mbggenerated
     */
    private String roomAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_customer_room
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.id
     *
     * @return the value of yunmu_customer_room.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.id
     *
     * @param id the value for yunmu_customer_room.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.project_id
     *
     * @return the value of yunmu_customer_room.project_id
     *
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.project_id
     *
     * @param projectId the value for yunmu_customer_room.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.customer_code
     *
     * @return the value of yunmu_customer_room.customer_code
     *
     * @mbggenerated
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.customer_code
     *
     * @param customerCode the value for yunmu_customer_room.customer_code
     *
     * @mbggenerated
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.type_code
     *
     * @return the value of yunmu_customer_room.type_code
     *
     * @mbggenerated
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.type_code
     *
     * @param typeCode the value for yunmu_customer_room.type_code
     *
     * @mbggenerated
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.room_serial_code
     *
     * @return the value of yunmu_customer_room.room_serial_code
     *
     * @mbggenerated
     */
    public String getRoomSerialCode() {
        return roomSerialCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.room_serial_code
     *
     * @param roomSerialCode the value for yunmu_customer_room.room_serial_code
     *
     * @mbggenerated
     */
    public void setRoomSerialCode(String roomSerialCode) {
        this.roomSerialCode = roomSerialCode == null ? null : roomSerialCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.room_number
     *
     * @return the value of yunmu_customer_room.room_number
     *
     * @mbggenerated
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.room_number
     *
     * @param roomNumber the value for yunmu_customer_room.room_number
     *
     * @mbggenerated
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.room_desc
     *
     * @return the value of yunmu_customer_room.room_desc
     *
     * @mbggenerated
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.room_desc
     *
     * @param roomDesc the value for yunmu_customer_room.room_desc
     *
     * @mbggenerated
     */
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.room_area
     *
     * @return the value of yunmu_customer_room.room_area
     *
     * @mbggenerated
     */
    public String getRoomArea() {
        return roomArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.room_area
     *
     * @param roomArea the value for yunmu_customer_room.room_area
     *
     * @mbggenerated
     */
    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea == null ? null : roomArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.create_by
     *
     * @return the value of yunmu_customer_room.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.create_by
     *
     * @param createBy the value for yunmu_customer_room.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.create_time
     *
     * @return the value of yunmu_customer_room.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.create_time
     *
     * @param createTime the value for yunmu_customer_room.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.update_by
     *
     * @return the value of yunmu_customer_room.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.update_by
     *
     * @param updateBy the value for yunmu_customer_room.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.update_time
     *
     * @return the value of yunmu_customer_room.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.update_time
     *
     * @param updateTime the value for yunmu_customer_room.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.del_flag
     *
     * @return the value of yunmu_customer_room.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.del_flag
     *
     * @param delFlag the value for yunmu_customer_room.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.status
     *
     * @return the value of yunmu_customer_room.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.status
     *
     * @param status the value for yunmu_customer_room.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_customer_room.room_addr
     *
     * @return the value of yunmu_customer_room.room_addr
     *
     * @mbggenerated
     */
    public String getRoomAddr() {
        return roomAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_customer_room.room_addr
     *
     * @param roomAddr the value for yunmu_customer_room.room_addr
     *
     * @mbggenerated
     */
    public void setRoomAddr(String roomAddr) {
        this.roomAddr = roomAddr == null ? null : roomAddr.trim();
    }
}