package cn.lastwhisper.elasticsearch.operation;

import cn.lastwhisper.elasticsearch.operation.entity.Sku;
import cn.lastwhisper.elasticsearch.operation.enums.DbConfig;
import cn.lastwhisper.elasticsearch.operation.utils.DateUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 青橙商城数据导入
 * @author lastwhisper
 * @date 2019/12/1
 */
public class EsDataAdapter {

    private static Connection conn;
    private static QueryRunner queryRunner;

    private static RestHighLevelClient restHighLevelClient;

    static {
        try {
            // 初始化mysql连接
            Class.forName(DbConfig.DB_DRIVER.getValue());
            conn = DriverManager.getConnection(DbConfig.DB_URL.getValue(), DbConfig.DB_USERNAME.getValue(), DbConfig.DB_PASSWORD.getValue());
            // dbutils
            queryRunner = new QueryRunner();
            // 初始化es连接
            HttpHost http = new HttpHost(DbConfig.ES_HOSTNAME.getValue(), 9200, DbConfig.ES_SCHEMA.getValue());
            RestClientBuilder builder = RestClient.builder(http);
            restHighLevelClient = new RestHighLevelClient(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws SQLException, IOException {
        long beginTime = System.currentTimeMillis();

        // http：200 耗时：22.1s
        // 总耗时：25910
        fullImport();

        // 总耗时：26995
        //batchImport();

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - beginTime));
    }


    public static void fullImport() throws SQLException, IOException {
        try {
            List<Sku> skuList = fetchData();
            BulkResponse response = bulkImport(skuList);
            System.out.println("http：" + response.status().getStatus()
                    + " 耗时：" + response.getTook());
            if (response.hasFailures()) System.out.println("失败原因：" + response.buildFailureMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            restHighLevelClient.close();
        }
    }

    public static void batchImport() throws SQLException, IOException {
        try {
            int frequency = 0;
            Long total = total();//导入数据的总数
            Long basicTotal = 10000L;//每次导入的量
            Long start = 0L, end = basicTotal;
            while (total > 0L) {
                List<Sku> skuList = fetchData(start, end);
                BulkResponse response = bulkImport(skuList);
                System.out.println("http：" + response.status().getStatus()
                        + " 耗时：" + response.getTook());

                if (response.hasFailures()) System.out.println("失败原因：" + response.buildFailureMessage());

                if (total <= basicTotal) {
                    basicTotal = total;
                }
                total -= basicTotal;
                start += basicTotal;
                end += basicTotal;
                frequency++;
            }
            System.out.println("分批次数：" + frequency);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            restHighLevelClient.close();
        }
    }

    public static Long total() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tb_sku";
        return queryRunner.query(conn, sql, new ScalarHandler<>());
    }

    public static List<Sku> fetchData(Long start, Long end) throws SQLException {
        String sql = "SELECT id skuId,name,price,image,create_time createTime,category_name categoryName,brand_name brandName,spec,comment_num commentNum,sale_num saleNum,spu_id spuId FROM tb_sku limit ?,?";
        return queryRunner.query(conn, sql, new BeanListHandler<>(Sku.class), start, end);
    }

    public static List<Sku> fetchData() throws SQLException {
        String sql = "SELECT id skuId,name,price,image,create_time createTime,category_name categoryName,brand_name brandName,spec,comment_num commentNum,sale_num saleNum,spu_id spuId FROM tb_sku";
        return queryRunner.query(conn, sql, new BeanListHandler<>(Sku.class));
    }

    public static BulkResponse bulkImport(List<Sku> skuList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Sku sku : skuList) {
            Map<String, Object> skuMap = new HashMap<String, Object>();
            IndexRequest indexRequest = new IndexRequest("sku", "doc", sku.getSkuId());
            skuMap.put("name", sku.getName());
            skuMap.put("price", sku.getPrice());
            skuMap.put("image", sku.getImage());
            skuMap.put("createTime", DateUtils.dateFormat(sku.getCreateTime()));
            skuMap.put("categoryName", sku.getCategoryName());
            skuMap.put("brandName", sku.getBrandName());
            // "{'颜色': '红色', '版本': '8GB+128GB'}"
            Map spec = JSON.parseObject(sku.getSpec());
            skuMap.put("spec", spec);
            skuMap.put("commentNum", sku.getCommentNum());
            skuMap.put("saleNum", sku.getSaleNum());
            skuMap.put("spuId", sku.getSpuId());
            indexRequest.source(skuMap);
            bulkRequest.add(indexRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}
