package cn.lastwhisper.order.service;

import cn.lastwhisper.order.dto.OrderDTO;

/**
 *
 * @author lastwhisper
 * @date 2019/10/26
 */
public interface OrderService {

    public OrderDTO create(OrderDTO orderDTO);

}
