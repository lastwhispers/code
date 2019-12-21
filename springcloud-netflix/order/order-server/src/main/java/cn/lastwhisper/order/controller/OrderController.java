package cn.lastwhisper.order.controller;

import cn.lastwhisper.order.convert.OrderForm2OrderDTOConvert;
import cn.lastwhisper.order.dto.OrderDTO;
import cn.lastwhisper.order.enums.ResultEnum;
import cn.lastwhisper.order.exception.OrderException;
import cn.lastwhisper.order.form.OrderForm;
import cn.lastwhisper.order.service.OrderService;
import cn.lastwhisper.order.utils.ResultVOUtil;
import cn.lastwhisper.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 订单
 * @author lastwhisper
 * @date 2019/10/26
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *  保存主订单以及订单详情
     * 1.参数校验（）
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        // 1.参数校验
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车商品信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        // 2.创建订单
        OrderDTO order = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", order.getOrderId());

        return ResultVOUtil.success(map);

    }

    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }

}
