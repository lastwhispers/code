package cn.cunchang;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producer")
public interface HelloFeignClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHello(@RequestParam("name") String name);
}