package cn.lastwhisper.learn8.util.collection;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap
 * @author lastwhisper
 * @date 2020/4/13
 */
@Slf4j
public class LinkedHashMapTest {

    /**
     * 最近最少使用到的（least recently used）
     * LinkedHashMap继承HashMap，LinkedHashMap实现LRU核心方法：
     *
     *  LinkedHashMap队头是最近最少使用的结点，队尾是最近最常使用的
     *
     *  void afterNodeAccess(Node<K,V> p) { }
     *      HashMap putVal的元素已经存在时回调，当前该节点移动到队尾
     *
     *  void afterNodeInsertion(boolean evict) { }
     *      HashMap putVal时回调
     *
     *      boolean removeEldestEntry(Map.Entry<K,V> eldest)
     *         留着用户重写，默认返回false。返回true时，删除最近最少使用的元素，也就是队头。
     *
     *  void afterNodeRemoval(Node<K,V> p) { }
     *      HashMap remove时回调，删除HashMap remove的元素
     *
     *
     */

    /**
     * 稳定性
     */
    @Test
    public void testInsertOrder() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>() {{
            put(10, 10);
            put(9, 9);
            put(20, 20);
            put(1, 1);
        }};
        Assert.assertNotNull(map.get(9));
        Assert.assertNotNull(map.get(20));
        Assert.assertNotNull(map.get(9));
        log.info(JSON.toJSONString(map));

    }

    /**
     * lru
     */
    @Test
    public void testAccessOrder() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(4,0.75f,true) {
            {
                put(10, 10);
                put(9, 9);
                put(20, 20);
                put(1, 1);
            }
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > 3;
            }
        };

        log.info("初始化：{}",JSON.toJSONString(map));
        Assert.assertNotNull(map.get(9));
        log.info("map.get(9)：{}",JSON.toJSONString(map));
        Assert.assertNotNull(map.get(20));
        log.info("map.get(20)：{}",JSON.toJSONString(map));

    }

    @Test
    public void testIterator(){
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(4,0.75f,true) {
            {
                put(10, 10);
                put(9, 9);
                put(20, 20);
                put(1, 1);
            }
        };

        log.info("迭代访问 start");
        Iterator<Map.Entry<Integer,Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,Integer> entry = iterator.next();
            log.info(JSON.toJSONString(entry));
        }
        log.info("迭代访问 end");
    }

}
