package cn.cunchang.spi;

/**
 * spi-interface: 是针对厂商定义的接口项目，只提供接口，不提供实现
 *
 * 服务接口 (Service Interface)，这是提供者实现的
 * Connection口就是其服务接口
 * @author lastwhisper
 */
public interface Driver {

    void getConnection();

}
