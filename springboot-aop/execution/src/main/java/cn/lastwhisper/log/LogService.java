package cn.lastwhisper.log;

import cn.lastwhisper.anno.AdminOnly;
import cn.lastwhisper.bean.Product;
import org.springframework.stereotype.Component;

/**
 * Created by cat on 2017-02-19.
 */
@Component
public class LogService implements Loggable {
    // @AdminOnly AnnoAspectConfig 注解匹配时使用
    @AdminOnly
    @Override
    public void log() {
        System.out.println("log for LogService");
    }

    // annoArg AnnoAspectConfig 注解匹配时使用
    public void annoArg(Product product){
        System.out.println("annoArg for LogService");
    }

}
