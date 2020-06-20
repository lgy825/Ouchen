package com.ouchen.core.dao.sys;

import com.ouchen.core.model.sys.BussinessExt;

import java.util.List;
import java.util.Map;

public interface BussinessMapperExt {

    List<BussinessExt> getBussinessPage(Map<String, Object> params);
}