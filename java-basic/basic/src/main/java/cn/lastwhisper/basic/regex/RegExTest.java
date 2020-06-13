package cn.lastwhisper.basic.regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java 正则表达式
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

    @Test
    public void testLetter() {
        // 按指定模式在字符串查找
        String[] lines = {"13812345abc", "13812345ABC", "13812345;", "13812345】"};

        String regEx = "[a-zA-Z]";
        // 创建 Pattern 对象,这个类的实例是不可变的，可以安全地被多个并发线程使用。
        Pattern pattern = Pattern.compile(regEx);
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            // find()方法是部分匹配
            if (m.find()) {
                System.out.println("Found value:" + m.group(0));
            } else {
                System.out.println("NO MATCH");
            }
        }
    }

    @Test
    public void testRegExSplit() {
        String input = "pop-upu*123214/asd44";
        String regEx = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println("Found value: " + matcher.group(0));
        } else {
            System.out.println("NO MATCH");
        }
        System.out.println(Arrays.toString(pattern.split(input)));
    }

    @Test
    public void testIllegal() {
        // 按指定模式在字符串查找
        String[] lines = {"13812345abc", "13812345哈哈", "aaa13812345;", "13812345】"};

        String regEx = "[^0-9]";
        // 创建 Pattern 对象,这个类的实例是不可变的，可以安全地被多个并发线程使用。
        Pattern pattern = Pattern.compile(regEx);
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            // find()方法是部分匹配
            if (m.find()) {
                System.out.println("Found value:" + m.group(0));
            } else {
                System.out.println("NO MATCH");
            }
        }
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
     * 只允许英文字母、数字、下划线、英文句号、以及中划线组成
     */
    @Test
    public void testEmail2() {
        // 按指定模式在字符串查找
        String[] lines = {"zhangsan-001@gmail.com "};
        String regEx = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
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

}
