package com.yunmu.back.service.project.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.project.ProjectService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.company.CompanyMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.project.ProjectMapperExt;
import com.yunmu.core.dao.project.ProjectTypeMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExt;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.product.ProductExample;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExample;
import com.yunmu.core.model.project.ProjectExt;
import com.yunmu.core.model.project.ProjectType;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapperExt projectMapperExt;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectTypeMapper projectTypeMapper;

    @Autowired
    private CompanyMapperExt companyMapperExt;

    @Override
    public GenericPage<ProjectExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<ProjectExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<ProjectExt> projectExts=projectMapperExt.getProjectPage(params);

        for(ProjectExt projectExt:projectExts){
            ProjectType projectType=projectTypeMapper.selectByPrimaryKey(projectExt.getTypeCode());
            projectExt.setTypaName(projectType.getTypeName());
            CompanyExt companyExt=companyMapperExt.getCompanyByCode(projectExt.getCompanyCode());
            if(companyExt!=null){
                projectExt.setCompanyName(companyExt.getCompanyName());
            }

        }
        return new GenericPage<>(pageIndex, pageSize, projectExts, pageInfo.getTotal());
    }

    @Override
    public Boolean insert(Project project) {
        if(project!=null) {
            ProjectExample projectExample=new ProjectExample();
            ProjectExample.Criteria criteria=projectExample.createCriteria();
            criteria.andDelFlagEqualTo(0);
            criteria.andProjectNameEqualTo(project.getProjectName());
            if(projectMapper.countByExample(projectExample)>0){
                throw new DataException("项目名称已存在");
            }
            project.setCreateBy(ShiroUtils.getUser().getUserName());
            project.setCreateTime(new Date());
            project.setDelFlag(0);
            project.setStatus(1);
            try {
                projectMapper.insert(project);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Project project) {
        try {
            Project project1=projectMapper.selectByPrimaryKey(project.getId());
            int compareCount = 0;
            if(project1.getProjectName().equalsIgnoreCase(project.getProjectName())){
                compareCount=1;
            }
            ProjectExample projectExample=new ProjectExample();
            ProjectExample.Criteria criteria=projectExample.createCriteria();
            criteria.andDelFlagEqualTo(0);
            criteria.andProjectNameEqualTo(project.getProjectName());
            if(projectMapper.countByExample(projectExample)>compareCount){
                throw new DataException("项目名称已存在");
            }
            project.setUpdateBy(ShiroUtils.getUser().getUserName());
            project.setUpdateTime(new Date());
            projectMapper.updateByPrimaryKeySelective(project);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProjectType> getProjectType() {
        return projectTypeMapper.getProjectTypeAll();
    }

    @Override
    public Project getProjectById(String id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Project> getProjects() {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andDelFlagEqualTo(0);
        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public boolean delete(String id) {
        Project project=new Project();
        project.setDelFlag(1);
        project.setId(id);
        project.setUpdateBy(ShiroUtils.getUserId());
        project.setUpdateTime(new Date());
        projectMapper.updateByPrimaryKeySelective(project);
        return true;
    }

    @Override
    public List<Project> getProjectListByCompanyCode(String companyCode) {
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria=projectExample.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andDelFlagEqualTo(0);
        List<Project> projects=projectMapper.selectByExample(projectExample);
        return projects;
    }

    @Override
    public int getSysUserByCompanyCode(String companyCode) {
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria=projectExample.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andDelFlagEqualTo(0);
        return projectMapper.selectByExample(projectExample).size();
    }
}
