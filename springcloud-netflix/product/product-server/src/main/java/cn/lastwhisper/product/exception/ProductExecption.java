package cn.lastwhisper.product.exception;

import cn.lastwhisper.product.enums.ResultEnum;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
public class ProductExecption extends RuntimeException {

    private Integer code;

    public ProductExecption(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductExecption(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }
}
