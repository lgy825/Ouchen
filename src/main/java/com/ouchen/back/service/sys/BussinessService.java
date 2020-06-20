package com.ouchen.back.service.sys;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.sys.Bussiness;
import com.ouchen.core.model.sys.BussinessExt;

import java.util.Map;

public interface BussinessService {

    GenericPage<BussinessExt> getPageByCondition(Map<String, Object> params);

    Bussiness getBussinessById(String bussinessId);

    boolean updateBussiness(Bussiness bussiness);

    boolean insertBussiness(Bussiness bussiness);

    boolean deleteBussiness(String  bussinessId);
}
