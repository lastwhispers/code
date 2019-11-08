package cn.lastwhisper.springdatajpa;

import cn.lastwhisper.springdatajpa.dao.CustomerDao;
import cn.lastwhisper.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * 使用jpql语句和原生sql
 * @author lastwhisper
 * @date 2019/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindSql() {
        customerDao.findSql().forEach(System.out::println);
    }

    @Test
    public void testFindCustomer() {
        Optional<Customer> op = customerDao.findCustomer("阿里巴巴");
        if (op.isPresent()) {
            System.out.println("不为空：" + op.get());
        }
    }

    /**
     * 测试@Query注解声明的spql
     */
    @Test
    public void testFindAllCustomer() {
        customerDao.findAllCustomer().forEach(System.out::println);
    }


}
