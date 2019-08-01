package cn.lastwhisper.stack;

/**
 * @author lastwhisper
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        System.out.println("当前栈的容量："+arrayStack.size());
        arrayStack.push(6);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println("当前栈的容量："+arrayStack.size());
    }
}

class ArrayStack {
    private int[] arr;
    private int n = 0;

    public ArrayStack(int initialCapacity) {
        this.arr = new int[initialCapacity];
    }
    //
    // 栈满

    // 栈空
    public boolean isEmpty() {
        return n == 0;
    }

    // 入栈
    public void push(int data) {
        if (n > 0 && n == arr.length) {
            resize(2 * n);
        }
        arr[n++] = data;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        if (n > 0 && n == (arr.length / 4)) {
            resize(n / 2);
        }
        int data = arr[--n];
        return data;
    }
    // 查看栈顶元素
    public int peek(){
        int temp = n;
        return arr[--temp];
    }

    // size
    public int size() {
        return n;
    }

    // resize
    public void resize(int maxSize) {
        int[] newArr = new int[maxSize];
        for (int i = 0; i < size(); i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }
}