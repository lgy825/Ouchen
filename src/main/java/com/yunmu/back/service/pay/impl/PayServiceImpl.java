package com.yunmu.back.service.pay.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.pay.PayService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class PayServiceImpl implements PayService{

    @Autowired
    private PayMapperExt payMapperExt;
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public GenericPage<PayExt> getPageByCondition(Map<String, Object> params) {
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
        Page<PayExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<PayExt> payExts=payMapperExt.getPayPage(params);
        for(PayExt payExt:payExts ){
            payExt.setProjectName(projectMapper.selectByPrimaryKey(payExt.getProjectId()).getProjectName());
            int type=payExt.getPayType();
            if(type==0){
                payExt.setTypeName("日支出");
            }else if(type==1){
                payExt.setTypeName("月支出");
            }
        }
        return new GenericPage<>(pageIndex, pageSize, payExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(Pay pay) {
        if(pay!=null){
            PayExample payExample=new PayExample();
            PayExample.Criteria criteria=payExample.createCriteria();
            criteria.andPayNameEqualTo(pay.getPayName());
            criteria.andProjectIdEqualTo(pay.getProjectId());
            if(payMapper.countByExample(payExample)>0){
                throw new DataException("支出名称已存在");
            }
            pay.setCreateBy(ShiroUtils.getUser().getUserName());
            pay.setCreateTime(new Date());
            pay.setDelFlag(0);
            if(pay.getPayAmount()!=null){
                pay.setPayAmount(pay.getPayAmount().divide(new BigDecimal(100)));
            }
            try {
                payMapper.insert(pay);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Pay pay) {
        if(pay!=null){
            Pay pay1=payMapper.selectByPrimaryKey(pay.getPayId());
            int compareCount = 0;
            if(pay.getPayName().equals(pay1.getPayName())) {
                compareCount = 1;
            }
            PayExample payExample=new PayExample();
            PayExample.Criteria criteria=payExample.createCriteria();
            criteria.andPayNameEqualTo(pay.getPayName());
            criteria.andProjectIdEqualTo(pay.getProjectId());
            if(payMapper.countByExample(payExample)>compareCount){
                throw new DataException("支出名称已存在");
            }
            pay.setUpdateBy(ShiroUtils.getUser().getUserName());
            pay.setUpdateTime(new Date());
            if(pay.getPayAmount()!=null){
                pay.setPayAmount(pay.getPayAmount().divide(new BigDecimal(100)));
            }
            payMapper.updateByPrimaryKeySelective(pay);
            return true;
        }
        return false;
    }

    @Override
    public Pay getPayByIdById(String id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteByPrimaryKey(String id) {
        if(!StringUtils.isBlank(id)){
            Pay pay=new Pay();
            pay.setDelFlag(1);
            pay.setPayId(id);
            payMapper.updateByPrimaryKeySelective(pay);
            return true;
        }
        return false;
    }
}
