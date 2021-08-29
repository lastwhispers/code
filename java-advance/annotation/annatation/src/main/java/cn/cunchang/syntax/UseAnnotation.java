package cn.cunchang.syntax;

/**
 * @author cunchang
 */

@MyAnnotation(color = "red", value = "/user/login",
        arrayAttr = {4, 5, 6}, enumLevel = Level.INDIFFERENT,
        annotation = @MetaAnnotation("yyy"), clazz = Formatter.class)
public class UseAnnotation {

    @MyAnnotation("yyy")
    @SuppressWarnings("deprecation")
    public static void deprecatedFun() {
        sayHello();
    }

    @Deprecated
    public static void sayHello() {
        System.out.println("被弃用的函数");
    }

}
