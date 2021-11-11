package cn.cunchang.stream.transformation;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * 只能连接两个流，两个流的数据类型可以不同
 * 应用：可以将两种不同格式的数据统一成一种格式
 * Created by xuwei
 */
public class StreamConnectJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //第1份数据流
        DataStreamSource<String> text1 = env.fromElements("user:tom,age:18");
        //第2份数据流
        DataStreamSource<String> text2 = env.fromElements("user:jack;age:18");

        //连接两个流
        ConnectedStreams<String, String> connectStream = text1.connect(text2);

        connectStream.map(new CoMapFunction<String, String, String>() {

            //处理第1份数据流中的数据
            @Override
            public String map1(String value) throws Exception {
                return value.replace(",","-");
            }
            //处理第2份数据流中的数据
            @Override
            public String map2(String value) throws Exception {
                return value.replace(";","-");
            }
        }).print().setParallelism(1);

        env.execute("StreamConnectJava");
    }
}
