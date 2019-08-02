package cn.lastwhisper.jvm.classloading.passive;

import cn.lastwhisper.jvm.classloading.initiative.SuperClass;

/**
 * 被动使用类字段不触发初始化、演示二
 * -XX:+TraceClassLoading
 * @author lastwhisper
 */
public class NotInitialization2 {
    public static void main(String[] args){
        // 通过数组定义来引用类，不会触发此类的初始化
        // 会触发L+全类名的初始化
        SuperClass[] superClasses = new SuperClass[10];
    }
}
