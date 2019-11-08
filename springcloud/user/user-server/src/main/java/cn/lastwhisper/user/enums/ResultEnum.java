package cn.lastwhisper.user.enums;

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
    LOGIN_FAIL(1,"登录失败"),
    ROLE_ERROR(2,"角色错误")
    ;

    private Integer code;
    private String msg;

}
