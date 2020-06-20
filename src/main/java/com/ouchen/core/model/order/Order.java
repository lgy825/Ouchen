package com.ouchen.core.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    private String hourseCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.complete_time
     *
     * @mbggenerated
     */
    private Date completeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    private Date orderStartDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    private Date orderEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_count
     *
     * @mbggenerated
     */
    private Integer orderCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_pro_amount
     *
     * @mbggenerated
     */
    private BigDecimal orderProAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    private BigDecimal orderActAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    private BigDecimal orderRecAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_source
     *
     * @mbggenerated
     */
    private String orderSource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_status
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_way
     *
     * @mbggenerated
     */
    private String orderWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.project_id
     *
     * @mbggenerated
     */
    private String projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.is_choose
     *
     * @mbggenerated
     */
    private Integer isChoose;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.is_choose_product
     *
     * @mbggenerated
     */
    private Integer isChooseProduct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_channel
     *
     * @mbggenerated
     */
    private Integer orderChannel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_desc
     *
     * @mbggenerated
     */
    private String orderDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.id
     *
     * @return the value of yunmu_order.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.id
     *
     * @param id the value for yunmu_order.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.hourse_code
     *
     * @return the value of yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    public String getHourseCode() {
        return hourseCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.hourse_code
     *
     * @param hourseCode the value for yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    public void setHourseCode(String hourseCode) {
        this.hourseCode = hourseCode == null ? null : hourseCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.create_time
     *
     * @return the value of yunmu_order.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.create_time
     *
     * @param createTime the value for yunmu_order.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.create_by
     *
     * @return the value of yunmu_order.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.create_by
     *
     * @param createBy the value for yunmu_order.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.update_time
     *
     * @return the value of yunmu_order.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.update_time
     *
     * @param updateTime the value for yunmu_order.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.update_by
     *
     * @return the value of yunmu_order.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.update_by
     *
     * @param updateBy the value for yunmu_order.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.complete_time
     *
     * @return the value of yunmu_order.complete_time
     *
     * @mbggenerated
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.complete_time
     *
     * @param completeTime the value for yunmu_order.complete_time
     *
     * @mbggenerated
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_start_date
     *
     * @return the value of yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    public Date getOrderStartDate() {
        return orderStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_start_date
     *
     * @param orderStartDate the value for yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_end_time
     *
     * @return the value of yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    public Date getOrderEndTime() {
        return orderEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_end_time
     *
     * @param orderEndTime the value for yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_count
     *
     * @return the value of yunmu_order.order_count
     *
     * @mbggenerated
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_count
     *
     * @param orderCount the value for yunmu_order.order_count
     *
     * @mbggenerated
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_pro_amount
     *
     * @return the value of yunmu_order.order_pro_amount
     *
     * @mbggenerated
     */
    public BigDecimal getOrderProAmount() {
        return orderProAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_pro_amount
     *
     * @param orderProAmount the value for yunmu_order.order_pro_amount
     *
     * @mbggenerated
     */
    public void setOrderProAmount(BigDecimal orderProAmount) {
        this.orderProAmount = orderProAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_act_amount
     *
     * @return the value of yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    public BigDecimal getOrderActAmount() {
        return orderActAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_act_amount
     *
     * @param orderActAmount the value for yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    public void setOrderActAmount(BigDecimal orderActAmount) {
        this.orderActAmount = orderActAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_rec_amount
     *
     * @return the value of yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    public BigDecimal getOrderRecAmount() {
        return orderRecAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_rec_amount
     *
     * @param orderRecAmount the value for yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    public void setOrderRecAmount(BigDecimal orderRecAmount) {
        this.orderRecAmount = orderRecAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_source
     *
     * @return the value of yunmu_order.order_source
     *
     * @mbggenerated
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_source
     *
     * @param orderSource the value for yunmu_order.order_source
     *
     * @mbggenerated
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_status
     *
     * @return the value of yunmu_order.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_status
     *
     * @param orderStatus the value for yunmu_order.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_way
     *
     * @return the value of yunmu_order.order_way
     *
     * @mbggenerated
     */
    public String getOrderWay() {
        return orderWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_way
     *
     * @param orderWay the value for yunmu_order.order_way
     *
     * @mbggenerated
     */
    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay == null ? null : orderWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.del_flag
     *
     * @return the value of yunmu_order.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.del_flag
     *
     * @param delFlag the value for yunmu_order.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.project_id
     *
     * @return the value of yunmu_order.project_id
     *
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.project_id
     *
     * @param projectId the value for yunmu_order.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.is_choose
     *
     * @return the value of yunmu_order.is_choose
     *
     * @mbggenerated
     */
    public Integer getIsChoose() {
        return isChoose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.is_choose
     *
     * @param isChoose the value for yunmu_order.is_choose
     *
     * @mbggenerated
     */
    public void setIsChoose(Integer isChoose) {
        this.isChoose = isChoose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.is_choose_product
     *
     * @return the value of yunmu_order.is_choose_product
     *
     * @mbggenerated
     */
    public Integer getIsChooseProduct() {
        return isChooseProduct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.is_choose_product
     *
     * @param isChooseProduct the value for yunmu_order.is_choose_product
     *
     * @mbggenerated
     */
    public void setIsChooseProduct(Integer isChooseProduct) {
        this.isChooseProduct = isChooseProduct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_channel
     *
     * @return the value of yunmu_order.order_channel
     *
     * @mbggenerated
     */
    public Integer getOrderChannel() {
        return orderChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_channel
     *
     * @param orderChannel the value for yunmu_order.order_channel
     *
     * @mbggenerated
     */
    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_desc
     *
     * @return the value of yunmu_order.order_desc
     *
     * @mbggenerated
     */
    public String getOrderDesc() {
        return orderDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_desc
     *
     * @param orderDesc the value for yunmu_order.order_desc
     *
     * @mbggenerated
     */
    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }
}