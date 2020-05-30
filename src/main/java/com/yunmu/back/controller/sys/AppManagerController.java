package com.yunmu.back.controller.sys;

import com.yunmu.back.service.order.OrderService;
import com.yunmu.back.service.owner.OwnerService;
import com.yunmu.back.service.sys.AppManagerService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.sys.AppVersion;
import com.yunmu.core.model.sys.AppVersionExt;
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
 * Created by 13544 on 2019/5/19.
 */
@Controller
@RequestMapping("/appmana")
public class AppManagerController extends BaseController {

    @Autowired
    private AppManagerService appManagerService;


    @RequestMapping("/toApplist")
    public String toList() {
        return "sys/app/applist";
    }

    @RequestMapping("/toaddApp")
    public String toaddApp() {
        return "sys/app/addversion";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<AppVersionExt> getShopPageByCondition(HttpServletRequest request,
                                                            Integer pageIndex,
                                                            Integer pageSize,
                                                            String companyCode) {
        Map<String, Object> params = new HashMap<>();
        List<Project> projects = ShiroUtils.getAllMyCinemaList();
        List<String> projectIds = projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("companyCode", companyCode);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(appManagerService.getPageByCondition(params));
    }

    @RequestMapping("/saveApp")
    @ResponseBody
    public Result<Boolean> save(AppVersion appVersion) {
        if (StringUtils.isBlank(appVersion.getId())) {
            appVersion.setId(IdUtils.getId(11));
            try {
                appManagerService.insert(appVersion);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(appManagerService.update(appVersion));
        }
        return createSuccessResult(true);
    }


    @RequestMapping("/get")
    @ResponseBody
    public Result<AppVersion> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(appManagerService.getAppById(id));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "sys/app/applist";
        }
        model.addAttribute("appId", id);
        return "sys/app/addversion";
    }

    @RequestMapping("/disable")
    @ResponseBody
    public Result<Boolean> disableOwner(String id) {
        AppVersion appVersion=new AppVersion();
        appVersion.setId(id);
        appVersion.setStatus(1);
        return createSuccessResult(appManagerService.update(appVersion));
    }

    @RequestMapping("/undisable")
    @ResponseBody
    public Result<Boolean> undisableowner(String id) {
        AppVersion appVersion=new AppVersion();
        appVersion.setId(id);
        appVersion.setStatus(0);
        return createSuccessResult(appManagerService.update(appVersion));
    }


}

