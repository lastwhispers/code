package cn.lastwhisper.learn8.util.collection;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * TreeMap
 * @author lastwhisper
 * @date 2020/4/13
 */
@Slf4j
public class TreeMapTest {

    @Data
    class DTO implements Comparable<DTO> {

        private Integer id;

        public DTO(Integer id) {
            this.id = id;
        }

        @Override
        public int compareTo(DTO o) {
            //默认从小到大排序
            return id - o.getId();
        }
    }


    @Test
    public void testIterator() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("asdf", "nihao");
        m.put("sdf", "nihao");
        m.put("df", "nihao");
        m.keySet().iterator();
    }

    @Test
    public void testTwoComparable() {
        // 第一种排序，从小到大排序，实现 Comparable 的 compareTo 方法进行排序
        List<DTO> list = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            list.add(new DTO(i));
        }
        Collections.sort(list);
        log.info(JSON.toJSONString(list));

        // 第二种排序，从大到小排序，利用外部排序器 Comparator 进行排序
        Comparator comparator = (Comparator<DTO>) (o1, o2) -> o2.getId() - o1.getId();
        List<DTO> list2 = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            list2.add(new DTO(i));
        }
        Collections.sort(list, comparator);
        log.info(JSON.toJSONString(list2));
    }


}
