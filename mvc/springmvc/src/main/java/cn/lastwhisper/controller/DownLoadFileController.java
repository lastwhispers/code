package cn.lastwhisper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件下载测试
 * @author lastwhisper
 * @date 2019/12/20
 */
@Controller
public class DownLoadFileController {

    @RequestMapping("/download")
    @ResponseBody
    public String download(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;filename=stock.txt");
        String content = "Hello,Txt World";
        //ServletOutputStream outputStream = response.getOutputStream();
        try {
            OutputStream os = response.getOutputStream();
            os.write(content.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"id\":\"1\",\"username\":\"tomcat\",\"age\":18}";
        //return null;
    }

}
