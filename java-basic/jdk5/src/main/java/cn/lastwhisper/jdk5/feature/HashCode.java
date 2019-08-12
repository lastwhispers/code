package cn.lastwhisper.jdk5.feature;

import cn.lastwhisper.jdk5.feature.reflect.ReflectPoint;

import java.util.HashSet;
import java.util.Set;

/**
 * hashcode相关
 * hashcode比较对象是否相等
 * @author lastwhisper
 */
public class HashCode {
    // hashcode导致内存泄漏
    // 要想造成内存泄漏，你的工具类对象本身要持有指向传入对象的引用才行！但是当你的业务方法调用工具类的静态方法时，
    // 会生产一个称为方法栈帧的东西（每次方法调用，JVM都会生成一个方法栈帧），当方法调用结束返回的时候，
    // 当前方法栈帧就已经被弹出了并且被释放掉了。 整个过程结束时，工具类对象本身并不会持有传入对象的引用。
    // 各种内存泄漏 https://www.cnblogs.com/panxuejun/p/5883044.html
    //Java中由substring方法引发的内存泄漏
    // threadloca内存泄漏 https://www.jianshu.com/p/a1cd61fa22da

    public static void main(String[] args) {
        Set<ReflectPoint> set = new HashSet();
        ReflectPoint pt1 = new ReflectPoint(1, 1);
        ReflectPoint pt2 = new ReflectPoint(2, 2);
        ReflectPoint pt3 = new ReflectPoint(1, 1);

        set.add(pt1);
        set.add(pt2);
        set.add(pt3);
        set.add(pt1);

        // 对象重写hashcode方法，添加到集合中后不要修改对象的属性值
        pt1.setX(2);
        set.remove(pt1);
        // 1. 没有重写hashcode size=3。
        // 2. 重写hashcode size=2。
        // 3. 在2的基础上修改对象的属性值，再移除对象，size=2，内存泄漏。
        System.out.println(set.size());

    }

}
