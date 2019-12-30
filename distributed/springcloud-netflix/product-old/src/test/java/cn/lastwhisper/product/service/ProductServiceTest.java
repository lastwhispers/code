package cn.lastwhisper.product.service;

import cn.lastwhisper.product.ProductApplicationTests;
import cn.lastwhisper.product.dto.CartDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class ProductServiceTest  extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        productService.findUpAll().forEach(System.out::println);
    }

    @Test
    public void decreaseStock(){
        CartDTO cartDTO = new CartDTO("157875227953464068", 3);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}