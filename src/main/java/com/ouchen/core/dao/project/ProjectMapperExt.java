package com.ouchen.core.dao.project;

import com.ouchen.core.model.project.ProjectExt;

import java.util.List;
import java.util.Map;

public interface ProjectMapperExt {


    List<ProjectExt> getProjectPage(Map<String, Object> params);

    int getProjectCount(String companyCode);
}