package cn.cunchang.stream.transformation;

import org.apache.flink.api.common.functions.Partitioner;

/**
 * 自定义分区规则：按照数字的奇偶性进行分区
 * Created by xuwei
 */
public class MyPartitionerJava implements Partitioner<Integer> {

    @Override
    public int partition(Integer key, int numPartitions) {
        System.out.println("key:" + key + "，分区总数:" + numPartitions);
        if (key % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
