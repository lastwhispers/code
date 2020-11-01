package cn.lastwhisper.tool.http;

import org.apache.http.HttpResponse;

/**
 * 
 * @author lastwhisper
 * @date 2020/4/7
 */
public interface HandlerTemplate {

    void handler(HttpResponse response);

}
