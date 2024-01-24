package com.imooc.flink.sink;

import com.imooc.flink.utils.MySQLUtils;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * domain traffic
 */
public class PKMySQLSink extends RichSinkFunction<Tuple2<String, Double>> {


    Connection connection;
    PreparedStatement insertPstmt;
    PreparedStatement updatePstmt;


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        connection = MySQLUtils.getConnection();
        insertPstmt = connection.prepareStatement("insert into pk_traffic(domain,traffic) values (?,?)");
        updatePstmt = connection.prepareStatement("update pk_traffic set traffic=? where domain=?");

    }

    @Override
    public void close() throws Exception {
        super.close();
        if(insertPstmt != null) insertPstmt.close();
        if(updatePstmt != null) updatePstmt.close();
        if(connection != null) connection.close();
    }

    /**
     * 来一条数据就执行一次
     *
     * 1000w的数据  1000w次
     */
    @Override
    public void invoke(Tuple2<String, Double> value, Context context) throws Exception {
        System.out.println("=====invoke======" + value.f0 + "-->" + value.f1);

        updatePstmt.setDouble(1, value.f1);
        updatePstmt.setString(2 , value.f0);
        updatePstmt.execute();

        if(updatePstmt.getUpdateCount() == 0) {
            insertPstmt.setString(1, value.f0);
            insertPstmt.setDouble(2, value.f1);
            insertPstmt.execute();
        }

    }
}
