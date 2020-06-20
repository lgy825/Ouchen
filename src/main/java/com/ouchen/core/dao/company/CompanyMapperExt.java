package com.ouchen.core.dao.company;

import com.ouchen.core.model.company.Company;
import com.ouchen.core.model.company.CompanyExt;

import java.util.List;
import java.util.Map;

public interface CompanyMapperExt {

    List<CompanyExt> getCompanyPage(Map<String, Object> params);

    CompanyExt getCompanyByCode(String companyCode);

    List<Company> getCompanyByName(String companyName);



}