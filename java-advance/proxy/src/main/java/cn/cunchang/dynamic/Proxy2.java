package cn.cunchang.dynamic;

import cn.cunchang.Moveable;
import cn.cunchang.Tank;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 产生代理的类
 *
 */
public class Proxy2 {

    /**
     * 动态传入接口，其实jdk可以传多个接口
     *
     * @param interfaces
     * @return
     * @throws Exception
     */
    public static Object newProxyInstance(MyClassLoader classLoader,Class interfaces) throws Exception{
        //换行字符串
        String rt = "\r\n";
        String methodStr = "";
        //反射拿到接口的所有的方法
        Method[] methods = interfaces.getMethods();
        for(Method m : methods){
            methodStr += "@Override"+rt +
                    "public void "+ m.getName()+ "() {" +rt +
                    //计算方法运行了多长时间
                    "        long start = System.currentTimeMillis();" +rt +
                    "        m."+m.getName() +"();" +rt +
                    "        long end = System.currentTimeMillis();"+rt +
                    "        System.out.println(\"耗时：\"+(end-start));"+rt +
                    "}";
        }
        
        //只要能动态的 编译这段代码，就能动态的产生代理类！类的名字无所谓
        //动态编译的技术：JDK6 Compiler API，CGLib（用到了ASM） ，ASM
        //（CGLib、ASM不用源码来编译，能直接生成二进制文件，因为java的二进制文件格式是公开的）
        //Spring内部，如果是实现接口就是用的JDK本身的API产生代理，否则就用CGLib
        
        String src = 
        "package "+classLoader.getProxyClassPackage()+";"+ rt +

        "import cn.cunchang.Moveable;" + rt +

        "public class $Proxy0 implements "+ interfaces.getName() +"{"+rt +

        "    Moveable m;"+rt +
            
        "    public $Proxy0(Moveable m) {"+rt +
        "        super();"+rt +
        "        this.m = m;"+rt +
        "    }"+rt +

         methodStr +
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