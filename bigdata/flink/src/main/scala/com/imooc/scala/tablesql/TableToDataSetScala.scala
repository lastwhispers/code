package com.imooc.scala.tablesql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.bridge.scala.BatchTableEnvironment
import org.apache.flink.types.Row

/**
 * 将table转换成DataSet
 * Created by xuwei
 */
object TableToDataSetScala {
  def main(args: Array[String]): Unit = {
    //获取BatchTableEnvironment
    val bbEnv = ExecutionEnvironment.getExecutionEnvironment
    val bbTableEnv = BatchTableEnvironment.create(bbEnv)

    //创建输入表
    bbTableEnv.executeSql("" +
      "create table myTable(\n" +
      "id int,\n" +
      "name string\n" +
      ") with (\n" +
      "'connector.type' = 'filesystem',\n" +
      "'connector.path' = 'D:\\data\\source',\n" +
      "'format.type' = 'csv'\n" +
      ")")

    //获取table
    val table = bbTableEnv.from("myTable")

    //将table转换成DataSet
    import org.apache.flink.api.scala._
    val set = bbTableEnv.toDataSet[Row](table)
    set.map(row=>(row.getField(0).toString.toInt,row.getField(1).toString))
      .print()

  }
}
