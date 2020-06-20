package com.ouchen.back.service.customer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ouchen.back.service.customer.CustomerService;
import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.dao.customer.CustomerMapper;
import com.ouchen.core.dao.customer.CustomerMapperExt;
import com.ouchen.core.dao.customer.CustomerRoomMapper;
import com.ouchen.core.dao.customer.CustomerRoomMapperExt;
import com.ouchen.core.dao.project.ProjectMapper;
import com.ouchen.core.model.customer.Customer;
import com.ouchen.core.model.customer.CustomerExt;
import com.ouchen.core.model.customer.CustomerRoom;
import com.ouchen.core.model.customer.CustomerRoomExample;
import com.ouchen.core.util.IdUtils;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerMapperExt customerMapperExt;
    @Autowired
    private CustomerRoomMapper customerRoomMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerRoomMapperExt customerRoomMapperExt;

    @Override
    public GenericPage<CustomerExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<CustomerExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<CustomerExt> customerExts=customerMapperExt.getCustomerPage(params);
        for(CustomerExt customerExt:customerExts){
            CustomerRoomExample customerRoomExample=new CustomerRoomExample();
            CustomerRoomExample.Criteria criteria=customerRoomExample.createCriteria();
            criteria.andCustomerCodeEqualTo(customerExt.getId());
            criteria.andDelFlagEqualTo(0);
            criteria.andStatusEqualTo(10);
//            customerExt.setCustomerRoomList(customerRoomMapper.selectByExample(customerRoomExample));
            List<CustomerRoom> customerRooms=customerRoomMapper.selectByExample(customerRoomExample);
            customerExt.setRoomCount(customerRooms.size());

            customerExt.setProjectName(projectMapper.selectByPrimaryKey(customerExt.getProjectId()).getProjectName());

        }
        return new GenericPage<>(pageIndex, pageSize, customerExts, pageInfo.getTotal());
    }

    @Override
    public GenericPage<CustomerRoom> getRoomPage(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<CustomerRoom> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<CustomerRoom> customerRooms=customerRoomMapperExt.getCustomerRoomPage(params);
        return new GenericPage<>(pageIndex, pageSize, customerRooms, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(CustomerExt customerExt) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerExt,customer);
        customer.setDelFlag(0);
        Date date=new Date();
        //customer.setRoomCount(customer.getCustomerRoomList().size());
        customer.setCreateBy(ShiroUtils.getUserId());
        customer.setCreateTime(date);
        //customer.setStatus(10);
        for(CustomerRoom customerRoom:customerExt.getCustomerRoomList()){
            customerRoom.setCreateBy(ShiroUtils.getUserId());
            customerRoom.setCreateTime(date);
            customerRoom.setProjectId(customer.getProjectId());
            customerRoom.setDelFlag(0);
            customerRoom.setId(IdUtils.getId(10));
            customerRoom.setStatus(10);
            customerRoom.setCustomerCode(customer.getId());
            customerRoomMapper.insert(customerRoom);
        }

        customerMapper.insertSelective(customer);
        return true;
    }

    @Override
    public CustomerExt getCustomerById(String id) {
        CustomerExt customerExt=new CustomerExt();
        Customer customer=customerMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(customer,customerExt);
        CustomerRoomExample customerRoomExample=new CustomerRoomExample();
        CustomerRoomExample.Criteria criteria=customerRoomExample.createCriteria();
        criteria.andCustomerCodeEqualTo(customerExt.getId());
        criteria.andDelFlagEqualTo(0);
        criteria.andStatusEqualTo(10);
        customerExt.setCustomerRoomList(customerRoomMapper.selectByExample(customerRoomExample));
        return customerExt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(CustomerExt customerExt) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerExt,customer);
        customer.setDelFlag(0);
        Date date=new Date();
        //customer.setRoomCount(customer.getCustomerRoomList().size());
        customer.setUpdateBy(ShiroUtils.getUserId());
        customer.setUpdateTime(date);

        //
        CustomerRoomExample customerRoomExample=new CustomerRoomExample();
        CustomerRoomExample.Criteria criteria=customerRoomExample.createCriteria();
        criteria.andCustomerCodeEqualTo(customer.getId());
        criteria.andDelFlagEqualTo(0);
        int customerRooms=customerRoomMapper.countByExample(customerRoomExample);
        if(customerRooms>0){
            customerRoomMapper.deleteByExample(customerRoomExample);
        }

        for(CustomerRoom customerRoom:customerExt.getCustomerRoomList()){
            customerRoom.setUpdateBy(ShiroUtils.getUserId());
            customerRoom.setUpdateTime(date);
            customerRoom.setProjectId(customer.getProjectId());
            customerRoom.setDelFlag(0);
            //customerRoom.setId(IdUtils.getId(10));
            customerRoom.setStatus(10);
            customerRoom.setCustomerCode(customer.getId());
            customerRoomMapper.insertSelective(customerRoom);
        }

        customerMapper.updateByPrimaryKey(customer);
        return true;
    }

    @Override
    public boolean updateStatus(Customer customer) {
        customerMapper.updateByPrimaryKey(customer);
        return false;
    }
}
