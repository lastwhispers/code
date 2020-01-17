package cn.lastwhisper.leetcode.stackqueue.扁平化嵌套列表迭代器_341_中等;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator1 implements Iterator<Integer> {

    private LinkedList<Iterator<NestedInteger>> stack;
    private Integer num;
    private boolean flag;

    public NestedIterator1(List<NestedInteger> nestedList) {
        // 构造一个栈 栈顶元素存放的是当前活跃的列表
        stack = new LinkedList<>();
        stack.add(nestedList.iterator());
    }

    @Override
    public Integer next() {
        flag = false;
        return num;
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        // 取出栈顶活跃的迭代器
        while (!stack.isEmpty() && !flag) {
            // 取出栈顶元素
            Iterator<NestedInteger> iterator = stack.peekFirst();
            if (!iterator.hasNext()) {
                // 如果栈顶的迭代器已经是空了就出栈
                stack.pollFirst();
            } else {
                NestedInteger next = iterator.next();
                if (next == null) continue;
                if (next.isInteger()) {
                    num = next.getInteger();
                    flag = true;
                    iterator.remove();
                } else {
                    stack.offerFirst(next.getList().iterator());
                    iterator.remove();
                }
            }
        }

        return flag;
    }
}
