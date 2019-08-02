package cn.lastwhisper.javabasic.IterableAndIterator;

import java.util.Iterator;

/**
 * Resizing array stack
 * @author lastwhisper
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a;// stack entries
    private int N;// size

    // Create a empty stack of size cap.
    public ResizingArrayStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    // Add a item.
    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    // Delete recently added item.
    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // Is the stack empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // The numebr of items int the stack
    public int size() {
        return N;
    }

    private void resize(int max) {
        // Move the stack of size N<=max to a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }

}
