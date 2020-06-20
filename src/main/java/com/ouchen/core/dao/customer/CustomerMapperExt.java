package com.ouchen.core.dao.customer;

import com.ouchen.core.model.customer.CustomerExt;

import java.util.List;
import java.util.Map;

public interface CustomerMapperExt {

    List<CustomerExt> getCustomerPage(Map<String, Object> params);

}