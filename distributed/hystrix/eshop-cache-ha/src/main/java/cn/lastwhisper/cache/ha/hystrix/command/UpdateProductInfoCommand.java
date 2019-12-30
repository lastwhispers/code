package cn.lastwhisper.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 清空hystrix的request cache
 * @author lastwhisper
 * @date 2019/12/20
 */
public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private Long productId;

    public UpdateProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productId=productId;
    }

    @Override
    protected Boolean run() throws Exception {
        GetProductInfoCommand.flushCache(productId);
        return true;
    }


}
