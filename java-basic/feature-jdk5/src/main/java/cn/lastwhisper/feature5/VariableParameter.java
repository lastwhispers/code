package cn.lastwhisper.feature5;

/**
 * jdk5新特性：可变参数+增强for循环
 * @author lastwhisper
 */
public class VariableParameter {
    public static void main(String[] args) {
        System.out.println(add(10, 20));
        System.out.println(add(10, 20, 30));
        System.out.println(add(10, 20, 30, 40));
    }

    /**
     * 1. "..."表示可变参数，只能放在参数列表的最后位置
     * 2. "..."前后有无空格都行
     * 3. 编译器为可变参数隐式的创建一个数组，在方法中以数组的形式使用
     *
     * @param args
     * @return int
     */
    public static int add(int... args) {
        int sum = 0;
        //for (int i = 0; i < args.length; i++) {
        //    sum += args[i];
        //}
        // 增强for循环；每次循环的结果，可以使用修饰符，如使用final修饰即可作为匿名内部类的参数
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

}
