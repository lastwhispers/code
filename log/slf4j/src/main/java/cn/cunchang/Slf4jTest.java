package cn.cunchang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

    public static final Logger log = LoggerFactory.getLogger(Slf4jTest.class);

    /**
     * http://slf4j.org/manual.html
     * 适配器<br>
     * slf4j-simple、logback、nop、log4j、jul
     *
     */
    @Test
    public void test日志门面() {
        // 日志输出
        log.error("error");
        log.warn("wring");
        log.info("info"); // 默认级别
        log.debug("debug");
        log.trace("trace");

        // 使用占位符输出日志信息
        String name = "寸长";
        Integer age = 18;
        log.info("用户：{},{}",name,age);

        // 将系统的异常信息输出
        try {
            int i = 1/0;
        } catch (Exception e) {
           // e.printStackTrace();
            log.error("出现异常：",e);

        }
    }
}
