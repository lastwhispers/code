package cn.lastwhisper.learn8.util.concurrent.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList
 * @author lastwhisper
 * @date 2020/4/16
 */
@Slf4j
public class CopyOnWriteArrayListTest {

    @Test
    public void testIterator() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        list.add("10");
        list.add("20");
        list.add("30");
        Iterator<String> iterator = list.iterator();
        list.add("50");
        iterator.next();
        list.add("50");
    }


}
