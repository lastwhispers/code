package cn.lastwhisper.offer.interview_9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 面试题9：用两个栈实现队列（stack1用于入队，stack2用于出队）
 *  入队：将元素push到stack1
 *  出队：出队时stack2不为空，就将stack2栈顶pop
 *      如果stack2为空，就将stack1栈顶元素依次push到stack2中，再从stack2栈顶pop
 * 扩展：用两个队列实现栈（两个队列都用于入栈、出栈）
 *  入栈：queue1不为空，将元素入queue1；queue2不为空，将元素入queue2；
 *          都为空入queue1
 *  出栈：queue1和queue2都为空直接RuntimeException
 *       queue1不为空，将queue1元素依次出队并插入queue2，直到最后一个元素，
 *          该元素为待出栈元素，直接将该元素出队返回
 *       queue2不为空，将queue2元素依次出队并插入queue1，直到最后一个元素，
 *          该元素为待出栈元素，直接将该元素出队返回
 * @author cn.lastwhisper
 */
public class Solution_9 {
    public static void main(String[] args) {
        // 两个栈实现队列
        //testQueue();
        // 两个队列实现栈
        testStack();
    }

    private static void testStack() {
        CStack<String> stack = new CStack<>();
        stack.push("a").push("b").push("c");
        System.out.print(stack.pop() + "\t");
        System.out.print(stack.pop() + "\t");
        stack.push("e");
        System.out.print(stack.pop() + "\t");
        System.out.print(stack.pop() + "\t");
        System.out.print(stack.pop() + "\t");
    }

    private static void testQueue() {
        CQueue<String> cQueue = new CQueue<>();
        cQueue.appendTail("a").appendTail("b")
                .appendTail("c").appendTail("d");
        System.out.print(cQueue.deleteHead() + "\t");
        System.out.print(cQueue.deleteHead() + "\t");
        cQueue.appendTail("e");
        System.out.print(cQueue.deleteHead() + "\t");
        System.out.print(cQueue.deleteHead() + "\t");
        System.out.print(cQueue.deleteHead() + "\t");
    }
}

//栈
class CStack<T> {
    private Queue<T> queue1 = new LinkedList<>();
    private Queue<T> queue2 = new LinkedList<>();

    //入栈
    public CStack<T> push(T t) {
        if (!queue1.isEmpty()) {
            queue1.add(t);
        } else if (!queue2.isEmpty()) {
            queue2.add(t);
        } else {
            queue1.add(t);
        }
        return this;
    }

    //出栈
    public T pop() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("栈空无元素");
        }
        if (!queue1.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        } else {
            while (queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }
    }
}

//队列
class CQueue<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    //入队
    public CQueue<T> appendTail(T t) {
        stack1.push(t);
        return this;
    }

    //出队
    public T deleteHead() {
        //stack2为空，将stack1元素push到stack2
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.size() == 0) {
            throw new RuntimeException("stack2为空");
        }
        return stack2.pop();
    }
}
