package com.imooc.flink.state;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StateApp {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        test01(env);

        env.execute("SourceApp");
    }

    /**
     * 使用ValueState实现平均数
     */
    public static void test01(StreamExecutionEnvironment env) throws Exception {
        List<Tuple2<Long,Long>> list = new ArrayList<>();
        list.add(Tuple2.of(1L, 3L));
        list.add(Tuple2.of(1L, 7L));
        list.add(Tuple2.of(2L, 4L));
        list.add(Tuple2.of(1L, 5L));
        list.add(Tuple2.of(1L, 15L));
        list.add(Tuple2.of(2L, 2L));
        list.add(Tuple2.of(2L, 5L));

        env.fromCollection(list)
                .keyBy(x -> x.f0)
//                .flatMap(new AvgWithValueState())
                .flatMap(new AvgWithMapState())
                .print();

    }
}


class AvgWithMapState extends RichFlatMapFunction <Tuple2<Long,Long>,Tuple2<Long,Double>> {

    private transient MapState<String, Long> mapState;

    @Override
    public void open(Configuration parameters) throws Exception {
        MapStateDescriptor<String, Long> descriptor = new MapStateDescriptor<String, Long>("avg", String.class, Long.class);
        mapState = getRuntimeContext().getMapState(descriptor);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        mapState.put(UUID.randomUUID().toString(), value.f1);

        ArrayList<Long> elements = Lists.newArrayList(mapState.values());

        if(elements.size() == 3) {
            Long count = 0L;
            Long sum = 0L;

            for (Long element : elements) {
                count += 1;
                sum += element;
            }

            Double avg = sum / count.doubleValue();
            out.collect(Tuple2.of(value.f0, avg));

            mapState.clear();
        }
    }
}

class AvgWithValueState extends RichFlatMapFunction<Tuple2<Long,Long>,Tuple2<Long,Double>> {

    // 求平均数：记录条数  总和
    private transient ValueState<Tuple2<Long,Long>> sum;

    @Override
    public void open(Configuration parameters) throws Exception {

        ValueStateDescriptor<Tuple2<Long,Long>> descriptor
                = new ValueStateDescriptor<>("avg", Types.TUPLE(Types.LONG, Types.LONG));

        sum = getRuntimeContext().getState(descriptor);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        // TODO... ==> state   次数 和 总和

        Tuple2<Long, Long> currentState = sum.value();

        if(null == currentState) {
            currentState = Tuple2.of(0L,0L);
        }

        currentState.f0 += 1; // 次数
        currentState.f1 += value.f1; // 求和


        sum.update(currentState);

        // 达到3条数据 ==> 求平均数  clear
        if(currentState.f0 >=3 ){
            // key，平均数
            out.collect(Tuple2.of(value.f0, currentState.f1/currentState.f0.doubleValue()));
            sum.clear();
        }
    }
}
