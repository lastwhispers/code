package cn.lastwhisper.feature5.proxy.myproxy;

import java.io.*;

/**
 * @author cunchang
 * @date 2021/2/3 4:05 下午
 */
public class FileUtil {

    public static byte[] copyToByteArray(File file) {
        try (InputStream is = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(is);) {
            byte[] classBytes = new byte[bis.available()];
            bis.read(classBytes);
            return classBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyToFile(byte[] bytes, File file) {
        try (OutputStream os = new FileOutputStream(file);) {
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
