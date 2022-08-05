package cn.cunchang.tablesql;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

/**
 * 将table转换成DataSet
 * Created by xuwei
 */
public class TableToDataSetJava {
    public static void main(String[] args) throws Exception{
        //创建BatchTableEnvironment
        ExecutionEnvironment bbEnv = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment bbTableEnv = BatchTableEnvironment.create(bbEnv);

        //创建输入表
        bbTableEnv.executeSql("" +
                "create table myTable(\n" +
                "id int,\n" +
                "name string\n" +
                ") with (\n" +
                "'connector.type' = 'filesystem',\n" +
                "'connector.path' = 'D:\\data\\source',\n" +
                "'format.type' = 'csv'\n" +
                ")");

        //获取table
        Table table = bbTableEnv.from("myTable");

        //将table转换成DataSet
        DataSet<Row> set = bbTableEnv.toDataSet(table, Row.class);
        set.map(new MapFunction<Row, Tuple2<Integer,String>>() {
            @Override
            public Tuple2<Integer, String> map(Row row) throws Exception {
                int id = Integer.parseInt(row.getField(0).toString());
                String name = row.getField(1).toString();
                return new Tuple2<Integer, String>(id,name);
            }
        }).print();
    }
}
