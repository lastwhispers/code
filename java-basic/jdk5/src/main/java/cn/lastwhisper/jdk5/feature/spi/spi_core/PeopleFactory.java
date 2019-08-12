package cn.lastwhisper.jdk5.feature.spi.spi_core;

import cn.lastwhisper.jdk5.feature.spi.spi_interface.People;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * spi-core: 是提供给用户使用的核心jar文件,
 * 同样依赖于interface项目, 用户使用时需要引入spi-core.jar和厂商具体实现的jar
 * @author lastwhisper
 */
public class PeopleFactory {
    public void invoker(){
        ServiceLoader<People> services = ServiceLoader.load(People.class);
        Iterator<People> iterator = services.iterator();
        boolean notFound = true;
        while (iterator.hasNext()){
            notFound = false;
            People people = iterator.next();
            people.sayHello();
        }
    }
}
