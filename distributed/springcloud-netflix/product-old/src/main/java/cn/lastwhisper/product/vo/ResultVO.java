package cn.lastwhisper.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * http响应最外层数据
 * @author lastwhisper
 * @date 2019/10/26
 */
@Getter
@Setter
@NoArgsConstructor
public class ResultVO<T> {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应内容
     */
    private T data;
}
