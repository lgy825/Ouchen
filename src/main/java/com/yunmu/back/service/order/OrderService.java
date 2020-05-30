package com.yunmu.back.service.order;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.util.IncomSummaryObj;

import java.util.List;
import java.util.Map;

public interface OrderService {

    GenericPage<OrderExt> getPageByCondition(Map<String, Object> params);

    List<PayWay> getPayWay();

    List<OrderSource> getOrderSource();

    boolean saveOrder(OrderExt orderExt);

    boolean updateOrder(OrderExt orderExt);

    OrderExt getOrderDetail(String id);

    boolean delete(String orderId);

    boolean revoke(String orderId);

    OrderExt get(String id);

    List<OrderExt> getOrdersByDate(Map<String,String> params);

    double getAllRecByParam(Map<String,String> params);

    double getAllActByParam(Map<String,String> params);

    boolean updateOrderStatus(Order order);

    int getCountByCondition(Map<String,String> params);


    IncomSummaryObj getIncomSummary(Map<String, Object> params);




}
