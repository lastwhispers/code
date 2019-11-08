package cn.lastwhisper.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理器适配器 DispatcherServlet.getHandlerAdapter
 * 适配器模式
 * @author lastwhisper
 */
public class ControllerDemo3 implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/success3.jsp").forward(request, response);
    }

}
