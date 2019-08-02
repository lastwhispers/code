package cn.lastwhisper.javabasic.ClassLoader.other;

import java.util.Date;
import java.util.List;
//-Xbootclasspath/a:C:\Users\28529\Desktop\test\my_lib.jar
public class ClassLoaderTest {
  
    @SuppressWarnings("rawtypes")  
    public static void main(String[] args){
        System.out.println("ClassLoaderText:"+ClassLoaderTest.class.getClassLoader().getClass().getName());
        System.out.println("List:"+List.class.getClassLoader());
//        System.out.println("getSystemClassLoader:"+ClassLoader.getSystemClassLoader());

//        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
//        while(cl != null){
//            System.out.print(cl.getClass().getName()+"->");
//            cl = cl.getParent();
//        }

        System.out.println("--------------------------------------------------");

        try {
            Class classDateApp = new MyClassLoader("E:\\idea\\IdeaProject\\code\\java-basic\\src\\main\\java\\cn\\lastwhisper\\javabasic\\ClassLoader\\other\\classfile").loadClass("cn.lastwhisper.javabasic.ClassLoader.other.ClassLoaderAttachment");
            Class classDateMy = new MyClassLoader("E:\\idea\\IdeaProject\\code\\java-basic\\src\\main\\java\\cn\\lastwhisper\\javabasic\\ClassLoader\\other\\classfile").loadClass("ClassLoaderAttachment");
            Date dateApp = (Date) classDateApp.newInstance();
            Date dateMy = (Date) classDateMy.newInstance();
            //输出ClassLoaderAttachment类的加载器名称
            System.out.println("Default ClassLoader:"+ClassLoaderTest.class.getClassLoader().getSystemClassLoader());
            System.out.println("dateApp ClassLoader:"+dateApp.getClass().getClassLoader().getClass().getName());
            System.out.println("dateApp:"+dateApp);
            System.out.println("dateMy ClassLoader:"+dateMy.getClass().getClassLoader().getClass().getName());
            System.out.println("dateMy:"+dateMy);
            System.out.println("dateApp instanceof  ClassLoaderAttachment:" + (dateApp instanceof  ClassLoaderAttachment));
            System.out.println("dateApp instanceof  cn.lastwhisper.javabasic.ClassLoader.other.ClassLoaderAttachment:" + (dateApp instanceof ClassLoaderAttachment));
            System.out.println("dateMy instanceof  ClassLoaderAttachment:" + (dateMy instanceof  ClassLoaderAttachment));
            System.out.println("dateMy instanceof  cn.lastwhisper.javabasic.ClassLoader.other.ClassLoaderAttachment:" + (dateMy instanceof ClassLoaderAttachment ));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
      
} 