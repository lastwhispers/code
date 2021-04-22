package cn.lastwhisper.classloader.encrypt;

import java.io.*;

/**
 * 讲解classloader的实际作用，如自定义类加载器加载加密的字节码文件
 * @author lastwhisper
 */
public class ClassLoader2 extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // 1. 将正常的.class文件加密
        //encryptClass();
        // 2. 加载并解密字节码.class文件
        decryptAndLoadClass();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPathFile = "D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\src\\main\\java\\cn\\lastwhisper\\jdk5\\feature\\classloader\\encrypt\\ClassLoaderAttachment.class";
        try {
            FileInputStream fis = new FileInputStream(classPathFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 将fos加密到fis
            cypher(fis, bos);
            byte[] bytes = bos.toByteArray();
            //return this.defineClass(bytes, 0, bytes.length);
            return this.defineClass(name, bytes, 0, bytes.length);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void decryptAndLoadClass()throws Exception {
        ClassLoader2 classLoader2 = new ClassLoader2();
        // cn.lastwhisper.jdk5.feature.classloader.encrypt.ClassLoader2@5cad8086
        //Class<?> clazz = classLoader2.loadClass("ClassLoaderAttachment");
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        Class<?> clazz = classLoader2.loadClass("cn.lastwhisper.classloader.encrypt.ClassLoaderAttachment");
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj);
    }

    // 将正常的.class文件加密
    public static void encryptClass() throws Exception {
        String srcPath = "D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\target\\classes\\cn\\lastwhisper\\jdk5\\feature\\classloader\\encrypt\\ClassLoaderAttachment.class";
        String destPath = "D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\src\\main\\java\\cn\\lastwhisper\\jdk5\\feature\\classloader\\encrypt\\ClassLoaderAttachment.class";
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(destPath);
        // 将fis加密到fos
        cypher(fis, fos);
        fis.close();
        fos.close();
    }

    // 异或加密解密
    public static void cypher(InputStream istream, OutputStream ostream) throws Exception {
        //下面这段代码可能遇到255的字节，当成byte就成了-1
		/*byte b = 0;
		while((b = (byte)istream.read()) != -1)
		{
			ostream.write(b ^ 0xff);
		}*/

        int b = 0;
        while ((b = istream.read()) != -1) {
            ostream.write(((byte) b) ^ 0xff);
        }
    }

}
