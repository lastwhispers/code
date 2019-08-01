package cn.lastwhisper.componentregister.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * bean加载条件
 * @author lastwhisper
 */
public class LinuxCondition implements Condition {
    /**
     * 匹配linux操作系统
     *
     * @param context
     * @param metadata
     * @return boolean
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取运行时环境
        Environment environment = context.getEnvironment();
        // 获取操作系统
        String property = environment.getProperty("os.name");

        if (property.toLowerCase().contains("linux")) {
            return true;
        }
        return false;
    }
}
