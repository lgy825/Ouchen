package com.yunmu.core.dao.product;

import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.product.ProductExample;
import com.yunmu.core.model.product.ProductExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapperExt {

    List<ProductExt> getPayPage(Map<String, Object> params);
}