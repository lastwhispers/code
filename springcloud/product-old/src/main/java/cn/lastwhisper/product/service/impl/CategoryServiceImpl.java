package cn.lastwhisper.product.service.impl;

import cn.lastwhisper.product.domain.ProductCategory;
import cn.lastwhisper.product.repository.ProductCategoryRepository;
import cn.lastwhisper.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * @author lastwhisper
 * @date 2019/10/26
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

}
