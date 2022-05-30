package cn.cunchang.myjdkdynamic;

import cn.cunchang.Moveable;
import cn.cunchang.Tank;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

public class Proxy1 {

    public static Object newProxyInstance(MyClassLoader classLoader) throws Exception {
        String rt = "\r\n";
        String src =
                "package "+classLoader.getProxyClassPackage()+";" + rt +

                "import cn.cunchang.Moveable;" + rt +

                "public class $Proxy0 implements Moveable{" + rt +

                "    Moveable m;" + rt +

                "    public $Proxy0(Moveable m) {" + rt +
                "        super();" + rt +
                "        this.m = m;" + rt +
                "    }" + rt +

                "    @Override" + rt +
                "    public void move() {" + rt +
                "        long start = System.currentTimeMillis();" + rt +
                "        m.move();" + rt +
                "        long end = System.currentTimeMillis();" + rt +
                "        System.out.println(\"耗时：\"+(end-start));" + rt +
                "    }" + rt +
                "}";

        //1，生成代理类
        String fileName = classLoader.getDir() + File.separator + "$Proxy0.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.flush();
        fw.close();

        //2，将生成的类进行编译成class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();//拿到系统默认的编译器（其实就是javac）
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);//诊断监听器；语言；编码
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        CompilationTask task = compiler.getTask(null, fileMgr, null, null, null, units);
        task.call();
        fileMgr.close();

        //3，将class load到内存
        Class clazz = classLoader.findClass("$Proxy0");
        //4，，创建一个对象
        //不能用 clazz.newInstance();创建对象因为它会调用空构造方法
        Constructor<Moveable> constructor = clazz.getConstructor(Moveable.class);//获取某个类型参数的构造器
        return constructor.newInstance(new Tank());
    }

}