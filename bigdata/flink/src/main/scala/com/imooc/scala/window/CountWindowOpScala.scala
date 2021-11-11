package com.imooc.scala.window

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * CountWindow的使用
 * 1：滚动窗口
 * 2：滑动窗口
 * Created by xuwei
 */
object CountWindowOpScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("bigdata04", 9001)

    import org.apache.flink.api.scala._
    /**
     * 注意：由于我们在这里使用keyBy，会先对数据分组
     * 如果某个分组对应的数据窗口内达到了5个元素，这个窗口才会被触发执行
     */
    //CountWindow之滚动窗口：每隔5个元素计算一次前5个元素
    /*text.flatMap(_.split(" "))
      .map((_,1))
      .keyBy(0)
      //指定窗口大小
      .countWindow(5)
      .sum(1)
      .print()*/


    //CountWindow之滑动窗口：每隔1个元素计算一次前5个元素
    text.flatMap(_.split(" "))
        .map((_,1))
        .keyBy(0)
        //第一个参数：窗口大小，第二个参数：滑动间隔
        .countWindow(5,1)
        .sum(1)
        .print()

    env.execute("CountWindowOpScala")

  }
}
