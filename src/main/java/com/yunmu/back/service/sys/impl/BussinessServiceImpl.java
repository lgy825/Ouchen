package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.sys.BussinessService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.product.ProductMapper;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.sys.BussinessMapper;
import com.yunmu.core.dao.sys.BussinessMapperExt;
import com.yunmu.core.model.sys.Bussiness;
import com.yunmu.core.model.sys.BussinessExt;
import com.yunmu.core.model.sys.SysUserExt;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class BussinessServiceImpl implements BussinessService {

    @Autowired
    private BussinessMapperExt bussinessMapperExt;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private BussinessMapper bussinessMapper;

    @Override
    public GenericPage<BussinessExt> getPageByCondition(Map<String, Object> params) {
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
        Page<BussinessExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<BussinessExt> bussinessExts=bussinessMapperExt.getBussinessPage(params);

        if(bussinessExts!=null){
            for(BussinessExt bussinessExt:bussinessExts){
                bussinessExt.setProjectName(projectMapper.selectByPrimaryKey(bussinessExt.getProjectId()).getProjectName());
            }
        }
        return new GenericPage<>(pageIndex, pageSize, bussinessExts, pageInfo.getTotal());
    }

    @Override
    public Bussiness getBussinessById(String bussinessId) {
        return bussinessMapper.selectByPrimaryKey(bussinessId);
    }

    @Override
    public boolean updateBussiness(Bussiness bussiness) {

        bussiness.setUpdateTime(new Date());
        bussinessMapper.updateByPrimaryKeySelective(bussiness);
        return true;
    }

    @Override
    public boolean insertBussiness(Bussiness bussiness) {
        bussiness.setId(IdUtils.getId(16));
        bussiness.setDelFlag(0);
        bussiness.setCreateTime(new Date());
        bussinessMapper.insertSelective(bussiness);
        return true;
    }

    @Override
    public boolean deleteBussiness(String  bussinessId) {
        Bussiness bussiness=new Bussiness();
        bussiness.setUpdateTime(new Date());
        bussiness.setUpdateBy(ShiroUtils.getUserId());
        bussiness.setDelFlag(1);
        bussinessMapper.updateByPrimaryKeySelective(bussiness);
        return true;
    }
}
