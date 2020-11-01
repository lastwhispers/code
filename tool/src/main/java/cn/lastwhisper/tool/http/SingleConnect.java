package cn.lastwhisper.tool.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SingleConnect {

    public static void main(String[] args) throws ClientProtocolException, IOException {

        //实例化一个HttpClient  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.isoyu.com/uploads/beibei/beibei_0334.jpg");

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        //设置Header的User-Agent
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        httpGet.setConfig(requestConfig);


        FileOutputStream output = null;
        InputStream input = null;

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                //取得请求内容
                HttpEntity entity = response.getEntity();
                //显示内容
                if (entity != null) {
                    //这里可以得到文件的类型 如image/jpg /zip /tiff 等等 但是发现并不是十分有效，有时明明后缀是.rar但是取到的是null，这点特别说明
                    System.out.println(entity.getContentType());
                    //可以判断是否是文件数据流
                    System.out.println(entity.isStreaming());
                    //设置本地保存的文件
                    File storeFile = new File("d:/beibei_0334.jpg");
                    if (!storeFile.exists()) {
                        storeFile.createNewFile();
                    }
                    output = new FileOutputStream(storeFile);
                    //得到网络资源并写入文件
                    input = entity.getContent();
                    byte[] b = new byte[1024];
                    int j;
                    while ((j = input.read(b)) != -1) {
                        output.write(b, 0, j);
                    }
                    output.flush();

                }

            }
        } finally {
            if(input!=null){
                input.close();
                output.close();
            }
        }

    }
}  