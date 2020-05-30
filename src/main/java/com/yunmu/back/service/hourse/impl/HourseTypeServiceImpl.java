package com.yunmu.back.service.hourse.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.hourse.HourseTypeService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseTypeMapper;
import com.yunmu.core.dao.hourse.HourseTypeMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.hourse.HourseType;
import com.yunmu.core.model.hourse.HourseTypeExample;
import com.yunmu.core.model.hourse.HourseTypeExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class HourseTypeServiceImpl implements HourseTypeService {

    @Autowired
    private HourseTypeMapperExt hourseTypeMapperExt;
    @Autowired
    private HourseTypeMapper hourseTypeMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public GenericPage<HourseTypeExt> getPageByCondition(Map<String, Object> params) {
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
        Page<HourseTypeExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<HourseTypeExt> hourseTypeExts=hourseTypeMapperExt.getHourseTypePage(params);
        for(HourseTypeExt hourseType:hourseTypeExts){
            hourseType.setProjectName(projectMapper.selectByPrimaryKey(hourseType.getProjectId()).getProjectName());
        }
        return new GenericPage<>(pageIndex, pageSize, hourseTypeExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(HourseType hourseType) {
        if(hourseType!=null){
            HourseTypeExample hourseTypeExample=new HourseTypeExample();
            HourseTypeExample.Criteria criteria=hourseTypeExample.createCriteria();
            criteria.andTypeNameEqualTo(hourseType.getTypeName());
            criteria.andDelFlagEqualTo("0");
            criteria.andProjectIdEqualTo(hourseType.getProjectId());
            if(hourseTypeMapper.countByExample(hourseTypeExample)>0){
                throw new DataException("类型名称已存在");
            }
            hourseType.setCreateBy(ShiroUtils.getUser().getUserName());
            hourseType.setCreateTime(new Date());
            hourseType.setDelFlag("0");
            try {
                hourseTypeMapper.insert(hourseType);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(HourseType hourseType) {
        if(hourseType!=null){
            HourseType hourseType1=hourseTypeMapper.selectByPrimaryKey(hourseType.getId());
            int compareCount = 0;
            if(hourseType1.getTypeName().equalsIgnoreCase(hourseType.getTypeName())){
                compareCount=1;
            }
            HourseTypeExample hourseTypeExample=new HourseTypeExample();
            HourseTypeExample.Criteria criteria=hourseTypeExample.createCriteria();
            criteria.andTypeNameEqualTo(hourseType.getTypeName());
            criteria.andDelFlagEqualTo("0");
            criteria.andProjectIdEqualTo(hourseType.getProjectId());
            if(hourseTypeMapper.countByExample(hourseTypeExample)>compareCount){
                throw new DataException("类型名称已存在");
            }
            hourseType.setUpdateBy(ShiroUtils.getUserId());
            hourseType.setUpdateTime(new Date());
            hourseTypeMapper.updateByPrimaryKeySelective(hourseType);
            return true;
        }
        return false;
    }

    @Override
    public HourseType getHourseByIdById(String id) {
        return hourseTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteByPrimaryKey(String id) {
        if(!StringUtils.isBlank(id)){
            HourseType hourseType=new HourseType();
            hourseType.setDelFlag("1");
            hourseType.setId(id);
            hourseTypeMapper.updateByPrimaryKeySelective(hourseType);
            return true;
        }
        return false;
    }

    @Override
    public List<HourseType> getHourseTypeListById(List<String> ids) {
        HourseTypeExample hourseTypeExample=new HourseTypeExample();
        HourseTypeExample.Criteria criteria=hourseTypeExample.createCriteria();
        criteria.andIdIn(ids);
        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        criteria.andProjectIdIn(projectIds);
        return hourseTypeMapper.selectByExample(hourseTypeExample);
    }
}
