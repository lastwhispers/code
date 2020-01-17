package cn.lastwhisper.leetcode.stackqueue.数据流移动平均值_346_简单;

import java.util.LinkedList;
/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 */
public class MovingAverage {
    private LinkedList<Integer> dequeue = new LinkedList<>();
    private int size;
    private long sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        if (dequeue.size() == size) sum -= dequeue.removeFirst();
        dequeue.addLast(val);
        sum += val;
        // System.out.printf("size=%d, val=%d, dequeue=%s, sum=%d\n", size, val, dequeue, sum);
        return (double)sum / dequeue.size();
    }

}