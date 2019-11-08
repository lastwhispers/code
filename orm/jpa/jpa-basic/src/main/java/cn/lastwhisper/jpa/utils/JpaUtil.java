package cn.lastwhisper.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



/**
 *
 * @author lastwhisper
 * @date 2019/10/16
 */
public class JpaUtil {
    /**
     * JPA的实体管理器工厂：相当于Hibernate的SessionFactory
     */
    private static EntityManagerFactory em;

    static {
        // 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
        em = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 使用管理器工厂生产一个管理器对象
     * @return javax.persistence.EntityManager
     */
    public static EntityManager getEntityManager() {
        return em.createEntityManager();
    }

    public static void jpaTemplate(Callback callback) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 1、获取EntityManager
            em = JpaUtil.getEntityManager();
            // 2、获取事务
            tx = em.getTransaction();
            // 3、开启事务
            tx.begin();

            // do somethings
            callback.execute(em);

            // 5、提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}