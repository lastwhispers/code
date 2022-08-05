package cn.cunchang.batch.transformation;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

import java.util.ArrayList;

/**
 * outerJoin：外连接
 * 一共有三种情况
 * 1：leftOuterJoin
 * 2：rightOuterJoin
 * 3：fullOuterJoin
 * Created by xuwei
 */
public class BatchOuterJoinJava {
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

        System.out.println("==================左外连接====================");
        //对两份数据集执行leftOuterJoin操作
        text1.leftOuterJoin(text2)
                .where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {
                    @Override
                    public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second)
                            throws Exception {
                        if(second==null){
                            return new Tuple3<Integer, String, String>(first.f0,first.f1,"null");
                        }else{
                            return new Tuple3<Integer, String, String>(first.f0,first.f1,second.f1);
                        }
                    }
                }).print();

        System.out.println("========================================");

        System.out.println("==================右外连接====================");
        //对两份数据集执行rightOuterJoin操作
        text1.rightOuterJoin(text2)
                .where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {
                    @Override
                    public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second)
                            throws Exception {
                        if(first==null){
                            return new Tuple3<Integer, String, String>(second.f0,"null",second.f1);
                        }else{
                            return new Tuple3<Integer, String, String>(first.f0,first.f1,second.f1);
                        }
                    }
                }).print();

        System.out.println("========================================");

        System.out.println("==================全外连接====================");
        //对两份数据集执行fullOuterJoin操作
        text1.fullOuterJoin(text2)
                .where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {
                    @Override
                    public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second)
                            throws Exception {
                        if(first==null){
                            return new Tuple3<Integer, String, String>(second.f0,"null",second.f1);
                        }else if(second==null){
                            return new Tuple3<Integer, String, String>(first.f0,first.f1,"null");
                        }else{
                            return new Tuple3<Integer, String, String>(first.f0,first.f1,second.f1);
                        }
                    }
                }).print();
    }
}
