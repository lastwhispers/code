package cn.lastwhisper.springboot;


import org.mengyun.tcctransaction.api.Compensable;

public interface ServiceAPI {
    @Compensable
    String sendMessage(String message);
}
