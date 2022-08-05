package cn.cunchang.stream.transformation;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 根据规则把一个数据流切分为多个数据流
 * 注意：split只能分一次流，切分出来的流不能继续切分
 * split需要和select配合使用，选择切分后的流
 * 应用场景：将一份数据流切分为多份，便于针对每一份数据使用不同的处理逻辑
 * Created by xuwei
 */
public class StreamSplitJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> text = env.fromCollection(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        //按照数据的奇偶性对数据进行分流
        SplitStream<Integer> splitStream = text.split(new OutputSelector<Integer>() {
            @Override
            public Iterable<String> select(Integer value) {
                ArrayList<String> list = new ArrayList<>();
                if (value % 2 == 0) {
                    list.add("even");//偶数
                } else {
                    list.add("odd");//奇数
                }
                return list;
            }
        });

        //选择流
        DataStream<Integer> enevStream = splitStream.select("even");

        enevStream.print().setParallelism(1);

        env.execute("StreamSplitJava");
    }
}
