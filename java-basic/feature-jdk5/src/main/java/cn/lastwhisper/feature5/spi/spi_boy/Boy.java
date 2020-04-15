package cn.lastwhisper.feature5.spi.spi_boy;

import cn.lastwhisper.feature5.spi.spi_interface.People;

/**
 * spi-boy/spi-gril: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 * @author lastwhisper
 */
public class Boy implements People {
    @Override
    public void sayHello() {
        System.out.println("男孩说hello~~~");
    }
}
