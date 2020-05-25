package cn.lastwhisper.elasticsearch;

import cn.lastwhisper.elasticsearch.bean.Item;
import cn.lastwhisper.elasticsearch.mapper.HighlightResultMapper;
import cn.lastwhisper.elasticsearch.query.QueryVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * ElasticsearchTemplate
 * @author lastwhisper
 * @date 2020/5/22
 */
@SpringBootTest
public class EsTemplateTest {

    @Autowired
    ElasticsearchTemplate esTemplate;

    //@Autowired
    //ElasticsearchRestTemplate esTemplate;

    @Autowired
    private HighlightResultMapper highlightResultMapper;

    /**
     * 创建索引
     * 创建映射
     */
    @Test
    public void createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        esTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        esTemplate.putMapping(Item.class);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        esTemplate.deleteIndex(Item.class);
        // 根据索引名字删除
        //esRestTemplate.deleteIndex("indexName");
    }


    @Test
    public void testHighlightQuery() {
        QueryVO query = new QueryVO();
        query.setKeyword("手机");
        query.setPage(1);
        query.setSize(10);

        // 复合查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 以下为查询条件, 使用 must query 进行查询组合
        MultiMatchQueryBuilder matchQuery = QueryBuilders.multiMatchQuery(query.getKeyword(), "title", "category");
        boolQuery.must(matchQuery);

        PageRequest pageRequest = PageRequest.of(query.getPage() - 1, query.getSize());

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<span style=\"color:#F56C6C\">").postTags("</span>"),
                        new HighlightBuilder.Field("category").preTags("<span style=\"color:#F56C6C\">").postTags("</span>"))
                .withPageable(pageRequest)
                .build();
        Page<Item> items = esTemplate.queryForPage(searchQuery, Item.class, highlightResultMapper);

        items.forEach(System.out::println);
        // Item{id=1, title='小米<span style="color:#F56C6C">手机</span>10', category='<span style="color:#F56C6C">手机</span>',
        // brand='小米', price=3499.0, images='http://image.baidu.com/13123.jpg'}
    }


}
