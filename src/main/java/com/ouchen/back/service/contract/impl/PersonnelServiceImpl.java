package com.ouchen.back.service.contract.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ouchen.back.service.contract.PersonnelService;
import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.dao.company.CompanyMapperExt;
import com.ouchen.core.dao.personnel.PersonnelMapper;
import com.ouchen.core.dao.personnel.PersonnelMapperExt;
import com.ouchen.core.exception.DataException;
import com.ouchen.core.model.personnel.Personnel;
import com.ouchen.core.model.personnel.PersonnelExt;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    private PersonnelMapper personnelMapper;
    @Autowired
    private PersonnelMapperExt personnelMapperExt;
    @Autowired
    private CompanyMapperExt companyMapperExt;
    @Override
    public GenericPage<PersonnelExt> getPageByCondition(Map<String, Object> params) {
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
        Page<PersonnelExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<PersonnelExt> personnelExts=personnelMapperExt.getPersonnalPage(params);
        for(PersonnelExt personnelExt:personnelExts){
            personnelExt.setCompanyName(companyMapperExt.getCompanyByCode(personnelExt.getCompanyCode()).getCompanyName());
        }

        return new GenericPage<>(pageIndex, pageSize, personnelExts, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(Personnel personnel) {
        personnel.setCreateBy(ShiroUtils.getUserId());
        personnel.setCreateTime(new Date());
        personnel.setDelFlag(0);
        personnel.setPersonnelStatus(10);
        personnelMapper.insertSelective(personnel);
        return true;
    }

    @Override
    public Personnel getPersonnelById(String id) {

        return personnelMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Personnel personnel) {
        try {
            personnelMapper.updateByPrimaryKeySelective(personnel);
            return true;
        } catch (Exception e) {
            throw new DataException("用户信息修改失败");
        }
        //return false;
    }

    @Override
    public boolean updateStatus(Personnel personnel) {
        personnelMapper.updateByPrimaryKey(personnel);
        return true;
    }
}
