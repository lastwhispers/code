package com.imooc.scala.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * outerJoin：外连接
 * 一共有三种情况
 * 1：leftOuterJoin
 * 2：rightOuterJoin
 * 3：fullOuterJoin
 * Created by xuwei
 */
object BatchOuterJoinScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //初始化第一份数据 Tuple2<用户id，用户姓名>
    val text1 = env.fromCollection(Array((1, "jack"), (2, "tom"), (3, "mick")))
    //初始化第二份数据 Tuple2<用户id，用户所在城市>
    val text2 = env.fromCollection(Array((1, "bj"), (2, "sh"), (4, "gz")))

    //对两份数据集执行leftOuterJoin操作
    text1.leftOuterJoin(text2)
      .where(0)
      .equalTo(0){(first,second)=>{
        //注意：second中的元素可能为null
        if(second==null){
          (first._1,first._2,"null")
        }else{
          (first._1,first._2,second._2)
        }
      }}.print()

    println("========================================")

    //对两份数据集执行rightOuterJoin操作
    text1.rightOuterJoin(text2)
      .where(0)
      .equalTo(0){(first,second)=>{
        //注意：first中的元素可能为null
        if(first==null){
          (second._1,"null",second._2)
        }else{
          (first._1,first._2,second._2)
        }
      }}.print()

    println("========================================")

    //对两份数据集执行fullOuterJoin操作
    text1.fullOuterJoin(text2)
      .where(0)
      .equalTo(0){(first,second)=>{
        //注意：first和second中的元素都有可能为null
        if(first==null){
          (second._1,"null",second._2)
        }else if(second==null){
          (first._1,first._2,"null")
        }else{
          (first._1,first._2,second._2)
        }
      }}.print()

  }

}
