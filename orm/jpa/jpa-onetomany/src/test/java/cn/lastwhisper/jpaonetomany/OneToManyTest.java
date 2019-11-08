package cn.lastwhisper.jpaonetomany;

import cn.lastwhisper.jpaonetomany.dao.CustomerDao;
import cn.lastwhisper.jpaonetomany.dao.LinkManDao;
import cn.lastwhisper.jpaonetomany.domain.Customer;
import cn.lastwhisper.jpaonetomany.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * 测试一对多
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /*
     * cascade:配置级联操作
     * 		CascadeType.MERGE	级联更新
     * 		CascadeType.PERSIST	级联保存：
     * 		CascadeType.REFRESH 级联刷新：
     * 		CascadeType.REMOVE	级联删除：
     * 		CascadeType.ALL		包含所有
     * @OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
     */


    /**
     * 删除引发的问题：
     *  1. 删除从表数据：可以随时任意删除。
     *  2. 删除主表数据：
     *      2.1 有从表外键引用：
     *              （1）在默认情况下，主表会把从表外键字段置为null，然后删除主表数据。
     *                  如果在数据库的表结构上，外键字段有非空约束，默认情况就会报错了。
     *              （2）如果主表配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null，没有关系）
     *                  因为在删除时，它根本不会去更新从表的外键字段了。
     *              （3）如果还想删除，使用级联删除引用。
     *      2.2 没有从表外键引用：
     *              随便删
     */
    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testDelete() {
        customerDao.deleteById(1l);
    }

    /*
     *
     * lombok与集合生成hashcode导致的StackOverflowError
     *  https://blog.csdn.net/u013202238/article/details/80370868
     */

    /**
     * 保存操作
     * 需求:
     * 	    保存一个客户和一个联系人
     * 要求：
     * 	    创建一个客户对象和一个联系人对象
     *      建立客户和联系人之间关联关系（双向一对多的关联关系）
     *      先保存客户，再保存联系人
     * 问题：
     *		当我们建立了双向的关联关系之后，先保存主表，再保存从表时：
     *		会产生2条insert和1条update.
     * 		而实际开发中我们只需要2条insert。
     * 解决：
     *      主表放弃外键维护权@OneToMany(mappedBy="一的一方在多的一方的字段名")
     *          @OneToMany(mappedBy="customer")
     */
    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testSave() {
        Customer c = new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        LinkMan l = new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");

        c.getLinkMans().add(l);
        l.setCustomer(c);
        customerDao.save(c);
        linkManDao.save(l);

    }


}
