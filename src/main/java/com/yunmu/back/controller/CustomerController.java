package com.yunmu.back.controller;

import com.yunmu.back.service.customer.CustomerService;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.back.service.owner.OwnerService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.customer.Customer;
import com.yunmu.core.model.customer.CustomerExt;
import com.yunmu.core.model.customer.CustomerRoom;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/5/19.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/tocustomerlist")
    public String toList() {
        return "customer/customerlist";
    }

    @RequestMapping("/toadd")
    public String toaddOwner() {
        return "customer/newcustomer";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<CustomerExt> getShopPageByCondition(HttpServletRequest request,
                                                          Integer pageIndex,
                                                          Integer pageSize,
                                                          Integer customerStatus,
                                                          String customerName) {
        Map<String, Object> params = new HashMap<>();
        List<Project> projects = ShiroUtils.getAllMyCinemaList();
        List<String> projectIds = projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds", projectIds);
        if(customerStatus!=null && customerStatus!=-1){
            params.put("customerStatus", customerStatus);
        }

        params.put("customerName", customerName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(customerService.getPageByCondition(params));
    }

    @RequestMapping("/getroompage")
    @ResponseBody
    public PageResult<CustomerRoom> getRoomPage(HttpServletRequest request,
                                                Integer pageIndex,
                                                Integer pageSize,
                                                String customerCode) {
        Map<String, Object> params = new HashMap<>();
        List<Project> projects = ShiroUtils.getAllMyCinemaList();
        List<String> projectIds = projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds", projectIds);
        params.put("customerCode", customerCode);
        params.put("status", 10);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(customerService.getRoomPage(params));
    }

    @RequestMapping("/saveCustomer")
    @ResponseBody
    public Result<Boolean> save(@RequestBody  CustomerExt customerExt) {
        if (StringUtils.isBlank(customerExt.getId())) {
            customerExt.setId(IdUtils.getId(11));
            try {
                customerService.insert(customerExt);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(customerService.update(customerExt));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "customer/cutomerlist";
        }
        model.addAttribute("customerId", id);
        return "customer/lookcustomer";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<CustomerExt> update(String id) {
        if ("-1".equals(id)) {
            return createFailedResult("请选择客户");
        }
        return createSuccessResult(customerService.getCustomerById(id));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "customer/customerlist";
        }
        model.addAttribute("customerId", id);
        return "customer/newcustomer";
    }

    @RequestMapping("/disable")
    @ResponseBody
    public Result<Boolean> disableOwner(String id) {
        Customer customerExt = new Customer();
        customerExt.setId(id);
        customerExt.setStatus(10);
        return createSuccessResult(customerService.updateStatus(customerExt));
    }

    @RequestMapping("/undisable")
    @ResponseBody
    public Result<Boolean> undisableowner(String id) {
        Customer customerExt = new Customer();
        customerExt.setId(id);
        customerExt.setStatus(20);
        return createSuccessResult(customerService.updateStatus(customerExt));
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String id) {
        Customer customer=new Customer();
        customer.setId(id);
        customer.setDelFlag(1);
        return createSuccessResult(customerService.updateStatus(customer));
    }


}

