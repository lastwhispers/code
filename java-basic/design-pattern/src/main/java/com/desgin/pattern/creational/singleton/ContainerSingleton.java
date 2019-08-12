package com.desgin.pattern.creational.singleton;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by lastwhisper on 2019/1/27
 */
public class ContainerSingleton {

    private static Map<String, Object> singletonMap = new HashMap<String, Object>();

    private ContainerSingleton() {
    }

    public static void putInstance(String key, String instance){
        if(StringUtils.isNoneBlank(key) && instance != null){
            if(!singletonMap.containsKey(key)){
                singletonMap.put(key,instance);
            }
        }
    }
    public static Object getInstance(String key){
        return singletonMap.get(key);
    }

}
