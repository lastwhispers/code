package cn.lastwhisper;

import static java.lang.Math.abs;
import static java.lang.Math.*;
/**
 * jdk5新特性：静态导入
 * @author lastwhisper
 */
public class StaticImport {
    public static void main(String[] args) {
        // 正常使用
        System.out.println(Math.min(3, 9));
        System.out.println(Math.abs(3 - 9));
        // 使用静态导入
        System.out.println(min(3, 9));
        System.out.println(abs(3 - 9));
    }
}
