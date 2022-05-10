package com.roy.shardingDemo.algorithem;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/6
 * @description:
 **/

public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * select * from course where cid = ? or cid in (?,?)
     * @param availableTargetNames 真实表 course_1、course_2
     * @param shardingValue 精确条件值
     * @return 查询条件选中的真实表 course_1 or course_2
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        String cid = shardingValue.getColumnName();
        Long cidValue = shardingValue.getValue();
        //实现 course_$->{cid%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(cidValue);
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key = logicTableName+"_"+resB;
        if(availableTargetNames.contains(key)){
            return key;
        }
        //couse_1, course_2
        throw new UnsupportedOperationException("route "+ key +" is not supported ,please check your config");
    }
}
