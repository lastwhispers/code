package cn.lastwhisper.componentregister.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 * @author lastwhisper
 */
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 使用MyImportSelector类的所有注解
     * @return java.lang.String[] 导入组件的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.lastwhisper.componentregister.bean.Blue", "cn.lastwhisper.componentregister.bean.Yellow"};
    }
}
