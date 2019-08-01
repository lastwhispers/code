package cn.lastwhisper.greedy;

import java.util.*;

/**
 * 贪心解决集合覆盖问题
 * @author lastwhisper
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        // 广播电台和对应覆盖范围
        Map<String, Set<String>> broadcasts = new HashMap<>();
        // 覆盖地区
        Set<String> area1 = new HashSet<>();
        area1.add("北京");
        area1.add("上海");
        area1.add("天津");
        Set<String> area2 = new HashSet<>();
        area2.add("广州");
        area2.add("北京");
        area2.add("深圳");
        Set<String> area3 = new HashSet<>();
        area3.add("成都");
        area3.add("上海");
        area3.add("杭州");
        Set<String> area4 = new HashSet<>();
        area4.add("上海");
        area4.add("天津");
        Set<String> area5 = new HashSet<>();
        area5.add("杭州");
        area5.add("大连");
        broadcasts.put("k1", area1);
        broadcasts.put("k2", area2);
        broadcasts.put("k3", area3);
        broadcasts.put("k4", area4);
        broadcasts.put("k5", area5);

        // 需要覆盖的范围
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放已选择电台的集合
        List<String> selects = new ArrayList<String>();
        // 存放“遍历到的key对应的覆盖的范围地区的集合”和“需要覆盖的范围地区的集合”的交集
        Set<String> tempSet = new HashSet<>();
        // 存放遍历过程中，能够覆盖最大未覆盖地区对应电台的key
        String maxKey = null;
        while (allAreas.size() != 0) {
            // maxKey置空
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                // 清除集合上一次的缓存
                tempSet.clear();
                // areas key对应的覆盖的范围地区的集合
                Set<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // allAreas 需要覆盖的范围地区的集合
                tempSet.retainAll(allAreas);
                // 贪心算法的核心
                // tempSet.size() > broadcasts.get(maxKey).size())当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                // 将把tempSet.size()对应的key，更新掉maxKey
                if (tempSet.size() > 0 && (maxKey == null ||
                        tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }

            }
            // maxKey存在
            if (maxKey != null) {
                // 将maxKey加入已选择电台集合中
                selects.add(maxKey);
                // 并从未覆盖地区删除maxKey对应覆盖的地区
                allAreas.removeAll(broadcasts.get(maxKey));
                // 从广播中移除
                broadcasts.remove(maxKey);
            }

        }

        System.out.println(selects);

    }

}
