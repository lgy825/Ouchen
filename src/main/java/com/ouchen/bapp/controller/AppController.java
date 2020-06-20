package com.ouchen.bapp.controller;

import com.ouchen.bapp.service.AppService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.model.order.OrderExt;
import com.ouchen.core.model.owner.Owner;
import com.ouchen.core.model.sys.AppVersion;
import com.ouchen.core.model.sys.Bussiness;
import com.ouchen.core.util.AppRequestParam;
import com.ouchen.core.util.AppResponseObj;
import com.ouchen.core.util.IdUtils;
import com.ouchen.core.util.OrderDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Controller
@RequestMapping("app")
public class AppController extends BaseController{

    @Autowired
    private AppService appService;

    //用户登录接口
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOwnerPhone() == null && appRequestParam.getOwnerName() == null) {
            return createFailedResult("请输入手机号或用户名");
        }
        if (appRequestParam.getOwnerPwd() == null) {
            return createFailedResult("密码输入为空，请重新输入");
        }
        Owner owner = new Owner();
        owner.setOwnerPwd(appRequestParam.getOwnerPwd());
        owner.setOwnerName(appRequestParam.getOwnerName());
        owner.setOwnerTel(appRequestParam.getOwnerPhone());
        try {
            return createSuccessResult(appService.getOwnerByCondition(owner));
        } catch (Exception e) {
            return createFailedResult(e.getMessage());
        }
    }

    //用户修改密码接口
    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> updatePwd(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }
        Owner owner = new Owner();
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("用户id不能为空");
        }
        if (appRequestParam.getOwnerPwd() == null) {
            return createFailedResult("请输入您要修改的密码");
        }
        owner.setId(appRequestParam.getOwnerId());
        owner.setOwnerName(appRequestParam.getOwnerName());
        owner.setOwnerPwd(appRequestParam.getOwnerPwd());
        owner.setOwnerTel(appRequestParam.getOwnerPhone());
        owner.setToken(IdUtils.getId(11));
        return createSuccessResult(appService.update(owner));
    }

    //首页界面
    @RequestMapping("/getDateByCondition")
    @ResponseBody
    public Result<Map<String, Object>> getDateByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }

        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getHomeDataByCondition(params));
    }

    //获取收益，根据时间区间
    @RequestMapping("/getIncomeByCondition")
    @ResponseBody
    public Result<Double> getIncomeByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }
        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getIncomeByCondition(params));
    }

    //获取收益列表
    @RequestMapping("/getOrderPageByCondition")
    @ResponseBody
    public Result<List<AppResponseObj>> getOrderPageByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }

        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getOrderPage(params));
    }

    //根据请求获取不同状态的收益列表
    @RequestMapping("/getOrderPageList")
    @ResponseBody
    public PageResult<OrderExt> getOrderListByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedPageResult("登录过期");
                }
            }

        }

        Map<String, Object> params = new HashMap<>();

        try {
            if(appRequestParam.getPageIndex()!=null){
                params.put("pageIndex", appRequestParam.getPageIndex());
            }
            if(appRequestParam.getPageSize()!=null){
                params.put("pageSize", appRequestParam.getPageSize());
            }
            params.put("ownerId", appRequestParam.getOwnerId());
            params.put("beginTime", appRequestParam.getBeginTime());
            params.put("endTime", appRequestParam.getEndTime());
            if(appRequestParam.getOrderStatus()!=null){
                params.put("orderStatus", appRequestParam.getOrderStatus());
            }
        } catch (Exception e) {
            return createFailedPageResult("服务器异常，请联系管理员");

        }

        return createSuccessPageResult(appService.getOrderListByCondition(params));
    }

    //根据订单idcha'查看订单的详情
    @RequestMapping("/getOrderInfoById")
    @ResponseBody
    public Result<OrderDetailUtil> getOrderInfoById(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOrderId() == null) {
            return createFailedResult("500错误,订单id为空");
        }
        return createSuccessResult(appService.getOrderInfoById(appRequestParam.getOrderId()));

    }

    //根据订单ID获取订单详情
    @RequestMapping("/getOrderDetailById")
    @ResponseBody
    public Result<OrderExt> getOrderDetailById(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOrderId() == null) {
            return createFailedResult("500错误,订单id为空");
        }
        return createSuccessResult(appService.getOrderDetail(appRequestParam.getOrderId()));
    }

    //获取商务经理界面
    //根据订单ID获取订单详情
    @RequestMapping("/getBussinessByProjectId")
    @ResponseBody
    public Result<Bussiness> getBussinessByProjectId(@RequestBody AppRequestParam appRequestParam){
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }
        if(appRequestParam.getProjectId()==null || "".equals(appRequestParam.getProjectId())){
            return createFailedResult("服务器异常");
        }
        return  createSuccessResult(appService.getBussinessByProjectId(appRequestParam.getProjectId()));
    }

    //获取关于我们的说明
    @RequestMapping("/getAppVersionByProjectId")
    @ResponseBody
    public Result<AppVersion> getAppVersionByCompanyCode(@RequestBody AppRequestParam appRequestParam){
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(owner!=null){
                if(!appRequestParam.getToken().equals(owner.getToken())){
                    return createFailedResult("登录过期");
                }
            }

        }
        if(appRequestParam.getAppType()==null || "".equals(appRequestParam.getAppType())){
            return createFailedResult("服务器异常");
        }
        if(appRequestParam.getProjectId()==null || "".equals(appRequestParam.getProjectId())){
            return createFailedResult("服务器异常");
        }

        return createSuccessResult(appService.getAppVersionByProjectId(appRequestParam.getProjectId(),appRequestParam.getAppType()));



    }

}
