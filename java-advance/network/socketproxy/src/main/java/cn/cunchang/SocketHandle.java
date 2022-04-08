package cn.cunchang;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: 
 * @Date: 2021-03-22 15:49
 * @Since
 * @Description:
 */
public class SocketHandle extends Thread{

    private Socket socket;

    public SocketHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream clientOutput = null;
        InputStream clientInput = null;
        Socket proxySocket = null;
        InputStream proxyInput = null;
        OutputStream proxyOutput = null;
        try {
            clientInput = socket.getInputStream();
            clientOutput = socket.getOutputStream();
            String line;
            String host = "";
            LineBuffer lineBuffer = new LineBuffer(1024);
            StringBuilder headStr = new StringBuilder();
            //读取HTTP请求头，并拿到HOST请求头和method
            while (null != (line = lineBuffer.readLine(clientInput))) {
                System.out.println(line);
                headStr.append(line + "\r\n");
                if (line.length() == 0) {
                    break;
                } else {
                    String[] temp = line.split(" ");
                    if (temp[0].contains("Host")) {
                        host = temp[1];
                    }
                }
            }
            String type = headStr.substring(0, headStr.indexOf(" "));
            //根据host头解析出目标服务器的host和port
            String[] hostTemp = host.split(":");
            host = hostTemp[0];
            int port = 80;
            if (hostTemp.length > 1) {
                port = Integer.valueOf(hostTemp[1]);
            }
            //连接到目标服务器
            proxySocket = new Socket(host, port);
            proxyInput = proxySocket.getInputStream();
            proxyOutput = proxySocket.getOutputStream();
            //根据HTTP method来判断是https还是http请求
            if ("CONNECT".equalsIgnoreCase(type)) {//https先建立隧道
                clientOutput.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                clientOutput.flush();
            } else {//http直接将请求头转发
                proxyOutput.write(headStr.toString().getBytes());
            }
            //新开线程转发客户端请求至目标服务器
            new ProxyHandleThread(clientInput, proxyOutput,null).start();
            //转发目标服务器响应至客户端
            while (true) {
                clientOutput.write(proxyInput.read());
                print(proxyInput);
                /*String url ="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E6%9E%97%E5%BF%97%E7%8E%B2&step_word=&hs=2&pn=8&spn=0&di=7150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2732989282%2C3677770873&os=2359571368%2C569377150&simid=55908706%2C799425763&adpicid=0&lpn=0&ln=2666&fr=&fmq=1616661848576_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=star&bdtype=0&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2Fc3%2F28%2F51%2Fc328516d6a64807408f0180f8e7e9032.jpg%26refer%3Dhttp%3A%2F%2Fup.enterdesk.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619253875%26t%3D1dae7d851056b4b20dde8c9749192719&fromurl=ippr_z2C%24qAzdH3FAzdH3Fp7_z%26e3Bjgpj61jfh_z%26e3Bv54AzdH3F15ogs5w1AzdH3F8888cAzdH3F&gsm=9&rpstart=0&rpnum=0&islist=&querylist=&force=undefined";
                clientOutput.write(url.getBytes());*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (proxyInput != null) {
                try {
                    proxyOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (proxyOutput != null) {
                try {
                    proxyOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (proxySocket != null) {
                try {
                    proxySocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientInput != null) {
                try {
                    clientInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientOutput != null) {
                try {
                    clientOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void print (InputStream is) throws UnsupportedEncodingException {
        InputStreamReader isr =new InputStreamReader(is,"utf-8");
        BufferedReader br =new BufferedReader(isr);
        try {
            while ((br.read())!=-1) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
