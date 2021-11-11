package cn.cunchang.tablesql;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * TableAPI 和 SQL的使用
 * Created by xuwei
 */
public class TableAPIAndSQLOpJava {
    public static void main(String[] args) {
        //获取TableEnvironment对象
        EnvironmentSettings sSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        TableEnvironment sTableEnv = TableEnvironment.create(sSettings);

        //创建输入表
        /**
         * connector.type：指定connector的类型
         * connector.path：指定文件或者目录地址
         * format.type：文件数据格式化类型，现在只支持csv格式
         * 注意：SQL语句如果出现了换行，行的末尾可以添加空格或者\n都可以，最后一行不用添
         *
         * 注意：/Users/cunchang/tmp/data/source下面必须要有csv文件，内容：1,jack
         */
        sTableEnv.executeSql("" +
                "create table myTable(\n" +
                "id int,\n" +
                "name string\n" +
                ") with (\n" +
                "'connector.type' = 'filesystem',\n" +
                "'connector.path' = '/Users/cunchang/tmp/data/source',\n" +
                "'format.type' = 'csv'\n" +
                ")");

        //使用TableAPI实现数据查询和过滤等操作
        /*Table result = sTableEnv.from("myTable")
                .select($("id"), $("name"))
                .filter($("id").isGreater(1));*/

        //使用SQL实现数据查询和过滤等操作
        Table result = sTableEnv.sqlQuery("select id,name from myTable where id > 1");

        //输出结果到控制台
        result.execute().print();

        //创建输出表
        sTableEnv.executeSql("" +
                "create table newTable(\n" +
                "id int,\n" +
                "name string\n" +
                ") with (\n" +
                "'connector.type' = 'filesystem',\n" +
                "'connector.path' = '/Users/cunchang/tmp/data/res',\n" +
                "'format.type' = 'csv'\n" +
                ")");

        //输出结果到表newTable中
        result.executeInsert("newTable");

    }
}
