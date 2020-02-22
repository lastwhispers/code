package cn.lastwhisper.componentregister.config;

import cn.lastwhisper.componentregister.bean.Person;
import cn.lastwhisper.componentregister.filter.MyTypeFilter;
import cn.lastwhisper.componentregister.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * 配置类相当于是配置文件
 * @author lastwhisper
 */
//@Configuration // 告诉Spring这是一个配置类
/*
 * 注解@ComponentScan对应<context:component-scan/>
 *
 *  excludeFilters = Filter[];指定在扫描的时候按照什么规则来排除脑哪些组件
 *  includeFilters = Filter[];指定在扫描的时候，只需要包含哪些组件
 * 想让包扫描的filter生效，必须声明useDefaultFilters = false
 */
@ComponentScans(value = {
        @ComponentScan(value = "cn.lastwhisper", includeFilters = {
                // 这里面是一个@Filter注解数组，FilterType.ANNOTATION表示的排除的规则 ：按照注解来进行匹配
                // classes = {Controller.class}表示的是标有这些注解的类给纳入到IOC容器中
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                // FilterType.ASSIGNABLE_TYPE 按照给定类型进行匹配
                //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                // FilterType.ASPECTJ 表达式
                // FilterType.REGEX 正则表达式
                // FilterType.CUSTOM 自定义过滤规则
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        }, useDefaultFilters = false),
        @ComponentScan(value = "cn.lastwhisper", excludeFilters = {
                // FilterType.ASSIGNABLE_TYPE 按照给定类型进行匹配
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
        }, useDefaultFilters = false)
})
public class MainConfig {
    /**
     * 在Spring的IOC容器中，默认名称为：person222，如果设置Bean的value值，则按value值
     * 给容器中注册一个Bean
     */
    @Bean("personName")
    public Person person222() {
        return new Person("lastwhisper", 21);
    }
}
