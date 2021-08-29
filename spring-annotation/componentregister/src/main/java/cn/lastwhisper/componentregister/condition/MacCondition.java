package cn.lastwhisper.componentregister.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * bean加载条件
 * @author lastwhisper
 */
public class MacCondition implements Condition {
    /**
     * 匹配mac操作系统
     *
     * @param context 上下文
     * @param metadata 注解信息
     * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取到的是app classLoader，跟this获取的一样啊，好奇怪，有什么用呢？
        ClassLoader appClassLoader = context.getClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();

        // ioc BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // BeanDefinitionRegistry增删改查Bean注册信息
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取运行时环境
        Environment environment = context.getEnvironment();
        // 获取操作系统
        String property = environment.getProperty("os.name");

        if (property.toLowerCase().contains("mac os x")) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name").toLowerCase());
    }

}
