package cn.lastwhisper.product.repository;

import cn.lastwhisper.product.ProductApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class ProductInfoRepositoryTest  extends ProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findAllByProductStatus() {
        productInfoRepository.findByProductStatus(0).forEach(System.out::println);
    }

    @Test
    public void findByProductIdIn() {
        productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022")).forEach(System.out::println);
    }

}