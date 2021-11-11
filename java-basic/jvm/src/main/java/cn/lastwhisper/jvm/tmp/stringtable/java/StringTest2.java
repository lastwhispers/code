package cn.lastwhisper.jvm.tmp.stringtable.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  -XX:StringTableSize=1009
 * @author shkstart  shkstart@126.com
 * @create 2020  23:53
 */
public class StringTest2 {
    public static void main(String[] args) {
        // 一、使用 jinfo -flag 测试StringTableSize参数
//        System.out.println("我来打个酱油");
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * 二、StringTableSize参数对字符串常量池的影响
         *
         * 1.首先通过GenerateString生成 words.txt
         * 2.测试"-XX:StringTableSize="参数对字符串常量池的影响
         * 3.测试结果：1009:388ms  100009:99ms
         *  因为字符串常量池中是不会存储相同内容的字符串的。字符串常量池是一个固定大小的hashtable，大小为"-XX:StringTableSize="
         *  当hashtable比较小，加入常量池的字符串比较多的时候，就会造成hash冲突，导致链表过长，性能下降
         *
         */
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("words.txt"));
            long start = System.currentTimeMillis();
            String data;
            while((data = br.readLine()) != null){
                data.intern(); //如果字符串常量池中没有对应data的字符串的话，则在常量池中生成
            }

            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));//1009:388ms  100009:99ms
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
