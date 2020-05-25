package cn.lastwhisper.elasticsearch.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于对es搜索结果再加工
 */
@Component
public class HighlightResultMapper implements SearchResultMapper {

    /**
     * 比较通用，而且不依赖别的类库，但是会出现 Integer 不能 set 到 Long 的错误情况
     */
    //@Override
    //public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> clazz, Pageable pageable) {
    //    SearchHits hits = searchResponse.getHits();
    //    List<T> result = new ArrayList<>((int) hits.getTotalHits());
    //    for (SearchHit searchHit : hits) {
    //        // 高亮 K,V
    //        Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
    //        // 普通 K,V
    //        Map<String, Object> sourceFields = searchHit.getSourceAsMap();
    //        // 使用反射比较通用一些，也可以直接 new 对象，把值set进去
    //        try {
    //            T item = clazz.newInstance();
    //            Field[] fields = clazz.getDeclaredFields();
    //            for (Field field : fields) {
    //                // 拿到字段名，通过反射进行设置高亮或者普通
    //                String fieldName = field.getName();
    //                field.setAccessible(true);
    //                if (highlightFields.containsKey(fieldName)) {
    //                    field.set(item, highlightFields.get(fieldName).fragments()[0].toString());
    //                } else {
    //                    field.set(item, sourceFields.get(fieldName));
    //                }
    //            }
    //            result.add(item);
    //        } catch (InstantiationException | IllegalAccessException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    //    return new AggregatedPageImpl<>(result, pageable, hits.getTotalHits());
    //}

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> clazz, Pageable pageable) {
        SearchHits hits = searchResponse.getHits();
        List<T> result = new ArrayList<>((int) hits.getTotalHits());
        for (SearchHit searchHit : hits) {
            // 高亮 K,V
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            // 使用反射比较通用一些，也可以直接 new 对象，把值set进去
            try {
                T document = objectMapper.readValue(searchHit.getSourceAsString(), clazz);
                for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()) {
                    Field field = clazz.getField(entry.getKey());
                    field.setAccessible(true);
                    field.set(document, entry.getValue().fragments()[0].toString());
                }
                result.add(document);
            } catch (IllegalAccessException | IOException | NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
        return new AggregatedPageImpl<>(result, pageable, hits.getTotalHits());
    }

    @Override
    public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
        return null;
    }
}

