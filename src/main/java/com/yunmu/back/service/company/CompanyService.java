package com.yunmu.back.service.company;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExt;

import java.util.Map;

public interface CompanyService {

    GenericPage<CompanyExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(Company company);

    Boolean update(Company company);

    Company getCompanyById(String id);

    boolean deleteByPrimaryKey(String id);

}
