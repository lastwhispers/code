package cn.lastwhisper.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
@FeignClient("order")
public interface OrderClient {

    /**
     * 一个睡3s的方法
     */
    @PostMapping("/order/orderThread3")
    void orderThread3();

}
