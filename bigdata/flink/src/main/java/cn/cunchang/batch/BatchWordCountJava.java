package cn.cunchang.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 需求：统计指定文件中单词出现的总次数
 * Created by xuwei
 */
public class BatchWordCountJava {
    public static void main(String[] args) throws Exception{
        //获取执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String inputPath = "hdfs://localhost:9000/hello.txt";
        String outPath = "hdfs://localhost:9000/out2";

        //读取文件中的数据
        DataSource<String> text = env.readTextFile(inputPath);

        //处理数据
        AggregateOperator<Tuple2<String, Integer>> wordCount = text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            public void flatMap(String line, Collector<Tuple2<String, Integer>> out)
                    throws Exception {
                String[] words = line.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word, 1));
                }
            }
        }).groupBy(0)
                .sum(1)
                .setParallelism(1);

        //将结果数据保存到文件中
        wordCount.writeAsCsv(outPath,"\n"," ");

        //执行程序
        env.execute("BatchWordCountJava");
    }
}
