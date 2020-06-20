package com.ouchen.back.controller;

import com.ouchen.back.service.product.ProductService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.model.product.Product;
import com.ouchen.core.model.product.ProductExt;
import com.ouchen.core.model.project.Project;
import com.ouchen.core.util.IdUtils;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("product")
@Controller
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @RequestMapping("/toProductlist")
    public String toPaylist() {
        return "product/productlist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<ProductExt> getShopPageByCondition(HttpServletRequest request,
                                                         Integer pageIndex,
                                                         Integer pageSize,
                                                         String productName) {
        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds",projectIds);
        return createSuccessPageResult(productService.getPageByCondition(params));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result<Boolean> save(Product product) {
        if(StringUtils.isBlank(product.getId())) {
            product.setId(IdUtils.getId(11));
            try {
                productService.insert(product);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(productService.update(product));
        }
        return createSuccessResult(true);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteHourse(String productId) {

        return createSuccessResult(productService.deleteByPrimaryKey(productId));
    }

}
