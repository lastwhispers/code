package cn.lastwhisper.feature5.generic.Generic4;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用泛型DAO模板
 * @author lastwhisper
 */
public interface BaseDao<T> {

    void save(T t);

    void delete(int id);

    void delete(T t);

    void update(T t);

    T findById(int id);

    List<T> findByConditions(String conditions);

}
