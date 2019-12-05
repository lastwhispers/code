package cn.lastwhisper.elasticsearch.operation;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author lastwhisper
 * @date 2019/12/1
 */
public class BulkSave {

    public static void main(String[] args) throws IOException {
        //1.连接rest接口
        HttpHost http = new HttpHost("127.0.0.1", 9200, "http");
        RestClientBuilder builder = RestClient.builder(http);//rest构建器
        RestHighLevelClient restHighLevelClient = new
                RestHighLevelClient(builder);//高级客户端对象
        //2.封装请求对象
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest = new IndexRequest("sku", "doc", "4");
        Map skuMap = new HashMap();
        skuMap.put("name", "华为p30pro 火爆上市");
        skuMap.put("brandName", "华为");
        skuMap.put("categoryName", "手机");
        skuMap.put("price", 1010221);
        skuMap.put("createTime", "2019‐05‐01");
        skuMap.put("saleNum", 101021);
        skuMap.put("commentNum", 10102321);
        Map spec = new HashMap();
        spec.put("网络制式", "移动4G");
        spec.put("屏幕尺寸", "5");
        skuMap.put("spec", spec);
        indexRequest.source(skuMap);
        bulkRequest.add(indexRequest);//可以多次添加
        //3.获取响应结果
        BulkResponse response =
                restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        int status = response.status().getStatus();
        System.out.println(status);
        String message = response.buildFailureMessage();
        System.out.println(message);
        restHighLevelClient.close();
    }

}
