package cn.cunchang.tablesql;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * 创建TableEnvironment对象
 * Created by xuwei
 */
public class CreateTableEnvironmentJava {
    public static void main(String[] args) {
        /**
         * 注意：如果Table API 和 SQL不需要和DataStream或者DataSet互相转换
         * 则针对stream和batch都可以使用TableEnvironment
         */
        //指定底层引擎为Blink，以及数据处理模式-stream
        // 从1.11版本开始，Blink引擎成为Table API和SQL的默认执行引擎，在生产环境下
        EnvironmentSettings sSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        //创建TableEnvironment对象
        TableEnvironment sTableEnv = TableEnvironment.create(sSettings);

        //指定底层引擎为Blink，以及数据处理模式-batch
        EnvironmentSettings bSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inBatchMode().build();
        //创建TableEnvironment对象
        TableEnvironment bTableEnv = TableEnvironment.create(bSettings);

        /**
         * 注意：如果Table API和SQL需要和DataStream或者DataSet互相转换
         * 针对stream需要使用StreamTableEnvironment
         * 针对batch需要使用BatchTableEnvironment
         */
        //创建StreamTableEnvironment
        StreamExecutionEnvironment ssEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings ssSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment ssTableEnv = StreamTableEnvironment.create(ssEnv, ssSettings);

        //创建BatchTableEnvironment
        ExecutionEnvironment bbEnv = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment bbTableEnv = BatchTableEnvironment.create(bbEnv);
    }
}
