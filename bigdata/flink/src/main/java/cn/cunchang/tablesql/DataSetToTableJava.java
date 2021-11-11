package cn.cunchang.tablesql;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;

import java.util.ArrayList;

import static org.apache.flink.table.api.Expressions.$;

/**
 * 将DataSet转换为表
 * Created by xuwei
 */
public class DataSetToTableJava {
    public static void main(String[] args) {
        //获取BatchTableEnvironment
        ExecutionEnvironment bbEnv = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment bbTableEnv = BatchTableEnvironment.create(bbEnv);

        //获取DataSet
        //获取DataStream
        ArrayList<Tuple2<Integer, String>> data = new ArrayList<>();
        data.add(new Tuple2<Integer, String>(1,"jack"));
        data.add(new Tuple2<Integer, String>(2,"tom"));
        data.add(new Tuple2<Integer, String>(3,"mick"));
        DataSource<Tuple2<Integer, String>> set = bbEnv.fromCollection(data);

        //第一种：将DataSet转换为view视图
        bbTableEnv.createTemporaryView("myTable",set,$("id"),$("name"));
        bbTableEnv.sqlQuery("select * from myTable where id > 1 ").execute().print();

        //第二种：将DataSet转换为table对象
        bbTableEnv.fromDataSet(set,$("id"),$("name"))
                .filter($("id").isGreater(1))
                .execute()
                .print();
    }
}
