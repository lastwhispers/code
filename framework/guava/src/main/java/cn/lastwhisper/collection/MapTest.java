package cn.lastwhisper.collection;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * 参考：https://mp.weixin.qq.com/s/LOZ-p6divRjO9ZTcus101A
 *
 * @author cunchang
 * @date 2022/3/17 1:54 PM
 */
public class MapTest {

    /**
     * <p>
     * 案例：记录员工每个月工作的天数
     * 使用hashmap的话，得两个嵌套
     */
    @Test
    public void test双键Map() {
        // java中的Map只允许有一个key和一个value存在。
        // guava中的Table允许一个value存在两个key。Table中的两个key分别被称为rowKey和columnKey，也就是行和列。
        Table<String, String, Integer> table = HashBasedTable.create();
        //存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        //取出元素
        Integer dayCount = table.get("Hydra", "Feb");
        System.out.println("取出元素：" + dayCount);

//        1、获得key或value的集合
        //rowKey或columnKey的集合
        System.out.println("---rowKey或columnKey的集合");
        Set<String> rowKeys = table.rowKeySet();
        Set<String> columnKeys = table.columnKeySet();
        //value集合
        Collection<Integer> values = table.values();
        System.out.println("rowKeys：" + rowKeys);
        System.out.println("columnKeys：" + columnKeys);
        System.out.println("values：" + values);

//        2、计算key对应的所有value的和
        // 以统计所有rowKey对应的value之和为例：
        System.out.println("---统计所有rowKey对应的value之和");
        for (String key : table.rowKeySet()) {
            Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> row : rows) {
                total += row.getValue();
            }
            System.out.println(key + ": " + total);
        }
//        3、转换rowKey和columnKey
        // 行和列的转置，直接调用Tables的静态方法transpose
        System.out.println("---行和列的转置");
        Table<String, String, Integer> table2 = Tables.transpose(table);
        Set<Table.Cell<String, String, Integer>> cells = table2.cellSet();
        cells.forEach(cell ->
                System.out.println(cell.getRowKey() + "," + cell.getColumnKey() + ":" + cell.getValue())
        );

//        4、转为嵌套的Map
        // 将数据还原成嵌套Map的那种形式，使用Table的rowMap或columnMap方法就可以实现了
        System.out.println("---转为嵌套的Map");
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        System.out.println("rowMap: " + rowMap);
        System.out.println("columnMap: " + columnMap);
    }

    /**
     * 在普通Map中，如果要想根据value查找对应的key，没什么简便的办法，无论是使用for循环还是迭代器，都需要遍历整个Map。
     */
    @Test
    public void test双向Map() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra", "Programmer");
        biMap.put("Tony", "IronMan");
        biMap.put("Thanos", "Titan");
        //使用key获取value
        System.out.println("使用key获取value：" + biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
        //使用value获取key
        System.out.println("使用value获取key：" + inverse.get("Titan"));
    }

    /**
     * 上面我们用inverse方法反转了原来BiMap的键值映射，但是这个反转后的BiMap并不是一个新的对象，
     * 它实现了一种视图的关联，所以对反转后的BiMap执行的所有操作会作用于原先的BiMap上。
     */
    @Test
    public void test双向Map_坑_反转后操作的影响() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra", "Programmer");
        biMap.put("Tony", "IronMan");
        biMap.put("Thanos", "Titan");
        BiMap<String, String> inverse = biMap.inverse();

        // 原先值为IronMan时对应的键是Tony，虽然没有直接修改，但是现在键变成了Stark
        inverse.put("IronMan", "Stark");
        System.out.println(biMap);
    }

    @Test
    public void test双向Map_坑_value不可重复() {
        // 双向的BiMap，key和value都不允许重复
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Tony", "IronMan");
        // java.lang.IllegalArgumentException: value already present: IronMan
        biMap.put("Stark", "IronMan");
    }

    @Test
    public void test双向Map_坑_value不可重复2() {
        // 把新的key映射到已有的value上，使用forcePut方法强制替换掉原有的key
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Tony", "IronMan");
        biMap.forcePut("Stark", "IronMan");
        // 由于BiMap的value是不允许重复的，因此它的values方法返回的是没有重复的Set，而不是普通Collection：
        Set<String> values = biMap.values();
    }

    /**
     * 将一个键映射到多个值
     */
    @Test
    public void test多值Map() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);
        // {month=[3], day=[1, 2, 8]}
        System.out.println(multimap);

        // 1、获取值的集合
        System.out.println("---获取值的集合");
        Collection<Integer> day = multimap.get("day");
        // 如果在创建时指定为ArrayListMultimap类型，那么get方法将返回一个List：
        // 同理，你还可以创建HashMultimap、TreeMultimap等类型的Multimap。
        ArrayListMultimap<String, Integer> multimap2 = ArrayListMultimap.create();
        List<Integer> day2 = multimap2.get("day");
        // Multimap的get方法会返回一个非null的集合，但是这个集合的内容可能是空，看一下下面的例子：
        List<Integer> day22 = multimap2.get("day");
        List<Integer> year2 = multimap2.get("year");
        System.out.println(day22);
        System.out.println(year2);

    }

    /**
     * 和BiMap的使用类似，使用get方法返回的集合也不是一个独立的对象，
     * 可以理解为集合视图的关联，对这个新集合的操作仍然会作用于原始的Multimap上
     */
    @Test
    public void test多值Map_操作get后的集合() {
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);

        List<Integer> day = multimap.get("day");
        List<Integer> month = multimap.get("month");

        System.out.println("before：" + multimap);
        day.remove(0);//这个0是下标
        month.add(12);
        System.out.println("after：" + multimap);
    }

    /**
     * 使用asMap方法，可以将Multimap转换为Map<K,Collection>的形式，
     * 同样这个Map也可以看做一个关联的视图，在这个Map上的操作会作用于原始的Multimap
     */
    @Test
    public void test多值Map_转换为Map() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);

        Map<String, Collection<Integer>> map = multimap.asMap();
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
        map.get("day").add(20);
        System.out.println(multimap);
    }

    @Test
    public void test多值Map_坑_数量问题() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);

        for (Map.Entry<String, Integer> entry : multimap.entries()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }

        // size()方法返回的是所有key到单个value的映射，因此结果为4，entries()方法同理
        System.out.println(multimap.size());
        System.out.println(multimap.entries().size());
        // 但是它的keySet中保存的是不同的key的个数，例如下面这行代码打印的结果就会是2
        System.out.println(multimap.keySet().size());
    }

    public static String getRank(int score) {
        if (0 <= score && score < 60)
            return "fail";
        else if (60 <= score && score <= 90)
            return "satisfactory";
        else if (90 < score && score <= 100)
            return "excellent";
        return null;
    }

    /**
     * 要根据分数对考试成绩进行分类
     * guava中的RangeMap描述了一种从区间到特定值的映射关系，让我们能够以更为优雅的方法来书写代码
     */
    @Test
    public void test范围Map() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0, 60), "fail");
        rangeMap.put(Range.closed(60, 90), "satisfactory");
        rangeMap.put(Range.openClosed(90, 100), "excellent");

        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));

        rangeMap.remove(Range.closed(70, 80));
        System.out.println(rangeMap.get(75));
    }

    /**
     * ClassToInstanceMap相对普通Map
     * 1、取出对象时省去了复杂的强制类型转换
     * 2、通过泛型保证value要符合key所对应的类型
     */
    @Test
    public void test实例Map() {
        ClassToInstanceMap<Map> instanceMap = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();

        instanceMap.putInstance(HashMap.class, hashMap);
        instanceMap.putInstance(TreeMap.class, treeMap);
        // HashMap和TreeMap都集成了Map父类，但是如果想放入其他类型，就会编译报错
//        instanceMap.putInstance(ArrayList.class,list);
    }

    @Test
    public void test实例Map对比普通Map() {
        Map<Class, Object> map = new HashMap<>();

        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();

        map.put(HashMap.class, hashMap);
        map.put(TreeMap.class, treeMap);
        // 1、类型转换
        treeMap = (TreeMap<String, Object>) map.get(TreeMap.class);
        // 2、无法限制只存某些Class
        map.put(ArrayList.class, list);
    }

}
