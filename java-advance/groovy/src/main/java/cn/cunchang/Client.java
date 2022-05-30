package cn.cunchang;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSONObject;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.Test;

/**
 * @author cunchang
 * @date 2022/5/30 11:38 AM
 */
public class Client {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        FileReader jsonFileReader = new FileReader("rsp.json");
        String jsonStr = jsonFileReader.readString();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        FileReader groovyFileReader = new FileReader("Demo.groovy");
        String groovyStr = groovyFileReader.readString();

        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(groovyStr);
        PaymentRequestOrderBO paymentRequestOrderBO = (PaymentRequestOrderBO) script.invokeMethod("extract", jsonObject);

        System.out.println(JSONObject.toJSONString(paymentRequestOrderBO));
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        String helloScript = "package cn.cunchang;" +  // 可以是纯Java代码
                "class Hello {" +
                "String say(String name) {" +
                "System.out.println(\"hello, \" + name);" +
                " return name;" +
                "}" +
                "}";
        Class helloClass = groovyClassLoader.parseClass(helloScript);
        GroovyObject object = (GroovyObject) helloClass.newInstance();
        Object ret = object.invokeMethod("say", "vivo");
        System.out.println(helloClass.getName());
    }
}
