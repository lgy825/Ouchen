package com.yunmu.core.util;

import org.springframework.web.util.pattern.PathPattern;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * app订单详情适配类 ClassName:OrderDetalUtil
 *
 * @author ligy-008494
 * @create 2019-06-26 19:33
 */
public class OrderDetailUtil implements Serializable {

    private String oId;

    private Date oDate;

    private Double oRecAmount;

    private String payWay;

    private String sourceWay;

    private String hNumber;

    private Double orderActAmount;

    private Double payAmount;

    public Double getOrderActAmount() {
        return orderActAmount;
    }

    public void setOrderActAmount(Double orderActAmount) {
        this.orderActAmount = orderActAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String gethNumber() {
        return hNumber;
    }

    public void sethNumber(String hNumber) {
        this.hNumber = hNumber;
    }

    private List<OrderItem> orderItems;

    public Date getoDate() {
        return oDate;
    }

    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }

    public Double getoRecAmount() {
        return oRecAmount;
    }

    public void setoRecAmount(Double oRecAmount) {
        this.oRecAmount = oRecAmount;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getSourceWay() {
        return sourceWay;
    }

    public void setSourceWay(String sourceWay) {
        this.sourceWay = sourceWay;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }
}

