package cn.lastwhisper.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author lastwhisper
 * @date 2019/11/3
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;
    private String msg;
}
