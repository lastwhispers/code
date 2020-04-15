package cn.lastwhisper.learn8.lang;

import cn.lastwhisper.learn8.common.ReflectUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 学习String
 * @author lastwhisper
 * @date 2020/3/30
 */
@Slf4j
public class StringTest {

    @Test
    public void testValue(){
        String str = "hello world";
        // 拿到 string 里面的数组
        char[] value = (char[]) ReflectUtils.getValue(str,"value");
        log.info("修改之前{}", value);
        ReflectUtils.setValue(str,"value","hello java".toCharArray());
        log.info("修改之后{}", str);
    }

    /**
     * String 不变性
     */
    @Test
    public void testFinal() {
        String s = "hello";
        s = "world";

        String str = "hello world !!";
        // 这种写法是替换不掉的，必须接受 replace 方法返回的参数才行，这样才行：str = str.replace("l","dd");
        str.replace("l", "dd");
    }

    /**
     * String decode
     */
    @Test
    public void testGibberish() throws UnsupportedEncodingException {
        String str = "nihao 你好 喬亂";
        byte[] bytes = str.getBytes("ISO-8859-1");
        String s2 = new String(bytes, "ISO-8859-1");
        log.info(s2);
    }

    /**
     * String 相等判断
     */
    @Test
    public void testEquals() {
        String s1 = "A";
        String s2 = new String("A");
        String s3 = new String("a");
        Assert.assertTrue(s1.equals(s2));
        Assert.assertTrue(s1.equalsIgnoreCase(s3));
    }

    /**
     * String 替换、删除
     * replace 并不只是替换一个，是替换所有匹配到的字符或字符串
     * replaceAll 替换所有，并支持正则
     */
    @Test
    public void testReplace() {
        String str = "hello word !!";
        log.info("替换之前 :{}", str);
        str = str.replace('l', 'd');
        log.info("替换所有字符 :{}", str);
        str = str.replaceAll("d", "l");
        log.info("替换全部 :{}", str);
        str = str.replaceFirst("l", "");
        log.info("替换第一个 l :{}", str);
    }

    /**
     * String 拆分和合并
     */
    @Test
    public void testSplit() {
        String s = "boo:and:foo";
        log.info("s.split(\":\") 结果:{}", JSON.toJSONString(s.split(":")));
        log.info("s.split(\":\",2) 结果:{}", JSON.toJSONString(s.split(":", 2)));
        log.info("s.split(\":\",5) 结果:{}", JSON.toJSONString(s.split(":", 5)));
        log.info("s.split(\":\",-2) 结果:{}", JSON.toJSONString(s.split(":", -2)));
        log.info("s.split(\"o\") 结果:{}", JSON.toJSONString(s.split("o")));
        log.info("s.split(\"o\",2) 结果:{}", JSON.toJSONString(s.split("o", 2)));

        String a1 = ",a, ,  b  c ,";
        log.info("a.split(\",\") 结果:{}", JSON.toJSONString(a1.split(",")));

        List<String> list = Splitter.on(',')
                .trimResults()// 去掉空格
                .omitEmptyStrings()// 去掉空值
                .splitToList(a1);
        log.info("Guava 去掉空格的分割方法：{}", JSON.toJSONString(list));
    }

    /**
     * String 拆分和合并
     */
    @Test
    public void testJoin() {
        String s = "hello";
        String s1 = "china";
        log.info("String join api 一个字符串:{}", String.join(",", s1));
        log.info("String join api 不能join多个字符串:{}", String.join(",", s1).join(",", "null"));

        // 依次  join 多个字符串
        Joiner joiner = Joiner.on(",").skipNulls();
        String result = joiner.join("hello", null, "china");
        log.info("Guava join api 多个字符串:{}", result);

        List<String> list = Lists.newArrayList("hello", "china", null);
        log.info("Guava join api 自动删除 list 中空值:{}", joiner.join(list));

        log.info("String join api 不删除 list 中空值:{}", String.join(",", list));
    }

}
