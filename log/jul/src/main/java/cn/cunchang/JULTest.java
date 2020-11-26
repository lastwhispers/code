package cn.cunchang;

import cn.cunchang.log.MyJULLogFilter;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class JULTest {


    @Test
    public void testQuick() {
        // 1、创建日志记录器对象 Logger
        Logger logger = Logger.getLogger(this.getClass().getName());
        // 2、日志记录输出
        logger.info("hello jul");

        logger.log(Level.INFO, "info msg");
        String name = "jack";
        int age = 18;
        logger.log(Level.INFO, "用户信息:{0},{1}", new Object[]{name, age});
    }

    /**
     * 日志级别分类
     *
     * @see java.util.logging.Level 中定义了日志的级别
     */
    @Test
    public void testLogLevel() {
        // 1.获取日志对象
        Logger logger = Logger.getLogger(this.getClass().getName());
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("cofnig");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * 自定义日志配置
     *
     * @throws Exception
     */
    @Test
    public void testLogConfig() throws Exception {
        // 1.获取日志对象
        Logger logger = Logger.getLogger(this.getClass().getName());

        // 自定义日志配置
        // a.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // b.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // e.设置日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 输出到日志文件
        FileHandler fileHandler = new FileHandler(System.getProperty("user.home") + "/logs/jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * Logger之间的父子关系<p/>
     * <p>
     * JUL中Logger之间存在父子关系，这种父子关系通过树状结构存储，
     * JUL在初始化时会创建一个顶层 RootLogger作为所有Logger父Logger，
     * 存储上作为树状结构的根节点。并父子关系通过路径来关联。
     */
    @Test
    public void testLogParent() {
        // 日志记录器对象父子关系
        Logger logger1 = Logger.getLogger("cn.cunchang.log");
        Logger logger2 = Logger.getLogger("cn.cunchang");

        System.out.println("父子日志记录器：" + (logger1.getParent() == logger2));
        // 所有日志记录器对象的顶级父元素 class为java.util.logging.LogManager$RootLogger name为""
        System.out.println("logger2 parent:" + logger2.getParent() +
                "，name:" + logger2.getParent().getName());
    }

    /**
     * 日志配置文件<p/>
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib
     * <p>
     * 默认配置文件路径$JAVAHOME\jre\lib\logging.properties
     */
    @Test
    public void testProperties() throws IOException {
        // 读取自定义配置文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jul.properties");
        // 获取日志管理器对象
        LogManager logManager = LogManager.getLogManager();
        // 日志管理区加载配置文件
        logManager.readConfiguration(inputStream);

        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * JUL的架构实现
     */
    @Test
    public void testLogInner() throws IOException {
        /*
         * 1、获取日志对象logger
         *  1.1、初始化LogManager，LogManager.getLogManager()
         *     - 初始化rootLogger为java.util.logging.LogManager.RootLogger
         *     - 设置rootLogger的级别为Level.INFO
         *     - 将rootLogger添加到LogManager
         *  1.2、初始化Logger，LogManager.demandLogger(name, resourceBundleName, caller);
         *     - name一般是包名，logger只会初始化一次
         *     - 初始化Logger时默认使用rootLogger的配置（handler、format等）
         *
         */
//        Logger logger = Logger.getLogger(this.getClass().getName());
        Logger logger = Logger.getLogger("cn.cunchang.log");

        // 2、自定义日志配置
        // a.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // b.创建handler对象，用来处理日志输出位置
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象，是用来格式化LogRecord的
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // e.设置日志级别
        logger.setLevel(Level.ALL);
        // f.设置日志过滤器，日志级别之外更细粒度的控制
        logger.setFilter(new MyJULLogFilter());
        consoleHandler.setLevel(Level.ALL);

        /*
         * 3、日志记录输出
         *  msg会被封装成LogRecord
         */
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");




    }
}
