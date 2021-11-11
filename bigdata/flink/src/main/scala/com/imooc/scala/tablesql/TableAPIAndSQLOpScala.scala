package com.imooc.scala.tablesql

import org.apache.flink.table.api.{EnvironmentSettings, TableEnvironment}

/**
 * TableAPI 和 SQL的使用
 * Created by xuwei
 */
object TableAPIAndSQLOpScala {
  def main(args: Array[String]): Unit = {
    //获取TableEnvironment
    val sSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()
    val sTableEnv = TableEnvironment.create(sSettings)

    //创建输入表
    /**
     * connector.type：指定connector的类型
     * connector.path：指定文件或者目录地址
     * format.type：文件数据格式化类型，现在只支持csv格式
     * 注意：SQL语句中如果出现了换行，行尾末尾可以添加空格或者\n都可以，最后一行不用添加
     */
    sTableEnv.executeSql("" +
      "create table myTable(\n" +
      "id int,\n" +
      "name string\n" +
      ") with (\n" +
      "'connector.type' = 'filesystem',\n" +
      "'connector.path' = 'D:\\data\\source',\n" +
      "'format.type' = 'csv'\n" +
      ")")

    //使用TableAPI实现数据查询和过滤等操作
    import org.apache.flink.table.api._
    /*val result = sTableEnv.from("myTable")
      .select($"id", $"name")
      .filter($"id" > 1)*/

    //使用SQL实现数据查询和过滤等操作
    val result = sTableEnv.sqlQuery("select id,name from myTable where id > 1")

    //输出结果到控制台
    result.execute().print()

    //创建输出表
    sTableEnv.executeSql("" +
      "create table newTable(\n" +
      "id int,\n" +
      "name string\n" +
      ") with (\n" +
      "'connector.type' = 'filesystem',\n" +
      "'connector.path' = 'D:\\data\\res',\n" +
      "'format.type' = 'csv'\n" +
      ")")

    //输出结果到表newTable中
    result.executeInsert("newTable")
  }
}
