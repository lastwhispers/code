package cn.lastwhisper.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品上下架状态枚举
 * @author lastwhisper
 * @date 2019/10/26
 */
@AllArgsConstructor
@Getter
public enum ProductStatusEnum {
    /*在架商品*/
    UP(0, "在架"),
    /*下架商品*/
    DOWN(1, "下架");

    private Integer code;
    private String msg;
}
