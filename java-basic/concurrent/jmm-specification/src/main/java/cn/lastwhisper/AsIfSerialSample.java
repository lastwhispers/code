package cn.lastwhisper;

/**
 * @author ：
 * @date：2019/7/18
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class AsIfSerialSample {

    public static void main(String[] args) {
        /**
         * as-if-serial语义的意思是：不管怎么重排序（编译器和处理器为了提高并行度），（单线程）
         * 程序的执行结果不能被改变。编译器、runtime和处理器都必须遵守as-if-serial语义。
         *
         * 以下例子当中1、2步存在指令重排行为，但是1、2不能与第三步指令重排
         * 也就是第3步不可能先于1、2步执行，否则将改变程序的执行结果
         */
        double p = 3.14; //1
        double r = 1.0; //2
        double area = p * r * r; //3计算面积
    }

}
