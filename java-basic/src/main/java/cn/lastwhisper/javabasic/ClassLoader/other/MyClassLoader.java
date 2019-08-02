package cn.lastwhisper.javabasic.ClassLoader.other;


import java.io.*;

/**
 * 先将class文件加密，在findClass时再解密
 * @author lastwhisper
 */
public class MyClassLoader extends ClassLoader {

    //需要加载类.class文件的目录  
    private String classDir;

    //无参的构造方法，用于class.newInstance()构造对象使用  
    public MyClassLoader() {
    }

    public MyClassLoader(String classDir) {
        this.classDir = classDir;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //class文件的路径  
        String classPathFile = classDir + "\\" + name + ".class";
        System.out.println("classPathFile:" + classPathFile);
        try {
            //将class文件进行解密  
            FileInputStream fis = new FileInputStream(classPathFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            encodeAndDecode(fis, bos);
            byte[] classByte = bos.toByteArray();
            //将字节流变成一个class
            return defineClass(classByte, 0, classByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    //测试，先将ClassLoaderAttachment.class文件加密写到工程的class_temp目录下  
    public static void main(String[] args) throws Exception {
        //配置运行参数  
        //ClassLoaderAttachment.class原路径
        String srcPath = "E:\\idea\\IdeaProject\\code\\java-basic\\src\\main\\java\\cn\\lastwhisper\\javabasic\\ClassLoader\\other";
        //ClassLoaderAttachment.class输出的路径
        String desPath = "E:\\idea\\IdeaProject\\code\\java-basic\\src\\main\\java\\cn\\lastwhisper\\javabasic\\ClassLoader\\other\\classfile";
        String desFileName = "ClassLoaderAttachment.class";
        String desPathFile = desPath + "\\" + desFileName;
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(desPathFile);
        //将class进行加密
        encodeAndDecode(fis, fos);
        fis.close();
        fos.close();
    }

    /**
     * 加密和解密算法 
     * @param is
     * @param os
     * @throws Exception
     */
    private static void encodeAndDecode(InputStream is, OutputStream os) throws Exception {
        int bytes = -1;
        while ((bytes = is.read()) != -1) {
            bytes = bytes ^ 0xff;//和0xff进行异或处理
            os.write(bytes);
        }
    }

}  