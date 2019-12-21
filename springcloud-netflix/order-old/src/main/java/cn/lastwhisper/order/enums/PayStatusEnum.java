package cn.lastwhisper.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 支付状态枚举
 * @author lastwhisper
 * @date 2019/10/26
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    /**
     * 等待支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;
}
