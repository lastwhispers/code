package cn.lastwhisper.rabbitmq.adapter;


import cn.lastwhisper.rabbitmq.entity.Order;
import cn.lastwhisper.rabbitmq.entity.Packaged;

import java.io.File;
import java.util.Map;


public class MessageDelegate {

    public void handleMessage(byte[] messageBody) {
         System.err.println("默认方法, 消息内容:" + new String(messageBody));
    }

    public void consumeMessage(byte[] messageBody) {
        System.err.println("字节数组方法, 消息内容:" + new String(messageBody));
    }


    public void consumeMessage(String messageBody) {
        System.err.println("字符串方法, 消息内容:" + messageBody);
    }


    public void method1(String messageBody) {
        System.err.println("method1 收到消息内容:" + new String(messageBody));
    }

    public void method2(String messageBody) {
        System.err.println("method2 收到消息内容:" + new String(messageBody));
    }

	//
	//
    //public void consumeMessage(Map messageBody) {
    //    System.err.println("map方法, 消息内容:" + messageBody);
    //}
	//
	//
    //public void consumeMessage(Order order) {
    //    System.err.println("order对象, 消息内容, id: " + order.getId() +
    //            ", name: " + order.getName() +
    //            ", content: " + order.getContent());
    //}
	//
    //public void consumeMessage(Packaged pack) {
    //    System.err.println("package对象, 消息内容, id: " + pack.getId() +
    //            ", name: " + pack.getName() +
    //            ", content: " + pack.getDescription());
    //}
	//
    //public void consumeMessage(File file) {
    //    System.err.println("文件对象 方法, 消息内容:" + file.getName());
    //}
}
