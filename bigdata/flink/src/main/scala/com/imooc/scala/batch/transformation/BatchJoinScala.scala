package com.imooc.scala.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * join：内连接
 * Created by xuwei
 */
object BatchJoinScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //初始化第一份数据 Tuple2<用户id，用户姓名>
    val text1 = env.fromCollection(Array((1, "jack"), (2, "tom"), (3, "mick")))
    //初始化第二份数据 Tuple2<用户id，用户所在城市>
    val text2 = env.fromCollection(Array((1, "bj"), (2, "sh"), (4, "gz")))

    //对两份数据集执行join操作
    text1.join(text2)
      //注意：这里的where和equalsTo实现类似于on fieldA=fieldB的效果
      //where：指定左边数据集中参与比较的元素角标
      .where(0)
      //equalTo：指定右边数据集中参与比较的元素角标
      .equalTo(0){(first,second)=>{
        (first._1,first._2,second._2)
      }}.print()

  }

}
