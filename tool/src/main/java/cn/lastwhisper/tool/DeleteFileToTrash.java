package cn.lastwhisper.tool;

import com.sun.jna.platform.win32.W32FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 删除指定文件夹
 * @author lastwhisper
 * @date 2020/03/15
 */
public class DeleteFileToTrash {

    // https://blog.csdn.net/Felix_Dreammaker/article/details/79180471
    public static void main(String[] args) {

        //Desktop desktop = Desktop.getDesktop();
        //desktop.moveToTrash(new File("C:\\Users\\Administrator\\Desktop\\xxxx.rtf"));
        W32FileUtils fileUtils = (W32FileUtils) W32FileUtils.getInstance();
        boolean flag = fileUtils.hasTrash();

        try {
            fileUtils.moveToTrash(new File[]{new File("C:\\Users\\Administrator\\Desktop\\xxxxx.bmp")});

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
