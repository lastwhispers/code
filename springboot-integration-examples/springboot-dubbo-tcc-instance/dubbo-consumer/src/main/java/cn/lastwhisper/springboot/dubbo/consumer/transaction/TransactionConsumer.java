package cn.lastwhisper.springboot.dubbo.consumer.transaction;

import cn.lastwhisper.springboot.ServiceAPI;
import com.alibaba.dubbo.config.annotation.Reference;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
@Component("transactionConsumer")
public class TransactionConsumer {
    //@Reference(interfaceClass = ServiceAPI.class)
    @Autowired
    ServiceAPI serviceAPI;

    // 主事务
    @Compensable(confirmMethod = "confirmSendMessage", cancelMethod = "cancelSendMessage", asyncConfirm = true)
    public void sendMessage(String message) {
        //System.out.println("this is consumer sendMessage="+message);
        //System.out.println(serviceAPI.sendMessage(message));

        // 业务
        serviceAPI.saveOrder("001", message, "5");

        serviceAPI.isTrueSeats(message);
        serviceAPI.isNotSold(message);

    }

    public void confirmSendMessage(String message) {
        System.out.println("this is consumer confirmSendMessage=" + message);
    }

    public void cancelSendMessage(String message) {
        System.out.println("this is consumer cancelSendMessage=" + message);
    }
}
