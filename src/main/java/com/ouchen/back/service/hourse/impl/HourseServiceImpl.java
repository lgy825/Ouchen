package com.ouchen.back.service.hourse.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ouchen.back.service.hourse.HourseService;
import com.ouchen.back.service.hourse.HourseTypeService;
import com.ouchen.back.service.owner.OwnerService;
import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.dao.hourse.HourseMapper;
import com.ouchen.core.dao.hourse.HourseMapperExt;
import com.ouchen.core.model.hourse.Hourse;
import com.ouchen.core.model.hourse.HourseExample;
import com.ouchen.core.model.hourse.HourseExt;
import com.ouchen.core.model.hourse.HourseType;
import com.ouchen.core.model.owner.OwnerExt;
import com.ouchen.core.model.project.Project;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/5/20.
 */
@Service
public class HourseServiceImpl implements HourseService {

    @Autowired
    private HourseMapperExt hourseMapperExt;

    @Autowired
    private HourseMapper hourseMapper;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private HourseTypeService hourseTypeService;
    @Override
    public GenericPage<HourseExt> getPageByCondition(Map<String, Object> params) {
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
        List<HourseExt> hourseExts=hourseMapperExt.getHoursePage(params);
        for(HourseExt hourseExt:hourseExts){
            hourseExt.setOwnerName(ownerService.getOwnerRealName(hourseExt.getOwnerCode()));
            HourseType hourseType=hourseTypeService.getHourseByIdById(hourseExt.getTypeCode());
            hourseExt.setTypeName(hourseType.getTypeName());

        }
        return new GenericPage<>(pageIndex, pageSize, hourseExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(Hourse hourse) {
        if(hourse!=null) {
            hourse.setCreateBy(ShiroUtils.getUser().getUserName());
            hourse.setCreateTime(new Date());
            hourse.setDelFlag(0);
            try {
                hourseMapper.insertSelective(hourse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Hourse getHourseByIdById(String id) {
        return hourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Hourse hourse) {
        try {
            hourse.setUpdateBy(ShiroUtils.getUser().getUserName());
            hourse.setUpdateTime(new Date());
            hourseMapper.updateByPrimaryKeySelective(hourse);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByPrimaryKey(String id) {
        try {
            Hourse hourse=new Hourse();
            hourse.setId(id);
            hourse.setDelFlag(1);
            hourse.setUpdateBy(ShiroUtils.getUserId());
            hourse.setUpdateTime(new Date());
            hourseMapper.updateByPrimaryKeySelective(hourse);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Hourse> getAllHourse() {
        HourseExample hourseExample=new HourseExample();
        HourseExample.Criteria criteria=hourseExample.createCriteria();
        criteria.andDelFlagEqualTo(0);
        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        criteria.andProjectIdIn(projectIds);
        return hourseMapper.selectByExample(hourseExample);
    }
}
