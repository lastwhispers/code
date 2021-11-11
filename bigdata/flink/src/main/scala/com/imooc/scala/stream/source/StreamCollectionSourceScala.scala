package com.imooc.scala.stream.source

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * 基于collection的source的应用
 * 注意：这个source的主要应用场景是模拟测试代码流程的时候使用
 * Created by xuwei
 */
object StreamCollectionSourceScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //使用collection集合生成DataStream
    import org.apache.flink.api.scala._
    val text = env.fromCollection(Array(1, 2, 3, 4, 5))

    text.print().setParallelism(1)

    env.execute("StreamCollectionSourceScala")
  }
}
