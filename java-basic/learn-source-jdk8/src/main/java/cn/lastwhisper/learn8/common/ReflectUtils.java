package cn.lastwhisper.learn8.common;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static Field getField(Object target, String fieldName) {
        if (target == null) {
            return null;
        }
        if ("".equals(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = target.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 通过字段名从对象或对象的父类中得到字段的值
     * @param target 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
     */
    public static Object getValue(Object target, String fieldName) {
        Object obj = null;
        try {
            obj = getField(target, fieldName).get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;

    }

    public static void setValue(Object target, String fieldName, Object value) {
        try {
            Field field = getField(target, fieldName);
            field.set(target,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
