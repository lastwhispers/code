package cn.lastwhisper.elasticsearch;

import cn.lastwhisper.elasticsearch.bean.Item;
import cn.lastwhisper.elasticsearch.repository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * Spring Data Query
 *
 *  matchQuery：词条匹配，先分词然后在调用termQuery进行匹配
 *  TermQuery：词条匹配，不分词
 *  wildcardQuery：通配符匹配
 *  fuzzyQuery：模糊匹配
 *  rangeQuery：范围匹配
 *  booleanQuery：布尔查询
 *
 * @author lastwhisper
 * @date 2020/5/22
 */
@SpringBootTest
public class SpringDataQueryTest {

    @Autowired
    ItemRepository itemRepository;

    /**
     * matchQuery：词条匹配，先分词然后在调用termQuery进行匹配
     */
    @Test
    public void matchQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // match会对输入进行分词处理后再去查询，部分命中的结果也会按照评分由高到低显示出来。
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "坚果R1"));
        // 搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("total = " + total);
        items.forEach(System.out::println);
    }

    /**
     * TermQuery：词条匹配，不分词
     *
     * term是将传入的文本原封不动地（不分词）拿去查询。
     * match会对输入进行分词处理后再去查询，部分命中的结果也会按照评分由高到低显示出来。
     * match_phrase是按短语查询，只有存在这个短语的文档才会被显示出来。
     */
    @Test
    public void testTermQuery() {
        // 查询条件生成器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // term是将传入的文本原封不动地（不分词）拿去查询。
        builder.withQuery(QueryBuilders.termQuery("brand", "小米"));
        // 查询 自动分页 ,默认查找第一页的10条数据
        Page<Item> items = this.itemRepository.search(builder.build());
        items.forEach(System.out::println);
    }

    /**
     *  fuzzyQuery：模糊匹配
     */
    @Test
    public void testFuzzyQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("title", "小米"));

        Page<Item> items = this.itemRepository.search(builder.build());
        items.forEach(System.out::println);
    }

    /**
     *  booleanQuery：布尔查询
     */
    @Test
    public void testBooleanQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("category", "手机"))
                .must(QueryBuilders.termQuery("brand", "小米"))
        );

        Page<Item> items = this.itemRepository.search(builder.build());
        items.forEach(System.out::println);
    }

    /**
     * rangeQuery：范围匹配
     */
    @Test
    public void testRangeQuery() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").from(3000).to(4000));

        Page<Item> items = itemRepository.search(queryBuilder.build());
        items.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void searchAndSort() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        // 搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        items.forEach(System.out::println);
    }


}
