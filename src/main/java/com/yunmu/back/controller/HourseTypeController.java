package com.yunmu.back.controller;

import com.yunmu.back.service.hourse.HourseService;
import com.yunmu.back.service.hourse.HourseTypeService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseType;
import com.yunmu.core.model.hourse.HourseTypeExt;
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
@Controller
@RequestMapping("hourseType")
public class HourseTypeController extends BaseController {


    @Autowired
    private HourseTypeService hourseTypeService;

    @RequestMapping("/toHourseTypelist")
    public String toHourseTypelist() {
        return "hoursetype/hoursetypelist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<HourseTypeExt> getShopPageByCondition(HttpServletRequest request,
                                                            Integer pageIndex,
                                                            Integer pageSize,
                                                            String typeName,String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("typeName", typeName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(projectId==null && !"".equals(projectId)){
            List<Project> projects= ShiroUtils.getAllMyCinemaList();
            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
            params.put("projectIds",projectIds);
        }else{
            params.put("projectId",projectId);
        }
        return createSuccessPageResult(hourseTypeService.getPageByCondition(params));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result<Boolean> save(HourseType hourseType) {
        if(StringUtils.isBlank(hourseType.getId())) {
            hourseType.setId(IdUtils.getId(11));
            try {
                hourseTypeService.insert(hourseType);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(hourseTypeService.update(hourseType));
        }
        return createSuccessResult(true);
    }


    @RequestMapping("/get")
    @ResponseBody
    public Result<HourseType> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(hourseTypeService.getHourseByIdById(id));
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String id) {

        return createSuccessResult(hourseTypeService.deleteByPrimaryKey(id));
    }


}
