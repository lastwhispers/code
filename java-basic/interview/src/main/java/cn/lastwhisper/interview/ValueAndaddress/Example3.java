package cn.lastwhisper.interview.ValueAndaddress;

/**
 * @author gaojun
 * @desc 传递对象
 * @email 15037584397@163.com
 */
public class Example3 {
    public static void main(String[] args) {

        String name = "gaojun";
        pass(name);
        System.out.println("print in main , name is " + name);
    }

    public static void pass(String name) {
        name = "gaojun-update";
        System.out.println("print in pass , name is " + name);
    }
}
