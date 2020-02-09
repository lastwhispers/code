package cn.lastwhisper.consistenthash;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * https://www.iteye.com/blog/imtinx-1290696
 *
 */
public class ConsistentHash<T> {  
    /** 
     * 计算使用的hash函数，推荐使用MD5 
     */  
    private final Objects hashFunction;
    /** 
     * 虚拟节点的倍数 
     */  
    private final int numberOfReplicas;  
    /** 
     * cache节点环 
     */  
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();  
    public ConsistentHash(Objects hashFunction, int numberOfReplicas,
            Collection<T> nodes) {  
        this.hashFunction = hashFunction;  
        this.numberOfReplicas = numberOfReplicas;  
        for (T node : nodes) {  
            add(node);  
        }  
    }  
    /** 
     * @description    添加ceche服务器节点 
     * @param node     服务器节点，可以用服务器的IP表示 
     * @add date       2011-10-30 
     */  
    public void add(T node) {  
        for (int i = 0; i < numberOfReplicas; i++) {  
            circle.put(hashFunction.hash(node.toString() + i), node);
        }  
    }  
    /** 
     * @description    删除服务器节点 
     * @param node      服务器节点，可以用服务器的IP表示 
     * @add date       2011-10-30 
     */  
    public void remove(T node) {  
        for (int i = 0; i < numberOfReplicas; i++) {  
            circle.remove(hashFunction.hash(node.toString() + i));  
        }  
    }  
    /** 
     * @description    获取对象对应的cache服务器 
     * @param key       需要存储的对象 
     * @return          目标cache服务器 
     * @add date       2011-10-30 
     */  
    public T get(Object key) {  
        if (circle.isEmpty()) {  
            return null;  
        }  
        int hash = hashFunction.hash(key);  
        if (!circle.containsKey(hash)) {  
            //顺时针查找最近的节点  
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);  
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();  
        }  
        return circle.get(hash);  
    }  
}  