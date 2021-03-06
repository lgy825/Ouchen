package com.ouchen.core.dao.order;

import com.ouchen.core.model.order.Order;
import com.ouchen.core.model.order.OrderExt;
import com.ouchen.core.util.AppResponseObj;
import com.ouchen.core.util.IncomSummaryObj;

import java.util.List;
import java.util.Map;

public interface OrderMapperExt {

    List<OrderExt> getOrderPage(Map<String, Object> params);

    double getRecAmountByCondition(Map<String,String> params);

    double getActAmountByCondition(Map<String,String> params);

    int getCountByCondition(Map<String,String> params);


    List<AppResponseObj> getOrderPageBycondition(Map<String,String> params);

    void deleteOrderDetail(String orderCode);

    List<OrderExt> getOrderExport(Map<String,String> params);

    double getAllRecByParam(Map<String,String> params);

    double getAllActByParam(Map<String,String> params);

    List<Order> getOrderListBycondition(Map<String,String> params);


    List<OrderExt> getOrderList(Map<String, Object> params);

    IncomSummaryObj getIncomSummary(Map<String, Object> params);

    int getOrderCount(Map<String,String> params);
}