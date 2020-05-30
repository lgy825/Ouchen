package com.yunmu.back.service.pay;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExt;

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
