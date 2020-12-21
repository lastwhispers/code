package cn.cunchang;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class LogbackTest {

    public static final Logger log = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void testQuick() {
        log.error("error");
        log.warn("wring");
        log.info("info");
        log.debug("debug");// 默认级别
        log.trace("trace");
    }

    /**
     * logback自动加载配置文件的优先级如下：
     * 1.classpath下的logback-test.xml
     * 2.classpath下的logback.groovy
     * 3.classpath下的logback.xml
     * 4.META-INF/services/ch.qos.logback.classic.spi.Configurator中的 logback 配置实现类
     * 5.logback自带的默认配置文件BasicConfigurator
     */
    @Test
    public void test配置文件() {
        reloadConfig("logback_配置文件.xml");
        log.error("error");
        log.warn("wring");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }

    @Test
    public void testAppender配置() {
//        reloadConfig("logback_FileAppender配置.xml");
        reloadConfig("logback_HtmlAppender配置.xml");
        log.error("error");
        log.warn("wring");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }

    @Test
    public void testRollingFileAppender配置() {
        reloadConfig("logback_RollingFileAppender配置.xml");
        for (int i = 0; i < 100; i++) {
            log.error("error");
            log.warn("wring");
            log.info("info");
            log.debug("debug");
            log.trace("trace");
        }
    }

    @Test
    public void test异步配置() {
        reloadConfig("logback_异步配置.xml");
        for (int i = 0; i < 100; i++) {
            log.error("error");
            log.warn("wring");
            log.info("info");
            log.debug("debug");
            log.trace("trace");
        }
    }

    @Test
    public void test自定义logger() {
        reloadConfig("logback_自定义logger.xml");
        log.error("error");
        log.warn("wring");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }

    /**
     * 加载自定义logback配置文件
     *
     * @param logbackConfigPath
     */
    public void reloadConfig(String logbackConfigPath) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(logbackConfigPath);
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        loggerContext.reset();
        try {
            joranConfigurator.doConfigure(inputStream);
        } catch (Exception e) {
            log.error("logbackConfigPath Load logback config file error. Message: {}", e.getMessage());
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }
}
