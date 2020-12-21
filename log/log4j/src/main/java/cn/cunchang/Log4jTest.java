package cn.cunchang;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;


/**
 * @author cunchang
 */
public class Log4jTest {

    /**
     * 日志级别
     * fatal 指出每个严重的错误事件将会导致应用程序的退出。
     * error 指出虽然发生错误事件，但仍然不影响系统的继续运行。
     * warn 表明会出现潜在的错误情形。
     * info 一般和在粗粒度级别上，强调应用程序的运行全程。
     * debug 一般用于细粒度级别上，对调试应用程序非常有帮助。
     * trace 是程序追踪，可以用于输出程序运行中的变量，显示执行的流程。
     * <p>
     * 一般只使用4个级别，优先级从高到低为 ERROR > WARN > INFO > DEBUG
     */
    @Test
    public void testQuick() {
        // 初始化系统配置，不需要配置文件
        BasicConfigurator.configure();
        // 创建日志记录器对象
        Logger logger = Logger.getLogger(this.getClass());
        // 日志记录输出
        logger.info("hello log4j");

        // 日志级别
        logger.fatal("fatal");// 严重错误，一般会造成系统崩溃和终止运行
        logger.error("error");// 错误信息，但不会影响系统运行
        logger.warn("warn");// 警告信息，可能会发生问题
        logger.info("info");// 程序运行信息，数据库的连接、网络、IO操作等
        logger.debug("debug");// 调试信息，一般在开发阶段使用，记录程序的变量、参数
        logger.trace("trace");// 追踪信息，记录程序的所有流程信
    }

    /**
     * 熟悉log4j组件和配置文件
     */
    @Test
    public void test组件和配置文件() {
        PropertyConfigurator.configure(Loader.getResource("log4j_组件和配置文件.properties"));
        Logger logger = Logger.getLogger(this.getClass());

        // 日志级别
        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }


    /**
     * log4j内部日志 LogLog
     */
    @Test
    public void test内部日志() {
        // 开启内部日志
        LogLog.setInternalDebugging(true);

        PropertyConfigurator.configure(Loader.getResource("log4j_组件和配置文件.properties"));
        Logger logger = Logger.getLogger(this.getClass());

        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }

    /**
     * log4j Layout配置
     */
    @Test
    public void testLayout配置() {
        // 开启内部日志
        LogLog.setInternalDebugging(true);
        PropertyConfigurator.configure(Loader.getResource("log4j_Layout配置.properties"));
        Logger logger = Logger.getLogger(this.getClass());

        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }

    /**
     * log4j FileAppender配置
     */
    @Test
    public void testFileAppender配置() {
        // 开启内部日志
        LogLog.setInternalDebugging(true);
        PropertyConfigurator.configure(Loader.getResource("log4j_FileAppender配置.properties"));
        Logger logger = Logger.getLogger(this.getClass());

        for (int i = 0; i < 100; i++) {
            logger.fatal("fatal");
            logger.error("error");
            logger.warn("warn");
            logger.info("info");
            logger.debug("debug");
            logger.trace("trace");
        }
    }


    /**
     * log4j JDBCAppender配置
     */
    @Test
    public void testJDBCAppender配置() {
        // 开启内部日志
        LogLog.setInternalDebugging(true);
        PropertyConfigurator.configure(Loader.getResource("log4j_JDBCAppender配置.properties"));
        Logger logger = Logger.getLogger(this.getClass());

        for (int i = 0; i < 100; i++) {
            logger.fatal("fatal");
            logger.error("error");
            logger.warn("warn");
            logger.info("info");
            logger.debug("debug");
            logger.trace("trace");
        }
    }


    /**
     * 自定义Logger是为了，不同业务日志输出到不同位置<br>
     * 比如业务代码输出到文件，log4j日志输出到控制台
     */
    @Test
    public void testCustomLogger() {
        // 开启内部日志
        LogLog.setInternalDebugging(true);
        PropertyConfigurator.configure(Loader.getResource("log4j_自定义Logger.properties"));
        // 自定义 cn.cunchang
        Logger myLogger = Logger.getLogger(this.getClass());
        myLogger.fatal("myLogger fatal");
        myLogger.error("myLogger error");
        myLogger.warn("myLogger warn");
        myLogger.info("myLogger info");
        myLogger.debug("myLogger debug");
        myLogger.trace("myLogger trace");
        // 自定义 org.apache
        Logger rootLogger = Logger.getLogger(Logger.class);
        rootLogger.fatal("rootLogger fatal");
        rootLogger.error("rootLogger error");
        rootLogger.warn("rootLogger warn");
        rootLogger.info("rootLogger info");
        rootLogger.debug("rootLogger debug");
        rootLogger.trace("rootLogger trace");
    }

}
