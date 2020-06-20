package com.ouchen.back.service.order;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.order.Order;
import com.ouchen.core.model.order.OrderExt;
import com.ouchen.core.model.pay.PayWay;
import com.ouchen.core.model.source.OrderSource;
import com.ouchen.core.util.IncomSummaryObj;

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

    List<OrderExt> getOrdersByDate(Map<String,Object> params);

    boolean updateOrderStatus(Order order);

    IncomSummaryObj getIncomSummary(Map<String, Object> params);




}
