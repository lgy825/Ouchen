package com.ouchen.back.service.company;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.company.Company;
import com.ouchen.core.model.company.CompanyExt;

import java.util.Map;

public interface CompanyService {

    GenericPage<CompanyExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(Company company);

    Boolean update(Company company);

    Company getCompanyById(String id);

    boolean deleteByPrimaryKey(String id);

}
