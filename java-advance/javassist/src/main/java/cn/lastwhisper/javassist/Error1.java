package cn.lastwhisper.javassist;

import javassist.*;


/**
 * 测试
 * @author lastwhisper
 */
public class Error1 {

    public interface IHello {
        public void sayHello(String name);
    }
    /**
     * 错误示范：
     *  不会对类型进行强制检查：如 int i=System.nanoTime(); 或 int i="abc";
     */
    public static void main(String[] args) {
        // true表示根据当前类去搜索
        ClassPool pool = new ClassPool(true);
        try {
            // 构建CtClass对象
            CtClass targetClass = pool.makeClass("cn.lastwhisper.javassist.IHello");
            // 实现接口
            targetClass.addInterface(pool.get(IHello.class.getName()));
            // 实现方法，方法名
            String mName = "sayHello";
            // 实现方法，获取方法返回类型的CtClass
            CtClass returnType = pool.get(void.class.getName());
            // 实现方法，获取方法入参的CtClass
            CtClass[] parameters = new CtClass[]{pool.get(String.class.getName())};
            // 根据方法名、返回类型、入参类型实现方法
            CtMethod ctMethod = new CtMethod(returnType, mName, parameters, targetClass);

            // 设置实现方法的方法体
            String body = "{" +
                    "System.out.println(\"$1:\t\"+$1);" +
                    "int i=System.nanoTime();" +
                    //"int k =\"abc\";"+  //在clazz.newInstance()时才能发现错误
                    "System.out.println(i);" +
                    "System.out.println(\"$args:\t\"+$args);" +
                    "System.out.println(\"$type:\t\"+$type);" +
                    "System.out.println(\"$class:\t\"+$class);" +
                    "System.out.println(\"hello:\t\"+$1);" +
                    "}";
            ctMethod.setBody(body);
            targetClass.addMethod(ctMethod);
            // 装载Class
            Class clazz = targetClass.toClass();
            // 使用反射创建对象
            IHello hello = (IHello) clazz.newInstance();
            hello.sayHello("tom");
        } catch (NotFoundException | CannotCompileException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}