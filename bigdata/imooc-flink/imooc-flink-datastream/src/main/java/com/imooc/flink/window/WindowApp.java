package com.imooc.flink.window;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowApp {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        test01(env);

//        test02(env);
//        test03(env);
        env.execute("WindowApp");
    }

    public static void test03(StreamExecutionEnvironment env) {
        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Tuple2<String,Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        return Tuple2.of("pk", Integer.parseInt(value));
                    }
                }).keyBy(x -> x.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new PKProcessWindowFunction())
                .print();
    }


    public static void test02(StreamExecutionEnvironment env) {
        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Tuple2<String,Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        String[] splits = value.split(",");
                        return Tuple2.of(splits[0].trim(), Integer.parseInt(splits[1].trim()));
                    }
                }).keyBy(x -> x.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {

                        System.out.println("value1 = [" + value1 + "], value2 = [" + value2 + "]");
                        return Tuple2.of(value1.f0, value1.f1+value2.f1);
                    }
                })
                .print();
    }


    public static void test01(StreamExecutionEnvironment env) {
        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Integer>() {
                    @Override
                    public Integer map(String value) throws Exception {
                        return Integer.parseInt(value);
                    }
                })
                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .sum(0)
                .print();

//        env.socketTextStream("localhost", 9527)
//                .map(new MapFunction<String, Integer>() {
//                    @Override
//                    public Integer map(String value) throws Exception {
//                        return Integer.parseInt(value);
//                    }
//                })
//                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .sum(0)
//                .print();


        // hadoop,1  spark,1
//        env.socketTextStream("localhost", 9527)
//            .map(new MapFunction<String, Tuple2<String,Integer>>() {
//                @Override
//                public Tuple2<String, Integer> map(String value) throws Exception {
//                    String[] splits = value.split(",");
//                    return Tuple2.of(splits[0].trim(), Integer.parseInt(splits[1].trim()));
//                }
//            }).keyBy(x -> x.f0)
//            .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .sum(1)
//            .print();
    }
}
