package cn.lastwhisper.elasticsearch.repository;

import cn.lastwhisper.elasticsearch.bean.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {

}
