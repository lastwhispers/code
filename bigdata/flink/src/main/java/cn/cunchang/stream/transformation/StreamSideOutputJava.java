package cn.cunchang.stream.transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.Arrays;

/**
 * 使用sideoutput切分流
 * Created by xuwei
 */
public class StreamSideOutputJava {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> text = env.fromCollection(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        //按照数据的奇偶性对数据进行分流
        //首先定义两个sideoutput来保存切分出来的数据
        OutputTag<Integer> outputTag1 = new OutputTag<Integer>("even") {
        };//保存偶数
        OutputTag<Integer> outputTag2 = new OutputTag<Integer>("odd") {
        };//保存奇数

        SingleOutputStreamOperator<Integer> outputStream = text.process(new ProcessFunction<Integer, Integer>() {

            @Override
            public void processElement(Integer value, Context ctx, Collector<Integer> out)
                    throws Exception {
                if (value % 2 == 0) {
                    ctx.output(outputTag1, value);
                } else {
                    ctx.output(outputTag2, value);
                }
            }
        });

        //获取偶数数据流
        DataStream<Integer> evenStream = outputStream.getSideOutput(outputTag1);

        //对evenStream流进行二次切分
        OutputTag<Integer> outputTag11 = new OutputTag<Integer>("low") {
        };
        OutputTag<Integer> outputTag12 = new OutputTag<Integer>("high") {
        };
        SingleOutputStreamOperator<Integer> subOutputStream = evenStream.process(new ProcessFunction<Integer, Integer>() {

            @Override
            public void processElement(Integer value, Context ctx, Collector<Integer> out)
                    throws Exception {
                if (value <= 5) {
                    ctx.output(outputTag11, value);
                } else {
                    ctx.output(outputTag12, value);
                }
            }
        });

        //获取小于等于5的数据流
        DataStream<Integer> lowStream = subOutputStream.getSideOutput(outputTag11);
        //获取大于5的数据流
        DataStream<Integer> highStream = subOutputStream.getSideOutput(outputTag12);
//        lowStream.print().setParallelism(1);


        //获取奇数数据流
        DataStream<Integer> oddStream = outputStream.getSideOutput(outputTag2);
        SingleOutputStreamOperator<Integer> filterStream = oddStream.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer value) throws Exception {
                if (value <= 5) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        filterStream.print().setParallelism(1);


        env.execute("StreamSideOutputJava");
    }
}
