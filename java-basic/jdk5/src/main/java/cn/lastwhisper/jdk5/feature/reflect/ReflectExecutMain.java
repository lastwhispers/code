package cn.lastwhisper.jdk5.feature.reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author lastwhisper
 */
public class ReflectExecutMain {
    public static void main(String[] args) throws Exception {
        //InputStream is = new FileInputStream("D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\src\\config.properties");
        InputStream is = ReflectExecutMain.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(is);
        String className = properties.getProperty("className");

        Class<?> clazz = Class.forName(className);
        //Class<?> clazz = Class.forName("cn.lastwhisper.jdk5.feature.reflect.TestArrayArguments");
        Method mainMethod = clazz.getMethod("main", String[].class);
        System.out.println(mainMethod.invoke(null, (Object) new String[]{"arg1", "arg2", "arg3"}));
    }
}

class TestArrayArguments {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("----------" + arg + "----------");
        }
    }
}