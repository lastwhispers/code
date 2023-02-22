package cn.cunchang.tablesql;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

/**
 * 将table转换成DataStream
 * Created by xuwei
 */
public class TableToDataStreamJava {
    public static void main(String[] args) throws Exception{
        //获取StreamTableEnvironment
        StreamExecutionEnvironment ssEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings);

        //创建输入表
        ssTableEnv.executeSql("" +
                "create table myTable(\n" +
                "id int,\n" +
                "name string\n" +
                ") with (\n" +
                "'connector.type' = 'filesystem',\n" +
                "'connector.path' = 'D:\\data\\source',\n" +
                "'format.type' = 'csv'\n" +
                ")");

        //获取table
        Table table = ssTableEnv.from("myTable");

        //将table转换为DataStream
        //如果只有新增(追加)操作，可以使用toAppendStream
        DataStream<Row> appStream = ssTableEnv.toAppendStream(table, Row.class);
        appStream.map(new MapFunction<Row, Tuple2<Integer,String>>() {
            @Override
            public Tuple2<Integer, String> map(Row row) throws Exception {
                int id = Integer.parseInt(row.getField(0).toString());
                String name = row.getField(1).toString();
                return new Tuple2<Integer, String>(id,name);
            }
        }).print();

        //如果有增加操作，还有删除操作，则使用toRetractStream
        DataStream<Tuple2<Boolean, Row>> retStream = ssTableEnv.toRetractStream(table, Row.class);
        retStream.map(new MapFunction<Tuple2<Boolean, Row>, Tuple3<Boolean,Integer,String>>() {
            @Override
            public Tuple3<Boolean, Integer, String> map(Tuple2<Boolean, Row> tup) throws Exception {
                Boolean flag = tup.f0;
                int id = Integer.parseInt(tup.f1.getField(0).toString());
                String name = tup.f1.getField(1).toString();
                return new Tuple3<Boolean, Integer, String>(flag,id,name);
            }
        }).print();

        //注意：将table转换为DataStream之后，就需要调用StreamExecutionEnvironment中的execute方法了
        ssEnv.execute("TableToDataStreamJava");
    }
}
