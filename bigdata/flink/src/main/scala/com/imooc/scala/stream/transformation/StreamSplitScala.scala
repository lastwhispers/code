package com.imooc.scala.stream.transformation

import java.{lang, util}

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * 根据规则把一个数据流切分为多个数据流
 * 注意：split只能分一次流，切分出来的流不能继续切分
 * split需要和select配合使用，选择切分后的流
 * 应用场景：将一份数据流切分为多份，便于针对每一份数据使用不同的处理逻辑
 * Created by xuwei
 */
object StreamSplitScala {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //初始化数据
    val text = env.fromCollection(Array(1, 2, 4, 5, 6, 7, 8, 9, 10))

    //按照数据的奇偶性对数据进行分流
    val splitStream = text.split(new OutputSelector[Int] {
      override def select(value: Int): lang.Iterable[String] = {
        val list = new util.ArrayList[String]()
        if(value % 2 == 0){
          list.add("even")//偶数
        }else{
          list.add("odd")//奇数
        }
        list
      }
    })

    //选择流
    val evenStream = splitStream.select("even")
    evenStream.print().setParallelism(1)

    //二次切流会报错
    //Consecutive multiple splits are not supported. Splits are deprecated. Please use side-outputs.
    /*val lowHighStream = evenStream.split(new OutputSelector[Int] {
      override def select(value: Int): lang.Iterable[String] = {
        val list = new util.ArrayList[String]()
        if(value <= 5){
          list.add("low")
        }else{
          list.add("high")
        }
        list
      }
    })
    val lowStream = lowHighStream.select("low")
    lowStream.print().setParallelism(1)*/


    env.execute("StreamSplitScala")
  }
}
