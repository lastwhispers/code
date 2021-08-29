package cn.cunchang.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cunchang
 * @date 2021/6/23 2:37 下午
 */
public class ArraysTest {

    @Test
    public void test1Arrays的坑(){
        // 一个参数
        int[] arr = {1,2,3};
        List list = Arrays.asList(arr);
        System.out.println(list);
        System.out.println(list.size());
        // 三个参数
        list = Arrays.asList(1,2,3);
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void test2Arrays的坑(){
        String[] arr = {"欢迎","关注","Java"};
        List list = Arrays.asList(arr);
        System.out.println("arr="+Arrays.toString(arr));
        arr[1] = "爱上";
        list.set(2,"我");
        System.out.println("修改list,arr也会被修改="+Arrays.toString(arr));
        System.out.println("修改arr,list也会被修改="+list);
    }

    @Test
    public void test3Arrays的坑(){
        String[] arr = {"欢迎","关注","Java"};
        List list = Arrays.asList(arr);
        System.out.println("arr="+Arrays.toString(arr));
        list.add("新增");
        list.remove("关注");
    }

}
