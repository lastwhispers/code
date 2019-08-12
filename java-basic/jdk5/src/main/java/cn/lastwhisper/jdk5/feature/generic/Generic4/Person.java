package cn.lastwhisper.jdk5.feature.generic.Generic4;

public class Person {

    // 编号
    private Long id;

    // 姓名
    private String name;

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }

}