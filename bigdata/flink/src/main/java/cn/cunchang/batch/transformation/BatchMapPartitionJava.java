package cn.cunchang.batch.transformation;

import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.UUID;

/**
 * MapPartition的使用：一次处理一个分区的数据
 * Created by xuwei
 */
public class BatchMapPartitionJava {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //生成数据源数据
        DataSource<String> text = env.fromCollection(Arrays.asList("hello you", "hello me"));

        //每次处理一个分区的数据
        text.mapPartition(new MapPartitionFunction<String, String>() {
            @Override
            public void mapPartition(Iterable<String> iterable, Collector<String> out)
                    throws Exception {
                String id = UUID.randomUUID().toString();
                // 可以在此处创建数据库连接，建议把这块代码放到try-catch代码块中
                // 注意：此时是每个分区获取一个数据库连接，不需要每处理一条数据就获取一次连接
                for (String line : iterable) {
                    System.out.println("id:" + id + "，line：" + line);
                    String[] words = line.split(" ");
                    for (String word : words) {
                        out.collect(word);
                    }
                }
                //关闭数据库连接
            }
        }).print();
    }
}
