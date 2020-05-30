package com.yunmu.back.service.owner.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.owner.OwnerService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseMapperExt;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.project.ProjectMapperExt;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.util.MD5Util;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/5/19.
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    private static  final Logger log= LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Autowired
    private OwnerMapperExt ownerMapperExt;

    @Autowired
    private HourseMapperExt hourseMapperExt;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public GenericPage<OwnerExt> getPageByCondition(Map<String, Object> params) {
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
        Page<OwnerExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<OwnerExt> ownerExtList=ownerMapperExt.getOwnerPage(params);

        for(OwnerExt ownerExt:ownerExtList){
            int count=hourseMapperExt.getHouseCountByCondition(ownerExt.getId());
            ownerExt.setHourseCount(count);
            ownerExt.setProjectName(projectMapper.selectByPrimaryKey(ownerExt.getProjectId()).getProjectName());
        }
        return new GenericPage<>(pageIndex, pageSize, ownerExtList, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(Owner owner) {
        List<OwnerExt> ownerExt=ownerMapperExt.isExistOwner(owner);
        if(ownerExt.size()>0){
            throw new DataException("用户名或手机号已经存在");
        }
        if(owner!=null){
            owner.setToken("123456");
            owner.setStatus(1);
            owner.setCreateBy(ShiroUtils.getUser().getUserName());
            owner.setCreateTime(new Date());
            if(owner.getOwnerPwd()!=null){
                owner.setOwnerPwd(MD5Util.string2MD5(owner.getOwnerPwd()));
            }
            try {
                ownerMapper.insert(owner);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Owner getOwnerById(String id) {
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Owner owner) {
        try {
            if(owner.getOwnerPwd()!=null){
                owner.setOwnerPwd(MD5Util.string2MD5(owner.getOwnerPwd()));
            }
            ownerMapper.updateByPrimaryKeySelective(owner);
            return true;
        } catch (Exception e) {
            throw new DataException("用户信息修改失败");
        }
    }

    @Override
    public String getOwnerRealName(String id) {
        return ownerMapperExt.getOwnerRealNameById(id);
    }

}
