package cn.lastwhisper.aop;

/**
 * 处理业务类
 * @author lastwhisper
 */
public class MathCalculator {
    // 做除法
    public int div(int i, int j) {
        System.out.println("MathCalculator...div...");
        return i / j;
    }
}
