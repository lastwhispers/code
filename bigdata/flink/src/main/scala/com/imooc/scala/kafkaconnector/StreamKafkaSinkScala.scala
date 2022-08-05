package com.imooc.scala.kafkaconnector

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.connectors.kafka.internals.KafkaSerializationSchemaWrapper
import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkFixedPartitioner

/**
 * Flink向Kafka中生产数据
 * Created by xuwei
 */
object StreamKafkaSinkScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //开启checkpoint
    env.enableCheckpointing(5000)


    val text = env.socketTextStream("bigdata04", 9001)

    //指定FlinkKafkaProducer的相关配置
    val topic = "t3"
    val prop = new Properties()
    prop.setProperty("bootstrap.servers","bigdata01:9092,bigdata02:9092,bigdata03:9092")

    //指定kafka作为sink
    /*
     KafkaSerializationSchemaWrapper的几个参数
     1：topic：指定需要写入的topic名称即可
     2：partitioner，通过自定义分区器实现将数据写入到指定topic的具体分区中
     默认会使用FlinkFixedPartitioner，它表示会将所有的数据都写入指定topic的一个分区里面
     如果不想自定义分区器，也不想使用默认的，可以直接使用null即可
     3：writeTimeStamp，向topic中写入数据的时候，是否写入时间戳
     如果写入了，那么在watermark的案例中，使用extractTimestamp()提起时间戳的时候
     就可以直接使用recordTimestamp即可，它表示的就是我们在这里写入的数据对应的timestamp
     */
    val kafkaProducer = new FlinkKafkaProducer[String](topic, new KafkaSerializationSchemaWrapper[String](topic, null, false, new SimpleStringSchema()), prop, FlinkKafkaProducer.Semantic.EXACTLY_ONCE)
    text.addSink(kafkaProducer)

    env.execute("StreamKafkaSinkScala")

  }

}
