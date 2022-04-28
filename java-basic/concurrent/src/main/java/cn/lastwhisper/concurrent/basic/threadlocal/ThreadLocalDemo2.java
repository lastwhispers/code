package cn.lastwhisper.concurrent.basic.threadlocal;

/**
 * ThreadLocal原理
 *
 * @author lastwhisper
 */
public class ThreadLocalDemo2 {

    static ThreadLocal<Integer> tl1 = new ThreadLocal<>();
    static ThreadLocal<Integer> tl2 = new ThreadLocal<>();

    public static void main(String[] args){
        tl1.set(1);
        System.out.println(tl1.get());
        System.out.println(tl2.get());
        tl2.set(2);
        System.out.println(tl1.get());
        System.out.println(tl2.get());
    }

}
