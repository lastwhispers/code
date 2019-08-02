package cn.lastwhisper.javabasic.EqualsAndhashCode;

import java.util.HashSet;

/**
 * @author gaojun
 * @desc 比较equals() 返回true 以及 返回false时， hashCode()的值。
 * @email 15037584397@163.com
 */
public class ConflictHashCodeTest2 {

    public static void main(String[] args) {
        // 新建Person对象
        Person p1 = new Person("tom", 25);
        Person p2 = new Person("tom", 25);
        Person p3 = new Person("luck", 30);
        Person p4 = new Person("TOM", 25);
        // 新建HashSet对象

        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);

        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 比较p1 和 p4， 并打印它们的hashCode()
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);
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

        /**
         * @desc 重写hsahcode方法
         * @author gaojun
         */
        @Override
        public int hashCode() {
            int namehash = name.toUpperCase().hashCode();
            return namehash ^ age;
        }

        /**
         * @desc 重写equals方法
         * @author gaojun
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            // 如果是同一个对象返回true，反之返回false
            if (this == obj) {
                return true;
            }
            // 判断类型是否相同
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            Person person = (Person) obj;
            if (name.equals(person.name) && age == person.age) {
                return true;
            } else {
                return false;
            }
        }
    }
}
