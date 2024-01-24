package com.imooc.flink.app;

import com.alibaba.fastjson.JSON;
import com.imooc.flink.domain.Access;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * 新老用户的统计分析
 */
public class OsUserCntAppV2 {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        SingleOutputStreamOperator<Access> cleanStream = environment.readTextFile("data/access.json")
                .map(new MapFunction<String, Access>() {
                    @Override
                    public Access map(String value) throws Exception {
                        // TODO...  json ==> Access

                        try {
                            return JSON.parseObject(value, Access.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }

                    }
                }).filter(x -> x != null)
                .filter(new FilterFunction<Access>() {
                    @Override
                    public boolean filter(Access value) throws Exception {
                        return "startup".equals(value.event);
                    }
                });

        cleanStream.map(new MapFunction<Access, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(Access value) throws Exception {
                return Tuple2.of(value.nu, 1);
            }
        }).keyBy(x -> x.f0)
                .sum(1).print("总的新老用户:").setParallelism(1);


        /**
         * (iOS,1,38)
         * (Android,1,29)
         * (iOS,0,17)
         * (Android,0,16)
         *
         * ==>
         * (1, 67)
         * (0, 33)
         */

        environment.execute("OsUserCntAppV2");

    }
}
