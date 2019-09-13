package cn.lastwhisper.interview.InnerClass.local;

/**
 * 局部内部类实例
 * @author lastwhisper
 */
public class OuterClass {

    private static int a = 1;
    private String name;

    public OuterClass(String name) {
        this.name = name;
    }

    public void helloInnerClass() {
        System.out.println("我是外部类的helloInnerClass方法，内部类你可以调用我");
    }

    public void print(int price) {
        // 局部内部类，类比方法内的局部变量
        // 生命周期：局部内部类在一次方法调用之后就结束。
        class InnerClass {
            int innerPrice;

            public InnerClass(int innerPrice) {
                System.out.println("局部内部类~类比方法内的局部变量");
                this.innerPrice = innerPrice;
            }

            public void sell() {
                helloInnerClass();
                System.out.println("出售：" + name + " 单价:" + innerPrice);
            }
        }
        InnerClass apple = new InnerClass(price);
        apple.sell();
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass("苹果");
        outerClass.print(10);
    }
}
