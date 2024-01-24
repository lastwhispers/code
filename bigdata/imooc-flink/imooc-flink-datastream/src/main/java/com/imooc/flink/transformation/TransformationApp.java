package com.imooc.flink.transformation;

import com.imooc.flink.source.AccessSource;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;

public class TransformationApp {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


//        map(env);
//        filter(env);
//        flatMap(env);
//        keyBy(env);
//        reduce(env);

//        richMap(env);
//        union(env);
//        connect(env);
//        coMap(env);
        coFlatMap(env);

        env.execute("SourceApp");
    }

    public static void coFlatMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> stream1 = env.fromElements("a b c", "d e f");
        DataStreamSource<String> stream2 = env.fromElements("1,2,3", "4,5,6");

        stream1.connect(stream2)
                .flatMap(new CoFlatMapFunction<String, String, String>() {
                    @Override
                    public void flatMap1(String value, Collector<String> out) throws Exception {
                        String[] splits = value.split(" ");
                        for(String split : splits) {
                            out.collect(split);
                        }
                    }

                    @Override
                    public void flatMap2(String value, Collector<String> out) throws Exception {
                        String[] splits = value.split(",");
                        for(String split : splits) {
                            out.collect(split);
                        }
                    }
                }).print();
    }

    public static void coMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> stream1 = env.socketTextStream("localhost", 9527);
        SingleOutputStreamOperator<Integer> stream2 = env.socketTextStream("localhost", 9528) // 数值类型
                .map(new MapFunction<String, Integer>() {
                    @Override
                    public Integer map(String value) throws Exception {
                        return Integer.parseInt(value);
                    }
                });

        // 将2个流连接在一起
        stream1.connect(stream2).map(new CoMapFunction<String, Integer, String>() {

            // 处理第一个流的业务逻辑
            @Override
            public String map1(String value) throws Exception {
                return value.toUpperCase();
            }

            // 处理第二个流的业务逻辑
            @Override
            public String map2(Integer value) throws Exception {
                return value * 10 + "";
            }
        }).print();
    }

    /**
         * union 多流合并  数据结构必须相同
         * connect 双流  数据结构可以不同， 更加灵活
         */
    public static void connect(StreamExecutionEnvironment env) {

        DataStreamSource<Access> stream1 = env.addSource(new AccessSource());
        DataStreamSource<Access> stream2 = env.addSource(new AccessSource());

        SingleOutputStreamOperator<Tuple2<String, Access>> stream2new = stream2.map(new MapFunction<Access, Tuple2<String, Access>>() {
            @Override
            public Tuple2<String, Access> map(Access value) throws Exception {
                return Tuple2.of("pk", value);
            }
        });

        stream1.connect(stream2new).map(new CoMapFunction<Access, Tuple2<String,Access>, String>() {
            @Override
            public String map1(Access value) throws Exception {
                return value.toString();
            }

            @Override
            public String map2(Tuple2<String, Access> value) throws Exception {
                return value.f0 + "==>" + value.f1.toString();
            }
        }).print();

//        ConnectedStreams<Access, Access> connect = stream1.connect(stream2);
//
//        connect.map(new CoMapFunction<Access, Access, Access>() {
//            @Override
//            public Access map1(Access value) throws Exception {
//                return value;
//            }
//
//            @Override
//            public Access map2(Access value) throws Exception {
//                return value;
//            }
//        }).print();

    }


    public static void union(StreamExecutionEnvironment env) {

        DataStreamSource<String> stream1 = env.socketTextStream("localhost", 9527);
        DataStreamSource<String> stream2 = env.socketTextStream("localhost", 9528);

        DataStream<String> union = stream1.union(stream2);
//        stream1.union(stream2).print();
        stream1.union(stream1).print();
    }

    public static void richMap(StreamExecutionEnvironment env) {
        env.setParallelism(3);
        DataStreamSource<String> source = env.readTextFile("data/access.log");

        SingleOutputStreamOperator<Access> mapStream = source.map(new PKMapFunction());
        mapStream.print();

    }

        /**
         * wc： socket
         *
         * 进来的数据：pk,pk,flink   pk,spark,spark
         *
         *
         * wc需求分析：
         * 1) 读进来数据
         * 2) 按照指定分隔符进行拆分  pk pk flink pk spark spark
         * 3) 为每个单词赋上一个出现次数为1的值 (pk,1) (pk,1) ...
         * 4) 按照单词进行keyBy
         * 5) 分组求和
         *
         */
    public static void reduce(StreamExecutionEnvironment env) {

        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);

        source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] splits = value.split(",");
                for(String word : splits) {
                    out.collect(word);
                }
            }
        }).map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return Tuple2.of(value, 1);
            }
        }).keyBy(x -> x.f0) // word相同的都会分到一个task中去执行
           .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
               @Override
               public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                   return Tuple2.of(value1.f0, value1.f1 + value2.f1);
               }
           }).print();
    }

        /**
         * 按照domain分组，求traffic和
         */
    public static void keyBy(StreamExecutionEnvironment env) {
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

        mapStream.keyBy("domain").sum("traffic").print();
//        mapStream.keyBy(new KeySelector<Access, String>() {
//            @Override
//            public String getKey(Access value) throws Exception {
//                return value.getDomain();
//            }
//        }).sum("traffic").print();
//        KeyedStream<Access, String> keyedStream = mapStream.keyBy(Access::getDomain);
//        keyedStream.sum("traffic").print();
    }

        /**
         * 进来是一行行的数据： pk,pk,flink   pk,spark,spark
         * 需求：
         * 1) 把一行数据按照逗号进行分割
         * 2) 过滤掉pk
         */
    public static void flatMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);
        source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] splits = value.split(",");
                for(String split : splits) {
                    out.collect(split);
                }
            }
        }).filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return !"pk".equals(value);
            }
        }).print();
    }
        /**
         * filter 就是对DataStream中的数据进行过滤操作
         * 保留true
         */
    public static void filter(StreamExecutionEnvironment env) {
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

        SingleOutputStreamOperator<Access> filterStream = mapStream.filter(new FilterFunction<Access>() {
            @Override
            public boolean filter(Access value) throws Exception {
                return value.getTraffic() > 4000;
            }
        });

        filterStream.print();
    }

        /**
         * 读进来的数据是一行行的，也字符串类型
         *
         * 每一行数据 ==> Access
         *
         * 将map算子对应的函数作用到DataStream，产生一个新的DataStream
         *
         * map会作用到已有的DataStream这个数据集中的每一个元素上
         *
         */
    public static void map(StreamExecutionEnvironment env) {

//        DataStreamSource<String> source = env.readTextFile("data/access.log");
//
//        SingleOutputStreamOperator<Access> mapStream = source.map(new MapFunction<String, Access>() {
//            @Override
//            public Access map(String value) throws Exception {
//                String[] splits = value.split(",");
//                Long time = Long.parseLong(splits[0].trim());
//                String domain = splits[1].trim();
//                Double traffic = Double.parseDouble(splits[2].trim());
//
//                return new Access(time, domain, traffic);
//            }
//        });
//
//        mapStream.print();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);  // map  * 2 = 2
        list.add(2);  // map  * 2 = 4
        list.add(3);  // map  * 2 = 6
        DataStreamSource<Integer> source = env.fromCollection(list);

        source.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer value) throws Exception {
                return value * 2;
            }
        }).print();

    }
}
