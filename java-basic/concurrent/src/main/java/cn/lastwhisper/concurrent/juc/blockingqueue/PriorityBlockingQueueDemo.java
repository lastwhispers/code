package cn.lastwhisper.concurrent.juc.blockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author cunchang
 * @date 2022/6/18 11:11 AM
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person> pbq = new PriorityBlockingQueue<>();
        pbq.add(new Person(3,"person3"));
        System.err.println("容器为：" + pbq);
        pbq.add(new Person(2,"person2"));
        System.err.println("容器为：" + pbq);
        pbq.add(new Person(1,"person1"));
        System.err.println("容器为：" + pbq);
        pbq.add(new Person(4,"person4"));
        System.err.println("容器为：" + pbq);
        System.err.println("分割线----------------------------------------------------------------" );


        System.err.println("获取元素 " + pbq.take());
        System.err.println("当前容器为：" + pbq);
        System.err.println("分割线----------------------------------------------------------------" );

        System.err.println("获取元素 " + pbq.take());
        System.err.println("当前容器为：" + pbq);
        System.err.println("分割线----------------------------------------------------------------" );

        System.err.println("获取元素 " + pbq.take());
        System.err.println("当前容器为：" + pbq);
        System.err.println("分割线----------------------------------------------------------------" );

        System.err.println("获取元素 " + pbq.take());
        System.err.println("当前容器为：" + pbq);
        System.err.println("分割线----------------------------------------------------------------" );
    }

    static class Person implements Comparable<Person> {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        public Person() {
        }

        @Override
        public String toString() {
            return this.id + ":" + this.name;
        }

        @Override
        public int compareTo(Person person) {
            return Integer.compare(this.id, person.getId());
        }
    }
}
