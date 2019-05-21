package cn.lastwhisper.springbootaop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.lastwhisper.springbootaop.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootaopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootaopApplication.class, args);
    }

}
