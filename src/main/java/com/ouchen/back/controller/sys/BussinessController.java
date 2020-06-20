package com.ouchen.back.controller.sys;

import com.ouchen.back.service.sys.BussinessService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.model.sys.Bussiness;
import com.ouchen.core.model.sys.BussinessExt;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("bussiness")
@Controller
public class BussinessController extends BaseController {

    @Autowired
    private BussinessService bussinessService;

    @RequestMapping("/toBussinesslist")
    public String toList() {
        return "sys/bussiness/bussinesslist";
    }

    @RequestMapping("/toaddBussiness")
    public String toaddUser() {
        return "sys/bussiness/newbussiness";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<BussinessExt> getShopPageByCondition(HttpServletRequest request,
                                                           Integer pageIndex,
                                                           Integer pageSize,
                                                           String bussinessName) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginName", bussinessName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(bussinessService.getPageByCondition(params));
    }



    @RequestMapping("/get")
    @ResponseBody
    public Result<Bussiness> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(bussinessService.getBussinessById(id));
    }


    @RequestMapping("/toadd")
    public String toAdd() {
        return "sys/bussiness/newbussiness";
    }

    @RequestMapping("/saveBussiness")
    @ResponseBody
    public Result<Boolean> save(Bussiness bussiness) {
        if(StringUtils.isBlank(bussiness.getId())) {
            bussiness.setCreateBy(ShiroUtils.getUserId());
            return createSuccessResult(bussinessService.insertBussiness(bussiness));
        } else {
            bussiness.setUpdateBy(ShiroUtils.getUserId());
            return createSuccessResult(bussinessService.updateBussiness(bussiness));
        }
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "sys/bussiness/bussinesslist";
        }
        model.addAttribute("bussinessId", id);
        return "sys/bussiness/newbussiness";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String bussinessId) {

        return createSuccessResult(bussinessService.deleteBussiness(bussinessId));
    }
}
