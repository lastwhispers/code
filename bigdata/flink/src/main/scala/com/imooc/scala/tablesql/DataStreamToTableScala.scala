package com.imooc.scala.tablesql

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

/**
 * 将DataStream转换为表
 * Created by xuwei
 */
object DataStreamToTableScala {
  def main(args: Array[String]): Unit = {
    //获取StreamTableEnvironment
    val ssEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()
    val ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings)

    //获取DataStream
    import org.apache.flink.api.scala._
    val stream = ssEnv.fromCollection(Array((1, "jack"), (2, "tom"), (3, "mack")))

    //第一种：将DataStream转换为view视图
    import org.apache.flink.table.api._
    ssTableEnv.createTemporaryView("myTable",stream,'id,'name)
    ssTableEnv.sqlQuery("select * from myTable where id > 1").execute().print()


    //第二种：将DataStream转换为Table对象
    val table = ssTableEnv.fromDataStream(stream, $"id", $"name")
    table.select($"id",$"name")
      .filter($"id" > 1)
      .execute()
      .print()

    //注意：'id,'name 和 $"id", $"name" 这两种写法是一样的效果
  }
}
