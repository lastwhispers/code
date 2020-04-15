package cn.lastwhisper.feature5.reflect;

import java.lang.reflect.Field;

/**
 * 使用反射更新字段的值
 * @author lastwhisper
 */
public class ReflectUpdateField {
    public String str1 = "ball";
    public String str2 = "basketball";
    public String str3 = "lastwhisper";

    public static void main(String[] args) throws Exception {
        ReflectUpdateField obj = new ReflectUpdateField();
        System.out.println("未修改前：" + obj);

        Class<? extends ReflectUpdateField> clazz = obj.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                String oldValue = (String) field.get(obj);
                String newValue = oldValue.replace('b', '*');
                field.set(obj, newValue);
            }
        }

        System.out.println("修改后：" + obj);
    }

    @Override
    public String toString() {
        return "ReflectUpdateField{" +
                "str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                ", str3='" + str3 + '\'' +
                '}';
    }
}
