package com.imooc.flink.wm;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * EventTime结合WM的使用
 *
 * 输入数据的格式：时间字段,单词,次数
 */
public class EventTimeWMApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        test01(env);
        env.execute("EventTimeWMApp");
    }

    public static void test01(StreamExecutionEnvironment env) {
        OutputTag<Tuple2<String,Integer>> outputTag = new OutputTag<Tuple2<String, Integer>>("late-data"){

        };

        SingleOutputStreamOperator<String> lines = env.socketTextStream("localhost", 9527)
                .assignTimestampsAndWatermarks(
                        new BoundedOutOfOrdernessTimestampExtractor<String>(Time.seconds(0)) {
                            @Override
                            public long extractTimestamp(String element) {
                                return Long.parseLong(element.split(",")[0]);
                            }
                        }
                );
        SingleOutputStreamOperator<Tuple2<String, Integer>> mapStream = lines.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                String[] splits = value.split(",");
                return Tuple2.of(splits[1].trim(), Integer.parseInt(splits[2].trim()));
            }
        });

        // [0000,5000) [5000,10000)
        SingleOutputStreamOperator<String> window = mapStream.keyBy(x -> x.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sideOutputLateData(outputTag)
                .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                        System.out.println("-----reduce invoked----" + value1.f0 + "==>" + (value1.f1 + value2.f1));
                        return Tuple2.of(value1.f0, value1.f1 + value2.f1);
                    }
                }, new ProcessWindowFunction<Tuple2<String, Integer>, String, String, TimeWindow>() {

                    FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

                    @Override
                    public void process(String s, Context context, Iterable<Tuple2<String, Integer>> elements, Collector<String> out) throws Exception {
                        for (Tuple2<String, Integer> element : elements) {
                            out.collect("[" + format.format(context.window().getStart()) + "==>" + format.format(context.window().getEnd()) + "], " + element.f0 + "==>" + element.f1);
                        }
                    }
                });
        window.print();
        DataStream<Tuple2<String, Integer>> sideOutput = window.getSideOutput(outputTag);
        sideOutput.printToErr();

        /**
         * WM其实就是延迟触发的一种机制
         * WM = 数据所携带的时间(窗口中最大的时间) - 延迟执行的时间
         * WM >= 上一个窗口的结束边界 就会触发窗口的执行
         * 6999 - 2000  = 4999 >= 上一个窗口的结束边界
         */
    }
}
