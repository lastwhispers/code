package cn.lastwhisper.propertyvalue.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author lastwhisper
 */
public class Person {

    // @Value赋值
    // 1. 基本数据类型
    // 2. SpEl，#{}
    // 3. ${}读取配置文件以及运行时环境变量

    @Value("tom")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${person.nickname}")
    private String nickname;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
