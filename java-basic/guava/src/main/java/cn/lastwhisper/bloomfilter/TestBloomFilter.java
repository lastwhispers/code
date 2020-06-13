package cn.lastwhisper.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

public class TestBloomFilter {

    public static void main(String[] args) {
        Funnel<Integer> funnel = Funnels.integerFunnel();
        int size = 1000_000;
        double errorChance = 0.001;        //错误率
        BloomFilter<Integer> filter = BloomFilter.create(funnel, size, errorChance);
        for (int i = 0; i < size; i++) {
            filter.put(i);
        }
        for (int i = 0; i < size; i++) {
            if (!filter.mightContain(i)) {
                System.out.println("发现不存在的数据 : " + i);
            }
        }
    }
}