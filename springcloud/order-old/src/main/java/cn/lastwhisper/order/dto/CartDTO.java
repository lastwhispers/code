package cn.lastwhisper.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {

    private String productId;

    private Integer productQuantity;

}
