package cn.lastwhisper.elasticsearch.operation;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 新增或修改数据
 * @author lastwhisper
 * @date 2019/11/26
 */
public class Save {
    /**
     * HttpHost : url地址封装
     * RestClientBuilder： rest客户端构建器
     * RestHighLevelClient： rest高级客户端
     * IndexRequest： 新增或修改请求
     * IndexResponse：新增或修改的响应结果
     */
    public static void main(String[] args) throws IOException {
        //1.连接rest接口
        HttpHost http = new HttpHost("127.0.0.1", 9200, "http");
        // rest构建器
        RestClientBuilder builder = RestClient.builder(http);
        // 高级客户端对象
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        //2.封装请求对象
        IndexRequest indexRequest = new IndexRequest("sku", "doc", "3");
        Map<String, Object> skuMap = new HashMap<String, Object>();
        skuMap.put("name", "华为p30pro");
        skuMap.put("brandName", "华为");
        skuMap.put("categoryName", "手机");
        skuMap.put("price", 1010221);
        skuMap.put("createTime", "2019-05-01");
        skuMap.put("saleNum", 101021);
        skuMap.put("commentNum", 10102321);
        Map<String, Object> spec = new HashMap<String, Object>();
        spec.put("网络制式", "移动4G");
        spec.put("屏幕尺寸", "5");
        skuMap.put("spec", spec);
        indexRequest.source(skuMap);
        //3.获取响应结果
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        int status = response.status().getStatus();
        System.out.println("响应状态：" + status);
        restHighLevelClient.close();
    }

}
