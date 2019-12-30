package cn.lastwhisper.product.ha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品服务的接口
 * @author Administrator
 *
 */
@Controller
public class ProductController {

    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        return "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, " +
                "\"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", " +
                "\"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", " +
                "\"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\"," +
                " \"cityId\": 1, \"brandId\": 1}";
    }

}
