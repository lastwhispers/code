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

    @Override
    @Compensable(confirmMethod = "confirmIsTrueSeats", cancelMethod = "cancelIsTrueSeats", transactionContextEditor = DubboTransactionContextEditor.class)
    public boolean isTrueSeats(String seats) {
        // 假设1,2,3座位不存在
        if ("1,2,3".equals(seats)) {
            throw new IllegalArgumentException();
        } else {
            return true;
        }
    }


    @Override
    @Compensable(confirmMethod = "confirmIsNotSold", cancelMethod = "cancelIsNotSold", transactionContextEditor = DubboTransactionContextEditor.class)
    public boolean isNotSold(String seats) {
        // 假设4,5已经售出
        if ("4,5".equals(seats)) {
            throw new IllegalArgumentException();
        } else {
            return true;
        }
    }

    // 幂等性
    @Override
    @Compensable(confirmMethod = "confirmSaveOrder", cancelMethod = "cancelSaveOrder", transactionContextEditor = DubboTransactionContextEditor.class)
    public String saveOrder(String field, String seats, String seatsNum) {
        System.out.println("创建一个待支付状态的订单");
        return "";
    }


    public String confirmSaveOrder(String field, String seats, String seatsNum) {
        System.out.println("将订单修改为支付中");
        return "";
    }
    public String cancelSaveOrder(String field, String seats, String seatsNum) {
        System.out.println("将订单修改为已关闭");
        return "";
    }

    public boolean confirmIsNotSold(String seats) {
        System.out.println("this is confirmIsNotSold");
        return true;
    }

    public boolean cancelIsNotSold(String seats) {
        System.out.println("this is cancelIsNotSold");
        return true;
    }

    public boolean confirmIsTrueSeats(String seats) {
        System.out.println("this is confirmIsTrueSeats");
        return true;
    }

    public boolean cancelIsTrueSeats(String seats) {
        System.out.println("this is cancelIsTrueSeats");
        return true;
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
