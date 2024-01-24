package com.imooc.flink.app;

import com.alibaba.fastjson.JSON;
import com.imooc.flink.domain.Access;
import com.imooc.flink.udf.GaodeLocationMapFunction;
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
 * 按照省份维度进行新老用户的统计分析
 */
public class ProvinceUserCntAppV1 {

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
                })
                .map(new GaodeLocationMapFunction());

        SingleOutputStreamOperator<Tuple3<String, Integer, Integer>> result = cleanStream.map(new MapFunction<Access, Tuple3<String, Integer, Integer>>() {
            @Override
            public Tuple3<String, Integer, Integer> map(Access value) throws Exception {
                return Tuple3.of(value.province, value.nu, 1);
            }
        }).keyBy(new KeySelector<Tuple3<String, Integer, Integer>, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> getKey(Tuple3<String, Integer, Integer> value) throws Exception {
                return Tuple2.of(value.f0, value.f1);
            }
        }).sum(2);//.print("省份维度统计新老用户:").setParallelism(1);


        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();

        result.addSink(new RedisSink<Tuple3<String, Integer,Integer>>(conf, new RedisExampleMapper()));


        environment.execute("ProvinceUserCntAppV1");

    }

    static class RedisExampleMapper implements RedisMapper<Tuple3<String, Integer, Integer>> {

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "province-user-cnt:20301002");
        }

        @Override
        public String getKeyFromData(Tuple3<String, Integer, Integer> data) {
            return data.f0 + "_" + data.f1;
        }

        @Override
        public String getValueFromData(Tuple3<String, Integer, Integer> data) {
            return data.f2+"";
        }

    }
}
