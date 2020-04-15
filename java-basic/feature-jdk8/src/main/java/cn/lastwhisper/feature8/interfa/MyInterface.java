package cn.lastwhisper.feature8.interfa;

public interface MyInterface {

    default String getName() {
        return "呵呵呵";
    }

    public static void show() {
        System.out.println("接口中的静态方法");
    }

}

interface Named {
    default String getName() {
        return "hehehe";
    }
}

class MyClass implements MyInterface, Named {
    public String getName() {
        return Named.super.getName();
    }
}