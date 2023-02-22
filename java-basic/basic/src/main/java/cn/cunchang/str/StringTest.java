package cn.cunchang.str;

import org.junit.Test;

/**
 * @author cunchang
 * @date 2022/5/9 3:23 PM
 */
public class StringTest {

    public static void main(String[] args) {
//        String fileName = "819882007410001_20200309-20200309_0010387011560_KV691300131099C.pdf";
//        String REFNBR = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("."));
//        System.out.println(REFNBR);
        String srts = "1,";
        String[] arrs = srts.split(",");
        for (String arr : arrs) {
            System.out.println(arr);
        }
    }

    @Test
    public void formatTest() {
        System.out.println(String.format("百分百%s%",111));
    }
}
