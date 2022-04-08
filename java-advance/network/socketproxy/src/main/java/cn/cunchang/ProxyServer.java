package cn.cunchang;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;

/**
 * @Auther: 
 * @Date: 2021-03-22 16:22
 * @Since
 * @Description:
 */
public class ProxyServer extends JFrame {
        private JTextArea jta =new JTextArea();//create an JTextArea to output some information

        public static void main(String[] args){
            new ProxyServer();
        }

        public ProxyServer() {
            setLayout(new BorderLayout());
            add(new JScrollPane(jta), BorderLayout.CENTER);

            setTitle("Proxy Server by Li Zhaoji");
            setSize(500, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            try{
                ServerSocket serverSocket = new ServerSocket(8081);//Listening port 8081
                jta.append("Proxy Server started at " + new Date() + '\n');
                jta.append("Proxy Server is listening on port "+serverSocket.getLocalPort() + '\n' + '\n');

                int clientNo = 1;

                while (true) {

                    Socket socket = serverSocket.accept();

                    jta.append("1****************************************************************************1" + '\n');
                    jta.append("Starting thread for client " + clientNo + " at " + new Date() + '\n');

                    InetAddress inetAddress = socket.getInetAddress();//get the host PC's information
                    jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
                    jta.append("Client " + clientNo + "'s  IP Address is " + inetAddress.getHostAddress() + '\n');

                    HandleClient task = new HandleClient(socket);

                    new Thread(task).start();//start a new thread to handle the require of the host

                    clientNo++;
                }
            }
            catch(IOException ex) {
                System.err.println(ex);
            }
        }

        class HandleClient implements Runnable {
            private Socket socket;

            public HandleClient(Socket socket){
                this.socket = socket;
            }

            @Override
            public void run() {
                try{
                    BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream outputToClient = new PrintStream(socket.getOutputStream());
                    String str = inputFromClient.readLine();//read the GET require string from Host
                    jta.append("The require from Client:" + str  + '\n');
                    String inurl = str.substring(4, str.indexOf("HTTP/")-1);//get the URL string from GET
                    jta.append("The require URL:" + inurl  + '\n');
                    URL url = new URL(inurl);
                    InputStream infile = url.openStream();//the input stream that get the data from Web Server
                    int n = infile.available();//the number of bytes that accept from Web Server
                    byte buf[] = new byte[1024];
                    while((n = infile.read(buf)) >= 0){
                        outputToClient.write(buf, 0, n);//start send with buf[0], send n bytes
                    }
                    outputToClient.close();
                    socket.close();
                    inputFromClient.close();

                    jta.append("socket has been closed!!!" + '\n');
                    jta.append("2****************************************************************************2" + '\n' + '\n');
                }
                catch(IOException e) {
                    System.err.println(e);
                    jta.append("Sorry! There is no require file??? :("  + '\n');
                }
            }
        }

}
