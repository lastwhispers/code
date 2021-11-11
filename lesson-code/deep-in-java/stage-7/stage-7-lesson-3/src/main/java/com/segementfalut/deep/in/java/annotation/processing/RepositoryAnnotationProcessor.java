package com.segementfalut.deep.in.java.annotation.processing;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.sql.Wrapper;
import java.util.*;

/**
 * 1. 扩展 javax.annotation.processing.AbstractProcessor 抽象类
 * 2. 指定需要处理的注解类名（集合）
 * 3. 指定支持的 Java 源代码版本
 * 4. 指定支持的 Processorcom.segementfalut.deep.in.java.reflection.Repository 参数 Options（可选）
 * 5. 利用 Java SPI 配置 javax.annotation.processing.Processor 的实现类
 */
@SupportedAnnotationTypes(RepositoryAnnotationProcessor.REPOSITORY_ANNOTATION_CLASS_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RepositoryAnnotationProcessor extends AbstractProcessor {

    public static final String REPOSITORY_ANNOTATION_CLASS_NAME = "com.segementfalut.deep.in.java.reflection.Repository";

    private static final String CRUD_REPOSITORY_INTERFACE_CLASS_NAME = "com.segementfalut.deep.in.java.reflection.CrudRepository";

    /**
     * Key 为 CrudRepository 接口实现类，Value 为 CrudRepository 接口首个参数类型
     */
    private Map<String, String> crudRepositoryParameterizedTypesMapping = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 输出处理的注解类名称

        // 第一个阶段：处理阶段
        // 获取所有的编译类
        roundEnv.getRootElements()
                .stream()
                .filter(this::isRepositoryAnnotationPresent) // 过滤标注 @com.segementfalut.deep.in.java.reflection.Repository 的元素
                .forEach(this::processRepositoryAnnotatedElement);     // 处理标注 @com.segementfalut.deep.in.java.reflection.Repository 的元素


        // 第二个阶段：完成阶段
        if (roundEnv.processingOver()) {
            // 将 crudRepositoryParameterizedTypesMapping 输出到新生成的文件
            try {
                generateCrudRepositoryParameterizedTypesMetadata();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    private void generateCrudRepositoryParameterizedTypesMetadata() throws IOException {
        // 找到 ClassPath
        Filer filer = processingEnv.getFiler();
        String resourceName = "META-INF/crud-repos-mappings.properties";
        FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", resourceName);

        try (Writer writer = fileObject.openWriter()) {
            Properties properties = new Properties();
            properties.putAll(crudRepositoryParameterizedTypesMapping);
            properties.store(writer, "Generated by RepositoryAnnotationProcessor");
        }
    }


    private void processRepositoryAnnotatedElement(Element element) {

        if (!isConcreteClass(element) || !isCrudRepositoryType(element)) {
            return;
        }

        System.out.println("CrudRepository 实现类为 ：" + element.toString());

        //  CrudRepository 接口类型
        TypeMirror crudRepositoryGenericInterfaceType = getGenericInterfaceType(element, CRUD_REPOSITORY_INTERFACE_CLASS_NAME);

        System.out.println("CrudRepository 实现泛型接口定义为 ：" + crudRepositoryGenericInterfaceType.toString());

        // 由于 CrudRepository 是接口类型，可以强制转化为 DeclaredType
        DeclaredType declaredType = DeclaredType.class.cast(crudRepositoryGenericInterfaceType);
        // 获取泛型参数类型列表
        List<? extends TypeMirror> parameterizedTypes = declaredType.getTypeArguments();
        // 获取第一个参数类型
        TypeMirror firstArgumentType = parameterizedTypes.get(0);
        // 必然等于 User 对象
        System.out.println("CrudRepository 实现接口的首个泛型参数为 ：" + firstArgumentType.toString());
        //  Key 为 CrudRepository 接口实现类，Value 为 CrudRepository 接口首个参数类型
        crudRepositoryParameterizedTypesMapping.put(crudRepositoryGenericInterfaceType.toString(), firstArgumentType.toString());
    }

    private boolean isConcreteClass(Element element) {
        return !element.getModifiers().contains(Modifier.ABSTRACT);
    }

    private boolean isCrudRepositoryType(Element element) {
        return getGenericInterfaceType(element, CRUD_REPOSITORY_INTERFACE_CLASS_NAME) != null;
    }

    private TypeMirror getGenericInterfaceType(Element element, String interfaceTypeName) {
        ElementKind kind = element.getKind();
        if (kind.isClass() && element instanceof TypeElement) {
            TypeElement typeElement = (TypeElement) element;
            return typeElement.getInterfaces().stream() // 发现当前参数类型的所有接口
                    .filter(interfaceType -> typeEquals(interfaceType, interfaceTypeName))
                    .findFirst() // 找到第一个符合条件的 CrudRepository 接口
                    .orElse(null);
        }
        return null;
    }

    private boolean typeEquals(TypeMirror type, String typeName) {
        Types types = processingEnv.getTypeUtils();
        TypeMirror erasedType = types.erasure(type); // 擦写泛型参数
        return Objects.equals(typeName, erasedType.toString());
    }

    private boolean isRepositoryAnnotationPresent(Element element) {
        return isAnnotationPresent(element, REPOSITORY_ANNOTATION_CLASS_NAME);
    }

    private boolean isAnnotationPresent(Element element, String annotationClassName) {
        return element.getAnnotationMirrors() // 返回当前元素的所有注解集合
                .stream()
                .filter(annotation -> Objects.equals(annotationClassName, annotation.getAnnotationType().toString()))
                .count() > 0;
    }

//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latest();
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return singleton("com.segementfalut.deep.in.java.reflection.Repository");
//    }
}
