package cn.cunchang.window;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * TimeWindow的使用
 * 1：滚动窗口
 * 2：滑动窗口
 * hello
 */
public class TimeWindowOpJava {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text = env.socketTextStream("localhost", 9001);

        //TimeWindow之滚动窗口：每隔10秒计算一次前10秒时间窗口内的数据
//        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
//                    @Override
//                    public void flatMap(String line, Collector<Tuple2<String, Integer>> out)
//                            throws Exception {
//                        String[] words = line.split(" ");
//                        for (String word : words) {
//                            out.collect(new Tuple2<String, Integer>(word, 1));
//                        }
//                    }
//                }).keyBy(0)
//                //窗口大小
//                .timeWindow(Time.seconds(10))
//                .sum(1)
//                .print();

        //TimeWindow之滑动窗口：每隔5秒计算一次前10秒时间窗口内的数据
        text.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> out)
                    throws Exception {
                String[] words = line.split(" ");
                for (String word: words) {
                    out.collect(new Tuple2<String, Integer>(word,1));
                }
            }
        }).keyBy(0)
                //第一个参数：窗口大小，第二个参数：滑动间隔
                .timeWindow(Time.seconds(10),Time.seconds(5))
                .sum(1)
                .print();

        env.execute("TimeWindowOpJava");
    }
}
