package cn.lastwhisper.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/order")
public class HystrixController {


    //@HystrixCommand(
    //        fallbackMethod = "fallback",
    //        commandProperties = {
    //                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),//超时降级
    //                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//同意使用断路器来熔断请求
    //                //timeInMilliseconds/numBuckets内,最小请求数
    //                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
    //                //断路器打开后的休眠时间窗口,休眠结束后,断路器进入"半打开"状态
    //                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
    //                //断路器打开的错误比条件(在timeInMilliseconds内,请求超过requestVolumeThreshold前提下,错误比超60,开启断路器)
    //                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    //        },
    //        threadPoolProperties = {
    //                // 线程池参数
    //                @HystrixProperty(name = "coreSize", value = "20"),
    //                @HystrixProperty(name = "maxQueueSize", value = "10"),
    //                @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
    //                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
    //                //滚动时间窗口长度,被分成多个numBuckets,该参数需要被numBuckets整除
    //                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500"),
    //                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12")
    //        })

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
