package cn.lastwhisper.jvm.memorystruct;

public class Intern {

    //public static void main(String[] args) {
    //    String str1 = new StringBuilder("计算机").append("软件").toString();
    //    System.out.println(str1.intern() == str1);//true
    //
    //    //https://segmentfault.com/a/1190000011543995
    //    //sun.misc.Version.init();方法中已经存在"java"常量
    //    // 修改为new StringBuilder("ja").append("ava").toString(); true
    //    String str2 = new StringBuilder("ja").append("va").toString();
    //    // str2.intern()字符串常量池、str2堆中
    //    System.out.println(str2.intern() == str2);//false
    //}

    public static void main(String[] args){
        String x1 = new StringBuilder("XXX").toString();
        String x2 = new StringBuilder("XXX").toString();
        System.out.println(x1.intern() == x1.intern());
        System.out.println(x1.intern() == x2.intern());
        System.out.println(new StringBuilder("XXX").toString().intern() == x1.intern());
        System.out.println("XXXX" == new StringBuilder("XXXX").toString().intern());
    }
}
