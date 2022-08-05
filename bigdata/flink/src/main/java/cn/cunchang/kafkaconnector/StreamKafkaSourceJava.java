package cn.cunchang.kafkaconnector;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Flink从kafka中消费数据
 * Created by xuwei
 */
public class StreamKafkaSourceJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //指定FlinkKafkaConsumer相关配置
        String topic = "t1";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","bigdata01:9092,bigdata02:9092,bigdata03:9092");
        prop.setProperty("group.id","con1");
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), prop);

        //指定kafka作为source
        DataStreamSource<String> text = env.addSource(kafkaConsumer);

        //将读取到的数据打印到控制台
        text.print();

        env.execute("StreamKafkaSourceJava");
    }
}
