package cn.cunchang;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * 官方网站: http://logging.apache.org/log4j/1.2/
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
     * 特殊级别
     * OFF，可用来关闭日志记录。
     * ALL，启用所有消息的日志记录。
     * <p>
     * 一般只使用4个级别，优先级从高到低为 ERROR > WARN > INFO > DEBUG
     */
    @Test
    public void testQuick() {
        // 初始化系统配置，不需要配置文件
        BasicConfigurator.configure();
        // 创建日志记录器对象
        Logger logger = Logger.getLogger(this.getClass().getName());
        // 日志记录输出
        logger.info("hello log4j");

        // 日志级别
        // 严重错误，一般会造成系统崩溃和终止运行
        logger.fatal("fatal");
        // 错误信息，但不会影响系统运行
        logger.error("error");
        // 警告信息，可能会发生问题
        logger.warn("warn");
        // 程序运行信息，数据库的连接、网络、IO操作等
        logger.info("info");
        // 调试信息，一般在开发阶段使用，记录程序的变量、参数
        logger.debug("debug");
        // 追踪信息，记录程序的所有流程信
        logger.trace("trace");
    }


    @Test
    public void testCustomLogger() {
        // 自定义 cn.cunchang
        Logger Logger1 = Logger.getLogger(this.getClass());
        Logger1.fatal("fatal");
        Logger1.error("error");
        Logger1.warn("warn");
        Logger1.info("info");
        Logger1.debug("debug");
        Logger1.trace("trace");
        // 自定义 org.apache
        Logger rootLogger = Logger.getLogger(Logger.class);
        rootLogger.fatal("fatal");
        rootLogger.error("error");
        rootLogger.warn("warn");
        rootLogger.info("info");
        rootLogger.debug("debug");
        rootLogger.trace("trace");
    }

}
