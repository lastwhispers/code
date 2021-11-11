package com.imooc.scala.tablesql

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.types.Row

/**
 * 将table转换成DataStream
 * Created by xuwei
 */
object TableToDataStreamScala {
  def main(args: Array[String]): Unit = {
    //获取StreamTableEnvironment
    val ssEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()
    val ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings)

    //创建输入表
    ssTableEnv.executeSql("" +
      "create table myTable(\n" +
      "id int,\n" +
      "name string\n" +
      ") with (\n" +
      "'connector.type' = 'filesystem',\n" +
      "'connector.path' = 'D:\\data\\source',\n" +
      "'format.type' = 'csv'\n" +
      ")")

    //获取table
    val table = ssTableEnv.from("myTable")

    //将table转换为DataStream
    //如果只有新增(追加)操作，可以使用toAppendStream
    import org.apache.flink.api.scala._
    val appStream = ssTableEnv.toAppendStream[Row](table)
    appStream.map(row=>(row.getField(0).toString.toInt,row.getField(1).toString))
      .print()


    //如果有增加操作，还有删除操作，则使用toRetractStream
    val retStream = ssTableEnv.toRetractStream[Row](table)
    retStream.map(tup=>{
      val flag = tup._1
      val row = tup._2
      val id = row.getField(0).toString.toInt
      val name = row.getField(1).toString
      (flag,id,name)
    }).print()

    //注意：将table转换为DataStream之后，就需要调用StreamExecutionEnvironment中的execute方法了
    ssEnv.execute("TableToDataStreamScala")
  }
}
