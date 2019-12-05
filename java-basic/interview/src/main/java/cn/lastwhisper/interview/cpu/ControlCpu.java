package cn.lastwhisper.interview.cpu;

/**
 * 控制cpu利用率
 * @author lastwhisper
 * @date 2019/11/23
 */
public class ControlCpu {

    /**
     * 利用率70%
     */
    public static void main(String[] args) throws InterruptedException {
        long busyTime = 7;
        long nowSys;
        int i = 0;
        while (true) {
            nowSys = System.currentTimeMillis();
            while ((System.currentTimeMillis() - nowSys) <= busyTime) {
                i++;
                System.out.println(i + " 测试输出");
            }
            Thread.sleep(3);
        }
    }

}
