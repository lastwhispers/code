package cn.lastwhisper.springbootmybatisplus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("cn.lastwhisper.springbootmybatisplus.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootmybatisplusApplication {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisplusApplication.class, args);
    }

}
