package com.imooc.scala.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * cross：获取两个数据集的笛卡尔积
 * Created by xuwei
 */
object BatchCrossScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //初始化第一份数据
    val text1 = env.fromCollection(Array(1, 2))
    //初始化第二份数据
    val text2 = env.fromCollection(Array("a", "b"))

    //执行cross操作
    text1.cross(text2).print()
  }

}
