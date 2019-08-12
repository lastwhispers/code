package cn.lastwhisper.javabasic.InnerClass.anonymous;


/**
 * 匿名内部类实例
 * @author lastwhisper
 */
public class OuterClass {
    public static void print(InnerClass innerClass) {
        innerClass.print();
    }

    public static void main(String[] args) {
        OuterClass.print(new InnerClass() {
            @Override
            public void print() {
                System.out.println("匿名内部类~由于没有引用，每次新创建的，在Minor GC时被清除");
            }
        });
        //OuterClass.print(() -> System.out.println("匿名内部类~由于没有引用，每次新创建的，在Minor GC时被清除"));
    }
}


