package cn.lastwhisper.elasticsearch;

import cn.lastwhisper.elasticsearch.bean.Item;
import cn.lastwhisper.elasticsearch.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringDataTest {

    @Autowired
    ItemRepository itemRepository;

    /**
     * 创建索引、映射，并把数据保存
     */
    @Test
    public void index() {
        Item item = new Item(1, "小米手机7", "手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.index(item);
    }

    /**
     * 创建索引、映射，并把数据保存
     *  跟 index 效果一样
     */
    @Test
    public void save() {
        Item item = new Item(1, "小米手机7", "手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 批量保存
     */
    @Test
    public void saveAll() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * elasticsearch中没有修改，修改和新增是同一个接口，
     *  根据 Id 先删除再新增（文档的顺序会发生变化）
     */
    @Test
    public void update(){
        Item item = new Item(1, "小米手机10", "手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 查找所有
     */
    @Test
    public void testFindAll() {
        itemRepository.findAll().forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindPage() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        itemRepository.findAll(pageRequest).forEach(System.out::println);
    }

    /**
     * 分页排序查询
     */
    @Test
    public void testFindOrder() {
        Sort sort = Sort.by("price").descending();
        PageRequest pageRequest = PageRequest.of(0, 3, sort);
        itemRepository.findAll(pageRequest).forEach(System.out::println);
    }

}
