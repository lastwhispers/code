package cn.lastwhisper.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Administrator
 */
public class JavassistDynamicProxyClass {

    static class UserServiceImpl {

        public void getName(String userId) {
            System.out.println("UserServiceImpl————getName————代理方法中");
        }

        public String createUser(String name, int id) {
            System.out.println("UserServiceImpl————createUser————代理方法中");
            return name + id;
        }

    }

    /**
     * 代理类回调方法
     */
    public static Method getMethod(Class<?> cla, String name, Class... types) {
        try {
            return cla.getMethod(name, types);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 动态代理对象构建
     */
    public void newProxyInstance(ClassLoader loader, Class<?> proxyTarget,
                                 InvocationHandler invocationHandler) throws NotFoundException,
            CannotCompileException, IllegalAccessException,
            InstantiationException, NoSuchMethodException,
            InvocationTargetException {
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(proxyTarget.getClassLoader()));
        CtClass targetClass = pool.get(proxyTarget.getName());
        // 构建代理类，父类是targetClass
        CtClass proxyClass = pool.makeClass(proxyTarget.getName() + "$proxy",
                targetClass);
        // 构建代理类的成员变量，添加到代理类
        CtField handlerField = new CtField(pool.get(InvocationHandler.class.getName()), "h", proxyClass);
        proxyClass.addField(handlerField);

        int methodIndex = 0;
        for (CtMethod ctMethod : targetClass.getDeclaredMethods()) {
            // 满足非public，native、static、final直接跳过
            if (!AccessFlag.isPublic(ctMethod.getModifiers())) {
                continue;
            } else if ((ctMethod.getModifiers() & AccessFlag.NATIVE) != 0) {
                continue;
            } else if ((ctMethod.getModifiers() & AccessFlag.STATIC) != 0) {
                continue;
            } else if ((ctMethod.getModifiers() & AccessFlag.FINAL) != 0) {
                continue;
            }
            String methodName = ctMethod.getName() + methodIndex;
            CtField methodField = new CtField(pool.get(Method.class.getName()),
                    methodName, proxyClass);
            StringBuilder paramTypeSrc = new StringBuilder("new Class[]{");
            for (int i = 0; i < ctMethod.getParameterTypes().length; i++) {
                if (i != 0) {
                    paramTypeSrc.append(",");
                }
                paramTypeSrc.append(ctMethod.getParameterTypes()[i].getName()).append(".class");
            }
            paramTypeSrc.append("}");
            String d = proxyTarget.getName() + ".class";
            String initSrc = getClass().getName() + ".getMethod(" + d + ",\""
                    + ctMethod.getName() + "\"," + paramTypeSrc.toString() + ")";

            proxyClass.addField(methodField, initSrc);

            CtMethod copyMethod = CtNewMethod.copy(ctMethod, proxyClass, null);

            String bodySrc = "{";
            bodySrc += "Object result=h.invoke($0," + methodName + ",$args);";

            if (!"void".equals(copyMethod.getReturnType().getName())) {
                bodySrc += "return ($r)result;";
            }
            bodySrc += "}";

            copyMethod.setBody(bodySrc);
            proxyClass.addMethod(copyMethod);
            methodIndex++;
        }
        // 构建代理类的构造器
        CtConstructor constructor = new CtConstructor(
                new CtClass[]{pool.get(InvocationHandler.class.getName())},
                proxyClass);
        constructor.setBody("h=$1;");
        proxyClass.addConstructor(constructor);
        // 生成代理类字节码文件
        proxyClass.debugWriteFile("D:\\code\\GitRepository\\Apm\\apm\\javassist\\target");
        Class clazz = proxyClass.toClass();

        UserServiceImpl userService = (UserServiceImpl) clazz.getConstructor(
                InvocationHandler.class).newInstance(invocationHandler);
        userService.getName("11");
    }

    public static void main(String[] args) throws Exception {
        new JavassistDynamicProxyClass().newProxyInstance(JavassistDynamicProxyClass.class.getClassLoader(),
                UserServiceImpl.class, new InvocationHandler() {
                    UserServiceImpl target = new UserServiceImpl();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理前");
                        Object r = method.invoke(target, args);
                        System.out.println("代理后");
                        return r;
                    }
                });
    }
}


