package cn.cunchang.spi;


/**
 * spi-boy/spi-gril: 分别是两个厂商对interface的不同实现，所以他们会依赖于interface项目
 * @author lastwhisper
 */
public class OracleDriver implements cn.cunchang.chapter2.article1.spi.Driver {

    static {
        System.out.println("oracle driver 注册成功");
    }

    @Override
    public void getConnection() {
        System.out.println("获取oracle connection");
    }
}
