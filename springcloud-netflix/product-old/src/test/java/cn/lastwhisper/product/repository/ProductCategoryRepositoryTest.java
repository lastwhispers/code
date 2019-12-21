package cn.lastwhisper.product.repository;

import cn.lastwhisper.product.ProductApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
public class ProductCategoryRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findProductCategoryByCategoryTypeIn() {
        productCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 22)).forEach(System.out::println);
    }
}