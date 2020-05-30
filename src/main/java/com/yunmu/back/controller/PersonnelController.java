package com.yunmu.back.controller;

import com.yunmu.back.service.contract.PersonnelService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.customer.Customer;
import com.yunmu.core.model.personnel.Personnel;
import com.yunmu.core.model.personnel.PersonnelExt;
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
@RequestMapping("/personnel")
public class PersonnelController extends BaseController {

    @Autowired
    private PersonnelService personnalService;


    @RequestMapping("/toPersonnellist")
    public String toList() {
        return "personnel/personnellist";
    }

    @RequestMapping("/toadd")
    public String toaddOwner() {
        return "personnel/newpersonnel";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<PersonnelExt> getShopPageByCondition(HttpServletRequest request,
                                                           Integer pageIndex,
                                                           Integer pageSize,
                                                           String personnelName) {
        Map<String, Object> params = new HashMap<>();
//        List<Project> projects = ShiroUtils.getAllMyCinemaList();
//        List<String> projectIds = projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
//        params.put("projectIds", projectIds);
        params.put("personnelName", personnelName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(personnalService.getPageByCondition(params));
    }

    @RequestMapping("/savePersonnel")
    @ResponseBody
    public Result<Boolean> save(@RequestBody Personnel personnel) {
        if (StringUtils.isBlank(personnel.getId())) {
            personnel.setId(IdUtils.getId(11));
            try {
                personnalService.insert(personnel);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(personnalService.update(personnel));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "personnal/personnallist";
        }
        model.addAttribute("personnalId", id);
        return "personnal/lookpersonnal";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Personnel> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(personnalService.getPersonnelById(id));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "personnel/personnallist";
        }
        model.addAttribute("personnelId", id);
        return "personnel/newpersonnel";
    }

    @RequestMapping("/disable")
    @ResponseBody
    public Result<Boolean> disable(String id) {
        Personnel personnel = new Personnel();
        personnel.setId(id);
        personnel.setPersonnelStatus(11);
        return createSuccessResult(personnalService.updateStatus(personnel));
    }

    @RequestMapping("/undisable")
    @ResponseBody
    public Result<Boolean> undisable(String id) {
        Personnel personnel = new Personnel();
        personnel.setId(id);
        personnel.setPersonnelStatus(10);
        return createSuccessResult(personnalService.updateStatus(personnel));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String id) {
        Personnel personnel = new Personnel();
        personnel.setId(id);
        personnel.setDelFlag(1);
        return createSuccessResult(personnalService.updateStatus(personnel));
    }


}

