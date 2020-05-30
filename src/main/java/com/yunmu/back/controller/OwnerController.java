package com.yunmu.back.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.back.service.owner.OwnerService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.Encodes;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import com.yunmu.core.util.excel.ExportExcel;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/5/19.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController extends BaseController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/toOwnerlist")
    public String toList() {
        return "owner/ownerlist";
    }

    @RequestMapping("/toaddOwner")
    public String toaddOwner() {
        return "owner/newowner";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<OwnerExt> getShopPageByCondition(HttpServletRequest request,
                                                       Integer pageIndex,
                                                       Integer pageSize,
                                                       String ownerTel,
                                                       String ownerName,String projectId) {
        Map<String, Object> params = new HashMap<>();

        params.put("ownerTel", ownerTel);
        params.put("ownerName", ownerName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(projectId==null && !"".equals(projectId)){
            List<Project> projects= ShiroUtils.getAllMyCinemaList();
            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
            params.put("projectIds",projectIds);
        }else{
            params.put("projectId",projectId);
        }
        return createSuccessPageResult(ownerService.getPageByCondition(params));
    }

    @RequestMapping("/saveOwner")
    @ResponseBody
    public Result<Boolean> save(Owner owner) {
        if (StringUtils.isBlank(owner.getId())) {
            owner.setId(IdUtils.getId(11));
            try {
                ownerService.insert(owner);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(ownerService.update(owner));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "owner/ownerlist";
        }
        model.addAttribute("ownerId", id);
        return "owner/lookowner";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Owner> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(ownerService.getOwnerById(id));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "owner/ownerlist";
        }
        model.addAttribute("ownerId", id);
        return "owner/newowner";
    }

    @RequestMapping("/disableowner")
    @ResponseBody
    public Result<Boolean> disableOwner(String id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setStatus(1);
        return createSuccessResult(ownerService.update(owner));
    }

    @RequestMapping("/undisableowner")
    @ResponseBody
    public Result<Boolean> undisableowner(String id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setStatus(0);
        return createSuccessResult(ownerService.update(owner));
    }


}

