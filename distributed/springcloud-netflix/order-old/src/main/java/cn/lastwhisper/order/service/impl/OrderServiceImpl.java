package cn.lastwhisper.order.service.impl;

import cn.lastwhisper.order.client.ProductClient;
import cn.lastwhisper.order.domain.OrderDetail;
import cn.lastwhisper.order.domain.OrderMaster;
import cn.lastwhisper.order.domain.ProductInfo;
import cn.lastwhisper.order.dto.CartDTO;
import cn.lastwhisper.order.dto.OrderDTO;
import cn.lastwhisper.order.enums.OrderStatusEnum;
import cn.lastwhisper.order.enums.PayStatusEnum;
import cn.lastwhisper.order.repository.OrderDetailRepository;
import cn.lastwhisper.order.repository.OrderMasterRepository;
import cn.lastwhisper.order.service.OrderService;
import cn.lastwhisper.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lastwhisper
 * @date 2019/10/26
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        // 参数校验?controller层做
        // 1.查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        // 2.计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(BigDecimal.valueOf(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    // 保存订单详情
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 3.扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(o->new CartDTO(o.getProductId(),o.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);
        // 4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        //相同字段
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //不同字段
        orderMaster.setOrderAmount(BigDecimal.valueOf(5499));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

}
