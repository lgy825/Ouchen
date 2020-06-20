package com.ouchen.back.service.customer;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.customer.Customer;
import com.ouchen.core.model.customer.CustomerExt;
import com.ouchen.core.model.customer.CustomerRoom;

import java.util.Map;

public interface CustomerService {
    public GenericPage<CustomerExt> getPageByCondition(Map<String, Object> params);

    public GenericPage<CustomerRoom> getRoomPage(Map<String, Object> params);

    public Boolean insert(CustomerExt customerExt);

    public CustomerExt getCustomerById(String id);

    Boolean update(CustomerExt customerExt);

    boolean updateStatus(Customer customer);
}
