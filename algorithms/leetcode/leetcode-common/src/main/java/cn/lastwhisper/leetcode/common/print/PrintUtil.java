package cn.lastwhisper.leetcode.common.print;

import java.util.Collection;
import java.util.List;


/**
 * 打印工具类
 * @author lastwhisper
 * @date 2020/2/1
 */
public class PrintUtil {

    /**
     * 层次打印二维数组
     */
    public static void printLists(List<List<Integer>> lists) {
        System.out.println("[");
        int counter1 = 1;
        for (List<Integer> list : lists) {
            System.out.print("\t[\"");
            int counter2 = 1;
            for (Integer i : list) {
                if (counter2 != list.size()) {
                    System.out.print(i + "\",\"");
                } else {
                    System.out.print(i);
                }
                counter2++;
            }
            if (counter1 != lists.size()) {
                System.out.print("\"],\n");
            } else {
                System.out.print("\"]\n");
            }
            counter1++;
        }
        System.out.println("]");
    }

    public static void printStringLists(List<List<String>> lists) {
        System.out.println("[");
        int counter1 = 1;
        for (List<String> list : lists) {
            System.out.print("\t[\"");
            int counter2 = 1;
            for (String i : list) {
                if (counter2 != list.size()) {
                    System.out.print(i + "\",\"");
                } else {
                    System.out.print(i );
                }
                counter2++;
            }
            if (counter1 != lists.size()) {
                System.out.print("\"],\n");
            } else {
                System.out.print("\"]\n");
            }
            counter1++;
        }
        System.out.println("]");
    }

    /**
     * 打印数组
     */
    public static void printList(Collection<?> collection) {
        System.out.println(collection2String(collection));
    }

    /**
     * 将Collection扁平化迭代成String
     */
    public static String collection2String(Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("[\"");
            int counter = 1;
            for (Object object : collection) {
                sb.append(object);
                if (counter != collection.size()) {
                    sb.append("\",\"");
                }
                counter++;
            }
            sb.append("\"]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
