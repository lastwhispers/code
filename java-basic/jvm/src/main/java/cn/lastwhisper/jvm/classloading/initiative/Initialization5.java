package cn.lastwhisper.jvm.classloading.initiative;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 主动引用触发初始化、演示五
 * @author lastwhisper
 */
public class Initialization5 {

    public static void main(String[] args) {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {


            // REF_invokeStatic
            MethodHandle testREF_invokeStatic = lookup.findStatic(MethodHandleClass.class, "testREF_invokeStatic", MethodType.methodType(void.class, String.class));
            testREF_invokeStatic.invoke("啥也不干，打印一段话");

            // REF_getStatic

            // REF_putStatic
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
