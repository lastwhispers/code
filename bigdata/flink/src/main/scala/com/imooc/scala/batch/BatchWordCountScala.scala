package com.imooc.scala.batch

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * 需求：统计指定文件中单词出现的总次数
 * Created by xuwei
 */
object BatchWordCountScala {
  def main(args: Array[String]): Unit = {
    //获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    val inputPath = "hdfs://bigdata01:9000/hello.txt"
    val outPath = "hdfs://bigdata01:9000/out"

    //读取文件中的数据
    val text = env.readTextFile(inputPath)

    //处理数据
    import org.apache.flink.api.scala._
    val wordCount = text.flatMap(_.split(" "))
      .map((_, 1))
      .groupBy(0)
      .sum(1)
      .setParallelism(1)//这里面设置并行度为1是为了将所有数据写到一个文件里面，查看结果比较方便

    //将结果数据保存到文件中
    wordCount.writeAsCsv(outPath,"\n"," ")

    //执行程序
    env.execute("BatchWordCountScala")

  }

}
