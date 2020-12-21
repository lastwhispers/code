package cn.cunchang;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4j2Test {

    // 定义日志记录器对象
    public static final Logger log = LogManager.getLogger(Log4j2Test.class);

    /**
     * log4j-api 门面
     * log4j-core 实现
     */
    @Test
    public void testQuick() {
        // 日志消息输出
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("inf");
        log.debug("debug");
        log.trace("trace");
    }

}
