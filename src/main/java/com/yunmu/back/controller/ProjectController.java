package com.yunmu.back.controller;


import com.yunmu.back.service.project.ProjectService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExt;
import com.yunmu.core.model.project.ProjectType;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("project")
@Controller
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/toProjectlist")
    public String toProjectlist() {
        return "project/projectlist";
    }

    @RequestMapping("/toaddProject")
    public String newproject() {
        return "project/newproject";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<ProjectExt> getPage(HttpServletRequest request,
                                                         Integer pageIndex,
                                                         Integer pageSize,
                                                         String projectName) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectName", projectName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(projectService.getPageByCondition(params));
    }

    @RequestMapping("/saveProject")
    @ResponseBody
    public Result<Boolean> saveProject(Project project) {
        if (StringUtils.isBlank(project.getId())) {
            project.setId(IdUtils.getId(11));
            try {
                projectService.insert(project);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(projectService.update(project));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/getProjectTypeAll")
    @ResponseBody
    public Result<List<ProjectType>> getOrdeSourceAll() {
        return createSuccessResult(projectService.getProjectType());
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Project> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(projectService.getProjectById(id));
    }


    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "project/projectlist";
        }
        model.addAttribute("projectId", id);
        return "project/newproject";
    }

    @RequestMapping("/disableproject")
    @ResponseBody
    public Result<Boolean> disableOwner(String id) {
        Project project = new Project();
        project.setId(id);
        project.setStatus(1);
        return createSuccessResult(projectService.update(project));
    }

    @RequestMapping("/undisableproject")
    @ResponseBody
    public Result<Boolean> undisableowner(String id) {
        Project project = new Project();
        project.setId(id);
        project.setStatus(0);
        return createSuccessResult(projectService.update(project));
    }


    @RequestMapping("/getProject")
    @ResponseBody
    public Result<List<Project>> getProject() {

        return createSuccessResult(projectService.getProjects());

    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String id) {

        return createSuccessResult(projectService.delete(id));
    }



}
