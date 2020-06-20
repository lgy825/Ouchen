package com.ouchen.back.controller;

import com.google.common.collect.Maps;
import com.ouchen.back.service.hourse.HourseService;
import com.ouchen.back.service.hourse.HourseTypeService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.constant.ResultConstants;
import com.ouchen.core.model.hourse.Hourse;
import com.ouchen.core.model.hourse.HourseExt;
import com.ouchen.core.model.hourse.HourseType;
import com.ouchen.core.model.project.Project;
import com.ouchen.core.util.HourseTypeAndHourseVo;
import com.ouchen.core.util.IdUtils;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/5/20.
 */
@RequestMapping("hourse")
@Controller
public class HourseController extends BaseController {


    @Autowired
    private HourseService hourseService;
    @Autowired
    private HourseTypeService hourseTypeService;

    @RequestMapping("/toHourselist")
    public String toHourselist() {
        return "hourse/hourselist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<HourseExt> getShopPageByCondition(HttpServletRequest request,
                                                        Integer pageIndex,
                                                        Integer pageSize,
                                                        String hNumber,
                                                        String hNumberArea,String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("hNumber", hNumber);
        params.put("hNumberArea", hNumberArea);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(projectId==null || "".equals(projectId)){
            List<Project> projects= ShiroUtils.getAllMyCinemaList();
            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
            params.put("projectIds",projectIds);
        }else{
            params.put("projectId",projectId);
        }
        return createSuccessPageResult(hourseService.getPageByCondition(params));
    }

    @RequestMapping("/saveHourse")
    @ResponseBody
    public Result<Boolean> save(Hourse hourse) {
        if(StringUtils.isBlank(hourse.getId())) {
            hourse.setId(IdUtils.getId(11));
            try {
                hourseService.insert(hourse);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(hourseService.update(hourse));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String hId, Model model) {
        if(StringUtils.isBlank(hId)) {
            return "hourse/hourselist";
        }
        model.addAttribute("hId", hId);
        return "hourse/lookhourse";
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "hourse/hourselist";
        }
        model.addAttribute("hourseId", id);
        return "hourse/newhourse";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Hourse> getHourse(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(hourseService.getHourseByIdById(id));
    }

    @RequestMapping("/toaddHourse")
    public String toaddHourse() {
        return "hourse/newhourse";
    }

    @RequestMapping("/deleteHourse")
    @ResponseBody
    public Result<Boolean> deleteHourse(String hId) {

        return createSuccessResult(hourseService.deleteByPrimaryKey(hId));
    }

    @RequestMapping("/getHourseAndType")
    @ResponseBody
    public Result<HourseTypeAndHourseVo> getHourseAndType() {
        // TODO 院线用户和影核账号的逻辑判断

        Map<String, List<Hourse>> hourseMap = Maps.newHashMap();
        List<Hourse> hourseExts = hourseService.getAllHourse();
        if(CollectionUtils.isNotEmpty(hourseExts)) {
            hourseExts.forEach(hourseExt -> {
                if(hourseExt != null) {
                    String hourseId = hourseExt.getTypeCode();
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(hourseId)) {
                        if (hourseMap.containsKey(hourseId)) {
                            hourseMap.get(hourseId).add(hourseExt);
                        } else {
                            List<Hourse> cinemaListTmp = new ArrayList<>();
                            cinemaListTmp.add(hourseExt);
                            hourseMap.put(hourseId, cinemaListTmp);
                        }
                    }
                }
            });
        }
        Result<List<HourseType>> typeListsResult = createSuccessResult(hourseTypeService.getHourseTypeListById(hourseExts.stream().map(hourseExt -> {
            if (hourseExt == null) {
                return null;
            }
            return hourseExt.getTypeCode();
        }).collect(Collectors.toList())));
        if(ResultConstants.RESULT_CODE_FAILED.equals(typeListsResult.getResultCode())) {
            return createFailedResult(typeListsResult.getResultDesc());
        }

        return createSuccessResult(new HourseTypeAndHourseVo(typeListsResult.getResultData(), hourseMap));
    }
}
