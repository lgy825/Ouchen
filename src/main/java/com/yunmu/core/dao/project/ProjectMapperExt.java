package com.yunmu.core.dao.project;

import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExample;
import com.yunmu.core.model.project.ProjectExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectMapperExt {


    List<ProjectExt> getProjectPage(Map<String, Object> params);

    int getProjectCount(String companyCode);
}