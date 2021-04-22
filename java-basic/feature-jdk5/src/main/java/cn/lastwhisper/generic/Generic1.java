package cn.lastwhisper.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk5新特性：泛型
 * @author lastwhisper
 */
public class Generic1 {
    public static void main(String[] args) throws Exception {
        // 1.泛型问题引入
        //ArrayList collections = new ArrayList();
        //collections.add(1);
        //collections.add(1L);
        //collections.add("abc");
        //int i = (Integer) collections.get(1);//编译要强制类型转换且运行时出错！

        // 2.泛型是给编译器看的
        ArrayList<String> collection2 = new ArrayList<String>();
        ArrayList<Integer> collection3 = new ArrayList<Integer>();
        System.out.println(collection2.getClass() == collection3.getClass());
        // 使用反射将String值放到ArrayList<Integer>中
        collection3.getClass().getMethod(
                "add", Object.class).invoke(collection3, "字符串");
        System.out.println(collection3.get(0));

        // 3.通配符“？”，不知道泛型是什么
        printCollection(collection3);

    }

    //public static void printCollection(Collection<Object> cols) {
    //    for (Object col : cols) {
    //        System.out.println(col);
    //    }
    //    cols.add("字符串");//没错
    //    //cols = new HashSet<Date>();//错误
    //}

    public static void printCollection(Collection<?> cols) {
        for (Object col : cols) {
            System.out.println(col);
        }
        //cols.add("string");//错误
        cols.size();
    }

}
