package cn.lastwhisper.learn8.util.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Arrays
 * @author lastwhisper
 * @date 2020/4/12
 */
@Slf4j
public class ArraysTest {

    @Data
    static class SortDTO {
        private String sortTarget;

        public SortDTO(String sortTarget) {
            this.sortTarget = sortTarget;
        }
    }

    /**
     * Arrays 排序
     */
    @Test
    public void testSort() {
        List<SortDTO> list = ImmutableList.of(
                new SortDTO("300"),
                new SortDTO("50"),
                new SortDTO("200"),
                new SortDTO("220")
        );
        // 我们先把数组的大小初始化成 list 的大小，保证能够正确执行 toArray
        SortDTO[] array = new SortDTO[list.size()];
        list.toArray(array);

        log.info("排序之前：{}", JSON.toJSONString(array));
        Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
        log.info("排序之后：{}", JSON.toJSONString(array));
    }

    /**
     * Arrays 二分查找
     */
    @Test
    public void testBinarySearch() {
        List<SortDTO> list = ImmutableList.of(
                new SortDTO("300"),
                new SortDTO("50"),
                new SortDTO("200"),
                new SortDTO("220")
        );

        SortDTO[] array = new SortDTO[list.size()];
        list.toArray(array);

        log.info("搜索之前：{}", JSON.toJSONString(array));
        Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
        log.info("先排序，结果为：{}", JSON.toJSONString(array));
        int index = Arrays.binarySearch(array, new SortDTO("200"),
                Comparator.comparing(SortDTO::getSortTarget));
        if (index < 0) {
            throw new RuntimeException("没有找到 200");
        }
        log.info("搜索结果：{}", JSON.toJSONString(array[index]));
    }

    /**
     * Arrays copy
     */
    @Test
    public void testCopyOf() {
        char[] original = new char[]{'1', '2', '3', '4', '5', '6', '7'};
        char[] chars = Arrays.copyOfRange(original, 2, 4);
        log.info("copyOfRange：{}", Arrays.toString(chars));
    }


}
