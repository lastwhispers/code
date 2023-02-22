package cn.cunchang.batch.transformation;

import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.ArrayList;

/**
 * first-n：获取集合中的前N个元素
 * Created by xuwei
 */
public class BatchFirstNJava {
    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        ArrayList<Tuple2<Integer, String>> data = new ArrayList<>();
        data.add(new Tuple2<Integer, String>(2,"zs"));
        data.add(new Tuple2<Integer, String>(4,"ls"));
        data.add(new Tuple2<Integer, String>(3,"ww"));
        data.add(new Tuple2<Integer, String>(1,"aw"));
        data.add(new Tuple2<Integer, String>(1,"xw"));
        data.add(new Tuple2<Integer, String>(1,"mw"));

        //初始化数据
        DataSource<Tuple2<Integer, String>> text = env.fromCollection(data);

        //获取前3条数据，按照数据插入的顺序
        text.first(3).print();
        System.out.println("============================================");


        //根据数据中的第一列进行分组，获取每组的前2个元素
        text.groupBy(0).first(2).print();
        System.out.println("============================================");

        //根据数据中的第一列分组，再根据第二列进行组内排序[倒序],获取每组的前2个元素
        //分组排序取TopN
        text.groupBy(0).sortGroup(1, Order.DESCENDING).first(2).print();
        System.out.println("============================================");
    }
}
