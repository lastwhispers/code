package cn.cunchang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

/**
 * 分库分表
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application1.properties")
class ApplicationTest1 {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void insert() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(new Random().nextInt(999));
        orderDao.save(orderEntity);
    }

    /**
     * 根据db partition key需要查两次，因为知道在那个库，但是不知道在哪个表
     */
    @Test
    public void findByUserId() {
        List<OrderEntity> list = orderDao.findByUserId(950);
        log.info("list={}", list);
    }

    /**
     * 根据table partition key需要查两次，因为知道在那个表，但是不知道在哪个库
     */
    @Test
    public void findByOrderId() {
        //从主库查数据
//        HintManager.getInstance().setPrimaryRouteOnly();
        OrderEntity orderEntity = orderDao.findByOrderId(608253561113391105L);
        log.info("orderEntity={}", orderEntity);
    }

    /**
     * 根据 db 和 table partition key只需要查一次
     */
    @Test
    public void findByUserIdAndOrderId() {
        OrderEntity orderEntity = orderDao.findByUserIdAndOrderId(950,608253561335689217L);
        log.info("orderEntity={}", orderEntity);
    }


}
