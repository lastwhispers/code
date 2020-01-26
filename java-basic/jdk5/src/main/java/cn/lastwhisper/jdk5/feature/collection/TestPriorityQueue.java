package cn.lastwhisper.jdk5.feature.collection;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先队列
 * @author lastwhisper
 * @date 1/18/2020
 */
public class TestPriorityQueue {

    //自定义比较器，降序排列
    static Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer e1, Integer e2) {
            return e2 - e1;
        }
    };

    /**
     * peek()//返回队首元素
     * poll()//返回队首元素，队首元素出队列
     * add()//添加元素
     * size()//返回队列元素个数
     * isEmpty()//判断队列是否为空，为空返回true,不空返回false
     */
    @Test
    public void testTPriority() {
        //不用比较器，默认升序排列
        Queue<Integer> q = new PriorityQueue<>();
        q.add(3);
        q.add(2);
        q.add(4);
        while (!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
        System.out.println();
        /*
         * 输出结果
         * 2 3 4
         */
        //使用自定义比较器，降序排列
        Queue<Integer> qq = new PriorityQueue<>(comparator);
        qq.add(3);
        qq.add(2);
        qq.add(4);
        while (!qq.isEmpty()) {
            System.out.print(qq.poll() + " ");
        }
        /*
         * 输出结果
         * 4 3 2
         */
    }

}
