package com.ouchen.core.dao.pay;

import com.ouchen.core.model.pay.PayExt;

import java.util.List;
import java.util.Map;

public interface PayMapperExt {

    List<PayExt> getPayPage(Map<String, Object> params);
}