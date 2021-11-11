
# 入门
# 一、流处理和批处理程序开发
stream.SocketWindowWordCountJava 实时处理
batch.BatchWordCountJava 批处理

# 二、Flink核心API之DataStream API
## DataStreamAPI之DataSource
- stream.source.StreamCollectionSourceJava

## DataStreamAPI之Transformation
- stream.transformation
- 算子
    - union 合并多个流，多个流的数据类型必须一致
    - connect 只能连接两个流，两个流的数据类型可以不同
    - split 根据规则把一个数据流切分为多个流；代码：StreamSplitJava、StreamSideOutputJava
- 算子分区
    - shuffle 随机分区
    - rebalance 对数据集进行再平衡（顺序），重分区，消除数据倾斜
    - rescale 重分区（局部顺序）
    - partitionCustom 自定义分区；代码：StreamPartitionOpJava

## DataStreamAPI之DataSink
- stream.sink.StreamRedisSinkJava

# 三、Flink核心API之DataSetAPI
## DataSetAPI之Transformation
- batch.transformation
- 算子
    - mapPartition 类似map，一次处理一个分区的数据；代码：BatchMapPartitionJava
    - distinct 返回数据集中去重之后的元素
    - join 内连接；代码：BatchJoinJava
    - outerJoin 外连接；代码：BatchOuterJoinJava
    - cross 获取两个数据集的笛卡尔积；BatchCrossJava
    - union 返回多个数据集的总和，数据类型需要一致
    - first-n 获取集合中的前N个元素；BatchFirstNJava

# 四、Flink核心API之TableAPI和SQL
tablesql

## Table API 和SQL的使用
- CreateTableEnvironmentJava 创建一个TableEnvironment对象
- TableAPIAndSQLOpJava
- DataStreamToTableJava DataStream、DataSet和Table之间的互相转换

# 进阶
# 一、Window
- TimeWindowOpJava
- CountWindowOpJava
- MyTimeWindowJava







