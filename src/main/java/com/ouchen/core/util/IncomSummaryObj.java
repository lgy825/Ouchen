package com.ouchen.core.util;

import java.math.BigDecimal;

public class IncomSummaryObj {

    private BigDecimal orderRecAmountAll;

    private BigDecimal orderPayAmountAll;

    private BigDecimal orderActmountAll;

    private Integer orderCountAll;

    public BigDecimal getOrderRecAmountAll() {
        return orderRecAmountAll;
    }

    public void setOrderRecAmountAll(BigDecimal orderRecAmountAll) {
        this.orderRecAmountAll = orderRecAmountAll;
    }

    public BigDecimal getOrderPayAmountAll() {
        return orderPayAmountAll;
    }

    public void setOrderPayAmountAll(BigDecimal orderPayAmountAll) {
        this.orderPayAmountAll = orderPayAmountAll;
    }

    public BigDecimal getOrderActmountAll() {
        return orderActmountAll;
    }

    public void setOrderActmountAll(BigDecimal orderActmountAll) {
        this.orderActmountAll = orderActmountAll;
    }

    public Integer getOrderCountAll() {
        return orderCountAll;
    }

    public void setOrderCountAll(Integer orderCountAll) {
        this.orderCountAll = orderCountAll;
    }
}
