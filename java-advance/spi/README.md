
https://www.jianshu.com/p/46b42f7f593c


比如，我们日常使用是数据库驱动，会提供统一的规范（java.sql.Driver），各数据库服务商提供对应数据库的逻辑实现。当使用到该数据库时，直接引入不同的SPI服务实现即可。

常见场景：

Spring框架中有大量实现，如上图中Spring对servlet3.0规范的ServletContainerInitializer的实现。
数据库驱动程序加载不同数据库的实现，如上图中java.sql.Driver接口的实现。
日志框架log4j中的实现。
Dubbo中实现框架扩展的实现。

使用规范，下面了解一下使用SPI的基本规范步骤：

服务提供者定义对外接口及方法，比如数据库驱动会提供一个java.sql.Driver的接口。
针对定义的接口，提供一个实现类。
在项目或jar包的META-INF/services目录下，创建一个文本文件：名称为接口的“全限定名”，内容为实现类的全限定名(包+实现类名)。上面的截图中其实已经可以发现，统一都是如此。
服务调用者引入该项目的jar包，并将其放置于classpath下。
服务调用者通过核心API java.util.ServiceLoader来动态加载该实现，主要就是扫描classpath下所有jar包内META-INF/services目录下，按照指定格式定义的文件，并把其中类进行加载。
由于SPI机制使用的过程中无法进行传递构造参数，因此需提供一个无参的构造方法。
