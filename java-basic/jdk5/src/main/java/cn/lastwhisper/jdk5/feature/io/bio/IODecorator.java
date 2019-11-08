package cn.lastwhisper.jdk5.feature.io.bio;

import java.io.*;

/**
 * @author lastwhisper
 */
public class IODecorator {
    public static void main(String[] args) {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(""));
            BufferedReader bd = new BufferedReader(new FileReader(""));
            bd.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
