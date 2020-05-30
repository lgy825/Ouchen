package com.yunmu.back.service.product;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.product.ProductExt;

import java.util.Map;

public interface ProductService {

    GenericPage<ProductExt> getPageByCondition(Map<String, Object> params);

    boolean insert(Product product);

    Boolean update(Product product);

    Product getPayByIdById(String id);

    Boolean deleteByPrimaryKey(String id);
}
