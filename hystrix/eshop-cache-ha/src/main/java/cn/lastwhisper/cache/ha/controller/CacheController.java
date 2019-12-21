package cn.lastwhisper.cache.ha.controller;

import cn.lastwhisper.cache.ha.http.HttpClientUtils;
import cn.lastwhisper.cache.ha.hystrix.command.GetBrandNameCommand;
import cn.lastwhisper.cache.ha.hystrix.command.GetCityNameCommand;
import cn.lastwhisper.cache.ha.hystrix.command.GetProductInfoCommand;
import cn.lastwhisper.cache.ha.hystrix.command.GetProductInfosCommand;
import cn.lastwhisper.cache.ha.model.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 缓存服务的接口
 * @author Administrator
 *
 */
@Controller
public class CacheController {
    /**
     * Mq异步查询原生db，并将数据更新到各级缓存
     *
     * @param productId
     * @return java.lang.String
     */
    @RequestMapping("/change/product")
    @ResponseBody
    public Object changeProduct(Long productId) {
        // 拿到一个商品id
        // 调用商品服务的接口，获取商品id对应的商品的最新数据
        // 用HttpClient去调用商品服务的http接口
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);

        return "success";
    }

    /**
     * nginx之后的各级缓存都失效了，此时直接访问到最原生的db层了
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public Object getProductInfo(Long productId) {

        GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = null;

        // 同步执行
        productInfo = getProductInfoCommand.execute();

        // 异步执行
        //try {
        //    Future<ProductInfo> queue = getProductInfoCommand.queue();
        //    // 中间可以干别的事情
        //    productInfo = queue.get();
        //} catch (InterruptedException | ExecutionException e) {
        //    e.printStackTrace();
        //}

        // 通过semaphore隔离，从本地地区缓存中获取数据
        Long cityId = Objects.requireNonNull(productInfo).getCityId();
        GetCityNameCommand getCityNameCommand = new GetCityNameCommand(cityId);
        String cityName = getCityNameCommand.execute();
        productInfo.setCityName(cityName);

        // 通过threadPool隔离以及fallback，从本地品牌缓存中获取数据
        Long brandId = productInfo.getBrandId();
        GetBrandNameCommand getBrandNameCommand = new GetBrandNameCommand(brandId);
        String brandName = getBrandNameCommand.execute();
        productInfo.setBrandName(brandName);

        System.out.println(productInfo);
        return productInfo;
        //return "success";
    }

    /**
     * 通过一个HystrixObservableCommand批量获取多条商品信息
     */
    @RequestMapping("/getProductInfos")
    @ResponseBody
    public Object getProductInfos(String productIds) {

        GetProductInfosCommand getProductInfosCommand = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> observable = null;

        // 同步执行，subscribe只是取值而已
        observable = getProductInfosCommand.observe();

        // 延迟执行，等到subscribe时才开始执行construct
        //observable = getProductInfosCommand.toObservable();

        //Action1 忽略completed和error信号
        observable.subscribe(new Observer<ProductInfo>() {

            @Override
            public void onCompleted() {
                System.out.println("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println(productInfo);
            }
        });

        return "success";
    }

    /**
     * 通过多个HystrixCommand批量获取多条商品信息
     */
    @RequestMapping("/getProductInfos2")
    @ResponseBody
    public String getProductInfos2(String productIds) {

        for (String productId : productIds.split(",")) {
            GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(
                    Long.valueOf(productId));
            ProductInfo productInfo = getProductInfoCommand.execute();
            System.out.println(productInfo);
            System.out.println("是否来自缓存 " + getProductInfoCommand.isResponseFromCache());
        }
        return "success";
    }


}
