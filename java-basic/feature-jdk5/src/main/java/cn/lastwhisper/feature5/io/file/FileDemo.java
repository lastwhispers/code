package cn.lastwhisper.feature5.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  File API
 * @author lastwhisper
 * @date 2020/6/13
 */
public class FileDemo {

    /*
     * 我们要想实现IO的操作，就必须知道硬盘上文件的表现形式。
     * 而Java就提供了一个类File供我们使用。
     *
     * File:文件和目录(文件夹)路径名的抽象表示形式
     */
    @Test
    public void testConstructor() {
        // Creates a new File instance by converting the given pathname string into an abstract pathname.
        File file1 = new File("D:\\demo\\a.txt");
        // Creates a new File instance from a parent pathname string and a child pathname string.
        File file2 = new File("D:\\demo\\", "a.txt");
        // Creates a new File instance from a parent abstract pathname and a child pathname string.
        File file3 = new File("D:\\demo\\");
        File file4 = new File(file3, "a.txt");
    }

    /**
     * 文件以及文件夹创建
     */
    @Test
    public void testCreate() throws IOException {
        File file1 = new File("D:\\demo");
        System.out.println("mkdir:" + file1.mkdir());

        File file2 = new File(file1, "a.txt");
        System.out.println("ctfile:" + file2.createNewFile());

        // java.io.IOException: 系统找不到指定的路径。
        // file 的创建，建立在dir的基础下
        //File file3 = new File("D:\\test\\b.txt");
        //System.out.println("ctfile:" + file3.createNewFile());

        // dir 的创建，建立在dir的基础下
        File file4 = new File("D:\\dir1\\dir2");
        System.out.println("mkdir:" + file4.mkdirs());
    }

    /**
     * 文件以及文件夹删除
     *  1、要删除一个文件夹，请注意该文件夹内不能包含文件或者文件夹
     *  2、Java中的删除不走回收站
     */
    @Test
    public void testDelete() throws IOException {
        File file1 = new File("D:\\a.txt");
        System.out.println("ctfile:" + file1.createNewFile());
        System.out.println("delete:" + file1.delete());
    }

    /*
     * 重命名功能:public boolean renameTo(File dest)
     * 		如果路径名相同，就是改名。
     * 		如果路径名不同，就是改名并剪切。
     *
     * 路径以盘符开始：绝对路径	c:\\a.txt
     * 路径不以盘符开始：相对路径	a.txt
     */
    @Test
    public void testRename() throws IOException {
        // 需求：我要修改这个文件的名称为"b.txt"
        // File file = new File("D:\\a.txt");
        // File newFile = new File("D:\\b.txt");
        // System.out.println("renameTo:" + file.renameTo(newFile));

        File file2 = new File("D:\\a.txt");
        File newFile2 = new File("D:\\b.txt");
        System.out.println("renameTo:" + file2.renameTo(newFile2));
    }

    /*
     * 文件权限
     */
    @Test
    public void testSecurity() throws IOException {
        File file = new File("D:\\a.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        System.out.println("判断是否是目录:" + file.isDirectory());// false
        System.out.println("判断是否是文件:" + file.isFile());// true
        System.out.println("判断是否存在:" + file.exists());// true
        System.out.println("判断是否可读:" + file.canRead());// true
        System.out.println("判断是否可写:" + file.canWrite());// true
        System.out.println("判断是否隐藏:" + file.isHidden());// false
    }

    /*
     * 文件详情
     */
    @Test
    public void testFindDetail() throws IOException {
        File file = new File("D:\\a.txt");
        System.out.println("获取绝对路径:" + file.getAbsolutePath());
        System.out.println("获取相对路径:" + file.getPath());
        System.out.println("获取名称:" + file.getName());
        System.out.println("获取字节数:" + file.length());
        System.out.println("获取最后一次的修改时间:" + file.lastModified());

        // 1592035972157
        Date d = new Date(1592035972157L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(d);
        System.out.println(s);
    }

    /**
     * 文件相关信息
     */
    @Test
    public void testList(){
        File file = new File("d:\\");
        // public String[] list():获取指定目录下的所有文件或者文件夹的名称数组
        String[] strArray = file.list();
        for (String s : strArray) {
            System.out.println(s);
        }
        System.out.println("------------");
        // public File[] listFiles():获取指定目录下的所有文件或者文件夹的File数组
        File[] fileArray = file.listFiles();
        for (File f : fileArray) {
            System.out.println(f.getName());
        }
    }

}
