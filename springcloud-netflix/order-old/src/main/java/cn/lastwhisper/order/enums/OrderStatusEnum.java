package cn.lastwhisper.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 * @author lastwhisper
 * @date 2019/10/26
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    /**
     * 新订单
     */
    NEW(0,"新订单"),
    /**
     * 订单完结
     */
    FINISHED(1,"完结"),
    /**
     * 订单取消
     */
    CANCEL(2,"取消");
    private Integer code;

    private String message;

}
