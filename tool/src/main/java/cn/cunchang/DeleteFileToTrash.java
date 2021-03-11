package cn.cunchang;

import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.win32.W32FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 删除指定文件夹
 * @author cunchang
 * @date 2020/03/15
 */
public class DeleteFileToTrash {

    // https://blog.csdn.net/Felix_Dreammaker/article/details/79180471
    public static void main(String[] args) {

        FileUtils fileUtils = W32FileUtils.getInstance();
        boolean flag = fileUtils.hasTrash();

        try {
            fileUtils.moveToTrash(new File[]{new File("C:\\Users\\Administrator\\Desktop\\xxxxx.bmp")});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
