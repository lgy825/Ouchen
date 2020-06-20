package com.ouchen.core.dao.product;

import com.ouchen.core.model.product.ProductExt;

import java.util.List;
import java.util.Map;

public interface ProductMapperExt {

    List<ProductExt> getPayPage(Map<String, Object> params);
}