package com.yunmu.bapp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderDetailMapper;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayWayMapper;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.source.OrderSourceMapper;
import com.yunmu.core.dao.sys.AppVersionMapper;
import com.yunmu.core.dao.sys.BussinessMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderDetail;
import com.yunmu.core.model.order.OrderDetailExample;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.order.OrderProduct;
import com.yunmu.core.model.order.OrderProductExample;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.model.sys.*;
import com.yunmu.core.util.AppRequestParam;
import com.yunmu.core.util.AppResponseObj;
import com.yunmu.core.util.OrderDetailUtil;
import com.yunmu.core.util.OrderItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class AppServiceImpl implements AppService{

    @Autowired
    private OwnerMapperExt ownerMapperExt;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private OrderMapperExt orderMapperExt;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private HourseMapper hourseMapper;
    @Autowired
    private PayWayMapper payWayMapper;
    @Autowired
    private OrderSourceMapper orderSourceMapper;
    @Autowired
    private BussinessMapper bussinessMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public OwnerExt getOwnerByCondition(Owner owner) {
        OwnerExt owner1=ownerMapperExt.getOwnerByCondition(owner);
        if(owner1==null){
            throw new DataException("500","手机号或密码输入错误，请重新输入");
        }
        if(owner1.getStatus()==1){
            throw new DataException("500","该用户还未启用，请与管理原联系");
        }
        return owner1;
    }

    @Override
    public Boolean update(Owner owner) {
        owner.setCreateTime(new Date());
        try {
            ownerMapper.updateByPrimaryKeySelective(owner);
            return true;
        } catch (Exception e) {
            throw new DataException("用户信息修改失败");
        }
    }

    @Override
    public Map<String, Object> getHomeDataByCondition(Map<String, String> params) {

        //根据用户Id和时间获取收益
        double recAmountAll=orderMapperExt.getRecAmountByCondition(params);
        //获取房子出租的天数
        int count=orderMapperExt.getCountByCondition(params);
        //获取房子出租率
        Calendar now = Calendar.getInstance();
        int day=now.get(Calendar.DAY_OF_MONTH);
        String houseRate=Math.round((count*100)/day)+"";
        //获取应收
        double actAmountAll=orderMapperExt.getActAmountByCondition(params);
        //获取支出
        BigDecimal bigDecimal=new BigDecimal(recAmountAll);
        double extraCosts=(bigDecimal.subtract(new BigDecimal(actAmountAll))).setScale(2,BigDecimal.ROUND_UP).doubleValue();

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("incomeAll",actAmountAll);
        resultMap.put("count",count);
        resultMap.put("houseRate",houseRate);
        resultMap.put("extraCosts",extraCosts);

        return resultMap;
    }

    @Override
    public double getIncomeByCondition(Map<String, String> params) {
        double incomeAll=orderMapperExt.getActAmountByCondition(params);
        return incomeAll;
    }

    @Override
    public List<AppResponseObj> getOrderPage(Map<String, String> params) {
        List<AppResponseObj> orderExtList=orderMapperExt.getOrderPageBycondition(params);
        return orderExtList;
    }

    @Override
    public OrderDetailUtil getOrderInfoById(String orderId) {
        //先根据订单号获取订单信息
        Order order=orderMapper.selectByPrimaryKey(orderId);
        OrderExt orderExt=new OrderExt();
        if(order!=null){
            //根据订单id获取支出信息
            if(order.getIsChoose()!=null && order.getIsChoose()==2){
                OrderDetailExample orderDetailExample=new OrderDetailExample();
                OrderDetailExample.Criteria criteria=orderDetailExample.createCriteria();
                criteria.andOrderCodeEqualTo(orderId);
                criteria.andDelFlagEqualTo(0);
                List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
                for(OrderDetail orderDetail:orderDetails){
                    Pay pay=payMapper.selectByPrimaryKey(orderDetail.getPayCode());
                    orderDetail.setPayName(pay.getPayName());
                    orderDetail.setPayDesc(pay.getPayDesc());
                }
                orderExt.setOrderDetails(orderDetails);
            }
            BeanUtils.copyProperties(order,orderExt);
            if(orderExt.getOrderStatus()==10){
                orderExt.setOrderStatusStr("订单完成");
            }else if(orderExt.getOrderStatus()==11){
                orderExt.setOrderStatusStr("未入住");
            }else if(orderExt.getOrderStatus()==12){
                orderExt.setOrderStatusStr("已入住");
            }else if(orderExt.getOrderStatus()==13){
                orderExt.setOrderStatusStr("已取消");
            }
            //BigDecimal bigDecimal=new BigDecimal();
            BigDecimal payAmount=order.getOrderRecAmount().subtract(orderExt.getOrderRecAmount());
            orderExt.setPayAmount(payAmount);
            Map<Integer,String> payWays=getAllPayWayMap();
            Map<String,String> orderSources=getAllOrderSourceMap();
            if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }
            //获取房间号
            if(orderExt.getHourseCode()!=null){
                orderExt.setHourseNumber( hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
            }
        }

        OrderDetailUtil orderDetailUtil=new OrderDetailUtil();
        orderDetailUtil.setoDate(orderExt.getCreateTime());
        orderDetailUtil.setoId(orderExt.getId());
        orderDetailUtil.setoRecAmount(orderExt.getOrderRecAmount().doubleValue());
        orderDetailUtil.setPayWay(orderExt.getPayWay());
        orderDetailUtil.setSourceWay(orderExt.getSourceWay());
        orderDetailUtil.sethNumber(orderExt.getHourseNumber());
        //BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
        double payAmount=orderExt.getOrderRecAmount().subtract(orderExt.getOrderActAmount()).doubleValue();
        orderDetailUtil.setOrderActAmount(orderExt.getOrderActAmount().doubleValue());
        orderDetailUtil.setPayAmount(payAmount);
        List<OrderItem> orderItemList=new ArrayList<>();
        if(orderExt.getOrderDetails()!=null && orderExt.getOrderDetails().size()>0){
            for(OrderDetail pay:orderExt.getOrderDetails()){
                OrderItem orderItem=new OrderItem();
                orderItem.setdId(pay.getId());
                orderItem.setdAmount(pay.getAmount().doubleValue());
                orderItem.setdDate(pay.getCreateTime());
                orderItem.setdDesc(pay.getPayDesc());
                orderItem.setdName(pay.getPayName());
                orderItem.setdCount(pay.getCount());
                orderItemList.add(orderItem);
            }

        }
        orderDetailUtil.setOrderItems(orderItemList);
        return orderDetailUtil;
    }

    @Override
    public Owner getOwnerById(String ownerId) {
        return ownerMapper.selectByPrimaryKey(ownerId);
    }

    @Override
    public GenericPage<OrderExt> getOrderListByCondition(Map<String, Object> params) {
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
        Page<OrderExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<OrderExt> orderExts=orderMapperExt.getOrderList(params);
        Map<Integer,String> payWays=getAllPayWayMap();
        Map<String,String> orderSources=getAllOrderSourceMap();
        for(OrderExt orderExt:orderExts){
            if(orderExt.getHourseCode()!=null){
                orderExt.setHourseNumber(hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
            }
            if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }
        }
        return new GenericPage<>(pageIndex, pageSize, orderExts, pageInfo.getTotal());
    }

    @Override
    public OrderExt getOrderDetail(String id) {
        //先根据订单号获取订单信息
        Order order=orderMapper.selectByPrimaryKey(id);
        OrderExt orderExt=new OrderExt();
        if(order!=null){
            //根据订单id获取支出信息
            if(order.getIsChoose()!=null && order.getIsChoose()==2){
                OrderDetailExample orderDetailExample=new OrderDetailExample();
                OrderDetailExample.Criteria criteria=orderDetailExample.createCriteria();
                criteria.andOrderCodeEqualTo(id);
                criteria.andDelFlagEqualTo(0);
                List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
                for(OrderDetail orderDetail:orderDetails){
                    Pay pay=payMapper.selectByPrimaryKey(orderDetail.getPayCode());
                    orderDetail.setPayName(pay.getPayName());
                    orderDetail.setPayDesc(pay.getPayDesc());
                }

                orderExt.setOrderDetails(orderDetails);
            }
            if(orderExt.getOrderDetails()==null){
                orderExt.setOrderDetails(new ArrayList<>());
            }
            BeanUtils.copyProperties(order,orderExt);
            if(orderExt.getOrderStatus()==10){
                orderExt.setOrderStatusStr("订单完成");
            }else if(orderExt.getOrderStatus()==11){
                orderExt.setOrderStatusStr("未入住");
            }else if(orderExt.getOrderStatus()==12){
                orderExt.setOrderStatusStr("已入住");
            }else if(orderExt.getOrderStatus()==13){
                orderExt.setOrderStatusStr("已取消");
            }
            //BigDecimal bigDecimal=new BigDecimal();
            BigDecimal payAmount=order.getOrderRecAmount().subtract(orderExt.getOrderActAmount());
            orderExt.setPayAmount(payAmount);
            Map<Integer,String> payWays=getAllPayWayMap();
            Map<String,String> orderSources=getAllOrderSourceMap();
            if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }
            //获取房间号
            if(orderExt.getHourseCode()!=null){
                orderExt.setHourseNumber( hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
            }
        }
        return orderExt;
    }

    @Override
    public Bussiness getBussinessByProjectId(String projectId) {
        BussinessExample bussinessExample=new BussinessExample();
        BussinessExample.Criteria criteria=bussinessExample.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andDelFlagEqualTo(0);
        bussinessExample.setOrderByClause("create_time desc");
        List<Bussiness> bussinessList=bussinessMapper.selectByExample(bussinessExample);
        if(bussinessList.size()>0){

            return bussinessList.get(0);
        }
        return new Bussiness();
    }

    @Override
    public AppVersion getAppVersionByProjectId(String projectId,String appType) {
        //通过项目id获取公司code
        Project project=projectMapper.selectByPrimaryKey(projectId);
        AppVersionExample appVersionExample=new AppVersionExample();
        AppVersionExample.Criteria criteria=appVersionExample.createCriteria();
        criteria.andDelFlagEqualTo(0);
        criteria.andStatusEqualTo(0);
        criteria.andCompanyCodeEqualTo(project.getCompanyCode());
        criteria.andAppTypeEqualTo(appType);
        appVersionExample.setOrderByClause("create_time desc");
        List<AppVersion> appVersionList=appVersionMapper.selectByExample(appVersionExample);
        if(appVersionList.size()>0){
            return  appVersionList.get(0);
        }
        return new AppVersion();
    }

    public Map<Integer,String> getAllPayWayMap(){
        List<PayWay> payWays=payWayMapper.getPayWayAll();
        Map<Integer,String> stringMap=new HashMap<>();
        for(PayWay payWay:payWays){
            stringMap.put(payWay.getId(),payWay.getpName());
        }
        return stringMap;
    }

    public Map<String,String> getAllOrderSourceMap(){
        List<OrderSource> orderSources=orderSourceMapper.getOrderSourceAll();
        Map<String,String> stringMap=new HashMap<>();
        for(OrderSource orderSource:orderSources){
            stringMap.put(orderSource.getId(),orderSource.getName());
        }
        return stringMap;
    }


}
