package cn.lastwhisper;

import java.lang.instrument.Instrumentation;

/**
 * https://www.cnblogs.com/aspirant/p/8796974.html
 * @author lastwhisper
 */
public class Agent {
    /**
     * 该方法在main方法之前运行,与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
        System.out.println("agentOps:"+agentOps);
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst)
     * 则会执行 premain(String agentOps)
     */
    public static void premain(String agentOps) {
        System.out.println("=========premain方法执行2========");
        System.out.println("agentOps:"+agentOps);
    }
}
