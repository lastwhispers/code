package cn.lastwhisper.jvm.tmp.heap.java;

/**
 * @author shkstart  shkstart@126.com
 * @create 2020  17:28
 */
public class SimpleHeap {
    private int id;//属性、成员变量

    public SimpleHeap(int id) {
        this.id = id;
    }


    public void show() {
        System.out.println("My ID is " + id);
    }

    /**
     * 方法栈、堆、方法区之间的联系
     * @param args
     */
    public static void main(String[] args) {
        SimpleHeap sl = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);

        int[] arr = new int[10];

        Object[] arr1 = new Object[10];
    }
}
