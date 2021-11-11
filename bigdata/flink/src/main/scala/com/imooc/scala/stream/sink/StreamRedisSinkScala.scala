package com.imooc.scala.stream.sink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

/**
 * 需求：接收socket传输过来的数据，把数据保存到redis的list队列中
 * Created by xuwei
 */
object StreamRedisSinkScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //连接socket获取输入数据
    val text = env.socketTextStream("bigdata04", 9001)

    import org.apache.flink.api.scala._
    //组装数据，这里组装的是tuple2类型
    //第一个元素是指list队列的key名称
    //第二个元素是指需要向list队列中添加的元素
    val listData = text.map(word => ("l_words_scala", word))

    //指定redissink
    val conf = new FlinkJedisPoolConfig.Builder().setHost("bigdata04").setPort(6379).build()
    val redisSink = new RedisSink[Tuple2[String, String]](conf, new MyRedisMapper)
    listData.addSink(redisSink)

    env.execute("StreamRedisSinkScala")
  }

  class MyRedisMapper extends RedisMapper[Tuple2[String,String]]{
    //指定具体的操作命令
    override def getCommandDescription: RedisCommandDescription = {
      new RedisCommandDescription(RedisCommand.LPUSH)
    }

    //获取key
    override def getKeyFromData(data: (String, String)): String = {
      data._1
    }
    //获取value
    override def getValueFromData(data: (String, String)): String = {
      data._2
    }
  }

}
