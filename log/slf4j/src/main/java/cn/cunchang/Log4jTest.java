package cn.cunchang;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

    // 定义 log4j 日志记录器
    public static final Logger LOGGER = Logger.getLogger(Log4jTest.class);

    /**
     * 桥接器<br>
     * logback替换log4j，代码不需要修改
     *
     *
     */
    @Test
    public void test桥接器() {
        LOGGER.info("hello lgo4j");
    }

}
