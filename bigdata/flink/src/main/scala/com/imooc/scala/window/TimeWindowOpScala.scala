package com.imooc.scala.window

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * TimeWindow的使用
 * 1：滚动窗口
 * 2：滑动窗口
 * Created by xuwei
 */
object TimeWindowOpScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("bigdata04", 9001)

    import org.apache.flink.api.scala._

    //TimeWindow之滚动窗口：每隔10秒计算一次前10秒时间窗口内的数据
    /*text.flatMap(_.split(" "))
      .map((_,1))
      .keyBy(0)
      //窗口大小
      .timeWindow(Time.seconds(10))
      .sum(1)
      .print()*/

    //TimeWindow之滑动窗口：每隔5秒计算一次前10秒时间窗口内的数据
    text.flatMap(_.split(" "))
        .map((_,1))
        .keyBy(0)
        //第一个参数：窗口大小，第二个参数：滑动间隔
        .timeWindow(Time.seconds(10),Time.seconds(5))
        .sum(1)
        .print()

    env.execute("TimeWindowOpScala")

  }

}
