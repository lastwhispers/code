package com.imooc.scala.stream.transformation

import org.apache.flink.streaming.api.functions.co.CoMapFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * 只能连接两个流，两个流的数据类型可以不同
 * 应用：可以将两种不同格式的数据统一成一种格式
 * Created by xuwei
 */
object StreamConnectScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //第1份数据流
    val text1 = env.fromElements("user:tom,age:18")
    //第2份数据流
    val text2 = env.fromElements("user:jack_age:20")

    //连接两个流
    val connectStream = text1.connect(text2)

    connectStream.map(new CoMapFunction[String,String,String] {
      //处理第1份数据流中的数据
      override def map1(value: String): String = {
        value.replace(",","-")
      }
      //处理第2份数据流中的数据
      override def map2(value: String): String = {
        value.replace("_","-")
      }
    }).print().setParallelism(1)

    env.execute("StreamConnectScala")
  }
}
