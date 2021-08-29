package cn.lastwhisper.componentregister.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * bean加载条件
 * @author lastwhisper
 */
public class WindowsCondition implements Condition {
    /**
     * 匹配windows操作系统
     *
     * @param context 上下文
     * @param metadata 注解信息
     * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取运行时环境
        Environment environment = context.getEnvironment();
        // 获取操作系统
        String property = environment.getProperty("os.name");

        // 也可以根据BeanDefinitionRegistry进行匹配
        BeanDefinitionRegistry registry = context.getRegistry();
        if (property.toLowerCase().contains("win")) {
            return true;
        }
        return false;
    }


}
