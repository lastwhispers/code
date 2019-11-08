mybatis一级缓存和二级缓存
- 一级缓存是SqlSession级别的
- 二级缓存是Mapper级别的，多个 SqlSession 可以共用二级缓存

当我们在使用二级缓存时，所缓存的类一定要实现 java.io.Serializable 接口，这种就可以使用序列化
方式来保存对象。
