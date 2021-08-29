package cn.lastwhisper.componentregister.filter;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义ComponentScan过滤器
 * @author lastwhisper
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * 自定义包扫描过滤规则
     *
     * @param metadataReader 读取到当前正在扫描的类的信息
     * @param metadataReaderFactory 获取其他任何类型
     * @return boolean
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前正在扫描的类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取类名
        String className = classMetadata.getClassName();
        System.out.println(this.getClass().getSimpleName()+ " 扫描的类——》" + className);
        // 根据类名进行过滤
        if (className.contains("Book")) {
            return true;
        }
        // 证明filter在Conditional之前拦截
        //Annotation[] annotations = classMetadata.getClass().getAnnotations();
        //for (Annotation annotation : annotations) {
        //    if (annotation.annotationType() == Conditional.class) {
        //        return false;
        //    }
        //}
        //return true;
        return false;
    }

}
