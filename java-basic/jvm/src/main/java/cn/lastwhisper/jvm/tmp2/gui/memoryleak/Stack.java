package cn.lastwhisper.jvm.tmp2.gui.memoryleak;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author shkstart
 * @create 15:05
 */
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) { //入栈
        ensureCapacity();
        elements[size++] = e;
    }
    //存在内存泄漏
//    public Object pop() { //出栈
//        if (size == 0)
//            throw new EmptyStackException();
//        return elements[--size];
//    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
