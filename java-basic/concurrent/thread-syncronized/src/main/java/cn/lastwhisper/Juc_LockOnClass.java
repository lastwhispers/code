package cn.lastwhisper;


/**
 * synchronized锁class
 * @author cunchang
 * @date 2022/3/2 8:26 PM
 */
public class Juc_LockOnClass {
    static int stock;

    public static synchronized void decrStock(){
        System.out.println(--stock);
    }

    public static synchronized void cgg(){
        System.out.println();
    }

    public static void main(String[] args) {
        //Juc_LockOnClass.class对象
        Juc_LockOnClass.decrStock();
    }

}
