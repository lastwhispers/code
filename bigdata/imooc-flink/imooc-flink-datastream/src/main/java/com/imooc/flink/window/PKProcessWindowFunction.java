package com.imooc.flink.window;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * IN：待处理DataStream中每个元素的类型
 * OUT：结果DataStream中每个元素的类型
 * KEY：keyBy中指定key的类型
 * W extends Window： TimeWindow
 */
public class PKProcessWindowFunction extends ProcessWindowFunction<Tuple2<String,Integer>, String, String, TimeWindow> {
    @Override
    public void process(String s, Context context, Iterable<Tuple2<String, Integer>> elements, Collector<String> out) throws Exception {

        System.out.println("----process invoked...----");

        int maxValue = Integer.MIN_VALUE;
        for (Tuple2<String, Integer> element : elements) {
            maxValue = Math.max(element.f1, maxValue);
        }

        out.collect("当前窗口的最大值是:" + maxValue);
    }
}
