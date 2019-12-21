package cn.lastwhisper.product.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 对应CartDTO
 * @author lastwhisper
 * @date 2019/10/30
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseStockInput {
    private String productId;

    private Integer productQuantity;
}
