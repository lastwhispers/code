package cn.lastwhisper.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    /*商品不存在*/
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2,"商品库存不足")
    ;

    private Integer code;
    private String msg;


}
