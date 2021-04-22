package cn.lastwhisper.generic.Generic4;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用泛型DAO模板
 * @author lastwhisper
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    protected Class<T> clazz;

    public BaseDaoImpl() {
        // 1.获取对象对应的父类的类型
        Type baseDaoClass = this.getClass().getGenericSuperclass();
        // 2.转成带参数，即泛型的类型
        ParameterizedType pType = (ParameterizedType) baseDaoClass;
        // 3.获取参数泛型类型数组
        Type[] types = pType.getActualTypeArguments();
        // 4.由于我们的BaseDao<T>的泛型参数里只有一个类型T，因此数组的第一个元素就是类型T的实际上的类型
        clazz = (Class<T>) types[0];
        System.out.println("clazz = " + this.clazz);
    }

    @Override
    public void save(T t) {
        System.out.println("save：" + t);
    }

    @Override
    public void delete(int id) {
        System.out.println("delete id：" + id);
    }

    @Override
    public void delete(T t) {
        System.out.println("delete：" + t);
    }

    @Override
    public void update(T t) {
        System.out.println("update：" + t);
    }

    @Override
    public T findById(int id) {
        System.out.println("findById：" + id);
        return null;
    }

    @Override
    public List<T> findByConditions(String conditions) {
        System.out.println("findByConditions：" + conditions);
        return null;
    }
}
