package com.imooc.scala.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

import scala.collection.mutable.ListBuffer

/**
 * MapPartition的使用：一次处理一个分区的数据
 * Created by xuwei
 */
object BatchMapPartitionScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //生成数据源数据
    val text = env.fromCollection(Array("hello you", "hello me"))

    //每次处理一个分区的数据
    text.mapPartition(it=>{
      //可以在此处创建数据库连接，建议把这块代码放到try-catch代码块中
      //注意：此时是每个分区获取一次数据库连接，不需要每处理一条数据就获取一次连接，性能较高
      val res = ListBuffer[String]()
      it.foreach(line=>{
        val words = line.split(" ")
        for(word <- words){
          res.append(word)
        }
      })
      res
      //关闭数据库连接
    }).print()

    //No new data sinks have been defined since the last execution.
    //The last execution refers to the latest call to 'execute()', 'count()', 'collect()', or 'print()'.
    //注意：这对DataSetAPI，如果在后面调用的是count、collect、print，则最后不需要指定execute即可
    //env.execute("BatchMapPartitionScala")
  }

}
