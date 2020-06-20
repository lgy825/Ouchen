package com.ouchen.bapp.service;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.order.OrderExt;
import com.ouchen.core.model.owner.Owner;
import com.ouchen.core.model.owner.OwnerExt;
import com.ouchen.core.model.sys.AppVersion;
import com.ouchen.core.model.sys.Bussiness;
import com.ouchen.core.util.AppResponseObj;
import com.ouchen.core.util.OrderDetailUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface AppService {

    OwnerExt getOwnerByCondition(Owner owner);

    Boolean update(Owner owner);

    Map<String,Object> getHomeDataByCondition(Map<String,String> params);

    double getIncomeByCondition(Map<String,String> params);

    List<AppResponseObj> getOrderPage(Map<String,String> params);

    OrderDetailUtil getOrderInfoById(String orderId);

    Owner getOwnerById(String ownerId);

    GenericPage<OrderExt> getOrderListByCondition(Map<String, Object> params);

    OrderExt getOrderDetail(String id);

    Bussiness getBussinessByProjectId(String projectId);

    AppVersion getAppVersionByProjectId(String projectId,String appType);
}
