package cn.lastwhisper.order.service;

import cn.lastwhisper.order.dto.OrderDTO;

/**
 *
 * @author lastwhisper
 * @date 2019/10/26
 */
public interface OrderService {
    /**
     * buyer创建订单
     */
    OrderDTO create(OrderDTO orderDTO);
    /**
     * seller结束订单
     */
    OrderDTO finish(String orderId);

}
