package cn.lastwhisper.jvm.gc;

/**
 * 引用计数法的缺陷
 * @author lastwhisper
 */
public class ReferenceCountingGC {

    public Object install = null;
    
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2*_1MB];
    
    public static void main(String[] args){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.install=objB;
        objB.install=objA;

        objA=null;
        objB=null;

        System.gc();
    }
}
