package com.bfxy.rabbitmq.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 这里的Barista接口是定义来作为后面类的参数，这一接口定义来通道类型和通道名称。
 * 通道名称是作为配置用，通道类型则决定了app会使用这一通道进行发送消息还是从中接收消息。
 */
public interface Barista {
	  
    //String INPUT_CHANNEL = "input_channel";  
    String OUTPUT_CHANNEL = "output_channel";  

    //注解@Input声明了它是一个输入类型的通道，名字是Barista.INPUT_CHANNEL，也就是position3的input_channel。这一名字与上述配置app2的配置文件中position1应该一致，表明注入了一个名字叫做input_channel的通道，它的类型是input，订阅的主题是position2处声明的mydest这个主题  
//    @Input(Barista.INPUT_CHANNEL)  
//    SubscribableChannel loginput();  
    //注解@Output声明了它是一个输出类型的通道，名字是output_channel。这一名字与app1中通道名一致，表明注入了一个名字为output_channel的通道，类型是output，发布的主题名为mydest。  
    @Output(Barista.OUTPUT_CHANNEL)
    MessageChannel logoutput();  

//	String INPUT_BASE = "queue-1";  
//	String OUTPUT_BASE = "queue-1";  
//	@Input(Barista.INPUT_BASE)  
//	SubscribableChannel input1();  
//	MessageChannel output1();  
      
}  