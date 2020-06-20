package com.ouchen.core.dao.customer;

import com.ouchen.core.model.customer.CustomerRoom;

import java.util.List;
import java.util.Map;

public interface CustomerRoomMapperExt {

    List<CustomerRoom> getCustomerRoomPage(Map<String, Object> params);
}