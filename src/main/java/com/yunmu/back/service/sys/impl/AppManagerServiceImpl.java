package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.sys.AppManagerService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.company.CompanyMapperExt;
import com.yunmu.core.dao.sys.AppVersionMapper;
import com.yunmu.core.dao.sys.AppVersionMapperExt;
import com.yunmu.core.model.company.CompanyExt;
import com.yunmu.core.model.sys.AppVersion;
import com.yunmu.core.model.sys.AppVersionExt;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class AppManagerServiceImpl implements AppManagerService {

    @Autowired
    private AppVersionMapper appVersionMapper;
    @Autowired
    private AppVersionMapperExt appVersionMapperExt;
    @Autowired
    private CompanyMapperExt companyMapperExt;
    @Override
    public GenericPage<AppVersionExt> getPageByCondition(Map<String, Object> params) {
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
        Page<AppVersionExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<AppVersionExt> appVersionExts=appVersionMapperExt.getAppManagerPage(params);

        if(appVersionExts!=null){
            for(AppVersionExt appVersionExt:appVersionExts){
                CompanyExt companyExt=companyMapperExt.getCompanyByCode(appVersionExt.getCompanyCode());
                if(companyExt!=null){
                    appVersionExt.setCompanyName(companyExt.getCompanyName());
                }

            }
        }
        return new GenericPage<>(pageIndex, pageSize, appVersionExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(AppVersion appVersion) {
        appVersion.setCreateby(ShiroUtils.getUserId());
        appVersion.setCreateTime(new Date());
        if("0".equals(appVersion.getAppType())){
            appVersion.setAppType("IOS");
        }else if("1".equals(appVersion.getAppType())){
            appVersion.setAppType("Android");
        }
        appVersion.setDelFlag(0);
        appVersion.setStatus(0);
        appVersionMapper.insertSelective(appVersion);
        return true;
    }

    @Override
    public boolean update(AppVersion appVersion) {
        appVersion.setUpdateBy(ShiroUtils.getUserId());
        appVersion.setUpdateTime(new Date());
        //appVersion.setDelFlag(0);
        if("0".equals(appVersion.getAppType())){
            appVersion.setAppType("IOS");
        }else if("1".equals(appVersion.getAppType())){
            appVersion.setAppType("Android");
        }
        appVersionMapper.updateByPrimaryKeySelective(appVersion);
        return true;
    }

    @Override
    public AppVersion getAppById(String id) {

        return appVersionMapper.selectByPrimaryKey(id);
    }
}
