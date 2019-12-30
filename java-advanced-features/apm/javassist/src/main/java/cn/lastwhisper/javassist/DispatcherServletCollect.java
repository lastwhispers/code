package cn.lastwhisper.javassist;


import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class DispatcherServletCollect {

    public static void begin(Object[] params) {
        HttpServletRequest request = (HttpServletRequest) params[0];
        try {
            ServletInputStream is = request.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("APM 远程地址是：" + request.getRequestURI());
    }

    public static void end(Object[] params) {
        HttpServletResponse response = (HttpServletResponse) params[1];
        try {
            ServletOutputStream os = response.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
