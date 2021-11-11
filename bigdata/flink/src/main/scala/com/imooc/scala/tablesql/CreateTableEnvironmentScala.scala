package com.imooc.scala.tablesql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.bridge.scala.{BatchTableEnvironment, StreamTableEnvironment}
import org.apache.flink.table.api.{EnvironmentSettings, TableEnvironment}

/**
 * 创建TableEnvironment对象
 * Created by xuwei
 */
object CreateTableEnvironmentScala {
  def main(args: Array[String]): Unit = {

    /**
     * 注意：如果Table API 和 SQL不需要和DataStream或者DataSet互相转换
     * 则针对stream和batch都可以使用TableEnvironment
     */
    //指定底层引擎为Blink，以及数据处理模式-stream
    val sSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()
    //创建TableEnvironment对象
    val sTableEnv = TableEnvironment.create(sSettings)


    //指定底层引擎为Blink，以及数据处理模式-batch
    val bSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inBatchMode().build()
    //创建TableEnvironment对象
    val bTableEnv = TableEnvironment.create(bSettings)

    /**
     * 注意：如果Table API和SQL需要和DataStream或者DataSet互相转换
     * 针对stream需要使用StreamTableEnvironment
     * 针对batch需要使用BatchTableEnvironment
     */
    //创建StreamTableEnvironment
    val ssEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()
    val ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings)

    //创建BatchTableEnvironment
    //注意：此时只能使用旧的执行引擎，新的Blink执行引擎不支持和DataSet转换
    val bbEnv = ExecutionEnvironment.getExecutionEnvironment
    val bbTableEnv = BatchTableEnvironment.create(bbEnv)

  }
}
