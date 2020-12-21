package cn.cunchang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

    public static final Logger log = LoggerFactory.getLogger(Slf4jTest.class);

    /**
     * slf4j-api 门面
     * log4j-slf4j-impl 适配器
     * log4j-core 实现
     */
    @Test
    public void test日志门面() {
        // 日志输出
        log.error("error");
        log.warn("wring");
        log.info("info"); // 默认级别
        log.debug("debug");
        log.trace("trace");
    }
}
