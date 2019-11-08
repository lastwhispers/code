package cn.lastwhisper.test;

import cn.lastwhisper.jpa.domain.Customer;
import cn.lastwhisper.jpa.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA基础知识
 * @author lastwhisper
 * @date 2019/10/16
 */
public class JpaTest {
    /*
     * EntityManager
     *  getTransaction : 获取事务对象
     * 	persist ： 保存操作
     * 	merge ： 更新操作
     * 	remove ： 删除操作
     * 	find/getReference ： 根据id查询
     */

    /**
     * 查询策略：使用延迟加载策略
     *  只有使用对象时才会执行查询sql语句：先输出true，再打印sql
     */
    @Test
    public void testLoadOne() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、获取数据
            // primaryKey类型必须和实体主键类型一致，否则查询不到
            Customer c1 = em.getReference(Customer.class, 2L);
            Customer c2 = em.getReference(Customer.class, 2L);
            System.out.println(c1 == c2);
            // 5、提交事务
            tx.commit();
            //System.out.println(c1);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * 查询策略：使用立即加载策略
     *  不管查询的对象是否使用，都会执行查询sql语句：先输出sql语句，再输出true；
     */
    @Test
    public void testGetOne() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、获取数据
            // primaryKey类型必须和实体主键类型一致，否则查询不到
            Customer c1 = em.find(Customer.class, 2L);
            Customer c2 = em.find(Customer.class, 2L);
            System.out.println(c1 == c2);
            // 5、提交事务
            tx.commit();
            System.out.println(c1);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void testDelete() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、获取数据、删除数据
            // primaryKey类型必须和实体主键类型一致，否则查询不到
            Customer customer = em.find(Customer.class, 2L);
            System.out.println(customer);
            // 5、提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void testUpdate() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、获取数据、更新数据
            // primaryKey类型必须和实体主键类型一致，否则查询不到
            Customer customer = em.find(Customer.class, 1L);
            customer.setCustLevel("5");
            // 将customer从缓存中清除
            //em.clear();
            // merge会对比待更新对象与缓存中是否相同，不相同则更新；如果缓存没有则向数据库查询
            em.merge(customer);
            // 5、提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }

    }


    @Test
    public void testSave() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、保存数据
            em.persist(new Customer(null, "共致开源", "网络", "软件开发", "2", "北京区海淀区西二旗", "150"));
            // 5、提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }

    }

    @Test
    public void testJpa() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        // 创建实体管理器
        EntityManager em = factory.createEntityManager();
        // 获取事务对象
        EntityTransaction tx = em.getTransaction();
        // 开启事务
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("阿里巴巴");
        // 保存操作
        em.persist(customer);
        // 提交事务
        tx.commit();
        // 释放资源
        em.close();
        factory.close();
    }

}
