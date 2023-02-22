package cn.cunchang.stream.sink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * 需求：接收socket传输过来的数据，把数据保存到redis的list队列中
 * Created by xuwei
 */
public class StreamRedisSinkJava {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //连接socket获取输入数据
        DataStreamSource<String> text = env.socketTextStream("localhost", 9008);

        // 组装数据，这里组装的是tuple2类型
        // 第一个元素是指list队列的key名称
        // 第二个元素是指需要向list队列中添加的元素
        SingleOutputStreamOperator<Tuple2<String, String>> listData = text.map(new MapFunction<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> map(String word) throws Exception {
                return new Tuple2<String, String>("l_words_java", word);
            }
        });

        //指定redissink
        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("localhost").setPort(6379).build();
        RedisSink<Tuple2<String, String>> redisSink = new RedisSink<>(conf, new MyRedisMapper());
        listData.addSink(redisSink);

        env.execute("StreamRedisSinkJava");
    }

    public static class MyRedisMapper implements RedisMapper<Tuple2<String,String>>{

        //指定具体的操作命令
        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.LPUSH);
        }

        //获取key
        @Override
        public String getKeyFromData(Tuple2<String, String> data) {
            return data.f0;
        }
        //获取value
        @Override
        public String getValueFromData(Tuple2<String, String> data) {
            return data.f1;
        }
    }
}
