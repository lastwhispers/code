package cn.cunchang.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Guava的Cache
 * @author lastwhisper
 * @date 2020/6/2
 */
public class TestCache {

    // https://github.com/zhuzhenke/common-caches
    @Test
    public void testCacheUse() throws Exception{

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(1000)
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {

                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                });

        System.out.println(loadingCache.get("key"));


    }

}
