package cn.lastwhisper.springboot.dubbo.consumer.quickstart;

import cn.lastwhisper.springboot.ServiceAPI;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
//@Component
public class QuickstartConsumer {
    //@Reference(url ="dubbo://localhost:20880")
    //@Reference(interfaceClass = ServiceAPI.class)
    ServiceAPI serviceAPI;

    public void sendMessage(String message) {
        System.out.println(serviceAPI.sendMessage(message));
    }
}
