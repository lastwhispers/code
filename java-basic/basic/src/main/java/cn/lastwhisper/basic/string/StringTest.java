package cn.lastwhisper.basic.string;

import org.junit.Test;

public class StringTest {

    @Test
    public void testStringMatch() {
        System.out.println("niubi.pdf".matches(".*pdf"));
        System.out.println("niubi pdf".matches(".*pdf"));
        System.out.println(".pdf".matches(".*pdf"));
        System.out.println("niubi.pdf".matches("niubi.pdf"));
    }

}
