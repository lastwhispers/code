package cn.lastwhisper.interview.IterableAndIterator;

import java.util.Iterator;

/**
 * @author lastwhisper
 */
public class IterableAndIterator {
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>(100);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        // foreach
        for (String s : stack) {
            System.out.printf("%s ", s);
        }
        // iterator
        //Iterator<String> iterator = stack.iterator();
        //while (iterator.hasNext()){
        //    String temp = iterator.next();
        //    System.out.printf("%s ",temp);
        //}
        String s;
        for (Iterator iterator = stack.iterator(); iterator.hasNext();
             System.out.printf("%s ", new Object[]{s}))
            s = (String) iterator.next();
    }
}
