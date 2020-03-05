package cn.lastwhisper.websocket.controller;

import cn.lastwhisper.websocket.handler.MyWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/broadcast")
    public void broadcast() {
        MyWebSocket.broadcast();
    }
}