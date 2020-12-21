package cn.cunchang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * 全称为Jakarta Commons Logging，是Apache提供的一个通用日志API。
 * <p/>
 * 它是为 "所有的Java日志实现"提供一个统一的接口，它自身也提供一个日志的实现，但是功能非常常弱 (SimpleLog)。
 * 所以一般不会单独使用它。他允许开发人员使用不同的具体日志实现工具: Log4j, Jdk 自带的日志(JUL)
 * <p/>
 * JCL 有两个基本的抽象类:Log(基本记录器)和LogFactory(负责创建Log实例)。
 */
public class JCLTest {

    /**
     * 不引入log4j，使用jul
     * 引入log4j，使用log4j
     */
    @Test
    public void testQuick() {
        // 创建日志对象
        Log log = LogFactory.getLog(this.getClass());
        // 日志记录输出
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
    }



}
