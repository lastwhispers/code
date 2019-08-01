package cn.lastwhisper.componentregister.register;

import cn.lastwhisper.componentregister.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lastwhisper
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 把所有需要添加到容器中的bean通过BeanDefinitionRegistry里面的registerBeanDefinition方法来手动的进行注册
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类
     * @return void
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean blueb = registry.containsBeanDefinition("cn.lastwhisper.cn.lastwhisper.componentregister.bean.Blue");
        boolean redb = registry.containsBeanDefinition("cn.lastwhisper.cn.lastwhisper.componentregister.bean.Red");
        // 模拟注册条件
        if (blueb && redb) {
            // bean定义信息
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册bean，并且指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }

    }

}
