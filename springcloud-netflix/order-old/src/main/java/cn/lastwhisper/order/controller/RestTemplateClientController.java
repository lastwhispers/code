package cn.lastwhisper.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate调用三种使用方式
 * @author lastwhisper
 * @date 2019/10/28
 */
//@RestController
@Slf4j
public class RestTemplateClientController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;


    //@Autowired
    //private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        // 1.url硬编码，实际部署时可能不知道其他应用的ip，
        // 且当一个应用有多个实例时会很麻烦
        //RestTemplate restTemplate = new RestTemplate();
        //String url = "http://localhost:8080/msg";
        //String response = restTemplate.getForObject(url, String.class);

        // 2.loadBalancerClient根据应用名称获取url
        ServiceInstance product = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", product.getHost(), product.getPort() + "/msg");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // 3. 使用@Bean+注解
        //String url = "http://PRODUCT/msg";
        //String response = restTemplate.getForObject(url, String.class);

        log.info("url={},response={}", url, response);

        return response;
    }

}
