package cn.lastwhisper.javabasic.EqualsAndhashCode;

/**
 * @author eval
 * @description
 * @email 15037584397@163.com
 */
public class EqualsTest1 {
    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，使用默认equals比较
        Person p1 = new Person("tom", 25);
        Person p2 = new Person("tom", 25);
        System.out.printf("%s\n", p1.equals(p2));
    }

    /**
     * @author gaojun
     * @desc Person类
     */
    private static class Person {

        String name;
        int age;

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
