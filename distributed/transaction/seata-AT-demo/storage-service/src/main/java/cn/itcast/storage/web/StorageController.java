package cn.itcast.storage.web;

import cn.itcast.storage.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 虎哥
 */
@RestController
@RequestMapping("storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 扣减库存
     * @param code 商品编号
     * @param count 要扣减的数量
     * @return 无
     */
    @PutMapping("/{code}/{count}")
    public ResponseEntity<Void> deduct(@PathVariable("code") String code,@PathVariable("count") Integer count){
        storageService.deduct(code, count);
        return ResponseEntity.noContent().build();
    }
}
