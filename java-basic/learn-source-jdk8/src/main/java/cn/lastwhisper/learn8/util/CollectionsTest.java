package cn.lastwhisper.learn8.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.list.UnmodifiableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Collections
 * @author lastwhisper
 * @date 2020/4/12
 */
@Slf4j
public class CollectionsTest {

    @Test
    public void unmodifiable(){
        List<Integer> list =  new ArrayList<>();
        List unmodifiableList = UnmodifiableList.decorate(list);

    }

}
