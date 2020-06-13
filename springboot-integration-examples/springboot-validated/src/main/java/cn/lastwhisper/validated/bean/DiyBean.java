package cn.lastwhisper.validated.bean;

import cn.lastwhisper.validated.annotation.CannotHaveBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义规则
 * @author lastwhisper
 * @date 2020/5/28
 */
@Getter
@Setter
public class DiyBean {

    @CannotHaveBlank(message = "flag1不能为空")
    private String flag1;

}
