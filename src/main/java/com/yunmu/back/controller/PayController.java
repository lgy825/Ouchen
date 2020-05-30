package com.yunmu.back.controller;

import com.yunmu.back.service.pay.PayService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("pay")
@Controller
public class PayController extends BaseController{

    @Autowired
    private PayService payService;

    @RequestMapping("/toPaylist")
    public String toPaylist() {
        return "pay/paylist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<PayExt> getShopPageByCondition(HttpServletRequest request,
                                                     Integer pageIndex,
                                                     Integer pageSize,
                                                     String payName,String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("payName", payName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(projectId==null && !"".equals(projectId)){
            List<Project> projects= ShiroUtils.getAllMyCinemaList();
            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
            params.put("projectIds",projectIds);
        }else{
            params.put("projectId",projectId);
        }
        return createSuccessPageResult(payService.getPageByCondition(params));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result<Boolean> save(Pay pay) {
        if(StringUtils.isBlank(pay.getPayId())) {
            //判断该项目的支出名称是否已经存在，有则返回失败，无则创建
            pay.setPayId(IdUtils.getId(11));
            try {
                payService.insert(pay);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(payService.update(pay));
        }
        return createSuccessResult(true);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String payId) {

        return createSuccessResult(payService.deleteByPrimaryKey(payId));
    }

}
