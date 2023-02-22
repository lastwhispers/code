package com.imooc.scala.kafkaconnector

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
 * Flink从kafka中消费数据
 * Created by xuwei
 */
object StreamKafkaSourceScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //每隔5000 ms执行一次checkpoint(设置checkpoint的周期)
    env.enableCheckpointing(5000)

    //针对checkpoint的相关配置
    //设置模式为.EXACTLY_ONCE (这是默认值) ,还可以设置为AT_LEAST_ONCE
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    //确保两次Checkpoint之间有至少多少 ms的间隔(checkpoint最小间隔)
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500)
    //Checkpoint必须在一分钟内完成，或者被丢弃(checkpoint的超时时间)
    env.getCheckpointConfig.setCheckpointTimeout(60000)
    //同一时间只允许执行一个Checkpoint
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    //表示一旦Flink处理程序被cancel后，会保留Checkpoint数据，以便根据实际需要恢复到指定的Checkpoint
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    //设置状态数据存储的位置
    env.setStateBackend(new RocksDBStateBackend("hdfs://bigdata01:9000/flink/checkpoints",true))


    //指定FlinkKafkaConsumer相关配置
    val topic = "t1"
    val prop = new Properties()
    prop.setProperty("bootstrap.servers","bigdata01:9092,bigdata02:9092,bigdata03:9092")
    prop.setProperty("group.id","con1")
    val kafkaConsumer = new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), prop)

    //kafka consumer的消费策略设置
    //默认策略，读取group.id对应保存的offset开始消费数据，读取不到则根据kafka中auto.offset.reset参数的值开始消费数据
    kafkaConsumer.setStartFromGroupOffsets()
    //从最早的记录开始消费数据，忽略已提交的offset信息
    //kafkaConsumer.setStartFromEarliest()
    //从最新的记录开始消费数据，忽略已提交的offset信息
    //kafkaConsumer.setStartFromLatest()
    //从指定的时间戳开始消费数据，对于每个分区，其时间戳大于或等于指定时间戳的记录将被作为起始位置
    //kafkaConsumer.setStartFromTimestamp(176288819)


    //指定kafka作为source
    import org.apache.flink.api.scala._
    val text = env.addSource(kafkaConsumer)

    //将读取到的数据打印到控制台上
    text.print()

    env.execute("StreamKafkaSourceScala")
  }

}
