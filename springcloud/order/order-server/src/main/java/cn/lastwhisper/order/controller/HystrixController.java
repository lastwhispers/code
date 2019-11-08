package cn.lastwhisper.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Hystrix学习
 * @author lastwhisper
 * @date 2019/11/3
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @GetMapping("/getProductInfoList")
    //@HystrixCommand(fallbackMethod = "fallback")
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    //})
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    //@HystrixCommand
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/product/listForOrder", Collections.singletonList("157875227953464068"), String.class);
    }

    public String fallback() {
        return "服务降级方法";
    }

    public String defaultFallback() {
        return "默认服务降级方法";
    }
}
