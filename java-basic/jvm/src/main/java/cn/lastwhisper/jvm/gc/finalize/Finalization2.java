package cn.lastwhisper.jvm.gc.finalize;

/**
 * finalize方法的不确定性
 * @author lastwhisper
 */
public class Finalization2 {
    public static Finalization2 SAVE_HOOK = null;

    @Override
    protected void finalize() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("第一个文件资源关闭。。。");
        Thread.sleep(500);
        System.out.println("第二个文件资源关闭。。。");
        Thread.sleep(500);
        System.out.println("第三个文件资源关闭。。。");
        Thread.sleep(500);
        System.out.println("第四个文件资源关闭。。。");
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new Finalization2();
        System.out.println("SAVE_HOOK打开了四个文件,并且进行操作");
        SAVE_HOOK = null;
        System.out.println("利用finalize特性,将SAVE_HOOK=null,关闭四个文件资源");
        System.gc();
        Thread.sleep(2000);//处理别的业务
    }
}
