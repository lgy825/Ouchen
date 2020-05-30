package com.yunmu.back.service.customer;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.customer.CustomerMapper;
import com.yunmu.core.model.customer.Customer;
import com.yunmu.core.model.customer.CustomerExt;
import com.yunmu.core.model.customer.CustomerRoom;
import com.yunmu.core.model.personnel.Personnel;
import com.yunmu.core.model.personnel.PersonnelExt;

import java.util.Map;

public interface CustomerService {
    public GenericPage<CustomerExt> getPageByCondition(Map<String, Object> params);

    public GenericPage<CustomerRoom> getRoomPage(Map<String, Object> params);

    public Boolean insert(CustomerExt customerExt);

    public CustomerExt getCustomerById(String id);

    Boolean update(CustomerExt customerExt);

    boolean updateStatus(Customer customer);
}
