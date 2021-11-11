package cn.cunchang.stream.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * 基于collection的source的应用
 * Created by xuwei
 */
public class StreamCollectionSourceJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //使用collection集合生成DataStream
        DataStreamSource<Integer> text = env.fromCollection(Arrays.asList(1, 2, 3, 4, 5));

        text.print().setParallelism(1);

        env.execute("StreamCollectionSourceJava");
    }
}
