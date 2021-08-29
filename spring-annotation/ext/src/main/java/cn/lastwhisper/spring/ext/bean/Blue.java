package cn.lastwhisper.spring.ext.bean;

public class Blue {

    private String name;

    public Blue() {
        System.out.println("blue...constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}