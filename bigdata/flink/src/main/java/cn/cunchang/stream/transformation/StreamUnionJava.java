package cn.cunchang.stream.transformation;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * 合并多个流，多个流的数据类型必须一致
 * 应用场景：多种数据源的数据类型一致，数据处理规则也一致
 *
 * Created by xuwei
 */
public class StreamUnionJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //第1份数据流
        DataStreamSource<Integer> text1 = env.fromCollection(Arrays.asList(1, 2, 3, 4, 5));
        //第2份数据流
        DataStreamSource<Integer> text2 = env.fromCollection(Arrays.asList(6, 7, 8, 9, 10));

        //合并流
        DataStream<Integer> unionStream = text1.union(text2);

        //打印流中的数据
        unionStream.print().setParallelism(1);

        env.execute("StreamUnionJava");
    }
}
