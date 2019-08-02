package cn.lastwhisper.javabasic.SwapTwoInteger;

/**@desc 错误版本
 * @author eval
 */
public class Swap1 {

    /**
     * 第一步
     * 交换两个integer类型的变量
     */
    public static void swap(Integer value1, Integer value2) {
        //第二步定义临时变量tempt，将value1赋值给tempt
        Integer tempt = value1;
        //第三步 将value2赋值value1变量
        value1 = value2;
        //第四步 将tempt赋值给value2
        value2 = tempt;

    }

    public static void main(String[] args) {

        Integer firstValue = 50;
        Integer anotherValue = 100;
        System.out.println("交换前：firstValue=" + firstValue + "  anotherValue=" + anotherValue);
        swap(firstValue, anotherValue);
        System.out.println("交换后：firstValue=" + firstValue + "  anotherValue=" + anotherValue);

    }

}
