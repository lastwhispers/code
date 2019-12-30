package cn.lastwhisper.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回错误结果枚举
 * @author lastwhisper
 * @date 2019/10/28
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    /*参数错误枚举*/
    PARAM_ERROR(1,"参数错误"),
    CART_EMPTY(2,"购物车为空"),
    ORDER_NOT_EXIST(3,"订单不存在"),
    ORDER_STATUS_ERROR(4,"订单状态错误"),
    ORDER_DETAIL_NOT_EXIST(5,"订单详情不存在")
    ;

    private Integer code;
    private String msg;


}
