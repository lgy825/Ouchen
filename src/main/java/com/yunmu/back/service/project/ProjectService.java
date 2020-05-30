package com.yunmu.back.service.project;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExt;
import com.yunmu.core.model.project.ProjectType;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    GenericPage<ProjectExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(Project project);

    Boolean update(Project project);

    List<ProjectType> getProjectType();

    Project getProjectById(String id);

    List<Project> getProjects();

    boolean delete(String id);

    List<Project> getProjectListByCompanyCode(String companyCode);

    int getSysUserByCompanyCode(String companyCode);
}
