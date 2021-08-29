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
     * 把所有需要添加到容器中的bean，通过BeanDefinitionRegistry来手动的进行注册
     *
     * @param importingClassMetadata 使用MyImportSelector类的所有注解（MainConfigRegister类的注解）
     * @param registry BeanDefinition注册类
     * @return void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean blueb = registry.containsBeanDefinition("cn.lastwhisper.componentregister.bean.Blue");
        boolean redb = registry.containsBeanDefinition("cn.lastwhisper.componentregister.bean.Red");
        // 模拟注册条件
        if (blueb && redb) {
            // bean定义信息
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册bean，并且指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }

    }

}
