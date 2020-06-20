package com.ouchen.back.service.pay;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.pay.Pay;
import com.ouchen.core.model.pay.PayExt;

import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface PayService {

    GenericPage<PayExt> getPageByCondition(Map<String, Object> params);

    boolean insert(Pay pay);

    Boolean update(Pay pay);

    Pay getPayByIdById(String id);

    Boolean deleteByPrimaryKey(String id);
}
