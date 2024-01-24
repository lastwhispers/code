# 1、source
数据源
SourceApp
- test01，官方提供数据源实现，socketTextStream数据源
- test02，官方提供数据源实现，数值序列迭代器数据源
- test03，自定义数据源，SourceFunction、ParallelSourceFunction
- test04，自定义数据源，RichSourceFunction
- test05，官方提供数据源实现，kafka


# 2、transformation
算子处理数据源
TransformationApp


# 3、partitioner
分区，让某个并行度只处理某个分区数据
PartitionerApp

# 4、sink
输出算子处理结果
SinkApp
- PKMySQLSink，输出到mysql
- PKRedisSink，输出到redis


# 5、windows
无界流按窗口拆成有界流

WindowApp
- test01 window实例
- test02 窗口函数，增量 reduce
- test03 窗口函数，全量 ProcessWindowFunction 

# 6、wm
watermark

EventTimeWMApp，延迟窗口，以及对延迟数据处理

# 7、state
状态
- Keyed State
- 算子状态 (Operator State) ，Kafka consumer 每个并行实例维护了 topic partitions 和偏移量的 map 作为它的算子状态。

StateApp，state使用




