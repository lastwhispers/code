package cn.lastwhisper.order.exception;

import cn.lastwhisper.order.enums.ResultEnum;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String message ) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        this(resultEnum.getCode(),resultEnum.getMsg());
    }
}
