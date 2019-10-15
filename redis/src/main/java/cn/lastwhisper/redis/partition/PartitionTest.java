package cn.lastwhisper.redis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lastwhisper
 */
public class PartitionTest {
    private final static Object value = new Object();
    private static Integer data = 100;
    private static Map<Integer, Object> node0 = new LinkedHashMap<>();
    private static Map<Integer, Object> node1 = new LinkedHashMap<>();
    private static Map<Integer, Object> node2 = new LinkedHashMap<>();

    private static Map<Integer, Object> node3 = new LinkedHashMap<>();
    private static Map<Integer, Object> node4 = new LinkedHashMap<>();
    private static Map<Integer, Object> node5 = new LinkedHashMap<>();
    private static Map<Integer, Object> node6 = new LinkedHashMap<>();
    private static Map<Integer, Object> node7 = new LinkedHashMap<>();
    private static Map<Integer, Object> node8 = new LinkedHashMap<>();

    private static List<Map<Integer, Object>> oldNodes = new ArrayList<>();
    private static List<Map<Integer, Object>> newNodes = new ArrayList<>();

    static {
        oldNodes.add(node0);
        oldNodes.add(node1);
        oldNodes.add(node2);
        // 3节点扩容到4节点
        newNodes.add(node3);
        newNodes.add(node4);
        newNodes.add(node5);
        newNodes.add(node6);
        // 3节点扩容到6节点
        newNodes.add(node7);
        newNodes.add(node8);
    }

    public static void main(String[] args) {
        // 将请求db的数据，缓存到cache中
        dbToCache(oldNodes);
        // 新增一个节点
        dbToCache(newNodes);

        int count = 0;//数据发生迁移的量

        // 老节点对应新节点，数据发生迁移的量
        for (int i = 0; i < oldNodes.size(); i++) {
            Map<Integer, Object> oldNode = oldNodes.get(i);
            Map<Integer, Object> newNode = newNodes.get(i);
            int oldSize = oldNode.size();
            oldNode.putAll(newNode);
            int newSize = oldNode.size();
            count += newSize - oldSize;
        }
        // 新节点数据量
        for (int i = oldNodes.size(); i < newNodes.size(); i++) {
            count += newNodes.get(i).size();
        }
        System.out.println(count % 100); //3节点——》4节点 迁移率74%；3节点——》6节点迁移率50%

    }

    public static void dbToCache(List<Map<Integer, Object>> cache) {
        for (int k = 1; k <= data; k++) {
            int index = hash(k) % cache.size();
            cache.get(index).put(k, value);
        }
        //print(cache);
    }

    public static void print(List<Map<Integer, Object>> cache) {
        for (int i = 0; i < cache.size(); i++) {
            Map<Integer, Object> node = cache.get(i);
            System.out.println();
            System.out.println("结点" + i + " dbsize：" + node.size());
            System.out.print("结点" + i + " data：");
            for (Map.Entry entry : node.entrySet()) {
                System.out.print(entry.getKey() + ",");
            }
        }
    }


    public static int hash(Integer num) {
        return num.hashCode();
    }
}
