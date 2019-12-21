package cn.lastwhisper.elasticsearch.operation;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 各种查询
 * @author lastwhisper
 * @date 2019/11/26
 */
public class Select {

    private RestHighLevelClient restHighLevelClient = null;
    private SearchRequest searchRequest = null;

    @Before
    public void init() {
        //1.连接rest接口
        HttpHost http = new HttpHost("127.0.0.1", 9200, "http");
        RestClientBuilder builder = RestClient.builder(http);//rest构建器
        restHighLevelClient = new
                RestHighLevelClient(builder);//高级客户端对象
        //2.封装查询请求
        searchRequest = new SearchRequest("sku");
        searchRequest.types("doc"); //设置查询的类型
    }

    //@After
    public void after() throws IOException {
        //3.获取查询结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                RequestOptions.DEFAULT);

        // 查询花费时间，单位是毫秒
        TimeValue took = searchResponse.getTook();
        // 分片信息
        int total = searchResponse.getTotalShards();
        int success = searchResponse.getSuccessfulShards();
        int skipped = searchResponse.getSkippedShards();
        int failed = searchResponse.getFailedShards();
        // 搜索结果总览对象
        SearchHits searchHits = searchResponse.getHits();
        // 搜索到的总条数
        long totalHits = searchHits.getTotalHits();
        // 所有结果中文档得分的最高分
        float maxScore = searchHits.getMaxScore();

        System.out.println("took：" + took);
        System.out.println("_shards：");
        System.out.println("        total：" + total);
        System.out.println("        success：" + success);
        System.out.println("        skipped：" + skipped);
        System.out.println("        failed：" + failed);
        System.out.println("hits：");
        System.out.println("        total：" + totalHits);
        System.out.println("        max_score：" + maxScore);
        System.out.println("        hits：");
        // 搜索结果的文档对象数组，每个元素是一条搜索到的文档信息
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            // 索引库
            String index = hit.getIndex();
            // 文档类型
            String type = hit.getType();
            // 文档id
            String id = hit.getId();
            // 文档得分
            float score = hit.getScore();
            // 文档的源数据
            String source = hit.getSourceAsString();
            System.out.println("            _index：" + index);
            System.out.println("            _type：" + type);
            System.out.println("            _id：" + id);
            System.out.println("            _score：" + score);
            System.out.println("            _source：" + source);
        }
        restHighLevelClient.close();
    }


    /**
     * 匹配查询
     * 语法：
     *  SearchRequest： 查询请求对象
     *  SearchResponse：查询响应对象
     *  SearchSourceBuilder：查询源构建器
     *  MatchQueryBuilder：匹配查询构建器
     * 示例：
     *  查询商品名称包含手机的记录。
     */
    @Test
    public void test1() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "小米电视");
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
    }

    /**
     * 布尔与词条查询
     * 语法：
     *  QueryBuilders：查询构建器工厂
     *      BoolQueryBuilder：布尔查询构建器
     *      TermQueryBuilder：词条查询构建器
     * 示例：
     *  查询名称包含手机的，并且品牌为小米的记录
     */
    @Test
    public void test2() throws IOException {
        //2.封装查询请求
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 布尔查询构建器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "手机");
        // 词条查询构建器
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", "小米");

        boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

    }

    /**
     * 过滤查询
     * 示例：筛选品牌为小米的记录
     */
    @Test
    public void test3() throws IOException {
        //2.封装查询请求
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 布尔查询构建器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 词条查询构建器
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", "小米");

        boolQueryBuilder.filter(termQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
    }

    /**
     * 分组（聚合）查询
     * 语法：
     *  AggregationBuilders：聚合构建器工厂
     *  TermsAggregationBuilder：词条聚合构建器
     *  Aggregations：分组结果封装
     *  Terms.Bucket： 桶
     * 示例：
     *  按商品分类分组查询，求出每个分类的文档数
     */
    @Test
    public void test4() throws IOException {
        //1.连接rest接口
        HttpHost http = new HttpHost("127.0.0.1", 9200, "http");
        RestClientBuilder builder = RestClient.builder(http);//rest构建器
        RestHighLevelClient restHighLevelClient = new
                RestHighLevelClient(builder);//高级客户端对象
        //2.封装查询请求
        SearchRequest searchRequest = new SearchRequest("sku");
        searchRequest.types("doc"); //设置查询的类型
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                    .terms("sku_category").field("categoryName");
        searchSourceBuilder.aggregation(termsAggregationBuilder);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);

        //3.获取查询结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();

        Map<String, Aggregation> asMap = aggregations.getAsMap();
        Terms terms = (Terms) asMap.get("sku_category");
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount()
            );
        }
        restHighLevelClient.close();
    }

    /**
     * 范围查询
     * RangeQueryBuilder rangeQueryBuilder =
     * QueryBuilders.rangeQuery("price").gte(10000).lte(20000);
     */

    /**
     * 分页查询
     * searchSourceBuilder.size(30);
     * searchSourceBuilder.from(60);
     */

}
