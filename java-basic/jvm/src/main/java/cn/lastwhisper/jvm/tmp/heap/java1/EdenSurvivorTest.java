package cn.lastwhisper.jvm.tmp.heap.java1;

/**
 * -Xms600m -Xmx600m
 *
 * -XX:NewRatio ： 设置新生代与老年代的比例。默认值是2.
 * -XX:SurvivorRatio ：设置新生代中Eden区与Survivor区的比例。
 *      默认值是8，但是会自适应，如果要按8:1:1比例，需要参数设置 -XX:SurvivorRatio=8
 * -XX:-UseAdaptiveSizePolicy ：关闭自适应的内存分配策略  （没有用）
 * -Xmn:设置新生代的空间的大小。 （一般不设置）
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  17:23
 */
public class EdenSurvivorTest {
    public static void main(String[] args) {
        System.out.println("我只是来打个酱油~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
