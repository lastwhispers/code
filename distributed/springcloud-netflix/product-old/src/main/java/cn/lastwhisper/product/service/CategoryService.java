package cn.lastwhisper.product.service;

import cn.lastwhisper.product.domain.ProductCategory;

import java.util.List;

public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
