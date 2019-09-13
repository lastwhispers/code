package cn.lastwhisper.interview.InnerClass.member;

/**
 * 成员内部类实例
 * @author lastwhisper
 */
public class OuterClass {
    private String name;

    public OuterClass(String name) {
        this.name = name;
    }

    // 成员内部类，类比对象的成员变量
    private class InnerClass{
        int innerPrice;

        public InnerClass(int innerPrice) {
            System.out.println("成员内部类~类比对象的成员变量");
            this.innerPrice = innerPrice;
        }

        public void print() {
            helloInnerClass();
            System.out.println("出售：" + name + " 单价:" + innerPrice);
        }
    }

    public void helloInnerClass() {
        System.out.println("我是外部类的helloInnerClass方法，内部类你可以调用我");
    }

    public static void main(String[] args) {
        OuterClass sample = new OuterClass("香蕉");
        InnerClass inner = sample.new InnerClass(20);
        inner.print();
    }
}