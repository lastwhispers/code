package cn.lastwhisper.jdk5.feature.spi.spi_gril;

import cn.lastwhisper.jdk5.feature.spi.spi_interface.People;

/**
 * spi-boy/spi-gril: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 * @author lastwhisper
 */
public class Gril implements People {
    @Override
    public void sayHello() {
        System.out.println("女孩说hello~~~");
    }
}
