package cn.lastwhisper.profile.config;

import cn.lastwhisper.profile.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * *@Profile：根据当前环境动态激活和切换一系列组件
 *  以切换数据源为例：
 *  数据源：开发环境中(用的是A数据库)、测试环境(用的是B数据库)、而生产环境（用的又是C数据库）
 * *@Profile: 指定组件在哪一个环境的情况下才能被注册到容器中，不指定任何环境都能被注册这个组件
 *  1）加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中，默认是default环境，如果指定了
 *      default，那么这个bean默认会被注册到容器中
 *  2）@Profile 写在配置类上，只有是指定的环境，整个配置类里面的所有配置才能开始生效
 *  3）没有标注环境标识的bean，在任何环境都是加载的
 * @author lastwhisper
 */
@PropertySource("classpath:/db.properties")
@Configuration
public class MainConfigofProfile implements EmbeddedValueResolverAware {

    // 使用@Value()从properties文件取值
    @Value("${db.user}")
    private String user;
    // 使用StringValueResolver获取properties文件取值
    private String driverClass;

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        driverClass = resolver.resolveStringValue("${db.driverClass}");
    }
}
