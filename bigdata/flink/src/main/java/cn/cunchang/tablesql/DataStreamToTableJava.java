package cn.cunchang.tablesql;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.ArrayList;

import static org.apache.flink.table.api.Expressions.$;

/**
 * 将DataStream转换为表
 * Created by xuwei
 */
public class DataStreamToTableJava {
    public static void main(String[] args) {
        //获取StreamTableEnvironment
        StreamExecutionEnvironment ssEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings);

        //获取DataStream
        ArrayList<Tuple2<Integer, String>> data = new ArrayList<>();
        data.add(new Tuple2<Integer, String>(1,"jack"));
        data.add(new Tuple2<Integer, String>(2,"tom"));
        data.add(new Tuple2<Integer, String>(3,"mick"));
        DataStreamSource<Tuple2<Integer, String>> stream = ssEnv.fromCollection(data);

        //第一种：将DataStream转换为view视图
        ssTableEnv.createTemporaryView("myTable",stream,$("id"),$("name"));
        ssTableEnv.sqlQuery("select * from myTable where id > 1 ").execute().print();

        //第二种：将DataStream转换为table对象
        Table table = ssTableEnv.fromDataStream(stream, $("id"), $("name"));
        table.select($("id"),$("name"))
                .filter($("id").isGreater(1))
                .execute()
                .print();
    }
}
