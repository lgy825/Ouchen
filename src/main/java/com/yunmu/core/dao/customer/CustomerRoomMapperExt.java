package com.yunmu.core.dao.customer;

import com.yunmu.core.model.customer.CustomerRoom;
import com.yunmu.core.model.customer.CustomerRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerRoomMapperExt {

    List<CustomerRoom> getCustomerRoomPage(Map<String, Object> params);
}