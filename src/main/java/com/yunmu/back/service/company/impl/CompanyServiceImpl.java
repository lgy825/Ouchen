package com.yunmu.back.service.company.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.company.CompanyService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.company.CompanyMapper;
import com.yunmu.core.dao.company.CompanyMapperExt;
import com.yunmu.core.dao.project.ProjectMapperExt;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExt;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.SheetCollate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapperExt companyMapperExt;
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ProjectMapperExt projectMapperExt;

    @Override
    public GenericPage<CompanyExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if (params.containsKey("pageIndex")) {
            if (params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if (pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if (params.containsKey("pageSize")) {
            if (params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if (pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<CompanyExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<CompanyExt> companyExts = companyMapperExt.getCompanyPage(params);

        for (CompanyExt companyExt : companyExts) {
            int count = projectMapperExt.getProjectCount(companyExt.getCompanyCode());
            companyExt.setProjectCount(count);
        }
        return new GenericPage<>(pageIndex, pageSize, companyExts, pageInfo.getTotal());
    }

    @Override
    public Boolean insert(Company company) {
        if (company != null) {
            List<Company> companies = companyMapperExt.getCompanyByName(company.getCompanyName());
            if (companies.size() > 0) {
                throw new DataException("公司名称已存在，请重新输入");
            }
            company.setCreateBy(ShiroUtils.getUser().getUserName());
            company.setCreateTime(new Date());
            company.setDelFlag(0);
            try {
                companyMapper.insert(company);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Company company) {

        if(company!=null){
            List<Company> companies = companyMapperExt.getCompanyByName(company.getCompanyName());
            if (companies.size() > 0) {
                throw new DataException("公司名称已存在，请重新输入");
            }
            company.setUpdateBy(ShiroUtils.getUser().getUserName());
            company.setUpdateTime(new Date());
            companyMapper.updateByPrimaryKeySelective(company);
            return true;
        }
        return false;

    }

    @Override
    public Company getCompanyById(String id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteByPrimaryKey(String id) {
        Company company=new Company();
        company.setId(id);
        company.setDelFlag(1);
        companyMapper.updateByPrimaryKeySelective(company);
        return true;
    }
}
