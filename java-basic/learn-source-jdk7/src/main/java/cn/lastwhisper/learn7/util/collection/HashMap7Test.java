package cn.lastwhisper.learn7.util.collection;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 * @author lastwhisper
 * @date 2020/3/26
 */
public class HashMap7Test {

    /*
     * 一、重要参数
     *  1.DEFAULT_INITIAL_CAPACITY = 16 默认哈希表的容量
     *  2.DEFAULT_LOAD_FACTOR = 0.75f 默认负载因子
     *  3.loadFactor 自定义负载因子（一般不会指定，默认0.75f，用来算threshold）
     *  4.size k-v对的数量
     *  5.threshold 阈值，addEntry方法中size>=threshold&&null != table[K的Hash索引]时，哈希表将会扩容。
     *
     * 二、Hash索引：int index = hash(K) % Array.length ===> HashMap的策略HashCode(K) & (Array.length-1)
     * 三、Hash冲突：开放寻址法、拉链法===> HashMap的策略 拉链法
     * 四、为什么数组的大小是2^n？
     *      （1）加快哈希计算
     *      （2）减少哈希冲突
     *      n 为 2 的整数次幂，这样 n-1 后之前为 1 的位后面全是 1，这样就能保证 (n-1) & hash 后相应的位数既可能是 0 又可能是 1，
     *      这取决于 hash 的值，这样能保证散列的均匀，同时与运算效率高如果 n 不是 2 的整数次幂，会造成更多的 hash 冲突
     * 五、hash(K)扰动函数的原理？
     *  JDK 源码中 HashMap 的 hash 方法原理是什么？ - 胖君的回答 - 知乎
     *  https://www.zhihu.com/question/20733617/answer/111577937
     * 六、inflateTable()函数计算threshold的值，inflateTable()会被Map参数构造函数和put()函数调用，计算规则：
     *      （1）threshold初始化，有三种情况：
     *        （a）HashMap 无参构造函数，threshold=DEFAULT_INITIAL_CAPACITY
     *        （b）HashMap initialCapacity参数构造函数，threshold=initialCapacity
     *        （c）HashMap Map参数构造函数，threshold=Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
     *                 DEFAULT_INITIAL_CAPACITY)
     *      （2）int capacity = roundUpToPowerOf2(threshold)，找大于或等于threshold的2的幂次，capacity将作为HashMap内数组的容量
     *      （3）threshold = capacity * loadFactor，threshold值确定
     *
     * 七、初始化容量：initialCapacity=expectSize / 0.75f + 1.0f
     *      比如，需要在HashMap中存储27个K-V。
     *      （1）错误：设置initialCapacity=27，capacity=32，threshold=24=32*0.75=capacity*loadFactor
     *          当你连续存储到满了24个以后，再存入第25个K-V的时候，size=24>=threshold=24，有可能触发扩容。
     *      （2）正确：设置initialCapacity=37=27 / 0.75f + 1.0f，capacity=64，threshold=48=64*0.75=capacity*loadFactor
     *          当你连续存储到满了24个以后，再存入第25个K-V的时候，size=24<threshold=48，不会触发扩容。
     *      （3）不设置参数：initialCapacity=16，capacity=16，threshold=12=16*0.75=capacity*loadFactor
     *          当你连续存储到满了12个以后，再存入第13个K-V的时候，size=12>=threshold=12，有可能触发扩容。
     * 八、put()：
     *      （1）K=null单独处理，存入数组下标0的位置
     *      （2）hash(K)存在&&K存在(内存地址相同||equals)，覆盖值
     *          前两种情况不会触发扩容
     *      （3）K不为前两种情况，满足(size >= threshold) && (null != table[K的Hash索引])
     *          触发扩容
     * 九、resize()与transfer()：put(addEntry)和putAll时会调用
     *     resize()会创建原数组容量两倍的新数组，transfer()负责将老数组的链表转移到新数组
     *     transfer()步骤：
     *      遍历该链表中的结点e，重复1、2、3、4步骤
     *      （1）暂存Entry<K,V> next = e.next
     *      （2）rehash(数组长度变了，需要重新计算hash，找到在新数组的位置)找到e在新数组的位置newTable[i]，将e.next指向newTable[i]
     *      （3）将新数组位置替换成e， newTable[i] = e，完成头插法
     *      （4）e = next;
     * 十、并发读写成环或丢数据
     *
     *  https://blog.csdn.net/Lammonpeter/article/details/104271481
     *  http://www.imooc.com/article/292265
     *  https://zhuanlan.zhihu.com/p/96426441
     *
     *
     */

    // 一个可能要扩容的HashMap
    HashMap<String, String> possibleResizeMap = new HashMap<String, String>() {{
        this.put("1", "1");
        this.put("2", "2");
        this.put("3", "3");
        this.put("4", "4");
        this.put("5", "5");
        this.put("6", "6");
        this.put("7", "7");
        this.put("8", "8");
        this.put("9", "9");
        this.put("10", "10");
        this.put("11", "11");
        this.put("12", "12");
        /*--------------不扩容------------*/
        // size=12 >= threshold=12满足，但是null != table[bucketIndex]不满足
        this.put("13", "13");
        this.put("14", "14");

    }};

    @Test
    public void testHashCode() {

    }

    @Test
    public void testRepetitionK() {
        possibleResizeMap.put("1", "1");
    }

    @Test
    public void testResize() {
        /*--------------扩容------------*/
        // size=14 >= threshold=12满足，同时null != table[bucketIndex]满足
        possibleResizeMap.put("15", "15");
    }

    @Test
    public void testGet() {
        possibleResizeMap.get(null);
        possibleResizeMap.get("14");
        possibleResizeMap.get("15");
    }


}
