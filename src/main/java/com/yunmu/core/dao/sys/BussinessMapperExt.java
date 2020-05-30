package com.yunmu.core.dao.sys;

import com.yunmu.core.model.sys.Bussiness;
import com.yunmu.core.model.sys.BussinessExample;
import com.yunmu.core.model.sys.BussinessExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BussinessMapperExt {

    List<BussinessExt> getBussinessPage(Map<String, Object> params);
}