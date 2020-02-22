package cn.lastwhisper.activemq.ctrl;

import cn.lastwhisper.activemq.amq.producer.QueueSender;
import cn.lastwhisper.activemq.amq.producer.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jastar·Wang
 * @date 2018年4月4日
 * @since 1.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /** 队列名称 */
    public static final String QUEUE_NAME = "my.queue";

    /** 主题名称 */
    public static final String TOPIC_NAME = "my.topic";

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private TopicSender topicSender;

    /**
     * 发送消息到队列，一条消息只会被一个消费者接收，接收完后会从队列中删除
     *
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("sendToQueue")
    public String sendToQueue(String message) {
        String result = "";
        try {
            queueSender.send(QUEUE_NAME, message);
            result = "suc";
        } catch (Exception e) {
            result = e.getCause().toString();
        }
        return result;
    }

    /**
     * 发送消息到主题 ，所有订阅者都可以收到消息
     *
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("sendToTopic")
    public String sendToTopic(String message) {
        String result = "";
        try {
            topicSender.send(TOPIC_NAME, message);
            result = "suc";
        } catch (Exception e) {
            result = e.getCause().toString();
        }
        return result;
    }

}
