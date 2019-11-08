package cn.lastwhisper.order.controller;

import cn.lastwhisper.order.dto.OrderDTO;
import cn.lastwhisper.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * springcloud stream rabbitmq 消息发送方
 * @author lastwhisper
 * @date 2019/10/31
 */
//@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    //@GetMapping("/sendObject")
    //public void process2() {
    //    OrderDTO orderDTO = new OrderDTO();
    //    orderDTO.setBuyerOpenid("123456789");
    //    orderDTO.setBuyerAddress("北京昌平");
    //    streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    //}
}
