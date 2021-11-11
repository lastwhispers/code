package cn.cunchang.batch.transformation;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

import java.util.ArrayList;

/**
 * join：内连接
 * Created by xuwei
 */
public class BatchJoinJava {
    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //初始化第一份数据 Tuple2<用户id，用户姓名>
        ArrayList<Tuple2<Integer, String>> data1 = new ArrayList<>();
        data1.add(new Tuple2<Integer, String>(1,"jack"));
        data1.add(new Tuple2<Integer, String>(2,"tom"));
        data1.add(new Tuple2<Integer, String>(3,"mick"));
        DataSource<Tuple2<Integer, String>> text1 = env.fromCollection(data1);

        //初始化第二份数据 Tuple2<用户id，用户所在城市>
        ArrayList<Tuple2<Integer, String>> data2 = new ArrayList<>();
        data2.add(new Tuple2<Integer, String>(1,"bj"));
        data2.add(new Tuple2<Integer, String>(2,"sh"));
        data2.add(new Tuple2<Integer, String>(4,"gz"));
        DataSource<Tuple2<Integer, String>> text2 = env.fromCollection(data2);

        //对两份数据集执行join操作
        text1.join(text2)
                //注意：这里的where和equalTo实现了类似于on fieldA=fieldB的效果
                //where：指定左边数据集中参与比较的元素角标
                .where(0)
                .equalTo(0)
                //三个输入参数：
                //第一个tuple2是左边数据集的类型，
                //第二个tuple2是右边数据集的类型，
                //第三个tuple3是此函数返回的数据集类型
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {
                    @Override
                    public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second)
                            throws Exception {
                        return new Tuple3<Integer, String, String>(first.f0,first.f1,second.f1);
                    }
                }).print();

    }
}
