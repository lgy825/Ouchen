package com.yunmu.core.dao.customer;

import com.yunmu.core.model.customer.Customer;
import com.yunmu.core.model.customer.CustomerExample;
import com.yunmu.core.model.customer.CustomerExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapperExt {

    List<CustomerExt> getCustomerPage(Map<String, Object> params);

}