package cn.lastwhisper.jpa.utils;

import javax.persistence.EntityManager;

/**
 * @author lastwhisper
 */
@FunctionalInterface
public interface Callback {
    /**
     * 模板方法的回调方法
     * @param em EntityManager操作实体类的对象
     * @return void
     */
    void execute(EntityManager em);
}