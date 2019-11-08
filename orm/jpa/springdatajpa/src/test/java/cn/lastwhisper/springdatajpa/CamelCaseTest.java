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
 * 使用驼峰命名规则
 * @author lastwhisper
 * @date 2019/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CamelCaseTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test() {
        Optional<Customer> op = customerDao.findByCustName("阿里巴巴");
        if (op.isPresent()) {
            System.out.println(op.get());
        }
    }



}
