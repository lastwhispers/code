package cn.lastwhisper.propertyvalue.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;

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

    // 支付宝、微信、银行卡各有多少钱
    @Value("#{'${person.moneyList}'.split(',')}")
    private List<Integer> moneyList;

    @Value("#{'${person.moneyList}'.split(',')}")
    private Set<Integer> moneySet;


    public Person() {
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

    public List<Integer> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<Integer> moneyList) {
        this.moneyList = moneyList;
    }

    public Set<Integer> getMoneySet() {
        return moneySet;
    }

    public void setMoneySet(Set<Integer> moneySet) {
        this.moneySet = moneySet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickname='" + nickname + '\'' +
                ", moneyList=" + moneyList +
                ", moneySet=" + moneySet +
                '}';
    }
}
