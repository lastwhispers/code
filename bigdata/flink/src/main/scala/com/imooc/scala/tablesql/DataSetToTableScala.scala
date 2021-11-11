package com.imooc.scala.tablesql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.bridge.scala.BatchTableEnvironment

/**
 * 将DataSet转换为表
 * Created by xuwei
 */
object DataSetToTableScala {
  def main(args: Array[String]): Unit = {
    //获取BatchTableEnvironment
    val bbEnv = ExecutionEnvironment.getExecutionEnvironment
    val bbTableEnv = BatchTableEnvironment.create(bbEnv)

    //获取DataSet
    import org.apache.flink.api.scala._
    val set = bbEnv.fromCollection(Array((1, "jack"), (2, "tom"), (3, "mack")))

    //第一种：将DataSet转换为view视图
    import org.apache.flink.table.api._
    bbTableEnv.createTemporaryView("myTable",set,'id,'name)
    bbTableEnv.sqlQuery("select * from myTable where id > 1").execute().print()

    //第二种：将DataSet转换为table对象
    val table = bbTableEnv.fromDataSet(set, 'id, 'name)
    table.select($"id",$"name")
      .filter($"id" > 1 )
      .execute()
      .print()
  }

}
