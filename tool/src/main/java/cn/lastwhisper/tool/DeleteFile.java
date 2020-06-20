package cn.lastwhisper.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

/**
 * 删除指定文件夹下的
 *
 * @author lastwhisper
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
     * *.war
     * *.iml
     * *.jar
     */
    private static HashSet<String> deleteFiles;

    private static long count;

    public static void main(String[] args) throws IOException {
        init(args);
        check();
        //delete();
        after();
    }

    private static void init(String[] args) throws IOException {
        targetDirs = new HashSet<>();
        deleteDirs = new HashSet<>();
        deleteFiles = new HashSet<>();

        //InputStream inputStream = DeleteFile.class.getResourceAsStream("config.properties");
        InputStream inputStream = DeleteFile.class.getResourceAsStream("config.properties");

        Properties prop = new Properties();
        prop.load(inputStream);

        String targetDirsStr = prop.getProperty("targetDirs");
        String deleteDirsStr = prop.getProperty("deleteDirs");
        String deleteFilesStr = prop.getProperty("deleteFiles");
        String[] targetDirArr = targetDirsStr.split(",");
        String[] deleteDirsArr = deleteDirsStr.split(",");
        String[] deleteFilesArr = deleteFilesStr.split(",");

        targetDirs.addAll(Arrays.asList(targetDirArr));
        deleteDirs.addAll(Arrays.asList(deleteDirsArr));
        deleteFiles.addAll(Arrays.asList(deleteFilesArr));

    }

    private static void check() {
        if (targetDirs.isEmpty()) {
            throw new IllegalArgumentException("目标目录不能为空");
        }
        if (deleteDirs.isEmpty()) {
            throw new IllegalArgumentException("删除目录不能为空");
        }
        if (deleteFiles.isEmpty()) {
            throw new IllegalArgumentException("删除文件不能为空");
        }
    }

    private static void after() {
        System.out.println("delete count:" + count);
    }

    private static void delete() {
        targetDirs.forEach(dir ->
        {
            System.out.println("DeleteFile run,target==>" + dir);
            recursionDelDir(new File(dir));
        });
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
                for (String deleteDir : deleteDirs) {
                    if (f.getName().matches(deleteDir)) {
                        recursionDelFile(f);
                        count++;
                        break;
                    }
                }
            } else {
                for (String deleteFile : deleteFiles) {
                    if (f.getName().matches(deleteFile)) {
                        count++;
                        System.out.println("delete file==>" + f.getAbsolutePath() + " :" + f.delete());
                        break;
                    }
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
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                recursionDelFile(f);
            } else {
                count++;
                System.out.println("delete file==>" + f.getAbsolutePath() + " :" + f.delete());
            }
        }
        count++;
        System.out.println("delete dir==>" + file.getAbsolutePath() + " :" + file.delete());
    }

}
