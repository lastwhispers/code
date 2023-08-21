package cn.cunchang.optional;

import cn.cunchang.stream.Employee;
import org.junit.Test;

import java.util.Optional;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {
    
    // Optional.of(T t) : 创建一个 Optional 实例
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        //Optional<Employee> op1 = Optional.of(null);
        Employee emp = op.get();
        System.out.println(emp);
    }
    
    /**
     * Optional.ofNullable(T t):若 t不为 null,创建 Optional 实例,否则创建空实例
     * Optional.empty() : 创建一个空的 Optional 实例
     */
    @Test
    public void test2(){
		//Optional<Employee> op = Optional.ofNullable(null);
		//System.out.println(op.get());

		Optional<Employee> op1 = Optional.empty();
        System.out.println(op1.get());
    }

    /**
     *  isPresent() : 判断是否包含值
     *  orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     *  orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s获取的值
     */
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);

        if(op.isPresent()){
            System.out.println(op.get());
        }

        Employee emp = op.orElse(new Employee("张三"));
        System.out.println(emp);

        Employee emp2 = op.orElseGet(() -> new Employee("李四"));
        System.out.println(emp2);
    }

    /**
     *
     *  map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     *  flatMap(Function mapper):与map类似，要求返回值必须是Optional
     */
    @Test
    public void test4(){
        Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));

        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());

        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

    /**
     * 需求：获取一个男人心中女神的名字
     * 传统避免空指针异常
     */
    @Test
    public void test5(){
        Man man = new Man();
        String name = getGodnessName(man);
        System.out.println(name);
    }
    public String getGodnessName(Man man){
        if(man != null){
            Godness g = man.getGod();

            if(g != null){
                return g.getName();
            }
        }
        return "苍老师";
    }

    //运用 Optional 的实体类
    @Test
    public void test6(){
        Optional<Godness> godness = Optional.ofNullable(new Godness("林志玲"));

        Optional<NewMan> op = Optional.ofNullable(new NewMan(godness));
        String name = getGodnessName2(op);
        System.out.println(name);
    }

    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍老师"))
                .getName();
    }

    @Test
    public void test7() {
        Optional.ofNullable(null).map(o -> {
            if (o == null) {
                return null;
            } else {
                return o;
            }
        }).ifPresent(o -> {
            System.out.println(o+"1");
        });
    }

}
