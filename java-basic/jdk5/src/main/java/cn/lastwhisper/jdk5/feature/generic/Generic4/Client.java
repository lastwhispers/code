package cn.lastwhisper.jdk5.feature.generic.Generic4;

/**
 * 该层模拟service
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1L);
        person.setName("张三");
        // 交给Spring的IOC容器管理
        PersonDao personDao = new PersonDaoImpl();
        // 持久化到数据库
        personDao.save(person);

    }
}
