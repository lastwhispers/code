package cn.lastwhisper.order.convert;

import cn.lastwhisper.order.domain.OrderDetail;
import cn.lastwhisper.order.dto.OrderDTO;
import cn.lastwhisper.order.enums.ResultEnum;
import cn.lastwhisper.order.exception.OrderException;
import cn.lastwhisper.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lastwhisper
 * @date 2019/10/28
 */
@Slf4j
public class OrderForm2OrderDTOConvert {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = null;
        Gson gson = new Gson();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            //  throw new OrderException(ResultEnum.PARAM_ERROR.getMsg(), ResultEnum.PARAM_ERROR.getCode());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
