package cn.lastwhisper.interview.EqualsAndhashCode;

/**
 * @author gaojun
 * @desc
 * @email 15037584397@163.com
 */
public class EqualsTest3 {
    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，使用默认equals比较
        Person p1 = new Person("tom", 25);
        Person p2 = new Person("tom", 25);
        System.out.printf("p1.equals(p2) : %s\n", p1.equals(p2));
        System.out.printf("p1==p2 : %s\n", p1 == p2);
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
