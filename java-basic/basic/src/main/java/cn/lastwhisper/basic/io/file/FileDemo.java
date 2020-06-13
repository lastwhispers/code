package cn.lastwhisper.basic.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author lastwhisper
 * @date 2020/6/13
 */
public class FileDemo {

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
     *
     *
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


    @Test
    public void testDelete() throws IOException {
        File file1 = new File("D:\\a.txt");
        System.out.println("ctfile:" + file1.createNewFile());
        // 删除文件不进入回收站
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


}
