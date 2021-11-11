package cn.cunchang.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * map和flatmap的区别
 * <p>
 * https://www.cnblogs.com/wangjing666/p/9999666.html
 *
 * @author cunchang
 * @date 2021/10/5 4:43 下午
 */
public class StreamMapFlatMapDemo {

    // 对给定单词列表 ["Hello","World"],你想返回字母去重列表["H","e","l","o","W","r","d"]

    /**
     * 错误方式
     */
    @Test
    public void test1() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
    }

    /**
     * 正确
     */
    @Test
    public void test2() {
        String[] words = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                // Arrays.stream(words)得到 [Hello, World]
                .map(word -> word.split(""))
                // map 输入一个元素，返回一个元素
                // "Hello" -> "Hello".split("") => ["H","e","l","l","o"]
                // "World" -> "World".split("") => ["W","o","r","l","d"]
                // map处理后得到 Stream<String[]> 内容 [H, e, l, l, o],[W, o, r, l, d]
                .flatMap(Arrays::stream)
                // flatMap 输入一个元素，返回多个元素
                // flatMap将 Stream<String[]> 平铺  Stream<String>
                // [H, e, l, l, o],[W, o, r, l, d] =>[H, e, l, l, o, W, o, r, l, d]
                .distinct()
                .collect(toList());
        System.out.println(a); //[H, e, l, o, W, r, d]
    }

    @Test
    public void test3() {
        String[] words = new String[]{"Hello","World"};
        Stream<String> stringStream = Arrays.stream(words);
        // [Hello, World]
//        System.out.println(stringStream.collect(toList()));

        Stream<String[]> stringArrStream = stringStream.map(word -> word.split(""));
        // [[Ljava.lang.String;@136432db, [Ljava.lang.String;@7382f612]
//        System.out.println(stringArrStream.collect(toList()));
        // [H, e, l, l, o],[W, o, r, l, d]
//        List<String[]> strArrList = stringArrStream.collect(toList());
//        for (String[] strArr : strArrList) {
//            System.out.println(Arrays.toString(strArr));
//        }

        // [H, e, l, l, o, W, o, r, l, d]
        stringArrStream.flatMap(Arrays::stream).forEach(System.out::println);
    }

}
