package cn.lastwhisper.learn8.util.concurrent.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap
 * @author lastwhisper
 * @date 2020/4/16
 */
@Slf4j
public class ConcurrentHashMapTest {

    @Test
    public void testRegionSearch() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("1","1");

    }

}
