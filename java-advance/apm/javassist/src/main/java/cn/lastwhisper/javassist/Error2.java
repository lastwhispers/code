package cn.lastwhisper.javassist;

import javassist.*;

/**
 * 计算方法用时
 * @author lastwhisper
 */
public class Error2 {
    /**
     * 错误示范：不能引用在方法中其它地方定义的局部变量
     */
    public static void main(String[] args) {
        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(new LoaderClassPath(Error2.class.getClassLoader()));
        try {
            CtClass targetClass = pool.get("cn.lastwhisper.javassist.StringUtil");
            CtMethod ctMethod = targetClass.getDeclaredMethod("addString");
            ctMethod.insertBefore(
                    "{ " +
                            "System.out.println(\"====before====\");" +
                            "long begin = System.nanoTime();" +
                            "System.out.println(begin);" +
                            "}");
            // insertBefore代码块中的局部变量，insertAfter中访问不到
            ctMethod.insertAfter("{ " +
                    "System.out.println(\"====after====\");" +
                    "long end = System.nanoTime();" +
                    "System.out.println(end);" +
                    "}");
            //ctMethod.insertAfter("{ " +
            //        "System.out.println(\"====after====\");" +
            //        "long end = System.nanoTime();" +
            //        "System.out.println(\"耗时:\"+(end-begin));" +
            //        "}");
            Class clazz = targetClass.toClass();
            StringUtil util = (StringUtil) clazz.newInstance();
            System.out.println(util.addString(100));
        } catch (NotFoundException | CannotCompileException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
