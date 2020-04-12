package cn.lastwhisper.spring.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lastwhisper
 * @date 2020/2/18
 */
@Configuration
@ComponentScan("cn.lastwhisper.spring.event")
public class EventConfig {

    @Bean
    public BlackListNotifier blackListNotifier() {
        BlackListNotifier blackListNotifier = new BlackListNotifier();
        blackListNotifier.setNotificationAddress("blacklist@example.org");
        return blackListNotifier;
    }

    @Bean
    public EmailService emailService() {
        EmailService emailService = new EmailService();
        emailService.setBlackList(blackList());
        return emailService;
    }

    @Bean
    public List<String> blackList() {
        List<String> blackList = new ArrayList<>();
        blackList.add("known.spammer@example.org");
        blackList.add("known.hacker@example.org");
        blackList.add("john.doe@example.org");
        return blackList;
    }

}
