package cn.lastwhisper.order.client;

import cn.lastwhisper.order.domain.ProductInfo;
import cn.lastwhisper.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *
 * @author lastwhisper
 * @date 2019/10/28
 */
@FeignClient("product")
public interface ProductClient {

    @GetMapping("/msg")
    public String productMsg();

    @PostMapping("/product/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
