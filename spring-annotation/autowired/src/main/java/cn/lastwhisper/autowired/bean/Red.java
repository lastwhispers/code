package cn.lastwhisper.autowired.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author lastwhisper
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext：" + applicationContext);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("name：" + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String stringValue = resolver.resolveStringValue("操作系统：${os.name},#{2019710+1}");
        System.out.println(stringValue);
    }
}
