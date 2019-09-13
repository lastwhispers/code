package cn.lastwhisper.jdk5.feature.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lastwhisper
 */
public class SafeHashMapDemo {
    public static void main(String[] args){
        Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> synchronizedMap = Collections.synchronizedMap(hashMap);
        synchronizedMap.put("map","1");
        synchronizedMap.put("list","2");
        System.out.println(synchronizedMap.get("map"));
    }
}
