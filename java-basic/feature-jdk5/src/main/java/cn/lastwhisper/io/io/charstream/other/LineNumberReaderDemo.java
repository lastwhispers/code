package cn.lastwhisper.io.io.charstream.other;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/14
 */
public class LineNumberReaderDemo {

    /*
     * BufferedReader
     * 		|--LineNumberReader
     * 			public int getLineNumber()获得当前行号。
     * 			public void setLineNumber(int lineNumber)
     */
    public static void main(String[] args) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("d:\\a.txt"));

        // 行号从10开始
        lnr.setLineNumber(10);

        String line;
        while ((line = lnr.readLine()) != null) {
            System.out.println(lnr.getLineNumber() + " : " + line);
        }

        lnr.close();
    }

}
