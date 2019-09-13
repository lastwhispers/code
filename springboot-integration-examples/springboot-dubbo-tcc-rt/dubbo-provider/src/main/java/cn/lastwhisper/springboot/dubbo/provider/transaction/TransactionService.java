package cn.lastwhisper.springboot.dubbo.provider.transaction;

import cn.lastwhisper.springboot.ServiceAPI;
import com.alibaba.dubbo.config.annotation.Service;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
@Component
@Service(interfaceClass = ServiceAPI.class)
public class TransactionService implements ServiceAPI {

    @Compensable(confirmMethod = "confirmSendMessage", cancelMethod = "cancelSendMessage", transactionContextEditor = DubboTransactionContextEditor.class)
    @Override
    public String sendMessage(String message) {
        System.out.println("this is provider sendMessage=" + message);
        if ("123".equals(message)) {
            throw new NullPointerException();
        }
        return "tcc-provider-message=" + message;
    }

    public String confirmSendMessage(String message) {
        System.out.println("this is provider confirmSendMessage=" + message);
        return "tcc-provider-message=" + message;
    }

    public String cancelSendMessage(String message) {
        System.out.println("this is provider cancelSendMessage=" + message);
        return "tcc-provider-message=" + message;
    }
}
