package cn.cunchang.str;

/**
 * @author cunchang
 * @date 2022/5/9 3:23 PM
 */
public class StringTest {

    public static void main(String[] args) {
        String fileName = "819882007410001_20200309-20200309_0010387011560_KV691300131099C";
        String REFNBR = fileName.substring(fileName.lastIndexOf("_")+1);
        System.out.println(REFNBR);
    }

}
