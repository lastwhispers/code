package cn.cunchang.kafkaconnector;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaSerializationSchemaWrapper;

import java.util.Properties;

/**
 * Flink向Kafka中生产数据
 * Created by xuwei
 */
public class StreamKafkaSinkJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> text = env.socketTextStream("bigdata04", 9001);

        //指定FlinkKafkaProducer相关配置
        String topic = "t3";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","bigdata01:9092,bigdata02:9092,bigdata03:9092");

        //指定kafak作为sink
        FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer<>(topic, new KafkaSerializationSchemaWrapper<String>(topic, null, false, new SimpleStringSchema()), prop, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);
        text.addSink(kafkaProducer);

        env.execute("StreamKafkaSinkJava");

    }
}
