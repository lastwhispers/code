package cn.lastwhisper.annotation;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * jdk5新特性：注解
 *
 * @author lastwhisper
 */
public class AnnotationTest {

    @Test
    public void test一个什么都有的注解() {
        if (UseAnnotation.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = UseAnnotation.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(Arrays.toString(annotation.arrayAttr()));
            System.out.println(annotation.annotation().value());
            System.out.println(annotation.clazz());
            System.out.println(annotation.enumLevel());
        }
    }

    /**
     * Changing Annotation Parameters At Runtime
     * <p></p>
     *  https://segmentfault.com/a/1190000011213222
     *
     * @throws Exception
     */
    @Test
    public void test运行期动态修改注解value() throws Exception {
        Class<?> clazz = Class.forName("cn.lastwhisper.annotation.StudentRsp");
        //获取 StudentRsp 的 name 字段
        Field field = clazz.getDeclaredField("name");
        //获取 name 字段上的 JsonProperty 注解实例
        JsonProperty jsonProperty  = field.getAnnotation(JsonProperty.class);

        System.out.println("当前 jsonProperty value:" + jsonProperty.value());

        //获取 JsonProperty 这个代理实例所持有的 InvocationHandler
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(jsonProperty);
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field hField = invocationHandler.getClass().getDeclaredField("memberValues");
        // 设置访问权限
        hField.setAccessible(true);
        // 获取 memberValues
        Map memberValues = (Map) hField.get(invocationHandler);
        // 修改 value 属性值
        memberValues.put("value", "beisen_pro_name");

        // 获取 JsonProperty 的 value 属性值
        System.out.println("当前 jsonProperty value:" + jsonProperty.value());

    }

}
