package cn.lastwhisper.tool.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 单线程http连接池
 *
 * @author lastwhisper
 * @date 2020/4/7
 */
public class DownLoad {

    // 图片名称
    private static Long counter = 0L;
    // 失败次数
    private static Long failFreq = 0L;
    // 保存盘符
    private static String drive = "D:\\beibei\\";

    private static String prefix = "https://api.isoyu.com/uploads/beibei/";

    private static String infix = "beibei_";

    private static String suffix = ".jpg";

    public static void main(String[] args) {

        while (failFreq < 1000) {
            String num = getNum();

            String url = prefix + infix + num + suffix;

            String fileName = drive + infix + num + suffix;

            HttpPool.httpGet(url, response -> {

                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    // 200
                    failFreq = 0L;

                    FileOutputStream output = null;
                    InputStream input = null;
                    try {
                        //取得请求内容
                        HttpEntity entity = response.getEntity();
                        //显示内容
                        if (entity != null) {
                            //设置本地保存的文件
                            File storeFile = new File(fileName);
                            if (!storeFile.exists()) {
                                storeFile.createNewFile();

                                output = new FileOutputStream(storeFile);
                                //得到网络资源并写入文件
                                input = entity.getContent();
                                byte[] b = new byte[1024];
                                int j;
                                while ((j = input.read(b)) != -1) {
                                    output.write(b, 0, j);
                                }
                                output.flush();
                                System.out.println("request success, file name:" + fileName);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (input != null) {
                            try {
                                input.close();
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    failFreq++;
                    System.err.println("url:" + url + " failure,fail freq:" + failFreq);
                }

            });

        }


    }

    private static String getNum() {
        String result;
        counter++;
        if (counter < 10) {
            result = "000" + counter;
        } else if (counter < 100) {
            result = "00" + counter;
        } else if (counter < 1000) {
            result = "0" + counter;
        } else {
            result = Long.toString(counter);
        }

        return result;
    }

}
