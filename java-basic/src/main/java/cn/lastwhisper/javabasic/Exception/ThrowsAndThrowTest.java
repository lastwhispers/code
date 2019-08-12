package cn.lastwhisper.javabasic.Exception;

/**
 * throws和throw的基本用法以及自定义异常
 * @author lastwhisper
 */
public class ThrowsAndThrowTest {

    public static void main(String[] args) {
        try {
            test();
        } catch (MyException e) {
            System.out.println("Catch My Exception");
            e.printStackTrace();
        }
    }

    public static void test() throws MyException {
        try {
            int i = 10 / 0;
            System.out.println("i=" + i);
        } catch (ArithmeticException e) {
            throw new MyException("This is MyException，OuterClassExample() exception");
        }
    }
}

class MyException extends Exception {
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
    }
}