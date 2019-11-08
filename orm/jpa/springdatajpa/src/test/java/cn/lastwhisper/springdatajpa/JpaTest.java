package cn.lastwhisper.springdatajpa;

import cn.lastwhisper.springdatajpa.dao.CustomerDao;
import cn.lastwhisper.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 使用JpaRepository、JpaSpecificationExecutor
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findById：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     *
     *  观察customerDao、JdkDynamicAopProxy的invoke、SimpleJpaRepository
     */
    @Test
    @Transactional
    public void testGetOne() {
        //Customer c1 = customerDao.getOne(5L);
        Optional<Customer> op = customerDao.findById(5L);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        customerDao.deleteById(4L);
    }


    /**
     * 修改
     */
    @Test
    public void testUpdate() {
        Optional<Customer> op = customerDao.findById(3L);
        if (op.isPresent()) {
            Customer customer = op.get();
            customer.setCustName("阿里巴巴");
            customerDao.save(customer);
        }
    }

    /**
     * 新增
     */
    @Test
    public void testSave() {
        customerDao.save(new Customer(null, "共致开源", "网络", "软件开发", "2", "北京区海淀区西二旗", "150"));
    }


}
