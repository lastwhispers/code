package cn.lastwhisper.feature5.io.io;

import org.junit.Test;

import java.io.*;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesDemo {

    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 使用java.util.Properties类的load()方法加载properties文件
     */
    @Test
    public void testLoad1() {
        try {
            // 获取文件流（方法1或2均可）
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/config.properties"))); //方法1
//            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"); //方法2

            Properties prop = new Properties();
            prop.load(new InputStreamReader(inputStream, DEFAULT_ENCODING)); //加载格式化后的流

            String targetDirs = prop.getProperty("targetDirs");
            String deleteDirs = prop.getProperty("deleteDirs");
            String deleteFiles = prop.getProperty("deleteFiles");
            System.out.println("targetDirs: " + targetDirs);
            System.out.println("deleteDirs: " + deleteDirs);
            System.out.println("deleteFiles: " + deleteFiles);

        } catch (FileNotFoundException e) {
            System.out.println("properties文件路径有误！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用class变量的getResourceAsStream()方法
     * 注意：getResourceAsStream()方法的参数路径/包路径+properties文件名+.后缀
     */
    @Test
    public void testLoad2() {
        try {
            InputStream inputStream = PropertiesDemo.class.getResourceAsStream("/config.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String targetDirs = prop.getProperty("targetDirs");
            String deleteDirs = prop.getProperty("deleteDirs");
            String deleteFiles = prop.getProperty("deleteFiles");
            System.out.println("targetDirs: " + targetDirs);
            System.out.println("deleteDirs: " + deleteDirs);
            System.out.println("deleteFiles: " + deleteFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用class.getClassLoader()所得到的java.lang.ClassLoader的getResourceAsStream()方法
     * 注意：getResourceAsStream(name)方法的参数必须是包路径+文件名+.后缀
     */
    @Test
    public void testLoad3() {
        try {
            InputStream inputStream = PropertiesDemo.class.getClassLoader().getResourceAsStream("config.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String targetDirs = prop.getProperty("targetDirs");
            String deleteDirs = prop.getProperty("deleteDirs");
            String deleteFiles = prop.getProperty("deleteFiles");
            System.out.println("targetDirs: " + targetDirs);
            System.out.println("deleteDirs: " + deleteDirs);
            System.out.println("deleteFiles: " + deleteFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用java.lang.ClassLoader类的getSystemResourceAsStream()静态方法
     * getSystemResourceAsStream()方法的参数必须是包路径+文件名+.后缀
     */
    @Test
    public void testLoad4() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String targetDirs = prop.getProperty("targetDirs");
            String deleteDirs = prop.getProperty("deleteDirs");
            String deleteFiles = prop.getProperty("deleteFiles");
            System.out.println("targetDirs: " + targetDirs);
            System.out.println("deleteDirs: " + deleteDirs);
            System.out.println("deleteFiles: " + deleteFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用java.util.ResourceBundle类的getBundle()方法
     * 注意：注意：这个getBundle()方法的参数相对同目录路径，并去掉.properties后缀，否则将抛异常
     */
    @Test
    public void testLoad5() {
        ResourceBundle resource = ResourceBundle.getBundle("config");

        String targetDirs = resource.getString("targetDirs");
        String deleteDirs = resource.getString("deleteDirs");
        String deleteFiles = resource.getString("deleteFiles");
        System.out.println("targetDirs: " + targetDirs);
        System.out.println("deleteDirs: " + deleteDirs);
        System.out.println("deleteFiles: " + deleteFiles);
    }

    /**
     * 使用java.util.PropertyResourceBundle类的构造函数
     */
    public static void Method6() {
        ResourceBundle resource;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/demo/config.properties")));
            resource = new PropertyResourceBundle(inputStream);

            String driverClassName = resource.getString("driverClassName");
            System.out.println("Method6: " + driverClassName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
