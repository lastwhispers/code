package cn.lastwhisper.product.repository;

import cn.lastwhisper.product.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author lastwhisper
 * @date 2019/10/24
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 根据商品状态获取商品列表
     * 状态, 0正常1下架.
     *
     */
    public List<ProductInfo> findByProductStatus(Integer productStatus);

    public List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
