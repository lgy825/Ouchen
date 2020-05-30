package com.yunmu.core.dao.company;

import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExample;
import com.yunmu.core.model.company.CompanyExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyMapperExt {

    List<CompanyExt> getCompanyPage(Map<String, Object> params);

    CompanyExt getCompanyByCode(String companyCode);

    List<Company> getCompanyByName(String companyName);



}