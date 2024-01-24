package com.imooc.flink.partitioner;

import com.imooc.flink.source.AccessSourceV2;
import com.imooc.flink.transformation.Access;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PartitionerApp {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(3);
        DataStreamSource<Access> source = env.addSource(new AccessSourceV2());
        System.out.println(source.getParallelism());

        source.map(new MapFunction<Access, Tuple2<String, Access>>() {
            @Override
            public Tuple2<String, Access> map(Access value) throws Exception {
                return Tuple2.of(value.getDomain(), value);
            }
        }).partitionCustom(new PKPartitioner(), 0)
                .map(new MapFunction<Tuple2<String,Access>, Access>() {
                    @Override
                    public Access map(Tuple2<String, Access> value) throws Exception {

                        System.out.println("current thread id is:" + Thread.currentThread().getId()+ ", value is:" + value.f1);

                        return value.f1;
                    }
                }).print();


        env.execute("PartitionerApp");
    }
}
