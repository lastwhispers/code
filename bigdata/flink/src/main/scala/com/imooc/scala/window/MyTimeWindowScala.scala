package com.imooc.scala.window

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * 需求：自定义MyTimeWindow
 * Created by xuwei
 */
object MyTimeWindowScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("bigdata04", 9001)

    import org.apache.flink.api.scala._

    //自定义MyTimeWindow滚动窗口：每隔10秒计算一次前10秒时间窗口内的数据
    text.flatMap(_.split(" "))
      .map((_,1))
      .keyBy(0)
      //窗口大小
      .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
      .sum(1)
      .print()

    env.execute("MyTimeWindowScala")
  }
}
