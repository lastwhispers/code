package cn.cunchang;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

/**
 * 分表不分库
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application2.properties")
class ApplicationTest2 {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void insert() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(new Random().nextInt(999));
        orderDao.save(orderEntity);
    }

    /**
     * 根据非 partition key需要查两次，因为不知道在哪个表
     */
    @Test
    public void findByUserId() {
        List<OrderEntity> list = orderDao.findByUserId(950);
        log.info("list={}", list);
    }

    /**
     * 根据table partition key需要查一次，因为知道在那个表
     */
    @Test
    public void findByOrderId() {
        //从主库查数据
        HintManager.getInstance().setPrimaryRouteOnly();
        OrderEntity orderEntity = orderDao.findByOrderId(608253561113391105L);
        log.info("orderEntity={}", orderEntity);
    }


}
