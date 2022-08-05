package com.imooc.scala.stream.transformation

import org.apache.flink.api.common.functions.Partitioner

/**
 * 自定义分区规则：按照数字的奇偶性进行分区
 * Created by xuwei
 */
class MyPartitionerScala extends Partitioner[Int]{
  override def partition(key: Int, numPartitions: Int): Int = {
    println("分区总数："+numPartitions)
    if(key %  2 == 0){//偶数分到0号分区
      0
    }else{//奇数分到1号分区
      1
    }
  }
}
