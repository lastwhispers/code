package cn.lastwhisper.bcrypt;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 
 * @author lastwhisper
 * @date 2019/11/19
 */
public class BcryptTest {

    @Test
    public void test(){
        // Create an encoder with strength 16
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
        Assert.assertTrue(encoder.matches("123456", result));
    }

}
