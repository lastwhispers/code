package com.imooc.flink.app;

import com.alibaba.fastjson.JSON;
import com.imooc.flink.domain.Access;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.hash.BloomFilter;
import org.apache.flink.shaded.guava18.com.google.common.hash.Funnels;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * 新老用户的统计分析
 *
 * 原来是根据数据中的某个字段
 * 现在我们是根据每个device来进行判断是否是新老用户
 *
 * 思考：device放到state里面去呢
 *
 * 我们的实现：状态 + 布隆过滤器
 *
 */
public class OsUserCntAppV3 {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        SingleOutputStreamOperator<Access> cleanStream = environment.readTextFile("data/access.json")
                .map(new MapFunction<String, Access>() {
                    @Override
                    public Access map(String value) throws Exception {
                        // TODO...  json ==> Access

                        try {
                            return JSON.parseObject(value, Access.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }

                    }
                }).filter(x -> x != null)
                .filter(new FilterFunction<Access>() {
                    @Override
                    public boolean filter(Access value) throws Exception {
                        return "startup".equals(value.event);
                    }
                });


        cleanStream.keyBy(x -> x.deviceType)
                .process(new KeyedProcessFunction<String, Access, Access>() {

                    private transient ValueState<BloomFilter<String>> state;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        ValueStateDescriptor<BloomFilter<String>> descriptor = new ValueStateDescriptor<>("s", TypeInformation.of(new TypeHint<BloomFilter<String>>() {}));
                        state = getRuntimeContext().getState(descriptor);
                    }

                    @Override
                    public void processElement(Access value, Context ctx, Collector<Access> out) throws Exception {
                        String device = value.device;
                        BloomFilter<String> bloomFilter = state.value();
                        if(null == bloomFilter) {
                            bloomFilter = BloomFilter.create(Funnels.unencodedCharsFunnel(), 100000);
                        }

                        if(!bloomFilter.mightContain(device)) {
                            bloomFilter.put(device);
                            value.nu2 = 1;
                            state.update(bloomFilter);
                        }

                        out.collect(value);
                    }
                }).print();


        environment.execute("OsUserCntAppV3");

    }
}
