package com.yunmu.back.service.sys;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.sys.Bussiness;
import com.yunmu.core.model.sys.BussinessExt;

import java.util.Map;

public interface BussinessService {

    GenericPage<BussinessExt> getPageByCondition(Map<String, Object> params);

    Bussiness getBussinessById(String bussinessId);

    boolean updateBussiness(Bussiness bussiness);

    boolean insertBussiness(Bussiness bussiness);

    boolean deleteBussiness(String  bussinessId);
}
