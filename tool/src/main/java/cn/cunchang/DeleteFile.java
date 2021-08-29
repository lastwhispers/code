package cn.cunchang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

/**
 * 删除指定文件夹下的
 *
 * @author cunchang
 * @date 2019/11/22
 */
public class DeleteFile {

    /**
     * 可能的值：
     * /Users/cunchang/code/GitRepository
     */
    private static HashSet<String> targetDirs;

    /**
     * 可能的值：
     * .idea
     * bin
     */
    private static HashSet<String> deleteDirs;

    /**
     * 可能的值：
     * .war
     * .iml
     * .jar
     */
    private static HashSet<String> deleteFiles;

    private static long count;

    public static void main(String[] args) throws IOException {
        init(args);
        check();
        delete();
        after();
    }

    private static void init(String[] args) throws IOException {
        targetDirs = new HashSet<>();
        deleteDirs = new HashSet<>();
        deleteFiles = new HashSet<>();

        String configPath = "/config.properties";

        if (args != null && args.length > 0) {
            configPath = args[0];
        }
        InputStream inputStream = DeleteFile.class.getResourceAsStream(configPath);

        Properties prop = new Properties();
        prop.load(inputStream);

        String targetDirsStr = prop.getProperty("targetDirs");
        String deleteDirsStr = prop.getProperty("deleteDirs");
        String deleteFilesStr = prop.getProperty("deleteFiles");
        String[] targetDirArr = targetDirsStr.split(";");
        String[] deleteDirsArr = deleteDirsStr.split(";");
        String[] deleteFilesArr = deleteFilesStr.split(";");
        System.out.println("targetDirs:" + Arrays.toString(targetDirArr));
        System.out.println("deleteDirs:" + Arrays.toString(deleteDirsArr));
        System.out.println("deleteFiles:" + Arrays.toString(deleteFilesArr));
        targetDirs.addAll(Arrays.asList(targetDirArr));
        deleteDirs.addAll(Arrays.asList(deleteDirsArr));
        deleteFiles.addAll(Arrays.asList(deleteFilesArr));
    }

    private static void check() {
        if (targetDirs.isEmpty()) {
            throw new IllegalArgumentException("targetDirs not empty");
        }
        if (deleteDirs.isEmpty()) {
            throw new IllegalArgumentException("deleteDirs not empty");
        }
        if (deleteFiles.isEmpty()) {
            throw new IllegalArgumentException("deleteFiles not empty");
        }
    }

    private static void after() {
        System.out.println("delete count:" + count);
    }

    private static void delete() {
        for (String targetDir : targetDirs) {
            System.out.println("DeleteFile run,target==>" + targetDir);
            recursionDelDir(new File(targetDir));
        }
    }


    /**
     * 递归找到deleteDirs目录和deleteFiles文件
     * deleteDirs目录递归删除其下所有文件
     * deleteFiles文件直接删除
     */
    public static void recursionDelDir(File file) {
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                if (deleteDirs.contains(f.getName())) {
                    recursionDelFile(f);
                }
            } else {
                String fileName = f.getName();
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                if (deleteFiles.contains(fileName)) {
                    boolean deleteFlag = f.delete();
                    System.out.println("delete file==>" + f.getAbsolutePath() + " :" + deleteFlag);
                    count++;
                }
            }
            recursionDelDir(f);
        }
    }

    /**
     * 递归删除file目录下的文件
     */
    public static void recursionDelFile(File file) {
        if (file == null) {
            return;
        }
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            // 找到根,删除
            boolean deleteFlag = file.delete();
            System.out.println("delete dir==>" + file.getAbsolutePath() + " :" + deleteFlag);
            count++;
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                recursionDelFile(f);
            } else {
                boolean deleteFlag = file.delete();
                System.out.println("delete file==>" + f.getAbsolutePath() + " :" + deleteFlag);
                count++;
            }
        }
    }

}
