package cn.lastwhisper.product.service;

import cn.lastwhisper.product.ProductApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        categoryService.findByCategoryTypeIn(Arrays.asList(1, 2)).forEach(System.out::println);
    }
}