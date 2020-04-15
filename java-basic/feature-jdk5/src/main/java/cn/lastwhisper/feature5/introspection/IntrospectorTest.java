package cn.lastwhisper.feature5.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 内省
 * JDK中提供了对JavaBean进行操作的一些API，这套API就称为内省。
 * 如果要你自己去通过getX方法来访问私有的x，怎么做，有一定难度吧？
 * 用内省这套api操作JavaBean比用普通类的方式更方便。
 *
 */
public class IntrospectorTest {
    // https://blog.csdn.net/jiangyu1013/article/details/75280962
    public static void main(String[] args) {
        Point point = new Point(2, 5);
        String proName = "x";
        int newVlaue = 10;
        try {
            // 不使用内省
            setPropertyForExample(point, proName, newVlaue);
            Object x = getPropertyForExample(point, proName);
            System.out.println(x);
            // 使用内省
            //setProperty(point, proName, newVlaue);
            //Object x = getProperty(point, proName);
            // System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 不使用内省，需要 "x"-->"X"-->"setX"
    private static void setPropertyForExample(Object obj, String proName, int newVlaue) throws Exception {
        String methodName;
        if (proName.length() == 1) {
            methodName = "set" + proName.toUpperCase();
        } else {
            methodName = "set" + proName.substring(0, 1).toUpperCase() + proName.substring(1);
        }
        Method method = obj.getClass().getMethod(methodName, Integer.class);
        method.invoke(obj, newVlaue);
    }

    private static Object getPropertyForExample(Object obj, String proName) throws Exception {
        String methodName;
        if (proName.length() == 1) {
            methodName = "get" + proName.toUpperCase();
        } else {
            methodName = "get" + proName.substring(0, 1).toUpperCase() + proName.substring(1);
        }
        Method method = obj.getClass().getMethod(methodName);
        return method.invoke(obj);
    }

    private static void setProperty(Object obj, String proName, int newVlaue) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodSetX = proDescriptor.getWriteMethod();
        methodSetX.invoke(obj, newVlaue);
    }

    private static Object getProperty(Object obj, String proName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodGetX = proDescriptor.getReadMethod();
        return methodGetX.invoke(obj);
    }

    private static Object getProperty1(Object obj, String proName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Point.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getName().equals(proName)) {
                Method method = pd.getReadMethod();
                return method.invoke(obj);
            }
        }
        return null;
    }
}