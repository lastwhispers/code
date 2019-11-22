package cn.lastwhisper.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lastwhisper
 * @date 2019/11/19
 */
public class Test {
    public static void main(String[] args) {
        // 1.上下文
        Context context = new Context();
        //创建数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>(1);
        dataModel.put("name", "青橙电商系统");
        context.setVariables(dataModel);
        // 2.准备文件
        File dest = new File("d:/test_out.html");
        // 3.生成页面
        try {
            PrintWriter writer = new PrintWriter(dest, "UTF-8");
            //模板解析器
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            //模板模型
            resolver.setTemplateMode(TemplateMode.HTML);
            //后缀
            resolver.setSuffix(".html");
            //创建模板引擎
            TemplateEngine engine = new TemplateEngine();
            //设置模板解析器
            engine.setTemplateResolver(resolver);
            //执行模板引擎
            engine.process("test", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
