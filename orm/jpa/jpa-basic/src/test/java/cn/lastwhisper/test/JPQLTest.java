package cn.lastwhisper.test;

import cn.lastwhisper.jpa.utils.Callback;
import cn.lastwhisper.jpa.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * JPA中的复杂查询
 *  JPQL全称Java Persistence Query Language
 * @author lastwhisper
 * @date 2019/10/16
 */
public class JPQLTest {


    /**
     * 统计查询
     */
    @Test
    public void findCount() {
        JpaUtil.jpaTemplate(em -> {
            String jpql = "select count(*) from Customer";
            Query query = em.createQuery(jpql);
            System.out.println(query.getSingleResult());
        });
    }

    /**
     * 排序查询
     *  根据客户id倒序查询所有客户
     */
    @Test
    public void testOrder() {
        JpaUtil.jpaTemplate(em -> {
            String jpql = "from Customer order by custId desc";
            Query query = em.createQuery(jpql);
            query.getResultList().forEach(System.out::println);
        });
    }


    /**
     * 条件查询
     */
    @Test
    public void findCondition() {
        JpaUtil.jpaTemplate(em -> {
            // 创建Query对象
            String jpql = "from Customer where custName like ?1";
            Query query = em.createQuery(jpql);
            // 对占位符赋值，从1开始
            query.setParameter(1, "阿里巴%");
            // 返回对象
            Object obj = query.getSingleResult();
            System.out.println(obj);
        });
    }

    /**
     * 使用lambda优化匿名内部类
     */
    @Test
    public void testFindAll3() {
        JpaUtil.jpaTemplate((em -> {
            String jpql = "from Customer";
            Query query = em.createQuery(jpql);
            query.getResultList().forEach(System.out::println);
        }));
    }

    /**
     * 使用模板方法优化重复代码
     */
    @Test
    public void testFindAll2() {
        JpaUtil.jpaTemplate(new Callback() {
            @Override
            public void execute(EntityManager em) {
                String jpql = "from Customer";
                Query query = em.createQuery(jpql);
                query.getResultList().forEach(System.out::println);
            }
        });
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();
            // 4、创建Query对象
            String jpql = "from Customer";
            Query query = em.createQuery(jpql);
            query.getResultList().forEach(System.out::println);
            // 5、提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            if (em != null) em.close();
        }
    }

}
