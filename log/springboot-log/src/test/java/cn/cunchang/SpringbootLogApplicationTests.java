package cn.cunchang;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLogApplicationTests {

    // 声明日志记录器对象
    public static final Logger log = LoggerFactory.getLogger(SpringbootLogApplicationTests.class);

    @Test
    public void contextLoads() {
        // 打印日志信息
        log.error("error");
        log.warn("warn");
        log.info("info"); // 默认日志级别
        log.debug("debug");
        log.trace("trace");

        // 使用 lo4j2 使用桥接器切换为 slf4j 门面和 logback 日志实现
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(SpringbootLogApplicationTests.class);
        logger.info("log4j2 info");
    }

}
