package com.ouchen.back.service.product;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.product.Product;
import com.ouchen.core.model.product.ProductExt;

import java.util.Map;

public interface ProductService {

    GenericPage<ProductExt> getPageByCondition(Map<String, Object> params);

    boolean insert(Product product);

    Boolean update(Product product);

    Product getPayByIdById(String id);

    Boolean deleteByPrimaryKey(String id);
}
