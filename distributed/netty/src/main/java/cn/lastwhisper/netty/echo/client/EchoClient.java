package cn.lastwhisper.netty.echo.client;

import cn.lastwhisper.netty.common.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * echo客户端
 * @author lastwhisper
 * @date 2020/2/21
 */
public class EchoClient {

    public static void main(String[] args) throws Exception {
        new EchoClient().start(Constant.ip, Constant.port);
    }

    public void start(String host, int port) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //1、创建 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //2、指定 EventLoopGroup 以处理客户端事件；需要适用于 NIO 的实现
            bootstrap.group(group)
                    //3、适用于 NIO 传输的Channel 类型
                    .channel(NioSocketChannel.class)
                    //4、设置服务器的InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    //5、在创建Channel时，向 ChannelPipeline中添加一个 EchoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //6、连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = bootstrap.connect().sync();
            //7、阻塞，直到Channel 关闭
            f.channel().closeFuture().sync();
        } finally {
            //8、关闭线程池并且释放所有的资源
            group.shutdownGracefully().sync();
        }

    }


}
