package cn.lastwhisper.jvm.gc.finalize;

/**
 * 1.对象可以在被GC时自救
 * 2.自救的机会只有一次
 * @author lastwhisper
 */
public class Finalization {
    public static Finalization SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Finalization.SAVE_HOOK = this;
        System.out.println("处理系统资源、执行其他清理或者对象自救");
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new Finalization();
        // 在堆中无GC Roots，会被GC，但是SAVE_HOOK实现了finalize有一次自救的机会
        SAVE_HOOK = null;
        //显示调用垃圾回收
        System.gc();
        // finalize方法优先级很低，暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            System.out.println("SAVE_HOOK对象还活着");
        } else {
            System.out.println("SAVE_HOOK对象已经被回收");
        }
        // finalize方法只会调用一次，第二次自救失败
        SAVE_HOOK = null;
        System.gc();
        // finalize方法优先级很低，暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            System.out.println("SAVE_HOOK对象还活着");
        } else {
            System.out.println("SAVE_HOOK对象已经被回收");
        }
    }
}
