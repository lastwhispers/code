package cn.cunchang.v1;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class WeightRandom<K, V extends Number> {

    /**
     * 以权重为key
     */
    private TreeMap<Double, K> weightMap = new TreeMap<>();

    public WeightRandom(List<Pair<K, V>> list) {
        Preconditions.checkNotNull(list, "list can NOT be null!");
        for (Pair<K, V> pair : list) {
            // 权重值需大于0
            Preconditions.checkArgument(pair.getValue().doubleValue() > 0, String.format("非法权重值：pair=%s", pair));

            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();

            //权重累加值作为map的key
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());
        }
    }

    public K random() {
        // 在权重累加值范围内生成随机值
        double randomWeight = this.weightMap.lastKey() * Math.random();
        // 取key权重值大于随机值的节点
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, false);
        // 再取第一个节点
        return this.weightMap.get(tailMap.firstKey());
    }

    public static void main(String[] args) {

        List<Pair<String, Integer>> list;

        WeightRandom<String, Integer> random;

        list = Lists.newArrayList();
        list.add(new Pair<>("Gray", 1));
        list.add(new Pair<>("Normal", 9));

        random = new WeightRandom<>(list);

        Map<String, Integer> countMap = Maps.newHashMap();
        for (int i = 0; i < 10000; i++) {
            String randomKey = random.random();
            countMap.put(randomKey, countMap.getOrDefault(randomKey, 0) + 1);
        }

        for (Pair<String, Integer> pair : list) {
            log.debug("{}:{}", pair.getKey(), countMap.get(pair.getKey()));
        }
    }


//    public static void main(String[] args) {
//        List<Pair<String, Double>> pairs = new ArrayList<>();
//        pairs.add(new Pair<>("a", 1D));
//        pairs.add(new Pair<>("b", 1D));
//        pairs.add(new Pair<>("c", 1D));
//        pairs.add(new Pair<>("d", 1D));
//        pairs.add(new Pair<>("e", 1D));
//        pairs.add(new Pair<>("f", 6D));
//        WeightRandom<String, Double> weightRandom = new WeightRandom<>(pairs);
//        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
//        for (int i = 0; i < 110000; i++) {
//            String key = weightRandom.random();
//            if ("a".equals(key)) {
//                a++;
//            }
//            if ("b".equals(key)) {
//                b++;
//            }
//            if ("c".equals(key)) {
//                c++;
//            }
//            if ("d".equals(key)) {
//                d++;
//            }
//            if ("e".equals(key)) {
//                e++;
//            }
//            if ("f".equals(key)) {
//                f++;
//            }
//        }
//        System.out.println(a);// 9994
//        System.out.println(b);// 10035
//        System.out.println(c);// 10119
//        System.out.println(d);// 9950
//        System.out.println(e);// 10088
//        System.out.println(f);// 59814
//    }
}