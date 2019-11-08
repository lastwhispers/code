package cn.lastwhisper.order.controller;

import cn.lastwhisper.product.client.ProductClient;
import cn.lastwhisper.product.common.DecreaseStockInput;
import cn.lastwhisper.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * feign调用
 * @author lastwhisper
 * @date 2019/10/28
 */
@RestController
@Slf4j
public class FeignClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public List<ProductInfoOutput> getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response={}", productInfoList);
        return productInfoList;
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock() {
        //CartDTO cartDTO = new CartDTO("157875227953464068", 7);
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875227953464068", 7);
        productClient.decreaseStock(Arrays.asList(decreaseStockInput));
        return "ok";
    }

}
