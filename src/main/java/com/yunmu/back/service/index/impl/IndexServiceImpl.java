package com.yunmu.back.service.index.impl;

import com.yunmu.back.service.index.IndexService;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.model.hourse.HourseExample;
import com.yunmu.core.util.IncomSummaryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private OrderMapperExt orderMapperExt;
    @Autowired
    private HourseMapper hourseMapper;

    @Override
    public Map<String, Object> getSellData(String startTime, List<String> projectIds, String time) {
        //获取今日订单总收益

        Map<String,Object> resultObj=new HashMap<>();

        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", startTime);
        params.put("searchTimeEnd", time);
        params.put("orderStatus", 10);
        params.put("projectIds",projectIds);
        params.put("delFlag",0);
        IncomSummaryObj incomSummaryObj=orderMapperExt.getIncomSummary(params);
        BigDecimal orderPayAmountAll=incomSummaryObj.getOrderRecAmountAll().subtract(incomSummaryObj.getOrderActmountAll()).setScale(2,BigDecimal.ROUND_UP);
        incomSummaryObj.setOrderPayAmountAll(orderPayAmountAll);

        //房间总数
        HourseExample hourseExample=new HourseExample();
        HourseExample.Criteria criteria=hourseExample.createCriteria();
        criteria.andProjectIdIn( projectIds);
        criteria.andDelFlagEqualTo(0);
        int hourseCount=hourseMapper.selectByExample(hourseExample).size();
        int surplusHouser=hourseCount-incomSummaryObj.getOrderCountAll();

        double occupancynew =new BigDecimal(hourseCount-surplusHouser).divide(new BigDecimal(hourseCount)).multiply(new BigDecimal(100)).doubleValue();
        //入住人次
        //入住率
        //剩余房间
        resultObj.put("orderRecAmountAll",incomSummaryObj.getOrderRecAmountAll());
        resultObj.put("orderActmountAll",incomSummaryObj.getOrderActmountAll());
        resultObj.put("orderPayAmountAll",orderPayAmountAll);
        resultObj.put("orderCountAll",incomSummaryObj.getOrderCountAll());
        resultObj.put("hourseCount",hourseCount);
        resultObj.put("surplusHouser",surplusHouser);
        resultObj.put("occupancynew",occupancynew);
        return resultObj;
    }
}
