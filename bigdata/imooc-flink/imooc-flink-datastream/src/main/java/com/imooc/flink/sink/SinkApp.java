package com.imooc.flink.sink;

import com.imooc.flink.transformation.Access;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;

public class SinkApp {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        toMySQL(env);

        env.execute("SinkApp");
    }

    public static void toMySQL(StreamExecutionEnvironment env) {

        DataStreamSource<String> source = env.readTextFile("data/access.log");

        SingleOutputStreamOperator<Access> mapStream = source.map(new MapFunction<String, Access>() {
            @Override
            public Access map(String value) throws Exception {
                String[] splits = value.split(",");
                Long time = Long.parseLong(splits[0].trim());
                String domain = splits[1].trim();
                Double traffic = Double.parseDouble(splits[2].trim());

                return new Access(time, domain, traffic);
            }
        });

        SingleOutputStreamOperator<Access> result = mapStream.keyBy(new KeySelector<Access, String>() {
            @Override
            public String getKey(Access value) throws Exception {
                return value.getDomain();
            }
        }).sum("traffic");

        result.print();

        // mysql
        result.map(new MapFunction<Access, Tuple2<String, Double>>() {
            @Override
            public Tuple2<String, Double> map(Access value) throws Exception {
                return Tuple2.of(value.getDomain(), value.getTraffic());
            }
        }).addSink(new PKMySQLSink());

        // redis
//        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
//        result.map(new MapFunction<Access, Tuple2<String, Double>>() {
//            @Override
//            public Tuple2<String, Double> map(Access value) throws Exception {
//                return Tuple2.of(value.getDomain(), value.getTraffic());
//            }
//        }).addSink(new RedisSink<Tuple2<String, Double>>(conf, new PKRedisSink()));
    }


    public static void print(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);

        System.out.println("source:" + source.getParallelism());

        source.print().setParallelism(2);
    }
}
