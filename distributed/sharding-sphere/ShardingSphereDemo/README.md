
application.properties；这个是真正用的配置，运行哪个demo，把01、02等等的配置copy就行了

# application01.properties；
分表，用到了一库两表
```sql
CREATE TABLE course_1 (
                          cid BIGINT(20) PRIMARY KEY,
                          cname VARCHAR(50) NOT NULL,
                          user_id BIGINT(20) NOT NULL,
                          cstatus varchar(10) NOT NULL
);

CREATE TABLE course_2 (
                          cid BIGINT(20) PRIMARY KEY,
                          cname VARCHAR(50) NOT NULL,
                          user_id BIGINT(20) NOT NULL,
                          cstatus varchar(10) NOT NULL
);
```


```properties
#配置数据源
# 数据源名称
spring.shardingsphere.datasource.names=m1

spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/coursedb?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=123456
#配置真实表分布
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m1.course_$->{1..2}
#主键生成策略
# 主键字段名
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
# 主键生成算法
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
# 雪花算法的参数
spring.shardingsphere.sharding.tables.course.key-generator.props.worker.id=1
#配置分表策略
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid%2+1}
#其他运行属性
spring.shardingsphere.props.sql.show = true
spring.main.allow-bean-definition-overriding=true
```



# application02.properties；

分库，用到了一个两库两表

```properties
#配置多个数据源
spring.shardingsphere.datasource.names=m1,m2

spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/coursedb?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=123456

spring.shardingsphere.datasource.m2.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m2.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m2.url=jdbc:mysql://localhost:3306/coursedb2?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m2.username=root
spring.shardingsphere.datasource.m2.password=123456
#真实表分布，分库，分表
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m$->{1..2}.course_$->{1..2}

#主键生成策略
# 主键字段名
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
# 主键生成算法
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
# 雪花算法的参数
spring.shardingsphere.sharding.tables.course.key-generator.props.worker.id=1
#配置分表策略
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid%2+1}
#其他运行属性
spring.shardingsphere.props.sql.show = true
spring.main.allow-bean-definition-overriding=true
```



## inline策略不支持范围查询
```properties
#inline分片策略
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid%2+1}
#inline分库策略
spring.shardingsphere.sharding.tables.course.database-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.database-strategy.inline.algorithm-expression=m$->{cid%2+1}
```

```text
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: java.lang.IllegalStateException: Inline strategy cannot support this type sharding:RangeRouteValue(columnName=cid, tableName=course, valueRange=[730100102537940992‥730100102990925824])
### The error may exist in com/roy/shardingDemo/mapper/CourseMapper.java (best guess)
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: SELECT  cid,cname,user_id,cstatus  FROM course    WHERE  cid BETWEEN ? AND ? ORDER BY cid DESC
### Cause: java.lang.IllegalStateException: Inline strategy cannot support this type sharding:RangeRouteValue(columnName=cid, tableName=course, valueRange=[730100102537940992‥730100102990925824])

	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:77)
	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)
	at com.sun.proxy.$Proxy61.selectList(Unknown Source)
	at org.mybatis.spring.SqlSessionTemplate.selectList(SqlSessionTemplate.java:230)
	at com.baomidou.mybatisplus.core.override.PageMapperMethod.executeForMany(PageMapperMethod.java:173)
	at com.baomidou.mybatisplus.core.override.PageMapperMethod.execute(PageMapperMethod.java:86)
	at com.baomidou.mybatisplus.core.override.PageMapperProxy.invoke(PageMapperProxy.java:64)
	at com.sun.proxy.$Proxy68.selectList(Unknown Source)
	at com.roy.shardingDemo.ShardingJDBCTest.queryCourse(ShardingJDBCTest.java:56)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
```

## standard标准分片策略
解决inline策略不支持范围查询


```properties
#standard标准分片策略
spring.shardingsphere.sharding.tables.course.table-strategy.standard.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.standard.precise-algorithm-class-name=com.roy.shardingDemo.algorithem.MyPreciseTableShardingAlgorithm
spring.shardingsphere.sharding.tables.course.table-strategy.standard.range-algorithm-class-name=com.roy.shardingDemo.algorithem.MyRangeTableShardingAlgorithm

spring.shardingsphere.sharding.tables.course.database-strategy.standard.sharding-column=cid
spring.shardingsphere.sharding.tables.course.database-strategy.standard.precise-algorithm-class-name=com.roy.shardingDemo.algorithem.MyPreciseDSShardingAlgorithm
spring.shardingsphere.sharding.tables.course.database-strategy.standard.range-algorithm-class-name=com.roy.shardingDemo.algorithem.MyRangeDSShardingAlgorithm
```

standard标准分片策略支持自己根据一个分片键，实现精确和范围选择实际库表的逻辑
缺点：多个分片键字段的复杂查询，会扫全库全表



## complex复杂分片策略

支持自己根据多个分片键分片键，选择实际库表的逻辑

```properties
#complex复杂分片策略
spring.shardingsphere.sharding.tables.course.table-strategy.complex.sharding-columns= cid, user_id
spring.shardingsphere.sharding.tables.course.table-strategy.complex.algorithm-class-name=com.roy.shardingDemo.algorithem.MyComplexTableShardingAlgorithm

spring.shardingsphere.sharding.tables.course.database-strategy.complex.sharding-columns=cid, user_id
spring.shardingsphere.sharding.tables.course.database-strategy.complex.algorithm-class-name=com.roy.shardingDemo.algorithem.MyComplexDSShardingAlgorithm
```



## hint强制路由策略

强制路由

```properties
#hint强制路由策略
spring.shardingsphere.sharding.tables.course.table-strategy.hint.algorithm-class-name=com.roy.shardingDemo.algorithem.MyHintTableShardingAlgorithm
```



# application03.properties

绑定表



# application04.properties

读写分离

```properties
#配置主从数据源，要基于MySQL主从架构。
spring.shardingsphere.datasource.names=m0,s0

spring.shardingsphere.datasource.m0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m0.url=jdbc:mysql://localhost:3307/masterdemo?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m0.username=root
spring.shardingsphere.datasource.m0.password=123456

spring.shardingsphere.datasource.s0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.s0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.s0.url=jdbc:mysql://localhost:3308/masterdemo?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.s0.username=root
spring.shardingsphere.datasource.s0.password=123456
#读写分离规则， m0 主库，s0 从库
spring.shardingsphere.sharding.master-slave-rules.ds0.master-data-source-name=m0
spring.shardingsphere.sharding.master-slave-rules.ds0.slave-data-source-names[0]=s0
#基于读写分离的表分片
spring.shardingsphere.sharding.tables.t_dict.actual-data-nodes=ds0.t_dict

spring.shardingsphere.sharding.tables.t_dict.key-generator.column=dict_id
spring.shardingsphere.sharding.tables.t_dict.key-generator.type=SNOWFLAKE
spring.shardingsphere.sharding.tables.t_dict.key-generator.props.worker.id=1

spring.shardingsphere.props.sql.show = true
spring.main.allow-bean-definition-overriding=true
```

# application05.properties
主从读写分离

