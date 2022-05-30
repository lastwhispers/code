# 进阶

- annotation；java注解相关：编译期处理注解、运行时解析并修改注解
- groovy；Groovy可以被视为Java的一种脚本化改良版。

- javaagent：代码注入

- javassist：*Javassist*是一个开源的分析、编辑和创建Java字节码的类库
- network：java实现正向代理
- proxy：java动态代理
- spi：服务提供者接口（Service Provider Interface，简写为SPI）是JDK内置的一种服务提供发现机制。



# annotation

- **JSR 175: A Metadata Facility for the Java**TM **Programming Language**
- **JSR 269: Pluggable Annotation Processing API**



# spi

面向对象的设计推荐模块之间基于接口编程，模块之间不对实现类进行硬编码。一旦代码里涉及具体的实现类，就违反了可拔插的原则：如果需要替换组建的一种实现，就需要修改框架的代码。SPI机制正是解决这个问题。

Java中SPI机制主要思想是将装配的控制权移到程序之外，是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制，有点类似Spring的IOC机制。在模块化设计中这个机制尤其重要，其核心思想就是解耦。

**缺点**

- 不能按需加载。虽然 ServiceLoader 做了延迟加载，但是只能通过遍历的方式全部获取。如果其中某些实现类很耗时，而且你也不需要加载它，那么就形成了资源浪费
- 获取某个实现类的方式不够灵活，只能通过迭代器的形式获取

> Dubbo SPI 实现方式对以上两点进行了业务优化。

# javaagent

**简介**

javaagent是一种能够在不影响正常编译的情况下，修改字节码。java作为一种强类型的语言，不通过编译就不能能够进行jar包的生成。而有了javaagent技术，就可以在字节码这个层面对类和方法进行修改。同时，也可以把javaagent理解成一种代码注入的方式。但是这种注入比起spring的aop更加的优美。

**主要作用**

- 可以在加载java文件之前做拦截把字节码做修改
- 可以在运行期将已经加载的类的字节码做变更，但是这种情况下会有很多的限制，后面会详细说
   还有其他的一些小众的功能
- 获取所有已经被加载过的类
- 获取所有已经被初始化过了的类（执行过了clinit方法，是上面的一个子集）
- 获取某个对象的大小
- 将某个jar加入到bootstrapclasspath里作为高优先级被bootstrapClassloader加载
- 将某个jar加入到classpath里供AppClassload去加载
- 设置某些native方法的前缀，主要在查找native方法的时候做规则匹配

