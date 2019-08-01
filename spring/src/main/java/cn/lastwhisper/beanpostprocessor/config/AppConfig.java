package cn.lastwhisper.beanpostprocessor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //JavaConfig方式，即当前配置类相当于一个applicationConotext.xml文件
@ComponentScan(value = "cn.lastwhisper.beanpostprocessor") //默认扫描当前配置类（AppConfig）所在包及其子包
public class AppConfig {

}
