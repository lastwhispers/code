package cn.lastwhisper.jpaonetomany;

import cn.lastwhisper.jpaonetomany.dao.CustomerDao;
import cn.lastwhisper.jpaonetomany.dao.LinkManDao;
import cn.lastwhisper.jpaonetomany.domain.Customer;
import cn.lastwhisper.jpaonetomany.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * 多表查询
 *
 * @author lastwhisper
 * @date 2019/10/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试代理类
     */
    @Test
    @Transactional
    public void test() {
        Customer customer = customerDao.getOne(103L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        linkMans.forEach(System.out::println);
    }
    /*
     * https://www.cnblogs.com/ymqj520/p/11378280.html
     * toString造成的StackOverflowError
     */

    /**
     * 对象导航查询
     *  查询一个客户，获取该客户下的所有联系人
     */
    @Test
    // 由于是在java代码中测试，为了解决no session问题，将操作配置到同一个事务中
    @Transactional
    public void testFindLinkMan() {
        Customer customer = customerDao.findById(103L).get();
        Set<LinkMan> linkMans = customer.getLinkMans();//对象导航查询
        linkMans.forEach(System.out::println);
    }

    /**
     * 查询一个联系人，获取该联系人的所有客户
     */
    @Test
    @Transactional
    public void testFindCustomer() {
        LinkMan linkMan = linkManDao.findById(8L).get();
        Customer customer = linkMan.getCustomer(); //对象导航查询
        System.out.println(customer);
    }

    /*
     * 在客户对象的@OneToMany注解中添加fetch属性
     * 		FetchType.EAGER	：立即加载
     * 		FetchType.LAZY	：延迟加载
     */
    @Test
    @Transactional
    public void testFetchType() {
        //加上延迟加载后，不使用linkMans，不查库
        Customer customer = customerDao.findById(103L).get();
        Set<LinkMan> linkMans = customer.getLinkMans();
        linkMans.forEach(System.out::println);
    }


}
