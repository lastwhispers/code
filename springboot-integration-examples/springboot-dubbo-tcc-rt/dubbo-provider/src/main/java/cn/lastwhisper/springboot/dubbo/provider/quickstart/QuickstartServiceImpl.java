package cn.lastwhisper.springboot.dubbo.provider.quickstart;

import cn.lastwhisper.springboot.ServiceAPI;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
//@Component
//@Service(interfaceClass = ServiceAPI.class)
public class QuickstartServiceImpl implements ServiceAPI {

    @Override
    public String sendMessage(String message) {
        return "quickstart-provider-message=" + message;
    }
}
