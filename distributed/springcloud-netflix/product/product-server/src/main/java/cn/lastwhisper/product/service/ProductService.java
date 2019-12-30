package cn.lastwhisper.product.service;

import cn.lastwhisper.product.domain.ProductInfo;
import cn.lastwhisper.product.dto.CartDTO;

import java.util.List;

/**
 * @author Administrator
 */
public interface ProductService {

    /**
     * 查询所有在架商品
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     *  1.先检查商品是否存在
     *  2.再检查库存是否充足
     *  3.有一项不通过抛异常回滚事务
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
