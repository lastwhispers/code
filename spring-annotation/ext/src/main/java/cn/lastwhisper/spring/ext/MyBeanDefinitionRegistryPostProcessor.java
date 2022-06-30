package cn.lastwhisper.spring.ext;

import cn.lastwhisper.spring.ext.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;


@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("MyBeanDefinitionRegistryPostProcessor...bean的数量：" + beanFactory.getBeanDefinitionCount());
    }

    /**
     * BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        System.out.println("postProcessBeanDefinitionRegistry...bean的数量：" + registry.getBeanDefinitionCount());
        //RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Blue.class);

        //设置属性值
        builder.addPropertyValue("name","i am blue");
        //设置可通过@Autowire注解引用
        builder.setAutowireMode(AUTOWIRE_BY_NAME);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

//        registry.registerBeanDefinition("hello", beanDefinition);

        // 看springcloud openfeign源码，feign接口怎么在容器中声明的
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, Blue.class.getName(),
                new String[] { "hello" });
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

}