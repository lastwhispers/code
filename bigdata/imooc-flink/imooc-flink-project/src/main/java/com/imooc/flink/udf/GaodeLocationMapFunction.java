package com.imooc.flink.udf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.flink.domain.Access;
import com.imooc.flink.utils.StringUtils;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GaodeLocationMapFunction extends RichMapFunction<Access, Access> {

    CloseableHttpClient httpClient;

    @Override
    public void open(Configuration parameters) throws Exception {
        httpClient = HttpClients.createDefault();
    }

    @Override
    public void close() throws Exception {
         if(httpClient != null) httpClient.close();
    }

    @Override
    public Access map(Access value) throws Exception {
        String url = "https://restapi.amap.com/v3/ip?ip="+value.ip+"&output=json&key="+ StringUtils.GAODE_KEY;

        CloseableHttpResponse response = null;

        String province = "-";
        String city = "-";

        try {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");


                JSONObject jsonObject = JSON.parseObject(result);
                province = jsonObject.getString("province");
                city = jsonObject.getString("city");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
            value.province = province;
            value.city = city;
        }

        return value;
    }
}
