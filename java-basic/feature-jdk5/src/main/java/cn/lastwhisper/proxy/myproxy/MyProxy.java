package cn.lastwhisper.proxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {

    private static final String rt = "\r";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?> interfaces, MyInvocationHandler handler) throws IllegalArgumentException {
        if (null == handler) {
            throw new NullPointerException();
        }
        //根据代理接口构造代理类
        Method[] methods = interfaces.getMethods();
        StringBuilder proxyClassSb = new StringBuilder();
        proxyClassSb.append("package ")
                .append(classLoader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces.getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h){")
                .append("this.h = h;}").append(rt)
                .append(getMethodString(methods, interfaces))
                .append("}");

        String className = interfaces.getSimpleName() + "_$myProxy0";
        //写入JAVA文件 进行编译
        String filename = classLoader.getDir() + File.separator + "$MyProxy0.java";
        File myProxyFile = new File(filename);

        try {
            if(!myProxyFile.exists()){
                myProxyFile.createNewFile();
            }
            compile(proxyClassSb.toString(), myProxyFile);

            //利用自定义的classloader加载
            Class<?> $myProxy0 = classLoader.findClass("$MyProxy0");
            //$MyProxy0初始化
            Constructor<?> constructor = $myProxy0.getConstructor(MyInvocationHandler.class);

            Object o = constructor.newInstance(handler);

            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMethodString(Method[] methods, Class<?> interfaces) {
        StringBuilder sb = new StringBuilder();
        for (Method method : methods) {
            sb.append("public void ").append(method.getName())
                    .append("()").append("throws Throwable{")
                    .append("Method method1 = ").append(interfaces.getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\",new Class[]{});")
                    .append("this.h.invoke(this,method1,null);}").append(rt);
        }
        return sb.toString();
    }

    /**
     * @param proxyClassString 代理类的代码
     * @param myProxyFile      代理类的Java文件
     * @throws IOException
     */
    private static void compile(String proxyClassString, File myProxyFile) throws IOException {
        // in out
        FileUtil.copyToFile(proxyClassString.getBytes(), myProxyFile);
        // 调用系统编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> javaFileObjects = standardJavaFileManager.getJavaFileObjects(myProxyFile);

        JavaCompiler.CompilationTask compilerTask = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, javaFileObjects);

        compilerTask.call();

        standardJavaFileManager.close();
    }

}
