package cn.lastwhisper.interview.RelationFile;

import java.io.File;

/**
 * 删除指定文件夹
 * @author lastwhisper
 * @date 2019/11/22
 */
public class DeleteFile {

    public static void main(String[] args) {
        File file = new File("D:\\code");
        recursionFindDirectory(file);
    }

    /**
     * 递归找到target目录
     */
    public static void recursionFindDirectory(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            System.out.println("log.info f.getName=" + f.getName() + " f.isDirectory=" + f.isDirectory());
            if ("target".equals(f.getName())) {
                recursionDeleteFile(f);
                f.delete();
                System.out.println("log.info f=" + f.getName() + " is delete");
            } else {
                recursionFindDirectory(f);
            }
        }
    }

    /**
     * 递归删除target目录下的文件
     */
    public static void recursionDeleteFile(File file) {
        if (file == null) {
            return;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("log.info f=" + f.getName() + " f.isDirectory=" + f.isDirectory());
                recursionDeleteFile(f);
            } else {
                f.delete();
                System.out.println("log.info f=" + f.getName() + " is delete");
            }
        }
        file.delete();
        System.out.println("log.info f=" + file.getName() + " is delete");
    }

}
