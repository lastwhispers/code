package cn.lastwhisper.learn8.util.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList
 *
 * @author lastwhisper
 * @date 2020/4/13
 */
@Slf4j
public class LinkedListTest {

    @Test
    public void testFastFail() {
        List<String> linkedList = new LinkedList<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        // java.util.ConcurrentModificationException
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            if (data.equals("a")) {
                linkedList.remove("a");
            }
            System.out.println(data);
        }
        System.out.println("size=" + linkedList.size());
    }

    @Test
    public void testItertor() {
        List<String> list = new LinkedList<>();
        list.add("s1");
        list.add("s3");
        list.add("s3");
        list.add(null);
        list.add("s4");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            log.info("从头到尾迭代顺序：" + s);
        }

        while (listIterator.hasPrevious()) {
            String s = listIterator.previous();
            if (s.equals("s5")) {
                listIterator.remove();
            }
            log.info("从尾到头迭代顺序：" + s);
        }
    }

}
