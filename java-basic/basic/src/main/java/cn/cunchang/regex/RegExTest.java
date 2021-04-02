package cn.cunchang.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java 正则表达式
 *
 * @author lastwhisper
 * @date 2020/5/29
 */
public class RegExTest {

    @Test
    public void testPhone() {
        // 按指定模式在字符串查找
        String[] lines = {"13812345678"};
        // 138、166、198、199
        String regEx = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        match(regEx, lines);
    }

    @Test
    public void testLetter() {
        String[] lines = {"13812345abc", "13812345ABC", "13812345;", "13812345】"};
        String regEx = "[a-zA-Z]";
        match(regEx, lines);
    }

    @Test
    public void testRegExSplit() {
        String[] lines = {"pop-upu*123214/asd44"};
        String regEx = "[^a-zA-Z0-9]";
        match(regEx, lines);
    }

    @Test
    public void testIllegal() {
        // 按指定模式在字符串查找
        String[] lines = {"13812345abc", "13812345哈哈", "aaa13812345;", "13812345】"};
        String regEx = "[^0-9]";
        match(regEx, lines);
    }

    /**
     * 名称允许汉字、字母、数字，域名只允许英文域名
     */
    @Test
    public void testEmail1() {
        // 按指定模式在字符串查找
        String[] lines = {"杨元庆001Abc@lenovo.com.cn"};
        // 138、166、198、199
        String regEx = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        match(regEx, lines);
    }

    /**
     * 只允许英文字母、数字、下划线、英文句号、以及中划线组成
     */
    @Test
    public void testEmail2() {
        // 按指定模式在字符串查找
        String[] lines = {"zhangsan-001@gmail.com"};
        String regEx = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        match(regEx, lines);
    }

    @Test
    public void test1() {
        String[] lines = {"${asdasdasd}"};
        String regEx = "\\$\\{(.*?)\\}";
        match(regEx, lines);
    }

    private void match(String regEx, String[] lines) {
        // 创建 Pattern 对象,这个类的实例是不可变的，可以安全地被多个并发线程使用。
        Pattern pattern = Pattern.compile(regEx);
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            // matches()是全部匹配
            if (m.matches()) {
                System.out.println("Found value:" + m.group(0));
            } else {
                System.out.println("NO MATCH");
            }
        }
    }


    /**
     * 学习正则api
     */
    @Test
    public void test正则api() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aaa2223bb");
        System.out.println(m.find());//匹配2223,返回true
        System.out.println(m.start());//返回3,返回的是2223起始的索引号
        System.out.println(m.end());//返回7,返回的是2223结束的索引号
        System.out.println(m.group());//返回2223
        Matcher m2 = p.matcher("2223bb");
        System.out.println(m2.lookingAt()); //匹配2223,返回true
        System.out.println(m2.start()); //返回0,由于lookingAt()只能匹配前面的字符串,所以当使用lookingAt()匹配时,start()方法总是返回0
        System.out.println(m2.end()); //返回4
        System.out.println(m2.group()); //返回2223
        Matcher m3 = p.matcher("2223"); //如果Matcher m3=p.matcher("2223bb"); 那么下面的方法出错，因为不匹配返回false
        System.out.println(m3.matches()); //匹配整个字符串,返回true
        System.out.println(m3.start()); //返回0
        System.out.println(m3.end()); //返回3,原因相信大家也清楚了,因为matches()需要匹配所有字符串
        System.out.println(m3.group()); //返回2223
    }

    @Test
    public void test匹配12位数字(){
        Pattern p = Pattern.compile("\\d{12}");
        Matcher m = p.matcher("请问奥所，啊啊啊123456789101，阿萨德");
        System.out.println(m.find());//匹配2223,返回true
        System.out.println(m.start());//返回3,返回的是2223起始的索引号
        System.out.println(m.end());//返回7,返回的是2223结束的索引号
        System.out.println(m.group());//返回2223
    }

}
