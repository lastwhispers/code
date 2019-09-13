package cn.lastwhisper.service;

import cn.lastwhisper.config.ApplicationContextHolder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cat on 2017-03-12.
 */
@Component
public class MenuService {

    /**
     * CacheInterceptor
     *  |--invoke
     *      |--CacheAspectSupport.execute
     *      |--execute cachePutRequest.apply(cacheValue);
     *      |--collectPutRequests
     *      |--apply
     *
     */
    @Cacheable(cacheNames = {"menu"})
    public List<String> getMenuList() {
        System.out.println("");
        System.out.println("mock:get from db");
        return Arrays.asList("article", "comment", "admin");
    }

    // 内部方法调用AOP织入的方法,并不会执行AOP,因为内部方法是this调用的,需要代理对象调用
    //public List<String> getRecommends() {
    //    return this.getMenuList();
    //}

    // 通过ApplicationContext获取代理对象,可以解决
    // 获取新建一个类调用
    public List<String> getRecommends() {
        MenuService proxy = ApplicationContextHolder.getContext().getBean(MenuService.class);
        return proxy.getMenuList();
    }
}
