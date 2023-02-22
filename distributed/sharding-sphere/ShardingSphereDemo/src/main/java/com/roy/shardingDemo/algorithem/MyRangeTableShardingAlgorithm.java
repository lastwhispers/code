package com.roy.shardingDemo.algorithem;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/6
 * @description:
 **/

public class MyRangeTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    /**
     * select * from course where cid between 1 and 100;
     * @param availableTargetNames 真实表 course_1、course_2
     * @param shardingValue 范围条件值
     * @return 查询条件选中的真实表 course_1、course_2
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {

        Long upperVal = shardingValue.getValueRange().upperEndpoint();//100
        Long lowerVal = shardingValue.getValueRange().lowerEndpoint();//1

        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName+"_1",logicTableName+"_2");
    }
}
