package cn.lastwhisper.bloomfilter;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Random;

/**
 * 原文：https://www.iteye.com/blog/imtinx-1290636
 * Long类型元素的布隆过滤器
 */
class BloomFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int ELEM_NUM = 1000; // 欲容纳的元素个数
    private static final double PERCENTAGE = 0.001; // 希望的误差率
    private int hashNum; // hash函数的数量
    private int size; // 位向量的长度
    private BitSet bitVector; // 位向量

    public BloomFilter() {
        size = (int) Math.abs(ELEM_NUM * Math.log(PERCENTAGE)
                / (Math.log(2) * Math.log(2))) + 1;
        hashNum = (int) (Math.log(2) * ((double) size / ELEM_NUM));
        bitVector = new BitSet(size);
    }

    /**
     * 查找元素是否在集合中
     */
    public boolean search(Long elem) {
        boolean flag = true;
        int temp;
        Random random = new Random(elem);
        for (int i = 0; i < hashNum; i++) {
            temp = random.nextInt(size);
            if (!bitVector.get(temp)) {// 元素不在集合中
                bitVector.set(temp);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 获取位向量的长度
     */
    public int size() {
        return bitVector.size();
    }


    public int getHashNum() {
        return hashNum;
    }

    public void setHashNum(int hashNum) {
        this.hashNum = hashNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BitSet getBitVector() {
        return bitVector;
    }

    public void setBitVector(BitSet bitVector) {
        this.bitVector = bitVector;
    }
}