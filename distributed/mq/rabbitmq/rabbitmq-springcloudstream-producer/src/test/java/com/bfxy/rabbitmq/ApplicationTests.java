package com.bfxy.rabbitmq;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bfxy.rabbitmq.stream.RabbitmqSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private RabbitmqSender rabbitmqSender;
	
	
	@Test
	public void sendMessageTest1() {
       for(int i = 0; i < 1; i ++){
    	   try {
		       Map<String, Object> properties = new HashMap<String, Object>();
		       properties.put("SERIAL_NUMBER", "12345");
		       properties.put("BANK_NUMBER", "abc");
		       properties.put("PLAT_SEND_TIME", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
	    	   rabbitmqSender.sendMessage("Hello, I am amqp sender num :" + i, properties);
              
           } catch (Exception e) {
        	   System.out.println("--------error-------");
               e.printStackTrace(); 
           }
       }
       //TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
	}
	
}
