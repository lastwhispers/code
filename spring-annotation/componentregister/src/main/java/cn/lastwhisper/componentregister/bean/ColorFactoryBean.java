package cn.lastwhisper.componentregister.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean有啥用？
 *  与其他框架进行整合
 *
 *
 * @author lastwhisper
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回需要注册的对象
     */
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean.getObject");
        return new Color();
    }

    /**
     * 返回需要注册对象的class
     */
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单例
     *  true：单实例，在容器中保存一份
     *  false：多实例，每次获取都会创建一个新的bean
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
