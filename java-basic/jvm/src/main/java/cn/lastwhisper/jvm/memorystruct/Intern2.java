package cn.lastwhisper.jvm.memorystruct;

public class Intern2 {


    public static void main(String[] args) {
        String a1 = "XXX";
        String a2 = "XXX";
        System.out.println("编译器可以确定,只创建一个String的对象,a1 == a2：" + (a1 == a2));

        String x1 = new String("XXX");
        String x2 = new String("XXX");
        System.out.println("编译器无法确定,创建两个String的对象,x1 == x2：" + (x1 == x2));
        x1 = x1.intern();
        x2 = x2.intern();
        System.out.println("intern之后,进入常量池，指向同一个位置" +(x1 == x1));
        System.out.println("intern之后,进入常量池，指向同一个位置" +(x2 == x2));
        System.out.println("intern之后,进入常量池，指向同一个位置" +(x1 == x2));

    }
}
